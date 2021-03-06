package com.ivan.servlet.repositories.impl;

import com.ivan.servlet.entities.User;
import com.ivan.servlet.exceptions.DaoException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.UserDao;

import javax.sql.DataSource;
import java.sql.*;

public class BaseUserDao implements UserDao {

  private DataSource dataSource;
  private static final String USER_QUERY = "`User`.`id` as id, `User`.`email` as email ";

  public BaseUserDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public User getUser(String email) throws ServiceException {
    String userQuery = "SELECT " +
            USER_QUERY +
            "   FROM `User` " +
            "   WHERE `User`.`email` = ? " +
            "   ;";
    User user = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(userQuery);
      preparedStatement.setString(1, email);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        user = new User();
        user.setId(resultSet.getInt(1));
        user.setEmail(resultSet.getString(2));
      }
    } catch (SQLException e) {
      throw new DaoException("Error getting user from database");
    } finally {
      try {
        if (resultSet != null) {
          resultSet.close();
        }
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException ignored) {
      }
    }

    return user;
  }

  @Override
  public User getUser(Integer id) throws ServiceException {
    String userQuery = "SELECT " +
            USER_QUERY +
            "   FROM `User` " +
            "   WHERE `User`.`id` = ? " +
            "   ;";
    User user = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(userQuery);
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        user = new User();
        user.setId(resultSet.getInt(1));
        user.setEmail(resultSet.getString(2));
      }
    } catch (SQLException e) {
      throw new DaoException("Error getting user from database");
    } finally {
      try {
        if (resultSet != null) {
          resultSet.close();
        }
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException ignored) {
      }
    }

    return user;
  }

  @Override
  public User addUser(String email) throws ServiceException {
    String userQuery = "INSERT INTO `User`(`email`) VALUES (?);";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    User user = null;

    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(userQuery, PreparedStatement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, email);
      preparedStatement.executeUpdate();
      resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        user = new User();
        user.setId(resultSet.getInt(1));
        user.setEmail(email);
      }
    } catch (SQLException e) {
      throw new DaoException("Error adding user to database");
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

    return user;
  }

  @Override
  public boolean checkUserExists(String email) throws DaoException {
    String userQuery = "SELECT (1) FROM `User` WHERE `email` = ?;";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    boolean exists = false;

    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(userQuery, PreparedStatement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, email);
      resultSet = preparedStatement.executeQuery();
      exists = resultSet.next();
    } catch (SQLException e) {
      throw new DaoException("Error adding user to database");
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
    return exists;
  }

  @Override
  public boolean checkUserExists(Integer userId) throws DaoException {
    String userQuery = "SELECT (1) FROM `User` WHERE `id` = ?;";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    boolean exists = false;

    try {
      connection = dataSource.getConnection();
      preparedStatement = connection.prepareStatement(userQuery, PreparedStatement.RETURN_GENERATED_KEYS);
      preparedStatement.setInt(1, userId);
      resultSet = preparedStatement.executeQuery();
      exists = resultSet.next();
    } catch (SQLException e) {
      throw new DaoException("Error checking user to database");
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
    return exists;
  }
}
