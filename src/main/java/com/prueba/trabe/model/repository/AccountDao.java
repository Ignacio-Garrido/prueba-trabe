package com.prueba.trabe.model.repository;

import com.prueba.trabe.model.domain.Account;

public interface AccountDao {

	void create(Account account);

	void update(Account account);

	void delete(Account account);
}
