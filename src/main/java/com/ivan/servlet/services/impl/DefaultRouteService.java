package com.ivan.servlet.services.impl;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.NullRouteIdException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.CoordinateDao;
import com.ivan.servlet.repositories.Repository;
import com.ivan.servlet.repositories.RouteDao;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.RouteService;

public class DefaultRouteService implements RouteService {

    private RestService service;
    private Repository repository;

    public DefaultRouteService(RestService service, Repository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Override
    public Route addRoute(String name, Integer userId) throws ServiceException {
        return repository.getRepository(RouteDao.class).addRoute(userId, name);
    }

    @Override
    public void validateRoute(Integer routeId) throws ServiceException {
        if (routeId == null) {
            throw new NullRouteIdException("Route identifier is null");
        }
        repository.getRepository(RouteDao.class);
    }

}
