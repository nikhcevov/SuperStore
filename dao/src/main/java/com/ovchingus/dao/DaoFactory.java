package com.ovchingus.dao;

import com.ovchingus.dao.Settings.DaoSettings;
import com.ovchingus.dao.hibernate.DaoConnection;
import com.ovchingus.dao.hibernate.ProductDao;
import com.ovchingus.dao.hibernate.StoreDao;
import com.ovchingus.dao.hibernate.StoreProductDao;
import com.ovchingus.dao.hibernate.mappings.Product;
import com.ovchingus.dao.hibernate.mappings.Store;
import com.ovchingus.dao.hibernate.mappings.StoreProduct;

//TODO: DaoFactory do not working! Need to repair it.
public class DaoFactory {

    public static GenericDao getDao(Class daoType) {
        if (DaoSettings.isSourceDatabase()) {
            if (daoType == Store.class)
                return new StoreDao();
            if (daoType == Product.class)
                return new ProductDao();
            if (daoType == StoreProduct.class)
                return new StoreProductDao();
        }
        // TODO:
        if (DaoSettings.isSourceFile())
            return null;
        return null;
    }

    public static void openTransaction(GenericDao dao) {
        if (DaoSettings.isSourceDatabase()) {
            ((DaoConnection) dao).openCurrentSessionWithTransaction();
        }
    }

    public static void closeTransaction(GenericDao dao) {
        if (DaoSettings.isSourceDatabase()) {
            ((DaoConnection) dao).openCurrentSessionWithTransaction();
        }
    }


}
