package com.ovchingus.persistence.MySQL.entities;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Store")
public class StoreEntityMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer id;

    @NaturalId(mutable = true)
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;


    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "Store_Product",
            joinColumns = {@JoinColumn(name = "store_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<ProductEntityMySQL> productEntitiesMySQL = new HashSet<>();

    public StoreEntityMySQL(String name, String address, Set<ProductEntityMySQL> productEntitiesMySQL) {
        this.name = name;
        this.address = address;
        this.productEntitiesMySQL = productEntitiesMySQL;
    }

    public StoreEntityMySQL(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public StoreEntityMySQL() {
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

    public Set<ProductEntityMySQL> getProductEntitiesMySQL() {
        return productEntitiesMySQL;
    }

    public void setProductEntitiesMySQL(Set<ProductEntityMySQL> productEntities) {
        this.productEntitiesMySQL = productEntities;
    }

    @Override
    public String toString() {
        return "StoreEntityMySQL: " + this.id + ", " + this.name + ", " + this.address;
    }
}
