package com.ovchingus.persistence.sqlserver.dao;

import com.ovchingus.persistence.sqlserver.entities.ProductEntitySQLServer;

import java.util.List;

public class ProductDaoSQLServer extends ConnectionSQLServer<ProductEntitySQLServer, Integer> {

    public ProductDaoSQLServer() {
    }

    public boolean persist(ProductEntitySQLServer entity) {
        if (findByName(entity.getName()) != null)
            return false;
        else {
            getCurrentSession().save(entity);
        }
        return findByName(entity.getName()) != null;
    }

    public boolean update(ProductEntitySQLServer entity) {
        if (findByName(entity.getName()) != null) {
            getCurrentSession().merge(entity);
        }
        else return false;
        return findByName(entity.getName()) != null;
    }

    public ProductEntitySQLServer findById(Integer id) {
        return getCurrentSession().get(ProductEntitySQLServer.class, id);
    }

    public ProductEntitySQLServer findByName(String name) {
        return getCurrentSession().bySimpleNaturalId(ProductEntitySQLServer.class).load(name);
    }

    @Override
    public boolean contains(String name) {
        return findByName(name) != null;
    }

    public boolean delete(ProductEntitySQLServer entity) {
        if (findByName(entity.getName()) != null)
            getCurrentSession().delete(entity);
        else return false;
        return findByName(entity.getName()) != null;
    }

    @SuppressWarnings("unchecked")
    public List<ProductEntitySQLServer> findAll() {
        return (List<ProductEntitySQLServer>) getCurrentSession().createQuery("from ProductEntitySQLServer").list();
    }

    public boolean deleteAll() {
        List<ProductEntitySQLServer> entityList = findAll();
        for (ProductEntitySQLServer entity : entityList) {
            if (!delete(entity)) return false;
        }
        return true;
    }
}
