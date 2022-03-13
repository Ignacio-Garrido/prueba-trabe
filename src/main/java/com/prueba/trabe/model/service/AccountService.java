package com.prueba.trabe.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.prueba.trabe.model.service.dto.AccountDTO;
import com.prueba.trabe.model.service.dto.MovementDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AccountService {

	@Autowired
	private AccountDao accountDAO;
	
	@Autowired
	private MovementDao movementDAO;
	
	public AccountDTO getAccount (String number) throws NotFoundException {
		Account account = accountDAO.findByNumber(number);
		if (account == null) {
			throw new NotFoundException(number, Account.class);
		}
		return new AccountDTO(account);
	}
	
	public List<MovementDTO> getMovements (String number) throws NotFoundException {
		Account account = accountDAO.findByNumber(number);
		if (account == null) {
			throw new NotFoundException(number, Account.class);
		} else {
			return account.getMovements().stream().map(movement -> new MovementDTO(movement)).collect(Collectors.toList());
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public MovementDTO deposit (String number, int amount) throws NotFoundException {
		Account account = accountDAO.findByNumber(number);
		if (account == null) {
			throw new NotFoundException(number, Account.class);
		} else {
			List<Movement> movsList = new ArrayList<>();
			account.deposit(amount);
			Movement movement = new Movement();
			movement.setDate(LocalDate.now());
			movement.setAmount(amount);
			movement.setType(Type.Deposit);
			movement.setBalance(account.getBalance());
			movementDAO.create(movement);
			if(account.getMovements() != null) {
				movsList.addAll(account.getMovements());
			}
			movsList.add(movement);
			account.setMovements(movsList);
			accountDAO.update(account);
			return new MovementDTO(movement);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public MovementDTO withdraw (String number, int amount) throws NotFoundException, InsufficientBalanceException {
		Account account = accountDAO.findByNumber(number);
		if (account == null) {
			throw new NotFoundException(number, Account.class);
		} else if (account.getBalance() < amount) {
			throw new InsufficientBalanceException(number, amount);
		} else {
			List<Movement> movsList = new ArrayList<>();
			account.withdraw(amount);
			Movement movement = new Movement();
			movement.setDate(LocalDate.now());
			movement.setAmount(amount);
			movement.setType(Type.Withdrawal);
			movement.setBalance(account.getBalance());
			movementDAO.create(movement);
			if(account.getMovements() != null) {
				movsList.addAll(account.getMovements());
			}
			movsList.add(movement);
			account.setMovements(movsList);
			accountDAO.update(account);
			return new MovementDTO(movement);
		}
	}
}

