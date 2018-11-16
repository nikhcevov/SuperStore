package com.ovchingus.service.func;

import com.ovchingus.persistence.CSV.model.ProductEntityCSV;
import com.ovchingus.persistence.CSV.model.StoreEntityCSV;
import com.ovchingus.persistence.DaoFactory;
import com.ovchingus.persistence.GenericDao;

import java.util.List;
import java.util.Map;

public class ServiceCSV implements ServiceMethods<StoreEntityCSV, ProductEntityCSV> {

    private GenericDao daoStoreEntity = DaoFactory.getDao(StoreEntityCSV.class);

    private GenericDao daoProductEntity = DaoFactory.getDao(ProductEntityCSV.class);


    @Override
    public void createStore(StoreEntityCSV store) {

    }

    @Override
    public void createProduct(ProductEntityCSV product) {

    }

    @Override
    public List<ShopList> createShopList(List<ProductEntityCSV> list) {
        return null;
    }

    @Override
    public void insertProductListToStore(StoreEntityCSV store, ShopList list) {

    }

    @Override
    public StoreEntityCSV findStoreWithCheapestProduct(ProductEntityCSV product) {
        return null;
    }

    @Override
    public Map<ProductEntityCSV, Integer> findProductListForSum(Double budget) {
        return null;
    }

    @Override
    public Integer buyListOfProductsInOneStore(StoreEntityCSV store, Map<ProductEntityCSV, Integer> map) {
        return null;
    }

    @Override
    public StoreEntityCSV findStoreWithCheapestShopList(Map<ProductEntityCSV, Integer> map) {
        return null;
    }
}
