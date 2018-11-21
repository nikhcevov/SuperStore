package com.ovchingus.persistence;

import com.ovchingus.persistence.csv.dao.ProductDaoCSV;
import com.ovchingus.persistence.csv.dao.StoreDaoCSV;
import com.ovchingus.persistence.csv.entities.ProductEntityCSV;
import com.ovchingus.persistence.csv.entities.StoreEntityCSV;
import com.ovchingus.persistence.settings.DaoSettings;
import com.ovchingus.persistence.sqlserver.dao.ConnectionSQLServer;
import com.ovchingus.persistence.sqlserver.dao.ProductDaoSQLServer;
import com.ovchingus.persistence.sqlserver.dao.StoreDaoSQLServer;
import com.ovchingus.persistence.sqlserver.dao.StoreProductDaoSQLServer;
import com.ovchingus.persistence.sqlserver.entities.ProductEntitySQLServer;
import com.ovchingus.persistence.sqlserver.entities.StoreEntitySQLServer;
import com.ovchingus.persistence.sqlserver.entities.StoreProductEntitySQLServer;

//TODO: DaoFactory did wrong in terms of factory method pattern! Need to repair it.
public class DaoFactory {

    public static GenericDao getDao(Class daoType) {
        if (DaoSettings.isSourceMySQL()) {
            if (daoType == StoreEntitySQLServer.class)
                return new StoreDaoSQLServer();
            if (daoType == ProductEntitySQLServer.class)
                return new ProductDaoSQLServer();
            if (daoType == StoreProductEntitySQLServer.class)
                return new StoreProductDaoSQLServer();
            // especially bad that is need for program load time initialize connection
            if (daoType == ConnectionSQLServer.class)
                return new StoreDaoSQLServer();
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
            ((ConnectionSQLServer) dao).openCurrentSessionWithTransaction();
        }
    }

    public static void closeTransaction(GenericDao dao) {
        if (DaoSettings.isSourceMySQL()) {
            ((ConnectionSQLServer) dao).openCurrentSessionWithTransaction();
        }
    }


}
