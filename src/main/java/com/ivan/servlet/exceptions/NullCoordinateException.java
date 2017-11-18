package com.ivan.servlet.exceptions;

public class NullCoordinateException extends ServiceException {

    public NullCoordinateException() {
        super("Coordinate is null");
    }

    public NullCoordinateException(String message) {
        super(message);
    }
}
