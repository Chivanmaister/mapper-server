package com.ivan.servlet.exceptions;

public class InvalidNameException extends ServiceException {

  public InvalidNameException() {
    super(ErrorCodes.INVALID_ROUTE_NAME, "Invalid name");
  }

  public InvalidNameException(String message) {
    super(ErrorCodes.INVALID_ROUTE_NAME, message);
  }
}
