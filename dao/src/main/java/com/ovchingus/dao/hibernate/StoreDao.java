package com.ovchingus.dao.hibernate;

import com.ovchingus.dao.hibernate.mappings.Store;

import java.util.List;

public class StoreDao extends DaoConnection<Store, Integer> {

    public StoreDao() {
    }

    public void persist(Store entity) {
        getCurrentSession().save(entity);
    }

    public void update(Store entity) {
        getCurrentSession().update(entity);
    }

    public Store findById(Integer id) {
        Store store = getCurrentSession().get(Store.class, id);
        return store;
    }

    public Store findByName(String name) {
        Store store = getCurrentSession().bySimpleNaturalId(Store.class).load(name);
        return store;
    }

    public void delete(Store entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Store> findAll() {
        List<Store> list = (List<Store>) getCurrentSession().createQuery("from Store").list();
        return list;
    }

    public void deleteAll() {
        List<Store> entityList = findAll();
        for (Store entity : entityList) {
            delete(entity);
        }
    }
}
