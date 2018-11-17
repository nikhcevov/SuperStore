package com.ovchingus.service.func;

import com.ovchingus.persistence.CSV.model.ProductEntityCSV;
import com.ovchingus.persistence.CSV.model.StoreEntityCSV;
import com.ovchingus.persistence.DaoFactory;
import com.ovchingus.persistence.GenericDao;
import com.ovchingus.service.func.model.ShopItem;

import java.util.Map;

public class ServiceCSV implements ServiceMethods {

    private GenericDao daoStoreEntity = DaoFactory.getDao(StoreEntityCSV.class);

    private GenericDao daoProductEntity = DaoFactory.getDao(ProductEntityCSV.class);

    @Override
    public void createStore(Integer storeId, String name, String address) {

    }

    @Override
    public void createProduct(Integer productId, String name) {

    }

    @Override
    public ShopItem createShopItem(String storeName, String productName, Integer qty, Double price) {
        return null;
    }

    @Override
    public void insertProductToStore(String storeName, String productName, Integer qty, Double price) {

    }

    @Override
    public String findStoreWithCheapestProduct(String productName) {
        return null;
    }

    @Override
    public Map<String, Integer> findProductListForSum(Double budget) {
        return null;
    }

    @Override
    public Integer buyProductsInOneStore(String storeName, String productName, Integer qty) {
        return null;
    }

    @Override
    public String findStoreWithCheapestShopList(String query) {
        return null;
    }
}
