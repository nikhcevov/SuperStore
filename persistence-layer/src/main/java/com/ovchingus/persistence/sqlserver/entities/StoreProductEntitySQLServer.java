package com.ovchingus.persistence.sqlserver.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "StoreProduct")
public class StoreProductEntitySQLServer implements Serializable {

    @EmbeddedId
    private StoreProductPK id;

    @Column(name = "price")
    private Double price;

    @Column(name = "qty")
    private Integer qty;

    @Embeddable
    public static class StoreProductPK implements Serializable {

        @Column(name = "store_id")
        private Integer storeId;

        @Column(name = "product_id")
        private Integer productId;

        public StoreProductPK() {
        }

        public StoreProductPK(Integer storeId, Integer productId) {
            this.storeId = storeId;
            this.productId = productId;
        }

        public Integer getStoreId() {
            return storeId;
        }

        public void setStoreId(Integer storeId) {
            this.storeId = storeId;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof StoreProductPK)) return false;
            StoreProductPK that = (StoreProductPK) o;
            return Objects.equals(getStoreId(), that.getStoreId()) &&
                    Objects.equals(getProductId(), that.getProductId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getStoreId(), getProductId());
        }

        @Override
        public String toString() {
            return "StoreProductPK: [" + this.storeId + "] : [" + this.productId + "]";
        }
    }

    public StoreProductEntitySQLServer(StoreProductPK id, Double price, Integer qty) {
        this.id = id;
        this.price = price;
        this.qty = qty;
    }

    public StoreProductEntitySQLServer(Double price, Integer qty) {
        this.price = price;
        this.qty = qty;
    }

    public StoreProductEntitySQLServer() {
    }

    public StoreProductPK getId() {
        return id;
    }

    public void setId(StoreProductPK id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "StoreProductEntitySQLServer: " + this.id + ", " + this.price + ", " + this.qty;
    }
}
