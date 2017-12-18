package com.ivan.servlet.services;

import com.ivan.servlet.entities.Coordinate;
import com.ivan.servlet.exceptions.ServiceException;

import java.util.List;

public interface CoordinateService {

  void addCoordinate(Double latitude, Double longitude, Integer routeId) throws ServiceException;

  void validateLatitude(Double latitude) throws ServiceException;

  void validateLongitude(Double longitude) throws ServiceException;

  List<Coordinate> findCoordinates(Integer routeId) throws ServiceException;
}
