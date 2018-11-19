package com.ovchingus.persistence.mysql.dao;

import com.ovchingus.persistence.mysql.entities.StoreProductEntityMySQL;

import java.util.List;

public class StoreProductDaoMySQL extends ConnectionMySQL<StoreProductEntityMySQL, StoreProductEntityMySQL.StoreProductPK> {

    public boolean persist(StoreProductEntityMySQL entity) {
        getCurrentSession().save(entity);
        return getCurrentSession().contains(entity);
    }

    public boolean update(StoreProductEntityMySQL entity) {
        getCurrentSession().update(entity);
        return getCurrentSession().contains(entity);
    }

    public StoreProductEntityMySQL findById(StoreProductEntityMySQL.StoreProductPK id) {
        return getCurrentSession().get(StoreProductEntityMySQL.class, id);
    }

    public boolean delete(StoreProductEntityMySQL entity) {
        getCurrentSession().delete(entity);
        return !getCurrentSession().contains(entity);
    }

    @SuppressWarnings("unchecked")
    public List<StoreProductEntityMySQL> findAll() {
        return (List<StoreProductEntityMySQL>) getCurrentSession().createQuery("from StoreProductEntityMySQL").list();
    }

    public boolean deleteAll() {
        List<StoreProductEntityMySQL> entityList = findAll();
        for (StoreProductEntityMySQL entity : entityList) {
            if (!delete(entity)) return false;
        }
        return true;
    }

    @Override
    public StoreProductEntityMySQL findByName(String name) {
        return null;
    }
}
