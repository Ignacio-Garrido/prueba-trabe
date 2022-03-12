package com.prueba.trabe.model.repository;

import javax.persistence.TypedQuery;

import org.springframework.dao.support.DataAccessUtils;
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
	
	@Override
	public Account findByNumber(String number) {
		TypedQuery<Account> query = entityManager.createQuery("from Account a where a.number = :number", Account.class).setParameter("number", number);
		return DataAccessUtils.singleResult(query.getResultList());
	}
}
