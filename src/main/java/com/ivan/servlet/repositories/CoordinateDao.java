package com.ivan.servlet.repositories;

import com.ivan.servlet.exceptions.ServiceException;

public interface CoordinateDao {

    void addCoordinate(Double latitude, Double longitude, Integer routeId) throws ServiceException;
}
