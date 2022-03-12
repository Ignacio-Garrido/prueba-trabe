package com.prueba.trabe.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.trabe.model.domain.Account;
import com.prueba.trabe.model.domain.Movement;
import com.prueba.trabe.model.domain.Type;
import com.prueba.trabe.model.exception.InsufficientBalanceException;
import com.prueba.trabe.model.exception.NotFoundException;
import com.prueba.trabe.model.repository.AccountDao;
import com.prueba.trabe.model.repository.MovementDao;
import com.prueba.trabe.model.service.util.AccountDTO;
import com.prueba.trabe.model.service.util.MovementDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AccountService {

	@Autowired
	private AccountDao accountDAO;
	
	@Autowired
	private MovementDao movementDAO;
	
	public AccountDTO getAccount (String number) {
		Account account = accountDAO.findByNumber(number);
		if (account == null) {
			throw new NotFoundException(number, Account.class);
		}
		return new AccountDTO(account);
	}
	
	public List<MovementDTO> getMovements (String number) {
		Account account = accountDAO.findByNumber(number);
		if (account == null) {
			throw new NotFoundException(number, Account.class);
		} else {
			return movementDAO.findAllByNumber().stream().map(movement -> new MovementDTO(movement)).collect(Collectors.toList());
		}
	}
	
	public MovementDTO deposit (String number, int amount) {
		Account account = accountDAO.findByNumber(number);
		if (account == null) {
			throw new NotFoundException(number, Account.class);
		} else {
			account.deposit(amount);
			Movement movement = new Movement();
			movement.setDate(LocalDate.now());
			movement.setAmount(amount);
			movement.setType(Type.Deposit);
			movement.setBalance(account.getBalance());
			movementDAO.create(movement);
			account.getMovements().add(movement);
			accountDAO.update(account);
		}
	}
	
	public MovementDTO withdraw (String number, int amount) {
		Account account = accountDAO.findByNumber(number);
		if (account == null) {
			throw new NotFoundException(number, Account.class);
		} else if (account.getBalance() < amount) {
			throw new InsufficientBalanceException(number, amount);
		} else {
			account.withdraw(amount);
			Movement movement = new Movement();
			movement.setDate(LocalDate.now());
			movement.setAmount(amount);
			movement.setType(Type.Withdrawal);
			movement.setBalance(account.getBalance());
			movementDAO.create(movement);
			account.getMovements().add(movement);
			accountDAO.update(account);
		}
	}
}

