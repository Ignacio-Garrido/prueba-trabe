package com.prueba.trabe.model.service.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.prueba.trabe.model.domain.Movement;
import com.sun.istack.NotNull;

public class MovementDTO {
	@NotEmpty
	private LocalDate date;
	@NotEmpty
	private String type;
	@NotNull
	private int amount;
	@NotNull
	private int balance;
	
	public MovementDTO() {
	}
	
	public MovementDTO(Movement movement) {
		this.date = movement.getDate();
		this.type = movement.getType().toString();
		this.amount = movement.getAmount();
		this.balance = movement.getBalance();
	}

	public LocalDate getDate() {
		return date;
	}

	public String getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public int getBalance() {
		return balance;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
