package com.ovchingus.service.implementations;

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
        ProductEntityCSV prod = (ProductEntityCSV) daoProductEntity.findByName(productName);

        List<ProductInfo> list = prod.getProducts();
        ProductInfo pi = new ProductInfo();
        pi.setStoreId(temp.getId());
        pi.setQty(qty);
        pi.setPrice(price);

        // check existence of adding element by storeID
        for (ProductInfo item : list) {
            if (item.getStoreId().equals(pi.getStoreId()))
                return false;
        }
        list.add(pi);
        prod.setProducts(list);
        return daoProductEntity.update(prod);


    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean updateProduct(String storeName, String productName, Integer qty, Double price) {
        ProductEntityCSV temp = (ProductEntityCSV) daoProductEntity.findByName(productName);
        StoreEntityCSV store = (StoreEntityCSV) daoStoreEntity.findByName(storeName);
        List<ProductInfo> list = new ArrayList<>(temp.getProducts());

        if (temp != null || store != null) {
            for (ProductInfo item : list)
                if (item.getStoreId().equals(store.getId())) {
                    item.setPrice(price);
                    item.setQty(qty);
                }

            ProductEntityCSV out = new ProductEntityCSV();
            out.setProducts(list);
            out.setName(productName);
            out.setId(temp.getId());

            return daoProductEntity.update(out);
        } else return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String findStoreWithCheapestProduct(String productName) {
        ProductEntityCSV temp = (ProductEntityCSV) daoProductEntity.findByName(productName);
        List<ProductInfo> list = temp.getProducts();

        if (temp != null && !list.isEmpty()) {
            ProductInfo out = null;
            Double min = 0.0;
            if (list.get(0).getPrice() >= 0) {
                min = list.get(0).getPrice();
            }
            for (ProductInfo item : list) {
                if (item.getPrice() <= min) {
                    min = item.getPrice();
                    out = new ProductInfo(item.getStoreId(),
                            item.getQty(), item.getPrice());
                }
            }
            StoreEntityCSV ans = (StoreEntityCSV) daoStoreEntity.findById(out.getStoreId());
            return ans.getName();
        } else return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Integer> findProductListForSum(String storeName, Double budget) {
        List<ProductEntityCSV> products = daoProductEntity.findAll();
        int counter = 0;
        Map<String, Integer> map = new HashMap<>();

        for (ProductEntityCSV item : products) {
            for (ProductInfo it : item.getProducts()) {
                if (it.getPrice() <= budget) {
                    counter = (int) (budget / it.getPrice());
                    map.put(item.getName(), counter);
                }
            }
        }
        return map;
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
