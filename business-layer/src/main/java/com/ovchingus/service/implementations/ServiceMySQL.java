package com.ovchingus.service.implementations;

import com.ovchingus.persistence.DaoFactory;
import com.ovchingus.persistence.GenericDao;
import com.ovchingus.persistence.sqlserver.dao.ConnectionSQLServer;
import com.ovchingus.persistence.sqlserver.dao.StoreProductDaoSQLServer;
import com.ovchingus.persistence.sqlserver.entities.ProductEntitySQLServer;
import com.ovchingus.persistence.sqlserver.entities.StoreEntitySQLServer;
import com.ovchingus.persistence.sqlserver.entities.StoreProductEntitySQLServer;
import com.ovchingus.service.ServiceMethods;

import java.util.Map;

public class ServiceMySQL implements ServiceMethods {

    private GenericDao daoProductEntity = DaoFactory.getDao(ProductEntitySQLServer.class);
    private GenericDao daoStoreEntity = DaoFactory.getDao(StoreEntitySQLServer.class);
    private GenericDao daoStoreProductEntity = DaoFactory.getDao(StoreProductEntitySQLServer.class);

    public GenericDao getConnection() {
        return connection;
    }

    private GenericDao connection = DaoFactory.getDao(ConnectionSQLServer.class);

    @Override
    @SuppressWarnings("unchecked")
    public boolean createStore(Integer storeId, String name, String address) {
        StoreEntitySQLServer store = new StoreEntitySQLServer();

        store.setName(name);
        store.setAddress(address);
        daoStoreEntity.openCurrentSession();
        boolean condition = daoStoreEntity.persist(store);
        daoStoreEntity.closeCurrentSession();
        return condition;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean createProduct(Integer productId, String name) {
        ProductEntitySQLServer prod = new ProductEntitySQLServer();

        if (productId != null)
            prod.setId(productId);
        prod.setName(name);
        daoProductEntity.openCurrentSession();
        boolean condition = daoProductEntity.persist(prod);
        daoProductEntity.closeCurrentSession();
        return condition;
    }

    /*
        @Override
        public ShopItem createShopItem(String storeName, String productName, Integer qty, Double price) {
            return null;
        }
    */
    @Override
    @SuppressWarnings("unchecked")
    public boolean insertProductToStore(String storeName, String productName, Integer qty, Double price) {
        StoreEntitySQLServer store;
        ProductEntitySQLServer product;
        boolean result;
        StoreProductDaoSQLServer dao = new StoreProductDaoSQLServer();

        daoStoreEntity.openCurrentSession();
        store = (StoreEntitySQLServer) daoStoreEntity.findByName(storeName);
        daoStoreEntity.closeCurrentSession();

        daoProductEntity.openCurrentSession();
        product = (ProductEntitySQLServer) daoProductEntity.findByName(productName);
        daoProductEntity.closeCurrentSession();

        //daoStoreProductEntity.openCurrentSession();
        if (store != null && product != null) {
            StoreProductEntitySQLServer sp = new StoreProductEntitySQLServer();
            StoreProductEntitySQLServer.StoreProductPK spPK = new StoreProductEntitySQLServer.StoreProductPK();
            spPK.setStoreId(store.getId());
            spPK.setProductId(product.getId());
            sp.setId(spPK);
            sp.setQty(qty);
            sp.setPrice(price);
            dao.openCurrentSessionWithTransaction();
            result = dao.persist(sp);//daoStoreProductEntity.persist(sp);
            dao.closeCurrentSessionWithTransaction();
        } else result = false;
        //daoStoreProductEntity.closeCurrentSession();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean updateProduct(String storeName, String productName, Integer qty, Double price) {
        StoreEntitySQLServer store;
        ProductEntitySQLServer product;
        boolean result;
        StoreProductDaoSQLServer dao = new StoreProductDaoSQLServer();

        daoStoreEntity.openCurrentSession();
        store = (StoreEntitySQLServer) daoStoreEntity.findByName(storeName);
        daoStoreEntity.closeCurrentSession();

        daoProductEntity.openCurrentSession();
        product = (ProductEntitySQLServer) daoProductEntity.findByName(productName);
        daoProductEntity.closeCurrentSession();

        //daoStoreProductEntity.openCurrentSession();
        if (store != null && product != null) {
            StoreProductEntitySQLServer sp = new StoreProductEntitySQLServer();
            StoreProductEntitySQLServer.StoreProductPK spPK = new StoreProductEntitySQLServer.StoreProductPK();
            spPK.setStoreId(store.getId());
            spPK.setProductId(product.getId());
            sp.setId(spPK);
            sp.setQty(qty);
            sp.setPrice(price);
            dao.openCurrentSessionWithTransaction();

            result = dao.update(sp);//daoStoreProductEntity.persist(sp);
            dao.closeCurrentSessionWithTransaction();
        } else result = false;
        //daoStoreProductEntity.closeCurrentSession();
        return result;
    }

    @Override
    public String findStoreWithCheapestProduct(String productName) {
        return null;
    }

    @Override
    public Map<String, Integer> findProductListForSum(String storeName, Double budget) {
        return null;
    }

    @Override
    public Double buyProductsInOneStore(String storeName, String productName, Integer qty) {
        return null;
    }

    @Override
    public String findStoreWithCheapestShopList(String query) {
        return null;
    }
}