package com.ivan.servlet.repositories;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.DaoException;
import com.ivan.servlet.exceptions.ServiceException;

import java.util.Date;
import java.util.List;

public interface RouteDao {

  Route getRoute(Integer id) throws ServiceException;

  Route addRoute(Integer userId, String name) throws ServiceException;

  void updateRoute(Integer id, String name) throws ServiceException;

  List<Route> findRoutesByUser(Integer userId, String name, Date fromDate, Date toDate) throws DaoException;
}
