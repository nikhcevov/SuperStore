package com.ovchingus.dao.hibernate;

import com.ovchingus.dao.GenericDao;

import java.util.List;

public class StoreDao extends DaoConnection implements GenericDao<Store, Integer> {

    public StoreDao() {
    }

    public void persist(Store entity) {
        openCurrentSessionWithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionWithTransaction();
    }

    public void update(Store entity) {
        openCurrentSessionWithTransaction();
        getCurrentSession().update(entity);
        closeCurrentSessionWithTransaction();
    }

    public Store findById(Integer id) {
        openCurrentSessionWithTransaction();
        Store store = getCurrentSession().get(Store.class, id);
        closeCurrentSessionWithTransaction();
        return store;
    }

    public Store findByName(String name) {
        openCurrentSessionWithTransaction();
        Store store = getCurrentSession().bySimpleNaturalId(Store.class).load(name);
        closeCurrentSessionWithTransaction();
        return store;
    }

    public void delete(Store entity) {
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(entity);
        closeCurrentSessionWithTransaction();
    }

    @SuppressWarnings("unchecked")
    public List<Store> findAll() {
        openCurrentSessionWithTransaction();
        List<Store> list = (List<Store>) getCurrentSession().createQuery("from Store").list();
        closeCurrentSessionWithTransaction();
        return list;
    }

    public void deleteAll() {
        openCurrentSessionWithTransaction();
        List<Store> entityList = findAll();
        for (Store entity : entityList) {
            delete(entity);
        }
        closeCurrentSessionWithTransaction();
    }

}
