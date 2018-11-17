package com.ovchingus.persistence.CSV.entities;

public class StoreEntityCSV {

    private Integer id;

    private String name;

    private String address;

    public StoreEntityCSV(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public StoreEntityCSV(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public StoreEntityCSV() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
