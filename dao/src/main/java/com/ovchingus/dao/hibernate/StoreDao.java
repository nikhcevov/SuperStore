package com.ovchingus.dao.hibernate;

import com.ovchingus.dao.GenericDao;

import java.util.List;

public class StoreDao extends DaoConnection implements GenericDao<Store, Integer> {

    public StoreDao() {
    }

    public void persist(Store entity) {
        openCurrentSession();
        getCurrentSession().save(entity);
        closeCurrentSession();
    }

    public void update(Store entity) {
        getCurrentSession().update(entity);
    }

    public Store findById(Integer id) {
        return getCurrentSession().get(Store.class, id);
    }

    public Store findByName(String name) {
        return getCurrentSession().bySimpleNaturalId(Store.class).load(name);
    }

    public void delete(Store entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Store> findAll() {
        return (List<Store>) getCurrentSession().createQuery("from Store").list();
    }

    public void deleteAll() {
        List<Store> entityList = findAll();
        for (Store entity : entityList) {
            delete(entity);
        }
    }

}
