package com.ovchingus.dao.core.jdbc;

import com.ovchingus.dao.util.KeyDb;

/**
 * Интерфейс идентифицируемых объектов.
 */
public interface Identified<PK> {

    public KeyDb identifyKey();
}
