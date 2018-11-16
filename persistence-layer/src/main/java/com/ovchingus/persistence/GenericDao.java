package com.ovchingus.persistence;

import java.util.List;

public interface GenericDao<T, PK> {

    public void persist(T entity);

    public void update(T entity);

    public T findById(PK id);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();

    public T findByName(String name);
}
