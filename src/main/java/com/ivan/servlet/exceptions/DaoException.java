package com.ivan.servlet.exceptions;

public class DaoException extends ServiceException {

  public DaoException(String message) {
    super(ErrorCodes.INTERNAL_ERROR, message);
  }

  public DaoException() {
    super(ErrorCodes.INTERNAL_ERROR, "Error in DAO layer");
  }

  public DaoException(String message, Throwable throwable) {
    super(ErrorCodes.INTERNAL_ERROR, message, throwable);
  }
}
