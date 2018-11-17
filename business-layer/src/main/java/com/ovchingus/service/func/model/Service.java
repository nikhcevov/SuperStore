package com.ovchingus.service.func.model;

import com.ovchingus.service.func.ServiceCSV;
import com.ovchingus.service.func.ServiceMethods;
import com.ovchingus.service.func.Settings;

import java.util.Map;

public class Service implements ServiceMethods {


    private ServiceCSV serviceCSV;
    private ServiceMySQL serviceMySQL;

    public void initialize() {
        if (Settings.isSourceMySQL())
            serviceMySQL = new ServiceMySQL();
        if (Settings.isSourceCSV())
            serviceCSV = new ServiceCSV();
    }

    @Override
    public void createStore(Integer id, String name, String address) {
        if (Settings.isSourceMySQL())
            serviceMySQL.createStore(id, name, address);
        if (Settings.isSourceCSV())
            serviceCSV.createStore(id, name, address);
    }

    @Override
    public void createProduct(Integer productId, String name) {
        if (Settings.isSourceMySQL())
            serviceMySQL.createProduct(productId, name);
        if (Settings.isSourceCSV())
            serviceCSV.createProduct(productId, name);
    }

    @Override
    public ShopItem createShopItem(String storeName, String productName, Integer qty, Double price) {
        if (Settings.isSourceMySQL())
            serviceMySQL.createShopItem(storeName, productName, qty, price);
        if (Settings.isSourceCSV())
            serviceCSV.createShopItem(storeName, productName, qty, price);
        return null;
    }

    @Override
    public void insertProductToStore(String storeName, String productName, Integer qty, Double price) {
        if (Settings.isSourceMySQL())
            serviceMySQL.insertProductToStore(storeName, productName, qty, price);
        if (Settings.isSourceCSV())
            serviceCSV.insertProductToStore(storeName, productName, qty, price);
    }


    @Override
    public String findStoreWithCheapestProduct(String productName) {
        if (Settings.isSourceMySQL())
            return serviceMySQL.findStoreWithCheapestProduct(productName);
        if (Settings.isSourceCSV())
            return serviceCSV.findStoreWithCheapestProduct(productName);
        return null;
    }

    @Override
    public Map<String, Integer> findProductListForSum(Double budget) {
        if (Settings.isSourceMySQL())
            return serviceMySQL.findProductListForSum(budget);
        if (Settings.isSourceCSV())
            return serviceCSV.findProductListForSum(budget);
        return null;
    }

    @Override
    public Integer buyProductsInOneStore(String storeName, String productName, Integer qty) {
        if (Settings.isSourceMySQL())
            return serviceMySQL.buyProductsInOneStore(storeName, productName, qty);
        if (Settings.isSourceCSV())
            return serviceCSV.buyProductsInOneStore(storeName, productName, qty);
        return null;
    }

    @Override
    public String findStoreWithCheapestShopList(String query) {
        if (Settings.isSourceMySQL())
            return serviceMySQL.findStoreWithCheapestShopList(query);
        if (Settings.isSourceCSV())
            return serviceCSV.findStoreWithCheapestShopList(query);
        return null;
    }


}
