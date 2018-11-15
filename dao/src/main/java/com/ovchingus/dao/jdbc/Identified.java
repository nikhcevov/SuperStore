package com.ovchingus.dao.jdbc;

/**
 * Интерфейс идентифицируемых объектов.
 */
public interface Identified<PK> {

    public KeyDb identifyKey();
}
