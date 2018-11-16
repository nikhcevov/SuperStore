package com.ovchingus.persistence;

import com.ovchingus.persistence.MySQL.ConnectionMySQL;
import com.ovchingus.persistence.MySQL.ProductDaoMySQL;
import com.ovchingus.persistence.MySQL.StoreDaoMySQL;
import com.ovchingus.persistence.MySQL.StoreProductDaoMySQL;
import com.ovchingus.persistence.MySQL.mappings.ProductEntityMySQL;
import com.ovchingus.persistence.MySQL.mappings.StoreEntityMySQL;
import com.ovchingus.persistence.MySQL.mappings.StoreProductEntityMySQL;
import com.ovchingus.persistence.settings.DaoSettings;

//TODO: DaoFactory do not working! Need to repair it.
public class DaoFactory {

    public static GenericDao getDao(Class daoType) {
        if (DaoSettings.isSourceDatabase()) {
            if (daoType == StoreEntityMySQL.class)
                return new StoreDaoMySQL();
            if (daoType == ProductEntityMySQL.class)
                return new ProductDaoMySQL();
            if (daoType == StoreProductEntityMySQL.class)
                return new StoreProductDaoMySQL();
        }
        // TODO:
        if (DaoSettings.isSourceFile())
            return null;
        return null;
    }

    public static void openTransaction(GenericDao dao) {
        if (DaoSettings.isSourceDatabase()) {
            ((ConnectionMySQL) dao).openCurrentSessionWithTransaction();
        }
    }

    public static void closeTransaction(GenericDao dao) {
        if (DaoSettings.isSourceDatabase()) {
            ((ConnectionMySQL) dao).openCurrentSessionWithTransaction();
        }
    }


}
