package com.ovchingus.dao.jdbc;

import com.ovchingus.dao.GenericDaoOLD;
import com.ovchingus.dao.jdbc.model.StoreProductJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class StoreProductJdbcDao extends AbstractJdbcDao<StoreProductJdbc, KeyDb> implements GenericDaoOLD<StoreProductJdbc, KeyDb> {

    StoreProductJdbcDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE StoreProductJdbc \n" +
                "SET price = ?, qty = ?  \n" +
                "WHERE store_id = ? AND product_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM StoreProductJdbc WHERE store_id = ? AND product_id = ?;";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT store_id, product_id, price, qty FROM StoreProductJdbc ";
    }

    @Override
    protected String getKeyQuery() {
        return "WHERE store_id = ? AND product_id = ?;";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO StoreProductJdbc (price, qty) \n" +
                "VALUES (?, ?);";
    }

    @Override
    protected void keyHandler(PreparedStatement statement, KeyDb key) throws PersistException {
        try {
            statement.setInt(1, key.identifyKey().getKey());
            statement.setInt(2, key.identifyKey().getValue());
        } catch (Exception e) {
            throw new PersistException(e);
        }

    }

    @Override
    protected List<StoreProductJdbc> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<StoreProductJdbc> result = new LinkedList<>();
        try {
            while (rs.next()) {
                StoreProductJdbc sp = new StoreProductJdbc();
                sp.setStoreId(rs.getInt("store_id"));
                sp.setProductId(rs.getInt("product_id"));
                sp.setPrice(rs.getDouble("price"));
                sp.setQty(rs.getInt("qty"));
                result.add(sp);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, StoreProductJdbc object) throws PersistException {
        try {
            statement.setInt(1, object.getStoreId());
            statement.setInt(2, object.getProductId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, StoreProductJdbc object) throws PersistException {
        try {
            statement.setDouble(1, object.getPrice());
            statement.setInt(2, object.getQty());
            statement.setInt(3, object.getStoreId());
            statement.setInt(4, object.getProductId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, StoreProductJdbc object) throws PersistException {
        try {
            statement.setDouble(1, object.getPrice());
            statement.setInt(2, object.getQty());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
