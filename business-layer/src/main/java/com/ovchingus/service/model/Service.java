package com.ovchingus.service.model;

import com.ovchingus.service.ServiceMethods;
import com.ovchingus.service.Settings;

import java.util.Map;

public class Service implements ServiceMethods {


    private ServiceCSV serviceCSV;
    private ServiceMySQL serviceMySQL;

    public Service() {
    }


    @SuppressWarnings("unchecked")
    private <T extends ServiceMethods> T getSource() {
        if (Settings.isSourceMySQL()) {
            if (serviceMySQL == null)
                serviceMySQL = new ServiceMySQL();
            return (T) serviceMySQL;
        }
        if (Settings.isSourceCSV()) {
            if (serviceCSV == null)
                serviceCSV = new ServiceCSV();
            return (T) serviceCSV;
        }
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
        return getSource().findStoreWithCheapestProduct(productName);
    }

    @Override
    public Map<String, Integer> findProductListForSum(String storeName, Double budget) {
        return getSource().findProductListForSum(storeName, budget);
    }

    @Override
    public Integer buyProductsInOneStore(String storeName, String productName, Integer qty) {
        return getSource().buyProductsInOneStore(storeName, productName, qty);
    }

    @Override
    public String findStoreWithCheapestShopList(String query) {
        return getSource().findStoreWithCheapestShopList(query);
    }


}
