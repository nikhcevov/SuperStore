package com.ovchingus.service.model;

import com.ovchingus.persistence.DaoFactory;
import com.ovchingus.persistence.GenericDao;
import com.ovchingus.persistence.csv.entities.ProductEntityCSV;
import com.ovchingus.persistence.csv.entities.ProductInfo;
import com.ovchingus.persistence.csv.entities.StoreEntityCSV;
import com.ovchingus.service.ServiceMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCSV implements ServiceMethods {

    private GenericDao daoStoreEntity = DaoFactory.getDao(StoreEntityCSV.class);

    private GenericDao daoProductEntity = DaoFactory.getDao(ProductEntityCSV.class);

    @Override
    @SuppressWarnings("unchecked")
    public boolean createStore(Integer storeId, String name, String address) {
        return daoStoreEntity.persist(new StoreEntityCSV(storeId, name, address));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean createProduct(Integer productId, String name) {
        return daoProductEntity.persist(new ProductEntityCSV(productId, name));
    }

    /*
        @Override
        public ShopItem createShopItem(String storeName, String productName, Integer qty, Double price) {

        }
    */
    @Override
    @SuppressWarnings("unchecked")
    public boolean insertProductToStore(String storeName, String productName, Integer qty, Double price) {
        StoreEntityCSV temp = (StoreEntityCSV) daoStoreEntity.findByName(storeName);
        ProductEntityCSV prod = (ProductEntityCSV) daoProductEntity.findByName(storeName);

        List<ProductInfo> list = prod.getProducts();
        ProductInfo pi = new ProductInfo();
        pi.setStoreId(temp.getId());
        pi.setQty(qty);
        pi.setPrice(price);

        list.add(pi);
        prod.setProducts(list);
        return daoProductEntity.update(prod);


    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean updateProduct(String storeName, String productName, Integer qty, Double price) {
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

        return daoProductEntity.update(out);
    }

    @Override
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
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
