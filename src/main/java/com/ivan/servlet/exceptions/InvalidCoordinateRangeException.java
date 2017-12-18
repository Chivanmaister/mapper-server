package com.ivan.servlet.exceptions;

public class InvalidCoordinateRangeException extends ServiceException {

  public InvalidCoordinateRangeException() {
    super(ErrorCodes.INVALID_COORDINATE_RANGE, "Invalid coordinate range");
  }

  public InvalidCoordinateRangeException(String message) {
    super(ErrorCodes.INVALID_COORDINATE_RANGE, message);
  }
}
