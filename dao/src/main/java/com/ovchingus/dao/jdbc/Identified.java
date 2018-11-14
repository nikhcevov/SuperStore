package com.ovchingus.dao.jdbc;

import com.ovchingus.dao.KeyDb;

/**
 * Интерфейс идентифицируемых объектов.
 */
public interface Identified<PK> {

    public KeyDb identifyKey();
}
