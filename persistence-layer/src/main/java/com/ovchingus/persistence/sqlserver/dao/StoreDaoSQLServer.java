package com.ovchingus.persistence.sqlserver.dao;

import com.ovchingus.persistence.sqlserver.entities.StoreEntitySQLServer;

import java.util.List;

public class StoreDaoSQLServer extends ConnectionSQLServer<StoreEntitySQLServer, Integer> {

    public StoreDaoSQLServer() {
    }

    public boolean persist(StoreEntitySQLServer entity) {
        if (findByName(entity.getName()) != null)
            return false;
        else {
            getCurrentSession().save(entity);
        }
        return findByName(entity.getName()) != null;
    }

    public boolean update(StoreEntitySQLServer entity) {
        if (findByName(entity.getName()) != null) {
            getCurrentSession().merge(entity);
        } else return false;
        return findByName(entity.getName()) != null;
    }

    public StoreEntitySQLServer findById(Integer id) {
        return getCurrentSession().get(StoreEntitySQLServer.class, id);
    }

    public StoreEntitySQLServer findByName(String name) {
        return getCurrentSession().bySimpleNaturalId(StoreEntitySQLServer.class).load(name);
    }

    @Override
    public boolean contains(String name) {
        return findByName(name) != null;
    }

    public boolean delete(StoreEntitySQLServer entity) {
        if (findByName(entity.getName()) != null)
            getCurrentSession().delete(entity);
        else return false;
        return findByName(entity.getName()) == null;
    }

    @SuppressWarnings("unchecked")
    public List<StoreEntitySQLServer> findAll() {
        return (List<StoreEntitySQLServer>) getCurrentSession().createQuery("from StoreEntitySQLServer").list();
    }

    public boolean deleteAll() {
        List<StoreEntitySQLServer> entityList = findAll();
        for (StoreEntitySQLServer entity : entityList) {
            if (!delete(entity)) return false;
        }
        return true;
    }
}
