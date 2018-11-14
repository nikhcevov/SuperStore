package com.ovchingus.dao.jdbc;

import com.ovchingus.dao.DaoFactoryOLD;
import com.ovchingus.dao.GenericDaoOLD;
import com.ovchingus.dao.jdbc.model.ProductJdbc;
import com.ovchingus.dao.jdbc.model.StoreJdbc;
import com.ovchingus.dao.jdbc.model.StoreProductJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class DaoFactoryJdbc implements DaoFactoryOLD<Connection> {

    //TODO: вынести в настройки
    private String user = "ovchingu_admin";//Логин пользователя
    private String password = "0egCOr2gONb";//Пароль пользователя
    private String url = "jdbc:mysql://64.62.211.134:3306/ovchingu_ShopAppDB";//URL адрес
    private String driver = "com.mysql.cj.jdbc.Driver";//Имя драйвера
    private Map<Class, DaoCreator<Connection>> creators;

    @Override
    public Connection getContext() throws PersistException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return connection;
    }

    @Override
    public GenericDaoOLD getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator<Connection> creator = creators.get(dtoClass);
        if (creator == null)
            throw new PersistException("Dao object for " + dtoClass + " not found.");

        return creator.create(connection);
    }

    //Регистрируем драйвер
    public DaoFactoryJdbc() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<>();

        // это называется method reference
        creators.put(StoreJdbc.class, StoreJdbcDao::new);
        creators.put(ProductJdbc.class, ProductJdbcDao::new);
        creators.put(StoreProductJdbc.class, StoreProductJdbcDao::new);
    }
}
