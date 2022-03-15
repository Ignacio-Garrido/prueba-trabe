package com.prueba.trabe.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.trabe.model.domain.Account;
import com.prueba.trabe.model.exception.InsufficientBalanceException;
import com.prueba.trabe.model.exception.NotFoundException;
import com.prueba.trabe.model.repository.AccountDao;
import com.prueba.trabe.model.service.AccountService;

@Configuration
public class DatabaseLoader {
	private final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

	@Autowired
	private AccountDao accountDAO;

	@Autowired
	private AccountService accountService;

	@Autowired
	private DatabaseLoader databaseLoader;

	@PostConstruct
	public void init() {
		try {
			databaseLoader.loadData();
		} catch (NotFoundException | InsufficientBalanceException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void loadData() throws NotFoundException, InsufficientBalanceException {
		// New accounts
		Account a1 = new Account("A00001"); // Account 1
		Account a2 = new Account("A00002"); // Account 2
		Account a3 = new Account("A00003"); // Account 3

		accountDAO.create(a1);
		accountDAO.create(a2);
		accountDAO.create(a3);

		accountService.deposit(a1.getNumber(), 1000);
		accountService.deposit(a2.getNumber(), 500);
		accountService.deposit(a3.getNumber(), 2500);

		accountService.withdraw(a1.getNumber(), 30);

	}
}
