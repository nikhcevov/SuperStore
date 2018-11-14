package com.ovchingus.dao.hibernate;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Product")
public class Product extends DaoConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @NaturalId
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "products")
    private Set<Store> stores = new HashSet<>();

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Set<Store> stores) {
        this.name = name;
        this.stores = stores;
    }

    public Product() {
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

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "Product: " + this.id + ", " + this.name;
    }
}
