package com.ovchingus.persistence.csv.entities;

import java.util.List;

public class ProductEntityCSV {

    private Integer id;
    private String name;
    private List<ProductInfo> products;

    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }

    public ProductEntityCSV(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductEntityCSV() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Product - ID: " + id + ", Name: " + name);
        /* TODO fix this part
        if (!products.isEmpty()) {
            for (ProductInfo item : products)
                out.append(", [In store with ID: ").append(item.getStoreId())
                        .append(", qty: ").append(item.getQty())
                        .append(", price: ").append(item.getPrice()).append("] ");
        }*/
        return out.toString();
    }
}
