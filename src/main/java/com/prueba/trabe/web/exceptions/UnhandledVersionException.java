package com.prueba.trabe.web.exceptions;

import java.util.stream.Collectors;

import org.springframework.validation.Errors;

public class UnhandledVersionException extends ResourceException {
  public UnhandledVersionException() {
    super("Versions supported: 1.0 or 2.0");
  }
}
