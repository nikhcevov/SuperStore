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
        Product product = getCurrentSession().get(Product.class, id);
        return product;
    }

    public Product findByName(String name) {
        Product product = getCurrentSession().bySimpleNaturalId(Product.class).load(name);
        return product;
    }

    public void delete(Product entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        List<Product> list = (List<Product>) getCurrentSession().createQuery("from Product").list();
        return list;
    }

    public void deleteAll() {
        List<Product> entityList = findAll();
        for (Product entity : entityList) {
            delete(entity);
        }
    }
}
