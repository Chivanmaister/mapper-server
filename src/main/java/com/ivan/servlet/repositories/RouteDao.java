package com.ivan.servlet.repositories;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.ServiceException;

public interface RouteDao {

    Route getRoute(Integer id) throws ServiceException;

    Route addRoute(Integer userId, String name) throws ServiceException;
}
