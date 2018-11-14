package com.ovchingus.dao.jdbc;

import com.ovchingus.dao.GenericDaoOLD;
import com.ovchingus.dao.KeyDb;
import com.ovchingus.dao.jdbc.model.ProductJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ProductJdbcDao extends AbstractJdbcDao<ProductJdbc, KeyDb> implements GenericDaoOLD<ProductJdbc, KeyDb> {

    ProductJdbcDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE ProductJdbc \n" +
                "SET name = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM ProductJdbc WHERE id = ?;";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name FROM ProductJdbc ";
    }

    @Override
    protected String getKeyQuery() {
        return "WHERE id = ?;";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO ProductJdbc (name) \n" +
                "VALUES (?);";
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
    protected List<ProductJdbc> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<ProductJdbc> result = new LinkedList<>();
        try {
            while (rs.next()) {
                ProductJdbc product = new ProductJdbc();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                result.add(product);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, ProductJdbc object) throws PersistException {
        try {
            statement.setInt(1, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ProductJdbc object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, ProductJdbc object) throws PersistException {
        try {
            statement.setString(1, object.getName());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
