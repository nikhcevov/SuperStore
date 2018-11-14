package com.ovchingus.dao.hibernate;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Store")
public class Store extends DaoConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer id;

    @NaturalId
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToMany(cascade = {CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
})
    @JoinTable(
            name = "Store_Product",
            joinColumns = {@JoinColumn(name = "store_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Product> products = new HashSet<>();

    public Store(String name, String address, Set<Product> products) {
        this.name = name;
        this.address = address;
        this.products = products;
    }

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Store() {
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Store: " + this.id + ", " + this.name + ", " + this.address;
    }
}
