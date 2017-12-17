package com.ivan.servlet.exceptions;

public class InvalidCoordinateException extends ServiceException {

    public InvalidCoordinateException() {
        super(ErrorCodes.INVALID_COORDINATE_RANGE, "Invalid coordinate");
    }

    public InvalidCoordinateException(String message) {
        super(ErrorCodes.INVALID_COORDINATE_RANGE, message);
    }
}
