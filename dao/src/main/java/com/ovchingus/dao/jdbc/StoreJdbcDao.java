package com.ovchingus.dao.jdbc;

import com.ovchingus.dao.GenericDaoOLD;
import com.ovchingus.dao.jdbc.model.StoreJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class StoreJdbcDao extends AbstractJdbcDao<StoreJdbc, KeyDb> implements GenericDaoOLD<StoreJdbc, KeyDb> {

    public StoreJdbcDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE StoreJdbc \n" +
                "SET name = ?, address  = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM StoreJdbc WHERE id = ?;";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name, address FROM StoreJdbc ";
    }

    @Override
    protected String getKeyQuery() {
        return "WHERE id = ?;";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO StoreJdbc (name, address) \n" +
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
    protected List<StoreJdbc> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<StoreJdbc> result = new LinkedList<>();
        try {
            while (rs.next()) {
                StoreJdbc store = new StoreJdbc();
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
    protected void prepareStatementForDelete(PreparedStatement statement, StoreJdbc object) throws PersistException {
        try {
            statement.setInt(1, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, StoreJdbc object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getAddress());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, StoreJdbc object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getAddress());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}