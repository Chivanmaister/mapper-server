package com.ivan.servlet.repositories.impl;

import com.ivan.servlet.entities.Route;
import com.ivan.servlet.exceptions.RouteRepositoryException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.RouteDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;

public class BaseRouteDao implements RouteDao {

    private MysqlDataSource dataSource;
    private static final String ROUTE_QUERY = " `Route`.`id` as id, `Route`.`date` as date, `Route`.`user_id` as userId, `Route`.`name` as name ";

    public BaseRouteDao(MysqlDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Route addRoute(Integer userId, String name) throws ServiceException {
        String queryString = "INSERT INTO `Route`(`date`, `user_id`, `name`) VALUES (?, ?, ?);";
        Route route = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                route = new Route();
                route.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RouteRepositoryException("Error adding route");
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
            } catch (SQLException e) {}
        }

        return route;
    }

    @Override
    public void updateRoute(Integer id, String name) throws ServiceException{
        String queryString = "UPDATE `Route` " +
                "   SET `Route`.`name` = ? " +
                "   WHERE `Route`.`id` = ? " +
                ";";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RouteRepositoryException("Error updating route");
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

    public Route getRoute(Integer id) throws ServiceException {
        String queryString = "SELECT " +
                ROUTE_QUERY +
                "   FROM `Route` " +
                "   WHERE `Route`.`id` = ?;";
        Route route = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                route = new Route();
                route.setId(resultSet.getInt(1));
                route.setDate(resultSet.getTimestamp(2));
                route.setUserId(resultSet.getInt(3));
                route.setName(resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new RouteRepositoryException("Error getting route");
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
            } catch (SQLException ignored) {}
        }
        return route;
    }
}
