package com.ta.test.challenge.exception;

public class ElementNotFoundException extends RuntimeException {

  public ElementNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}