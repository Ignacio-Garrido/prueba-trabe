package com.prueba.trabe.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
	@SequenceGenerator(name = "account_generator", sequenceName = "account_seq")
	private Long id_acc;
	
	@Column(nullable = false, unique = true)
	private String number;
	
	private int balance;
	
	@OneToMany
	@JoinTable(name="t_acc_mov",
			joinColumns=@JoinColumn(name="id_acc"),
			inverseJoinColumns=@JoinColumn(name="id_mov"))
	private List<Movement> movements;
	
	public Account() {
	}

	public Account(String number) {
		super();
		this.number = number;
		this.balance = 0;
	}

	public Long getId_acc() {
		return id_acc;
	}

	public String getNumber() {
		return number;
	}

	public int getBalance() {
		return balance;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setId_acc(Long id_acc) {
		this.id_acc = id_acc;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
	
	public void deposit(int amount) {
		this.balance += amount;
	}
	
	public void withdraw(int amount) {
		this.balance -= amount;
	}
}
