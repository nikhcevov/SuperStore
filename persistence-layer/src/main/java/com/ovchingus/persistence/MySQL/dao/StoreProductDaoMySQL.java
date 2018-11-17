package com.ovchingus.persistence.MySQL.dao;

import com.ovchingus.persistence.MySQL.entities.StoreProductEntityMySQL;

import java.util.List;

public class StoreProductDaoMySQL extends ConnectionMySQL<StoreProductEntityMySQL, StoreProductEntityMySQL.StoreProductPK> {

    public StoreProductDaoMySQL() {
    }

    public void persist(StoreProductEntityMySQL entity) {
        getCurrentSession().save(entity);
    }

    public void update(StoreProductEntityMySQL entity) {
        getCurrentSession().update(entity);
    }

    public StoreProductEntityMySQL findById(StoreProductEntityMySQL.StoreProductPK id) {
        return getCurrentSession().get(StoreProductEntityMySQL.class, id);
    }

    public void delete(StoreProductEntityMySQL entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<StoreProductEntityMySQL> findAll() {
        return (List<StoreProductEntityMySQL>) getCurrentSession().createQuery("from StoreProductEntityMySQL").list();
    }

    public void deleteAll() {
        List<StoreProductEntityMySQL> entityList = findAll();
        for (StoreProductEntityMySQL entity : entityList) {
            delete(entity);
        }
    }

    @Override
    public StoreProductEntityMySQL findByName(String name) {
        return null;
    }
}
