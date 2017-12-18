package com.ivan.servlet.exceptions;

public class InvalidServiceException extends ServiceException {

  public InvalidServiceException() {
    super(ErrorCodes.INTERNAL_ERROR, "Invalid service");
  }

  public InvalidServiceException(String message) {
    super(ErrorCodes.INTERNAL_ERROR, message);
  }
}
