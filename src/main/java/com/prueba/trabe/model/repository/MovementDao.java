package com.prueba.trabe.model.repository;

import com.prueba.trabe.model.domain.Movement;

public interface MovementDao {

	void create(Movement movement);

	void update(Movement movement);

	void delete(Movement movement);
}
