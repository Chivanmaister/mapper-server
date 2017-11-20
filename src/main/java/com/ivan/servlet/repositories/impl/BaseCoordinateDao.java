package com.ivan.servlet.repositories.impl;

import com.ivan.servlet.exceptions.CoordinateRepositoryException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.CoordinateDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseCoordinateDao implements CoordinateDao {

    private MysqlDataSource dataSource;
    private static final String COORDINATE_QUERY = " `Coordinate`.`id` as id, `Coordinates`.`latitude` as latitude, `Coordinates`.`longitude` as longitude, `Coordinates`.`route_id` as routeId ";

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
            throw new CoordinateRepositoryException("Error inserting coordinate into database");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ignored) {}
        }
    }
}
