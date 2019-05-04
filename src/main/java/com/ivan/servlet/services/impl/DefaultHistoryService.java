package com.ivan.servlet.services.impl;

import com.ivan.servlet.entities.Coordinate;
import com.ivan.servlet.entities.Route;
import com.ivan.servlet.entities.details.History;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.DefaultRepository;
import com.ivan.servlet.services.CoordinateService;
import com.ivan.servlet.services.HistoryService;
import com.ivan.servlet.services.RestService;
import com.ivan.servlet.services.RouteService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DefaultHistoryService implements HistoryService {

  private RestService restService;
  private DefaultRepository defaultRepository;

  public DefaultHistoryService(RestService restService, DefaultRepository defaultRepository) {
    this.restService = restService;
    this.defaultRepository = defaultRepository;
  }

  @Override
  public List<History> getHistoryList(Integer userId, String name, Long dateFrom, Long dateTo) throws ServiceException {
    List<History> historyList = new LinkedList<>();
    Date fromDate = null;
    Date toDate = null;

    validateDates(dateFrom, dateTo);
    if (dateFrom != null) {
      fromDate = new Date(dateFrom);
    }
    if (dateTo != null) {
      toDate = new Date(dateTo);
    }

    List<Route> routes = restService.getService(RouteService.class).findRoutes(userId, name, fromDate, toDate);
    for (Route route : routes) {
      List<Coordinate> coordinates = restService.getService(CoordinateService.class).findCoordinates(route.getId());
      History history = new History();
      history.setRoute(route);
      history.setCoordinates(coordinates);
      historyList.add(history);
    }
    return historyList;
  }

  private void validateDates(Long dateFrom, Long dateTo) {
    if (dateFrom != null && dateTo != null) {
      if (dateFrom > dateTo) {
        Long temp = dateTo;
        dateTo = dateFrom;
        dateFrom = temp;
      }
    }
  }
}
