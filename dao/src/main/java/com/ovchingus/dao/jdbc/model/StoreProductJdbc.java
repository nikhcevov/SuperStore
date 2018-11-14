package com.ovchingus.dao.jdbc.model;


import com.ovchingus.dao.KeyDb;
import com.ovchingus.dao.jdbc.Identified;

public class StoreProductJdbc implements Identified<KeyDb> {

    private Integer storeId;
    private Integer productId;
    private Double price;
    private Integer qty;

    public StoreProductJdbc() {
    }

    public StoreProductJdbc(Integer productId, Double price, Integer qty) {
        this.productId = productId;
        this.price = price;
        this.qty = qty;
    }

    public StoreProductJdbc(Integer storeId, Integer productId, Double price, Integer qty) {
        this.storeId = storeId;
        this.productId = productId;
        this.price = price;
        this.qty = qty;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public KeyDb identifyKey() {
        return new KeyDb(storeId,productId);
    }
}
