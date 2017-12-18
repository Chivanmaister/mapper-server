package com.ivan.servlet.repositories.impl;

import com.ivan.servlet.entities.Coordinate;
import com.ivan.servlet.exceptions.DaoException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.CoordinateDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BaseCoordinateDao implements CoordinateDao {

  private MysqlDataSource dataSource;
  private static final String COORDINATE_QUERY = " `Coordinates`.`id` as id, `Coordinates`.`latitude` as latitude, `Coordinates`.`longitude` as longitude, `Coordinates`.`route_id` as routeId ";

  public BaseCoordinateDao(MysqlDataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void addCoordinate(Double latitude, Double longitude, Integer routeId) throws ServiceException {
    String insertQuery = "INSERT INTO `Coordinates`(`latitude`, `longitude`, `route_id`) VALUES (?, ?, ?);";
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(insertQuery);
      preparedStatement.setDouble(1, latitude);
      preparedStatement.setDouble(2, longitude);
      preparedStatement.setInt(3, routeId);
      preparedStatement.executeUpdate();
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
