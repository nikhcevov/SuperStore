package com.ovchingus.dao.model;


import com.ovchingus.dao.core.jdbc.Identified;
import com.ovchingus.dao.util.KeyDb;

public class Product implements Identified<KeyDb> {

    private Integer id;
    private String name;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public KeyDb identifyKey() {
        return new KeyDb(id);
    }
}
