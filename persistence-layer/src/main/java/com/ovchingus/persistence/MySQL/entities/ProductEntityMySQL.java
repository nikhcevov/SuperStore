package com.ovchingus.persistence.MySQL.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Product")
public class ProductEntityMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;


    @NaturalId(mutable = true)
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "productEntitiesMySQL")
    private Set<StoreEntityMySQL> storeEntitiesMySQL = new HashSet<>();

    public ProductEntityMySQL(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductEntityMySQL(String name) {
        this.name = name;
    }

    public ProductEntityMySQL(String name, Set<StoreEntityMySQL> storeEntitiesMySQL) {
        this.name = name;
        this.storeEntitiesMySQL = storeEntitiesMySQL;
    }

    public ProductEntityMySQL() {
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

    public Set<StoreEntityMySQL> getStoreEntitiesMySQL() {
        return storeEntitiesMySQL;
    }

    public void setStoreEntitiesMySQL(Set<StoreEntityMySQL> mySQLStoreEntities) {
        this.storeEntitiesMySQL = mySQLStoreEntities;
    }

    @Override
    public String toString() {
        return "ProductEntityMySQL: " + this.id + ", " + this.name;
    }
}
