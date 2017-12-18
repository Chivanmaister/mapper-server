package com.ivan.servlet.exceptions;

public class InvalidClassException extends ServiceException {

  public InvalidClassException() {
    super(ErrorCodes.INTERNAL_ERROR, "Unknown class");
  }

  public InvalidClassException(String message) {
    super(ErrorCodes.INTERNAL_ERROR, message);
  }
}
