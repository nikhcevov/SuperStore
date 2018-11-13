package com.ovchingus.dao.core;

/** Фабрика объектов для работы с базой данных */
public interface DaoFactory<T> {

    interface DaoCreator<T> {
        GenericDao create(T context);
    }

    /** Возвращает подключение к базе данных */
    T getContext() throws Exception;

    /** Возвращает объект для управления персистентным состоянием объекта */
    GenericDao getDao(T context, Class dtoClass) throws Exception;
}