package com.ovchingus.service.model;

public class ShopListItem {
    private Product product;
    private Double price;
    private Integer qty;

    public ShopListItem(Product product, Double price, Integer qty) {
        this.product = product;
        this.price = price;
        this.qty = qty;
    }

    public ShopListItem() {
    }

    public ShopListItem(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
