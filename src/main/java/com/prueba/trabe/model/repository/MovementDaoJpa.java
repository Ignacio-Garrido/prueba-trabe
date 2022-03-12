package com.prueba.trabe.model.repository;

import org.springframework.stereotype.Repository;

import com.prueba.trabe.model.domain.Movement;
import com.prueba.trabe.model.repository.util.GenericDaoJpa;

@Repository
public class MovementDaoJpa extends GenericDaoJpa implements MovementDao {

	@Override
	public void create(Movement movement) {
		entityManager.persist(movement);
	}

	@Override
	public void update(Movement movement) {
		entityManager.merge(movement);
	}

	@Override
	public void delete(Movement movement) {
		entityManager.remove(movement);
	}
}
