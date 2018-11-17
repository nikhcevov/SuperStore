package com.ovchingus.persistence;

import com.sun.istack.internal.Nullable;

import java.util.List;

public interface GenericDao<T, PK> {

    public boolean persist(T entity);

    public boolean update(T entity);

    @Nullable
    public T findById(PK id);

    public boolean delete(T entity);

    @Nullable
    public List<T> findAll();

    public boolean deleteAll();

    @Nullable
    public T findByName(String name);
}
