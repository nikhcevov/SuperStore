package com.ovchingus.persistence;

import com.ovchingus.persistence.csv.dao.ProductDaoCSV;
import com.ovchingus.persistence.csv.dao.StoreDaoCSV;
import com.ovchingus.persistence.csv.entities.ProductEntityCSV;
import com.ovchingus.persistence.csv.entities.StoreEntityCSV;
import com.ovchingus.persistence.mysqls.dao.ConnectionMySQL;
import com.ovchingus.persistence.mysqls.dao.ProductDaoMySQL;
import com.ovchingus.persistence.mysqls.dao.StoreDaoMySQL;
import com.ovchingus.persistence.mysqls.dao.StoreProductDaoMySQL;
import com.ovchingus.persistence.mysqls.entities.ProductEntityMySQL;
import com.ovchingus.persistence.mysqls.entities.StoreEntityMySQL;
import com.ovchingus.persistence.mysqls.entities.StoreProductEntityMySQL;
import com.ovchingus.persistence.settings.DaoSettings;

//TODO: DaoFactory did wrong in terms of factory method pattern! Need to repair it.
public class DaoFactory {

    public static GenericDao getDao(Class daoType) {
        if (DaoSettings.isSourceMySQL()) {
            if (daoType == StoreEntityMySQL.class)
                return new StoreDaoMySQL();
            if (daoType == ProductEntityMySQL.class)
                return new ProductDaoMySQL();
            if (daoType == StoreProductEntityMySQL.class)
                return new StoreProductDaoMySQL();
        }
        if (DaoSettings.isSourceCSV())
            if (daoType == StoreEntityCSV.class)
                return new StoreDaoCSV();
        if (daoType == ProductEntityCSV.class)
            return new ProductDaoCSV();
        return null;
    }

    public static void openTransaction(GenericDao dao) {
        if (DaoSettings.isSourceMySQL()) {
            ((ConnectionMySQL) dao).openCurrentSessionWithTransaction();
        }
    }

    public static void closeTransaction(GenericDao dao) {
        if (DaoSettings.isSourceMySQL()) {
            ((ConnectionMySQL) dao).openCurrentSessionWithTransaction();
        }
    }


}
