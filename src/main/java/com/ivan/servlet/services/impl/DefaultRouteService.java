package com.ivan.servlet.services.impl;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.Repository;
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
        return null;
    }

}
