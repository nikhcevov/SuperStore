package com.ovchingus.service.func.model;

import com.ovchingus.service.func.ServiceCSV;
import com.ovchingus.service.func.ServiceMethods;
import com.ovchingus.service.func.Settings;

import java.util.List;
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
    public void createStore(String store) {
        if (Settings.isSourceMySQL())
            serviceMySQL.createStore(store);
        if (Settings.isSourceCSV())
            serviceCSV.createStore(store);
    }

    @Override
    public void createProduct(String product) {
        if (Settings.isSourceMySQL())
            serviceMySQL.createProduct(product);
        if (Settings.isSourceCSV())
            serviceCSV.createProduct(product);
    }

    @Override
    public List<String> createShopList(List<String> list) {
        if (Settings.isSourceMySQL())
            return serviceMySQL.createShopList(list);
        if (Settings.isSourceCSV())
            return serviceCSV.createShopList(list);
        return null;
    }

    @Override
    public void insertProductListToStore(String store, String list) {
        if (Settings.isSourceMySQL())
            serviceMySQL.insertProductListToStore(store, list);
        if (Settings.isSourceCSV())
            serviceCSV.insertProductListToStore(store, list);
    }

    @Override
    public String findStoreWithCheapestProduct(String product) {
        if (Settings.isSourceMySQL())
            return serviceMySQL.findStoreWithCheapestProduct(product);
        if (Settings.isSourceCSV())
            return serviceCSV.findStoreWithCheapestProduct(product);
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
    public String findStoreWithCheapestShopList(Map<String, Integer> map) {
        if (Settings.isSourceMySQL())
            return serviceMySQL.findStoreWithCheapestShopList(map);
        if (Settings.isSourceCSV())
            return serviceCSV.findStoreWithCheapestShopList(map);
        return null;
    }

    @Override
    public Integer buyListOfProductsInOneStore(String store, Map<String, Integer> map) {
        if (Settings.isSourceMySQL())
            return serviceMySQL.buyListOfProductsInOneStore(store, map);
        if (Settings.isSourceCSV())
            return serviceCSV.buyListOfProductsInOneStore(store, map);
        return null;
    }
}
