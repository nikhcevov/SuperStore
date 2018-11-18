package com.ovchingus.service.model;

public class ShopItem {
    private Integer productId;

    private Integer qty;

    private Integer storeId;

    private Double price;

    public ShopItem(Integer productId, Integer qty, Integer storeId, Double price) {
        this.productId = productId;
        this.qty = qty;
        this.storeId = storeId;
        this.price = price;
    }

    public ShopItem() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
