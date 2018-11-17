package com.ovchingus.persistence.MySQL.dao;

import com.ovchingus.persistence.MySQL.entities.StoreEntityMySQL;

import java.util.List;

public class StoreDaoMySQL extends ConnectionMySQL<StoreEntityMySQL, Integer> {

    public StoreDaoMySQL() {
    }

    public void persist(StoreEntityMySQL entity) {
        getCurrentSession().save(entity);
    }

    public void update(StoreEntityMySQL entity) {
        getCurrentSession().update(entity);
    }

    public StoreEntityMySQL findById(Integer id) {
        StoreEntityMySQL storeEntityMySQL = getCurrentSession().get(StoreEntityMySQL.class, id);
        return storeEntityMySQL;
    }

    public StoreEntityMySQL findByName(String name) {
        StoreEntityMySQL storeEntityMySQL = getCurrentSession().bySimpleNaturalId(StoreEntityMySQL.class).load(name);
        return storeEntityMySQL;
    }

    public void delete(StoreEntityMySQL entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<StoreEntityMySQL> findAll() {
        List<StoreEntityMySQL> list = (List<StoreEntityMySQL>) getCurrentSession().createQuery("from StoreEntityMySQL").list();
        return list;
    }

    public void deleteAll() {
        List<StoreEntityMySQL> entityList = findAll();
        for (StoreEntityMySQL entity : entityList) {
            delete(entity);
        }
    }
}
