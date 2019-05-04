package com.ivan.servlet.services.impl;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.InvalidRouteIdException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.DefaultRepository;
import com.ivan.servlet.repositories.RouteDao;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.RouteService;

import java.util.Date;
import java.util.List;

public class DefaultRouteService implements RouteService {

  private RestService service;
  private DefaultRepository defaultRepository;

  public DefaultRouteService(RestService service, DefaultRepository defaultRepository) {
    this.service = service;
    this.defaultRepository = defaultRepository;
  }

  @Override
  public Route addRoute(String name, Integer userId) throws ServiceException {
    return defaultRepository.getRepository(RouteDao.class).addRoute(userId, name);
  }

  @Override
  public void validateRouteExists(Integer routeId) throws ServiceException {
    if (routeId == null) {
      throw new InvalidRouteIdException("Invalid route identifier");
    }
    defaultRepository.getRepository(RouteDao.class);
  }

  @Override
  public void updateRouteName(Integer id, String name) throws ServiceException {
    defaultRepository.getRepository(RouteDao.class).updateRoute(id, name);
  }

  @Override
  public List<Route> findRoutes(Integer userId, String name, Date fromDate, Date toDate) throws ServiceException {
    List<Route> routes;
    routes = defaultRepository.getRepository(RouteDao.class).findRoutesByUser(userId, name, fromDate, toDate);
    return routes;
  }
}
