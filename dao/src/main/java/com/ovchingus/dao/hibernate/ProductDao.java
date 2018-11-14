package com.ovchingus.dao.hibernate;

import com.ovchingus.dao.GenericDao;

import java.util.List;

public class ProductDao extends DaoConnection implements GenericDao<Product, Integer> {

    public ProductDao() {
    }

    public void persist(Product entity) {
        getCurrentSession().persist(entity);
    }

    public void update(Product entity) {
        openCurrentSessionWithTransaction();
        getCurrentSession().update(entity);
        closeCurrentSessionWithTransaction();
    }

    public Product findById(Integer id) {
        openCurrentSessionWithTransaction();
        Product product = getCurrentSession().get(Product.class, id);
        closeCurrentSessionWithTransaction();
        return product;
    }

    public Product findByName(String name) {
        openCurrentSessionWithTransaction();
        Product product = getCurrentSession().bySimpleNaturalId(Product.class).load(name);
        closeCurrentSessionWithTransaction();
        return product;
    }

    public void delete(Product entity) {
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(entity);
        closeCurrentSessionWithTransaction();
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        openCurrentSessionWithTransaction();
        List<Product> list = (List<Product>) getCurrentSession().createQuery("from Product").list();
        closeCurrentSessionWithTransaction();
        return list;
    }

    public void deleteAll() {
        openCurrentSessionWithTransaction();
        List<Product> entityList = findAll();
        for (Product entity : entityList) {
            delete(entity);
        }
        closeCurrentSessionWithTransaction();
    }
}
