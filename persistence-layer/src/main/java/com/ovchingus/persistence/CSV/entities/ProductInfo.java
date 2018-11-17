package com.ovchingus.persistence.CSV.entities;

public class ProductInfo {
    private Integer storeId;
    private Integer qty;
    private Double price;

    public ProductInfo(Integer id, Integer qty, Double price) {
        this.storeId = id;
        this.qty = qty;
        this.price = price;
    }

    public ProductInfo() {
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
