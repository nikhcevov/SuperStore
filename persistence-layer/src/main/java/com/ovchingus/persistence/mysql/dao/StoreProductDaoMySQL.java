package com.ovchingus.persistence.mysql.dao;

import com.ovchingus.persistence.mysql.entities.StoreProductEntityMySQL;

import java.util.List;

public class StoreProductDaoMySQL extends ConnectionMySQL<StoreProductEntityMySQL, StoreProductEntityMySQL.StoreProductPK> {

    public StoreProductDaoMySQL() {
    }

    public boolean persist(StoreProductEntityMySQL entity) {
        getCurrentSession().save(entity);
        return true;
    }

    public boolean update(StoreProductEntityMySQL entity) {
        getCurrentSession().update(entity);
        return true;
    }

    public StoreProductEntityMySQL findById(StoreProductEntityMySQL.StoreProductPK id) {
        return getCurrentSession().get(StoreProductEntityMySQL.class, id);
    }

    public boolean delete(StoreProductEntityMySQL entity) {
        getCurrentSession().delete(entity);
        return true;
    }

    @SuppressWarnings("unchecked")
    public List<StoreProductEntityMySQL> findAll() {
        return (List<StoreProductEntityMySQL>) getCurrentSession().createQuery("from StoreProductEntityMySQL").list();
    }

    public boolean deleteAll() {
        List<StoreProductEntityMySQL> entityList = findAll();
        for (StoreProductEntityMySQL entity : entityList) {
            delete(entity);
        }
        return true;
    }

    @Override
    public StoreProductEntityMySQL findByName(String name) {
        return null;
    }
}
