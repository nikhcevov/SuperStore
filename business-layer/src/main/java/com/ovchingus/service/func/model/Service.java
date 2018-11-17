package com.ovchingus.service.func.model;

import com.ovchingus.service.func.ServiceMethods;
import com.ovchingus.service.func.Settings;

import java.util.Map;

public abstract class Service implements ServiceMethods {


    private ServiceCSV serviceCSV;
    private ServiceMySQL serviceMySQL;

    public Service() {
        if (Settings.isSourceMySQL())
            serviceMySQL = new ServiceMySQL();
        if (Settings.isSourceCSV())
            serviceCSV = new ServiceCSV();
    }

    @SuppressWarnings("unchecked")
    private <T extends Service> T getSource() {
        if (Settings.isSourceMySQL())
            return (T) serviceMySQL;
        if (Settings.isSourceCSV())
            return (T) serviceCSV;
        return null;
    }

    @Override
    public boolean createStore(Integer id, String name, String address) {
        return getSource().createStore(id, name, address);
    }

    @Override
    public boolean createProduct(Integer productId, String name) {
        return getSource().createProduct(productId, name);
    }

    /* //TODO add support of list with products
        @Override
        public ShopItem createShopItem(String storeName, String productName, Integer qty, Double price) {
            if (Settings.isSourceMySQL())
                serviceMySQL.createShopItem(storeName, productName, qty, price);
            if (Settings.isSourceCSV())
                serviceCSV.createShopItem(storeName, productName, qty, price);
            return null;
        }
    */
    @Override
    public boolean insertProductToStore(String storeName, String productName, Integer qty, Double price) {
        return getSource().insertProductToStore(storeName, productName, qty, price);
    }

    @Override
    public boolean updateProduct(String storeName, String productName, Integer qty, Double price) {
        return getSource().updateProduct(storeName, productName, qty, price);
    }


    @Override
    public String findStoreWithCheapestProduct(String productName) {
        getSource().findStoreWithCheapestProduct(productName);
        return null;
    }

    @Override
    public Map<String, Integer> findProductListForSum(String storeName, Double budget) {
        getSource().findProductListForSum(storeName, budget);
        return null;
    }

    @Override
    public Integer buyProductsInOneStore(String storeName, String productName, Integer qty) {
        getSource().buyProductsInOneStore(storeName, productName, qty);
        return null;
    }

    @Override
    public String findStoreWithCheapestShopList(String query) {
        getSource().findStoreWithCheapestShopList(query);
        return null;
    }


}
