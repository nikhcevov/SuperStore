package com.ovchingus.dao.hibernate;

import com.ovchingus.dao.GenericDao;

import java.util.List;

public class StoreProductDao extends DaoConnection implements GenericDao<StoreProduct, StoreProduct.StoreProductPK> {

    public StoreProductDao() {
    }

    public void persist(StoreProduct entity) {
        getCurrentSession().save(entity);
    }

    public void update(StoreProduct entity) {
        getCurrentSession().update(entity);
    }

    public StoreProduct findById(StoreProduct.StoreProductPK id) {
        return getCurrentSession().get(StoreProduct.class, id);
    }

    public void delete(StoreProduct entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<StoreProduct> findAll() {
        return (List<StoreProduct>) getCurrentSession().createQuery("from StoreProduct").list();
    }

    public void deleteAll() {
        List<StoreProduct> entityList = findAll();
        for (StoreProduct entity : entityList) {
            delete(entity);
        }
    }
}
