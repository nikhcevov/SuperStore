package com.ovchingus.dao.core.jdbc;

import com.ovchingus.dao.core.GenericDao;
import com.ovchingus.dao.model.Store;
import com.ovchingus.dao.util.KeyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class StoreJdbcDao extends AbstractJdbcDao<Store, KeyDb> implements GenericDao<Store, KeyDb> {

    public StoreJdbcDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Store \n" +
                "SET name = ?, address  = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Store WHERE id = ?;";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name, address FROM Store ";
    }

    @Override
    protected String getKeyQuery() {
        return "WHERE id = ?;";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Store (name, address) \n" +
                "VALUES (?, ?);";
    }

    @Override
    protected void keyHandler(PreparedStatement statement, KeyDb key) throws PersistException {
        try {
            statement.setInt(1, key.identifyKey().getKey());
        } catch (Exception e) {
            throw new PersistException(e);
        }

    }

    @Override
    protected List<Store> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Store> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Store store = new Store();
                store.setId(rs.getInt("id"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                result.add(store);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Store object) throws PersistException {
        try {
            statement.setInt(1, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Store object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getAddress());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Store object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getAddress());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}