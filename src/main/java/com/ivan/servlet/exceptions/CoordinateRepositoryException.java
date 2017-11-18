package com.ivan.servlet.exceptions;

import com.ivan.servlet.entities.Coordinate;

public class CoordinateRepositoryException extends ServiceException {

    public CoordinateRepositoryException() {
        super("Coordinate repository error");
    }

    public CoordinateRepositoryException(String message) {
        super(message);
    }
}
