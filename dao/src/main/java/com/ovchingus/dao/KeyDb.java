package com.ovchingus.dao;

import com.ovchingus.dao.jdbc.Identified;

public class KeyDb implements Identified<KeyDb> {

    private Integer key;

    public Integer getKey() {
        return key;
    }

    private Integer value;


    public KeyDb(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public KeyDb() {
    }

    public KeyDb(Integer key) {
        this.key = key;
        this.value = null;
    }

    public KeyDb identifyKey() {
        return new KeyDb(key, value);
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
