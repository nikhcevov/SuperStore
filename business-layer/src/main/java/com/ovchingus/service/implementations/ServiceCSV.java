package com.ovchingus.service.implementations;

import com.ovchingus.persistence.DaoFactory;
import com.ovchingus.persistence.GenericDao;
import com.ovchingus.persistence.csv.entities.ProductEntityCSV;
import com.ovchingus.persistence.csv.entities.ProductInfo;
import com.ovchingus.persistence.csv.entities.StoreEntityCSV;
import com.ovchingus.service.ServiceMethods;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

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

        if (temp != null && prod != null) {
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
        } else return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean updateProduct(String storeName, String productName, Integer qty, Double price) {
        ProductEntityCSV temp = (ProductEntityCSV) daoProductEntity.findByName(productName);
        StoreEntityCSV store = (StoreEntityCSV) daoStoreEntity.findByName(storeName);

        if (temp != null && store != null) {
            List<ProductInfo> list = new ArrayList<>(temp.getProducts());
            if (!list.isEmpty()) {
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
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String findStoreWithCheapestProduct(String productName) {
        ProductEntityCSV temp = (ProductEntityCSV) daoProductEntity.findByName(productName);

        if (temp != null) {
            List<ProductInfo> list = temp.getProducts();
            if (!list.isEmpty()) {
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
                if (out != null) {
                    StoreEntityCSV ans = (StoreEntityCSV) daoStoreEntity.findById(out.getStoreId());
                    return ans.getName();
                }
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Integer> findProductListForSum(String storeName, Double budget) {
        List<ProductEntityCSV> products = daoProductEntity.findAll();
        int counter;
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
    public Double buyProductsInOneStore(String storeName, String productName, Integer qty) {
        StoreEntityCSV store = (StoreEntityCSV) daoStoreEntity.findByName(storeName);
        ProductEntityCSV product = (ProductEntityCSV) daoProductEntity.findByName(productName);
        Double sum = null;

        if (store != null && product != null) {
            for (ProductInfo item : product.getProducts()) {
                if (item.getStoreId().equals(store.getId()) && item.getQty() >= qty) {
                    item.setQty(item.getQty() - qty);
                    sum = item.getPrice() * qty;
                    updateProduct(storeName, productName, item.getQty(), item.getPrice());
                }
            }
        }
        return sum;
    }


    /**
     * ATTENTION !!!!!!!!!!!! DON`T LOOK INSIDE
     */
    @Override
    @SuppressWarnings("unchecked")
    public String findStoreWithCheapestShopList(String query) {
        Map<ProductEntityCSV, Integer> qtyForProd = new HashMap<>();
        MultiMap storesWithProdList = new MultiValueMap();
        int counterOfNecessaryProductTypes = 0;

        // find products and pull them to map of ProductEntity and qty
        // A.K. parce query
        for (String item : query.split(";")) {
            String name = item.substring(0, item.indexOf(","));
            Integer qty = Integer.parseInt(item.substring(item.indexOf(",") + 1));
            ProductEntityCSV prod = (ProductEntityCSV) daoProductEntity.findByName(name);
            if (prod != null) {
                qtyForProd.put(prod, qty);
                // pull all products from query to Map of [StoreID,list<products>]
                for (ProductInfo pi : prod.getProducts()) {
                    storesWithProdList.put(pi.getStoreId(), prod);
                }
                counterOfNecessaryProductTypes++;
            } else return null;
        }
        Map<Integer, Double> storeIdAndSum = new HashMap<>();
        // each entry is one store
        for (Object entryObj : storesWithProdList.entrySet()) {
            Map.Entry entry = (Map.Entry) entryObj;
            Integer curStoreID = (Integer) entry.getKey();
            List<ProductEntityCSV> curProdList = (List<ProductEntityCSV>) entry.getValue();
            //sum of query in entry
            double entrySum = 0.0;
            //sum found for count of products.
            // if < then counterOfNecessaryProducts then not all products in store exists.
            int countOfProductsTypes = 0;

            // for each product in store
            for (ProductEntityCSV item : curProdList) {
                // for the first find productInfo for curStoreId
                for (ProductInfo it : item.getProducts()) {
                    if (it.getStoreId().equals(curStoreID)) {
                        // find necessary qty of product
                        Integer qty = qtyForProd.get(item);

                        if (it.getQty() >= qty) {
                            entrySum += it.getPrice() * qty;
                            countOfProductsTypes++;
                        }
                    }
                }
            }
            // that mean that all of querring products exist in one store
            if (countOfProductsTypes == counterOfNecessaryProductTypes)
                storeIdAndSum.put(curStoreID, entrySum);
        }

        // step3: find stores with cheapest sum
        Double minSum = Double.MAX_VALUE;
        Integer minSumStoreID = null;
        StoreEntityCSV out;
        for (Map.Entry<Integer, Double> item : storeIdAndSum.entrySet()) {
            if (minSum >= item.getValue()) {
                minSum = item.getValue();
                minSumStoreID = item.getKey();
            }
        }
        out = (StoreEntityCSV) daoStoreEntity.findById(minSumStoreID);
        if (out != null)
            return out.getName();
        else return null;
    }
}
