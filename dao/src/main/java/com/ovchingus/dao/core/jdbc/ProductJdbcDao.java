package com.ovchingus.dao.core.jdbc;

import com.ovchingus.dao.core.GenericDao;
import com.ovchingus.dao.model.Product;
import com.ovchingus.dao.util.KeyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ProductJdbcDao extends AbstractJdbcDao<Product, KeyDb> implements GenericDao<Product, KeyDb> {

    ProductJdbcDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Product \n" +
                "SET name = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Product WHERE id = ?;";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name FROM Product ";
    }

    @Override
    protected String getKeyQuery() {
        return "WHERE id = ?;";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Product (name) \n" +
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
    protected List<Product> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Product> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Product product = new Product();
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
    protected void prepareStatementForDelete(PreparedStatement statement, Product object) throws PersistException {
        try {
            statement.setInt(1, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Product object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Product object) throws PersistException {
        try {
            statement.setString(1, object.getName());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
