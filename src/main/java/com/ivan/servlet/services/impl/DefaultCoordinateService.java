package com.ivan.servlet.services.impl;

import com.ivan.servlet.entities.Coordinate;
import com.ivan.servlet.exceptions.InvalidCoordinateRangeException;
import com.ivan.servlet.exceptions.InvalidCoordinateException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.CoordinateDao;
import com.ivan.servlet.repositories.Repository;
import com.ivan.servlet.services.CoordinateService;
import com.ivan.servlet.services.Service;

import java.util.List;

public class DefaultCoordinateService implements CoordinateService {

  private Service service;
  private Repository repository;

  public DefaultCoordinateService(Service restService, Repository repository) {
    service = restService;
    this.repository = repository;
  }

  public Coordinate addCoordinate(Double latitude, Double longitude, Integer routeId) throws ServiceException {
    return repository.getRepository(CoordinateDao.class).addCoordinate(latitude, longitude, routeId);
  }

  public void validateLatitude(Double latitude) throws ServiceException {
    if (latitude == null) {
      throw new InvalidCoordinateException("Invalid latitude");
    }
    if (latitude > 85D || latitude < -85D) {
      throw new InvalidCoordinateRangeException("Invalid latitude range");
    }
  }

  @Override
  public void validateLongitude(Double longitude) throws ServiceException {
    if (longitude == null) {
      throw new InvalidCoordinateException("Invalid longitude");
    }
    if (longitude > 180D || longitude < -180D) {
      throw new InvalidCoordinateRangeException("Invalid longitude range");
    }
  }

  @Override
  public List<Coordinate> findCoordinates(Integer routeId) throws ServiceException {
    List<Coordinate> coordinates;
    coordinates = repository.getRepository(CoordinateDao.class).findCoordinates(routeId);
    return coordinates;
  }
}
