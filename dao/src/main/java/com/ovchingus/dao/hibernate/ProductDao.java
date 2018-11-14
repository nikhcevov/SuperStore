package com.ovchingus.dao.hibernate;

import com.ovchingus.dao.GenericDao;

import java.util.List;

public class ProductDao extends DaoConnection implements GenericDao<Product, Integer> {

    public ProductDao() {
    }

    public void persist(Product entity) {
        getCurrentSession().save(entity);
    }

    public void update(Product entity) {
        getCurrentSession().update(entity);
    }

    public Product findById(Integer id) {
        return getCurrentSession().get(Product.class, id);
    }

    public Product findByName(String name) {
        return getCurrentSession().bySimpleNaturalId(Product.class).load(name);
    }

    public void delete(Product entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        return (List<Product>) getCurrentSession().createQuery("from Product").list();
    }

    public void deleteAll() {
        List<Product> entityList = findAll();
        for (Product entity : entityList) {
            delete(entity);
        }
    }
}
