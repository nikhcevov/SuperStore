package com.ovchingus.dao.core;

import java.util.List;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 *
 * @param <T>  тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public interface GenericDao<T, PK> {

    /**
     * Создает новую запись, соответствующую объекту object
     */
    public void insert(T object) throws Exception;

    /**
     * Возвращает объект соответствующий записи с первичным ключом key или null
     */
    public T getByPK(PK key) throws Exception;

    /**
     * Сохраняет состояние объекта object в базе данных
     */
    public void update(T object) throws Exception;

    /**
     * Удаляет запись об объекте из базы данных
     */
    public void delete(T object) throws Exception;

    /**
     * Возвращает список объектов соответствующих всем записям в базе данных
     */
    public List<T> getAll() throws Exception;
}
