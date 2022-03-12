package com.prueba.trabe.model.exception;

public class NotFoundException extends ModelException {
  public NotFoundException(String number, Class<?> clazz) {
    super("Not found " + clazz.getSimpleName() + " with number " + number);
  }
}
