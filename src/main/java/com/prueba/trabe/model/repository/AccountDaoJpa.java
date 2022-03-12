package com.prueba.trabe.model.repository;

import org.springframework.stereotype.Repository;

import com.prueba.trabe.model.domain.Account;
import com.prueba.trabe.model.repository.util.GenericDaoJpa;

@Repository
public class AccountDaoJpa extends GenericDaoJpa implements AccountDao {

	@Override
	public void create(Account account) {
		entityManager.persist(account);
	}

	@Override
	public void update(Account account) {
		entityManager.merge(account);
	}

	@Override
	public void delete(Account account) {
		entityManager.remove(account);
	}
}
