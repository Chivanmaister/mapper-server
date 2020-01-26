package com.ivan.servlet.repositories.impl;

import com.ivan.servlet.entities.Coordinate;
import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.DaoException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.CoordinateDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BaseCoordinateDao implements CoordinateDao {

  private DataSource dataSource;
  private static final String COORDINATE_QUERY = " `Coordinate`.`id` as id, `Coordinate`.`latitude` as latitude, `Coordinate`.`longitude` as longitude, `Coordinate`.`route_id` as routeId, `Coordinate`.`counter` as counter ";

  public BaseCoordinateDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Coordinate addCoordinate(Double latitude, Double longitude, Integer routeId) throws ServiceException {
    Coordinate coordinate = null;
    PreparedStatement preparedStatement = null;
    Connection connection = null;
    String insertQuery = "INSERT INTO `Coordinates`(`latitude`, `longitude`, `route_id`) VALUES (?, ?, ?);";
    ResultSet resultSet;

    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setDouble(1, latitude);
      preparedStatement.setDouble(2, longitude);
      preparedStatement.setInt(3, routeId);
      preparedStatement.executeUpdate();
      resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        coordinate = new Coordinate();
        coordinate.setId(resultSet.getInt(1));
        coordinate.setLatitude(latitude);
        coordinate.setLongitude(longitude);
        coordinate.setRouteId(routeId);
      }
    } catch (SQLException e) {
      throw new DaoException("Error inserting coordinate into database");
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException ignored) {
      }
    }
    return coordinate;
  }

  @Override
  public List<Coordinate> findCoordinates(Integer routeId) throws DaoException {
    String queryString = "SELECT " +
            COORDINATE_QUERY +
            " FROM `Coordinates` " +
            " WHERE `Coordinates`.`route_id` = ?" +
            ";";
    List<Coordinate> coordinates = new LinkedList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(queryString);
      preparedStatement.setInt(1, routeId);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Coordinate coordinate = new Coordinate();
        coordinate.setId(resultSet.getInt(1));
        coordinate.setLatitude(resultSet.getDouble(2));
        coordinate.setLongitude(resultSet.getDouble(3));
        coordinate.setRouteId(resultSet.getInt(4));
        coordinates.add(coordinate);
      }
    } catch (SQLException e) {
      throw new DaoException("Error finding coordinates");
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (resultSet != null) {
          resultSet.close();
        }
      } catch (SQLException ignored) {
      }
    }
    return coordinates;
  }
}
