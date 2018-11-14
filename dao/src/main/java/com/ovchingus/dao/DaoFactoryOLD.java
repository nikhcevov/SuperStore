package com.ovchingus.dao;

/** Фабрика объектов для работы с базой данных */
public interface DaoFactoryOLD<T> {

    interface DaoCreator<T> {
        GenericDaoOLD create(T context);
    }

    /** Возвращает подключение к базе данных */
    T getContext() throws Exception;

    /** Возвращает объект для управления персистентным состоянием объекта */
    GenericDaoOLD getDao(T context, Class dtoClass) throws Exception;
}