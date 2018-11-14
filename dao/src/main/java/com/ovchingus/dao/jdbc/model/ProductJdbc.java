package com.ovchingus.dao.jdbc.model;


import com.ovchingus.dao.KeyDb;
import com.ovchingus.dao.hibernate.Product;
import com.ovchingus.dao.jdbc.Identified;


public class ProductJdbc extends Product implements Identified<KeyDb> {

    public ProductJdbc(Integer id, String name) {
        super(id, name);
    }

    public ProductJdbc(String name) {
        super(name);
    }

    public ProductJdbc() {
    }

    @Override
    public KeyDb identifyKey() {
        return new KeyDb(getId());
    }
}
