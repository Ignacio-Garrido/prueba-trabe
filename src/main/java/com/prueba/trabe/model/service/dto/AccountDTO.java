package com.prueba.trabe.model.service.dto;

import javax.validation.constraints.NotEmpty;

import com.prueba.trabe.model.domain.Account;

public class AccountDTO {
	@NotEmpty
	private String number;
	private int balance;
	
	public AccountDTO() {
	}

	public AccountDTO(Account account) {
		this.number = account.getNumber();
		this.balance = account.getBalance();
	}

	public String getNumber() {
		return number;
	}

	public int getBalance() {
		return balance;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
