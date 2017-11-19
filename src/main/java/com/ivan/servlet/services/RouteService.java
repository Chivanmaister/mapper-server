package com.ivan.servlet.services;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.ServiceException;

public interface RouteService {

    Route addRoute(String name, Integer userId) throws ServiceException;

    void validateRouteExists(Integer routeId) throws ServiceException;

    void updateRouteName(Integer id, String name) throws ServiceException;
}
