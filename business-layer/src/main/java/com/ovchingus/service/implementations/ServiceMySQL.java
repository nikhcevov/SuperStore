package com.ovchingus.service.implementations;

import com.ovchingus.service.ServiceMethods;

import java.util.Map;

public class ServiceMySQL implements ServiceMethods {


    @Override
    public boolean createStore(Integer storeId, String name, String address) {
        return true;
    }

    @Override
    public boolean createProduct(Integer productId, String name) {
        return true;
    }

    /*
        @Override
        public ShopItem createShopItem(String storeName, String productName, Integer qty, Double price) {
            return null;
        }
    */
    @Override
    public boolean insertProductToStore(String storeName, String productName, Integer qty, Double price) {
        return true;
    }

    @Override
    public boolean updateProduct(String storeName, String productName, Integer qty, Double price) {
        return true;
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