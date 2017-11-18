package com.ivan.servlet.services.impl;

import com.ivan.servlet.exceptions.InvalidCoordinateRangeException;
import com.ivan.servlet.exceptions.NullCoordinateException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.CoordinateDao;
import com.ivan.servlet.repositories.Repository;
import com.ivan.servlet.services.CoordinateService;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.RouteService;

public class DefaultCoordinateService implements CoordinateService {

    private RestService routeService;
    private Repository repository;

    public DefaultCoordinateService(RestService restService, Repository repository) {
        routeService = restService;
        this.repository = repository;
    }

    public void addCoordinate(Double latitude, Double longitude, Integer routeId) throws ServiceException {
        repository.getRepository(CoordinateDao.class).addCoordinate(latitude, longitude, routeId);
    }

    public void validateLatitude(Double coordinate) throws ServiceException {
        if (coordinate == null) {
            throw new NullCoordinateException("Invalid latitude");
        }
        if (coordinate > 85D || coordinate < -85D) {
            throw new InvalidCoordinateRangeException("Invalid latitude range");
        }
    }

    @Override
    public void validateLongitude(Double longitude) throws ServiceException {
        if (longitude == null) {
            throw new NullCoordinateException("Invalid longitude");
        }
        if (longitude > 180D || longitude < -180D) {
            throw new InvalidCoordinateRangeException("Invalid longitude range");
        }
    }
}
