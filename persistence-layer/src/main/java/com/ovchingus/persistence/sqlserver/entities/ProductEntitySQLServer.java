package com.ovchingus.persistence.sqlserver.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Product")
public class ProductEntitySQLServer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;


    @NaturalId(mutable = true)
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "productEntitiesMySQL")
    private Set<StoreEntitySQLServer> storeEntitiesMySQL = new HashSet<>();

    public ProductEntitySQLServer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductEntitySQLServer(String name) {
        this.name = name;
    }

    public ProductEntitySQLServer(String name, Set<StoreEntitySQLServer> storeEntitiesMySQL) {
        this.name = name;
        this.storeEntitiesMySQL = storeEntitiesMySQL;
    }

    public ProductEntitySQLServer() {
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

    public Set<StoreEntitySQLServer> getStoreEntitiesMySQL() {
        return storeEntitiesMySQL;
    }

    public void setStoreEntitiesMySQL(Set<StoreEntitySQLServer> mySQLStoreEntities) {
        this.storeEntitiesMySQL = mySQLStoreEntities;
    }

    @Override
    public String toString() {
        return "ProductEntitySQLServer: " + this.id + ", " + this.name;
    }
}
