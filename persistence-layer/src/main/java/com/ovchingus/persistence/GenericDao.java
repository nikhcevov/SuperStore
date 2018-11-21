package com.ovchingus.persistence;


import java.util.List;

public interface GenericDao<T, PK> {

    public boolean persist(T entity);

    public boolean update(T entity);

    public T findById(PK id);

    public boolean delete(T entity);

    public List<T> findAll();

    public boolean deleteAll();

    public T findByName(String name);

    public boolean contains(String name);

    public void openCurrentSession();

    public void closeCurrentSession();
}
