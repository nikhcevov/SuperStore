package com.ovchingus.service.func.model;

import com.ovchingus.service.func.ServiceMethods;

import java.util.Map;

public class ServiceMySQL implements ServiceMethods {


    @Override
    public void createStore(Integer storeId, String name, String address) {

    }

    @Override
    public void createProduct(Integer productId, String name) {

    }

    /*
        @Override
        public ShopItem createShopItem(String storeName, String productName, Integer qty, Double price) {
            return null;
        }
    */
    @Override
    public void insertProductToStore(String storeName, String productName, Integer qty, Double price) {

    }

    @Override
    public void updateProduct(String storeName, String productName, Integer qty, Double price) {

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
    public Integer buyProductsInOneStore(String storeName, String productName, Integer qty) {
        return null;
    }

    @Override
    public String findStoreWithCheapestShopList(String query) {
        return null;
    }
}