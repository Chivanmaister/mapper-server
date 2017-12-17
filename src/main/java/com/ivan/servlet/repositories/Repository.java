package com.ivan.servlet.repositories;

import com.ivan.servlet.exceptions.InvalidClassException;
import com.ivan.servlet.exceptions.ServiceException;
import com.ivan.servlet.repositories.impl.BaseCoordinateDao;
import com.ivan.servlet.repositories.impl.BaseHistoryDao;
import com.ivan.servlet.repositories.impl.BaseRouteDao;
import com.ivan.servlet.repositories.impl.BaseUserDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.util.HashMap;
import java.util.Map;

public class Repository {
    private Map<Class, Object> repositoryMap = new HashMap<>();
    private MysqlDataSource dataSource;

    public Repository() {
        setDataSource();
        repositoryMap.put(UserDao.class, new BaseUserDao(dataSource));
        repositoryMap.put(RouteDao.class, new BaseRouteDao(dataSource));
        repositoryMap.put(CoordinateDao.class, new BaseCoordinateDao(dataSource));
        repositoryMap.put(HistoryDao.class, new BaseHistoryDao(dataSource));
    }

    private void setDataSource() {
        dataSource = new MysqlDataSource();
        dataSource.setUser("mapperdb");
        dataSource.setPassword("Zarawa2410985");
        dataSource.setDatabaseName("mapper");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mapper");
    }

    @SuppressWarnings("unchecked")
    public <T> T getRepository(Class<T> repositoryClass) throws ServiceException {
        if (!repositoryMap.containsKey(repositoryClass)) {
            throw new InvalidClassException("Error getting repository");
        }
        return (T) repositoryMap.get(repositoryClass);
    }
}
