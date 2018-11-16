package com.ovchingus.persistence.MySQL;

import com.ovchingus.persistence.MySQL.mappings.ProductEntityMySQL;

import java.util.List;

public class ProductDaoMySQL extends ConnectionMySQL<ProductEntityMySQL, Integer> {

    public ProductDaoMySQL() {
    }

    public void persist(ProductEntityMySQL entity) {
        getCurrentSession().save(entity);
    }

    public void update(ProductEntityMySQL entity) {
        getCurrentSession().update(entity);
    }

    public ProductEntityMySQL findById(Integer id) {
        ProductEntityMySQL productEntityMySQL = getCurrentSession().get(ProductEntityMySQL.class, id);
        return productEntityMySQL;
    }

    public ProductEntityMySQL findByName(String name) {
        ProductEntityMySQL productEntityMySQL = getCurrentSession().bySimpleNaturalId(ProductEntityMySQL.class).load(name);
        return productEntityMySQL;
    }

    public void delete(ProductEntityMySQL entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<ProductEntityMySQL> findAll() {
        List<ProductEntityMySQL> list = (List<ProductEntityMySQL>) getCurrentSession().createQuery("from ProductEntityMySQL").list();
        return list;
    }

    public void deleteAll() {
        List<ProductEntityMySQL> entityList = findAll();
        for (ProductEntityMySQL entity : entityList) {
            delete(entity);
        }
    }
}
