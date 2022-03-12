package com.prueba.trabe.model.exception;

public class InsufficientBalanceException extends ModelException {
  public InsufficientBalanceException(String number, int amount) {
    super("Cannot withdraw " + amount + " from account " + number);
  }
}
