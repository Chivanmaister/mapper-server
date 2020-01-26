package com.ivan.servlet.exceptions;

public class UserNotExistsException extends ServiceException {

  public UserNotExistsException() {
    super(ErrorCodes.INVALID_USER, "Invalid user");
  }

  public UserNotExistsException(String message) {
    super(ErrorCodes.INVALID_USER, message);
  }
}
