package com.ovchingus.persistence;

import com.ovchingus.persistence.csv.dao.ProductDaoCSV;
import com.ovchingus.persistence.csv.dao.StoreDaoCSV;
import com.ovchingus.persistence.csv.entities.ProductEntityCSV;
import com.ovchingus.persistence.csv.entities.StoreEntityCSV;
import com.ovchingus.persistence.mysql.dao.ConnectionMySQL;
import com.ovchingus.persistence.mysql.dao.ProductDaoMySQL;
import com.ovchingus.persistence.mysql.dao.StoreDaoMySQL;
import com.ovchingus.persistence.mysql.dao.StoreProductDaoMySQL;
import com.ovchingus.persistence.mysql.entities.ProductEntityMySQL;
import com.ovchingus.persistence.mysql.entities.StoreEntityMySQL;
import com.ovchingus.persistence.mysql.entities.StoreProductEntityMySQL;
import com.ovchingus.persistence.settings.DaoSettings;

//TODO: DaoFactory do not working! Need to repair it.
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
