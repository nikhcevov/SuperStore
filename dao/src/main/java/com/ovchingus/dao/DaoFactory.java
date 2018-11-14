package com.ovchingus.dao;

import com.ovchingus.dao.hibernate.*;

public class DaoFactory {

    public DaoConnection daoConnection;

    public static GenericDao getDao(String source, Class type) {
        if (source.equals("database")) {
            if (type.getSimpleName().equalsIgnoreCase("Store")) return new StoreDao();
            if (type.equals(Product.class)) return new ProductDao();
            if (type.equals(StoreProduct.class)) return new StoreProductDao();
        }
        return null;
    }
}
