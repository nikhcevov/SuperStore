package com.ovchingus.persistence.mysql.dao;

import com.ovchingus.persistence.mysql.entities.ProductEntityMySQL;

import java.util.List;

public class ProductDaoMySQL extends ConnectionMySQL<ProductEntityMySQL, Integer> {

    public boolean persist(ProductEntityMySQL entity) {
        getCurrentSession().save(entity);
        return true;
    }

    public boolean update(ProductEntityMySQL entity) {
        getCurrentSession().update(entity);
        return true;
    }

    public ProductEntityMySQL findById(Integer id) {
        return getCurrentSession().get(ProductEntityMySQL.class, id);
    }

    public ProductEntityMySQL findByName(String name) {
        return getCurrentSession().bySimpleNaturalId(ProductEntityMySQL.class).load(name);
    }

    public boolean delete(ProductEntityMySQL entity) {
        getCurrentSession().delete(entity);
        return true;
    }

    @SuppressWarnings("unchecked")
    public List<ProductEntityMySQL> findAll() {
        return (List<ProductEntityMySQL>) getCurrentSession().createQuery("from ProductEntityMySQL").list();
    }

    public boolean deleteAll() {
        List<ProductEntityMySQL> entityList = findAll();
        for (ProductEntityMySQL entity : entityList) {
            delete(entity);
        }
        return true;
    }
}
