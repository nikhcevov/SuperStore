package com.ovchingus.service.func.model;

import com.ovchingus.persistence.CSV.entities.ProductEntityCSV;
import com.ovchingus.persistence.CSV.entities.ProductInfo;
import com.ovchingus.persistence.CSV.entities.StoreEntityCSV;
import com.ovchingus.persistence.DaoFactory;
import com.ovchingus.persistence.GenericDao;
import com.ovchingus.service.func.ServiceMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCSV implements ServiceMethods {

    private GenericDao daoStoreEntity = DaoFactory.getDao(StoreEntityCSV.class);

    private GenericDao daoProductEntity = DaoFactory.getDao(ProductEntityCSV.class);

    @Override
    public void createStore(Integer storeId, String name, String address) {
        StoreEntityCSV storeEntityCSV = new StoreEntityCSV();
        storeEntityCSV.setId(storeId);
        storeEntityCSV.setName(name);
        storeEntityCSV.setAddress(address);
        daoStoreEntity.persist(storeEntityCSV);
    }

    @Override
    public void createProduct(Integer productId, String name) {
        ProductEntityCSV productEntityCSV = new ProductEntityCSV();
        productEntityCSV.setId(productId);
        productEntityCSV.setName(name);
        daoProductEntity.persist(productEntityCSV);
    }

    /*
        @Override
        public ShopItem createShopItem(String storeName, String productName, Integer qty, Double price) {

        }
    */
    @Override
    public void insertProductToStore(String storeName, String productName, Integer qty, Double price) {
        StoreEntityCSV temp = (StoreEntityCSV) daoStoreEntity.findByName(storeName);
        ProductEntityCSV prod = (ProductEntityCSV) daoProductEntity.findByName(storeName);

        List<ProductInfo> list = prod.getProducts();
        ProductInfo pi = new ProductInfo();
        pi.setStoreId(temp.getId());
        pi.setQty(qty);
        pi.setPrice(price);

        list.add(pi);
        prod.setProducts(list);
        daoProductEntity.update(prod);


    }

    @Override
    public void updateProduct(String storeName, String productName, Integer qty, Double price) {
        ProductInfo productInfo = new ProductInfo();
        ProductEntityCSV temp = (ProductEntityCSV) daoProductEntity.findByName(productName);
        List<ProductInfo> list = new ArrayList<>();

        productInfo.setStoreId(temp.getId());
        productInfo.getStoreId();
        productInfo.setQty(qty);
        productInfo.setPrice(price);
        list.add(productInfo);

        ProductEntityCSV out = new ProductEntityCSV();
        out.setProducts(list);
        out.setName(productName);
        out.setId(temp.getId());

        daoProductEntity.update(out);
    }

    @Override
    public String findStoreWithCheapestProduct(String productName) {
        ProductEntityCSV temp = (ProductEntityCSV) daoProductEntity.findByName(productName);

        List<ProductInfo> list = temp.getProducts();
        ProductInfo out = new ProductInfo();
        if (list == null) {
            return null;
        }

        Double min = 0.0;

        if (list.get(0).getPrice() >= 0) {
            min = list.get(0).getPrice();
        }
        for (ProductInfo item : list) {
            if (item.getPrice() < min) {
                min = item.getPrice();
                out = item;
            }
        }

        StoreEntityCSV ans = new StoreEntityCSV();
        ans = (StoreEntityCSV) daoStoreEntity.findById(out.getStoreId());
        return ans.getName();
    }

    @Override
    public Map<String, Integer> findProductListForSum(String storeName, Double budget) {
        Map<String, Integer> map = new HashMap<>();
        List<StoreEntityCSV> temp = daoStoreEntity.findAll();
        List<String> storeNames = new ArrayList<>();
        List<ProductEntityCSV> products = daoProductEntity.findAll();

        for (StoreEntityCSV item : temp) {
            storeNames.add(item.getName());
        }
        /*
        for (ProductEntityCSV item : products) {
            for (item.getProducts())
        }
*/
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
