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

    public void addRoute(Integer userId, String name) throws ServiceException {
        String queryString = "INSERT INTO `Route`(`date`, `user_id`, `name`) VALUES (?, ?, ?);";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
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
            } catch (SQLException e) {}
        }
    }
}
