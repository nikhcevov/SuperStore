package com.ovchingus.service.func.model;

import com.ovchingus.service.func.ServiceMethods;

import java.util.List;
import java.util.Map;

public class ServiceMySQL implements ServiceMethods {

    @Override
    public void createStore(String store) {

    }

    @Override
    public void createProduct(String product) {

    }

    @Override
    public List<String> createShopList(List<String> list) {
        return null;
    }

    @Override
    public void insertProductListToStore(String store, String list) {

    }

    @Override
    public String findStoreWithCheapestProduct(String product) {
        return null;
    }

    @Override
    public Map<String, Integer> findProductListForSum(Double budget) {
        return null;
    }

    @Override
    public Integer buyListOfProductsInOneStore(String store, Map<String, Integer> map) {
        return null;
    }

    @Override
    public String findStoreWithCheapestShopList(Map<String, Integer> map) {
        return null;
    }
}
