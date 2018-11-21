package com.ovchingus.persistence.sqlserver.dao;

import com.ovchingus.persistence.sqlserver.entities.StoreProductEntitySQLServer;

import java.util.List;

public class StoreProductDaoSQLServer extends ConnectionSQLServer<StoreProductEntitySQLServer, StoreProductEntitySQLServer.StoreProductPK> {

    public boolean persist(StoreProductEntitySQLServer entity) {
        if (findById(entity.getId()) != null)
            return false;
        else {
            getCurrentSession().save(entity);
        }
        return findById(entity.getId()) != null;
    }

    public boolean update(StoreProductEntitySQLServer entity) {
        if (findById(entity.getId()) != null) {
            getCurrentSession().merge(entity);
        }
        else return false;
        return findById(entity.getId()) != null;
    }

    public StoreProductEntitySQLServer findById(StoreProductEntitySQLServer.StoreProductPK id) {
        return getCurrentSession().get(StoreProductEntitySQLServer.class, id);
    }

    public boolean delete(StoreProductEntitySQLServer entity) {
        if (findById(entity.getId()) != null)
            getCurrentSession().delete(entity);
        else return false;
        return findById(entity.getId()) != null;
    }

    @SuppressWarnings("unchecked")
    public List<StoreProductEntitySQLServer> findAll() {
        return (List<StoreProductEntitySQLServer>) getCurrentSession().createQuery("from StoreProductEntitySQLServer").list();
    }

    public boolean deleteAll() {
        List<StoreProductEntitySQLServer> entityList = findAll();
        for (StoreProductEntitySQLServer entity : entityList) {
            if (!delete(entity)) return false;
        }
        return true;
    }

    @Override
    public StoreProductEntitySQLServer findByName(String name) {
        return null;
    }

    @Override
    public boolean contains(String name) {
        return false;
    }
}
