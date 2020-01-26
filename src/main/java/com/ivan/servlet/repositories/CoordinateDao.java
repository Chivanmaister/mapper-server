package com.ivan.servlet.repositories;

import com.ivan.servlet.entities.Coordinate;
import com.ivan.servlet.exceptions.DaoException;
import com.ivan.servlet.exceptions.ServiceException;

import java.util.List;

public interface CoordinateDao {

  Coordinate addCoordinate(Double latitude, Double longitude, Integer routeId) throws ServiceException;

  List<Coordinate> findCoordinates(Integer routeId) throws DaoException;
}
