package com.ovchingus.persistence.mysql.dao;

import com.ovchingus.persistence.mysql.entities.ProductEntityMySQL;

import java.util.List;

public class ProductDaoMySQL extends ConnectionMySQL<ProductEntityMySQL, Integer> {

    public ProductDaoMySQL() {
    }

    public boolean persist(ProductEntityMySQL entity) {
        getCurrentSession().save(entity);
        return getCurrentSession().contains(entity);
    }

    public boolean update(ProductEntityMySQL entity) {
        getCurrentSession().update(entity);
        return getCurrentSession().contains(entity);
    }

    public ProductEntityMySQL findById(Integer id) {
        return getCurrentSession().get(ProductEntityMySQL.class, id);
    }

    public ProductEntityMySQL findByName(String name) {
        return getCurrentSession().bySimpleNaturalId(ProductEntityMySQL.class).load(name);
    }

    public boolean delete(ProductEntityMySQL entity) {
        getCurrentSession().delete(entity);
        return !getCurrentSession().contains(entity);
    }

    @SuppressWarnings("unchecked")
    public List<ProductEntityMySQL> findAll() {
        return (List<ProductEntityMySQL>) getCurrentSession().createQuery("from ProductEntityMySQL").list();
    }

    public boolean deleteAll() {
        List<ProductEntityMySQL> entityList = findAll();
        for (ProductEntityMySQL entity : entityList) {
            if (!delete(entity)) return false;
        }
        return true;
    }
}
