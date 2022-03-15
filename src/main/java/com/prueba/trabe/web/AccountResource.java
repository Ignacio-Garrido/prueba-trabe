package com.prueba.trabe.web;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.trabe.model.exception.InsufficientBalanceException;
import com.prueba.trabe.model.exception.NotFoundException;
import com.prueba.trabe.model.service.AccountService;
import com.prueba.trabe.model.service.dto.AccountDTO;
import com.prueba.trabe.model.service.dto.MovementDTO;
import com.prueba.trabe.web.exceptions.RequestBodyNotValidException;
import com.prueba.trabe.web.exceptions.UnhandledVersionException;

@RestController
@RequestMapping("/api/account")
public class AccountResource {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/{number}")
	public AccountDTO getAccount(@RequestHeader("version") String version, @PathVariable String number) throws NotFoundException, UnhandledVersionException {
		if (version.equals("1.0")) {
			return accountService.getAccount(number);
		} else if (version.equals("2.0")) {
			AccountDTO account = accountService.getAccount(number);
			account.setBalance(account.getBalance() * 2);
			return account;
		} else {
			throw new UnhandledVersionException();
		}
	}
	
	@GetMapping("/{number}/movements")
	public List<MovementDTO> getMovements(@PathVariable String number) throws NotFoundException {
	  return accountService.getMovements(number);
	}
	
	@PostMapping("/{number}/deposit")
	public MovementDTO deposit(@PathVariable String number, @RequestBody @Valid String amount, Errors errors) throws RequestBodyNotValidException, NotFoundException {
		  if (errors.hasErrors()) {
		    throw new RequestBodyNotValidException(errors);
		  }
		  return accountService.deposit(number, Integer.parseInt(amount));
	}
	
	@PostMapping("/{number}/withdraw")
	public MovementDTO withdraw(@PathVariable String number, @RequestBody @Valid String amount, Errors errors) throws RequestBodyNotValidException, NotFoundException, InsufficientBalanceException {
		  if (errors.hasErrors()) {
		    throw new RequestBodyNotValidException(errors);
		  }
		  return accountService.withdraw(number, Integer.parseInt(amount));
	}
}
