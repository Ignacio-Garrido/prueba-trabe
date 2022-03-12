package com.prueba.trabe.model.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Movement")
public class Movement {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movement_generator")
	@SequenceGenerator(name = "movement_generator", sequenceName = "movement_seq")
	private Long id_mov;
	
	private LocalDate date;

	@Enumerated(EnumType.STRING)
	private Type type;
	
	private int amount;
	
	private int balance;

	public Movement() {
	}

	public Movement(LocalDate date, Type type, int amount, int balance) {
		super();
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.balance = balance;
	}

	public Long getId() {
		return id_mov;
	}

	public LocalDate getDate() {
		return date;
	}

	public Type getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public int getBalance() {
		return balance;
	}

	public void setId(Long id_mov) {
		this.id_mov = id_mov;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
