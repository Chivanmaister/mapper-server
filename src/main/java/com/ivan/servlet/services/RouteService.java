package com.ivan.servlet.services;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.ServiceException;

import java.util.Date;
import java.util.List;

public interface RouteService {

  Route addRoute(String name, Integer userId) throws ServiceException;

  void validateRouteExists(Integer routeId) throws ServiceException;

  void updateRouteName(Integer id, String name) throws ServiceException;

  List<Route> findRoutes(Integer userId, String name, Date fromDate, Date toDate) throws ServiceException;
}
