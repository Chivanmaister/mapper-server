package com.ivan.servlet.exceptions;

public class InvalidCoordinateRangeException extends ServiceException {

    public InvalidCoordinateRangeException() {
        super("Invalid coordinate range");
    }

    public InvalidCoordinateRangeException(String message) {
        super(message);
    }
}
