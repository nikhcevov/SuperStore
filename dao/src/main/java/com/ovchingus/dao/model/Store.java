package com.ovchingus.dao.model;


import com.ovchingus.dao.core.jdbc.Identified;
import com.ovchingus.dao.util.KeyDb;

public class Store implements Identified<KeyDb> {

    private Integer id;
    private String name;
    private String address;

    public Store() {
    }

    public Store(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public KeyDb identifyKey() {
        return new KeyDb(id);
    }
}
