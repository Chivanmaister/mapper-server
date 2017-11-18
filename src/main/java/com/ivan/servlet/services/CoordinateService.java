package com.ivan.servlet.services;

import com.ivan.servlet.exceptions.ServiceException;

public interface CoordinateService {

    void addCoordinate(Double latitude, Double longitude, Integer routeId) throws ServiceException;

    void validateLatitude(Double latitude) throws ServiceException;

    void validateLongitude(Double longitude) throws ServiceException;
}
