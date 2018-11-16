package com.ovchingus.persistence.CSV.model;

public class ProductInfo {
    private Integer id;
    private Integer qty;
    private Double price;

    public ProductInfo(Integer id, Integer qty, Double price) {
        this.id = id;
        this.qty = qty;
        this.price = price;
    }

    public ProductInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
