package com.ovchingus.service.func;


import com.ovchingus.service.model.Product;
import com.ovchingus.service.model.ShopList;
import com.ovchingus.service.model.Store;

import java.util.List;
import java.util.Map;

public class ServiceMethods {

    /**
     * Создать магазин
     */
    public void createStore(Store store) {

    }

    /**
     * Создать товар
     */
    public void createProduct(Product product) {

    }

    /**
     * Создать список товаров из одьектов Product
     */
    public ShopList createShopList(List<Product> list) {
        return null;
    }

    /**
     * Завезти партию товаров в магазин (набор товар-количество
     * с возможностью установить/изменить цену
     */
    public void insertProductListToStore(Store store, ShopList list) {

    }

    /**
     * Найти магазин, в котором определенный товар самый дешевый
     */
    public Store findStoreWithCheapestProduct(Product product) {
        return null;
    }

    /**
     * Понять, какие товары можно купить в магазине на некоторую
     * сумму (например, на 100 рублей можно купить три мороженки
     * или две шоколадки) я так понял тут про уникальный товар а
     * не про набор разных
     */
    public Map<Product, Integer> findProductListForSum(Double budget) {
        return null;
    }

    /**
     * Купить партию товаров в магазине (параметры - сколько
     * каких товаров купить, метод возвращает общую стоимость
     * покупки либо её невозможность, если товара не хватает)
     */
    public Integer buyListOfProductsInOneStore(Store store, Map<Product, Integer> map) {
        return null;
    }

    /**
     * Найти, в каком магазине партия товаров (набор товар-количество) имеет
     * наименьшую сумму (в целом). Например, «в каком магазине дешевле всего купить
     * 10 гвоздей и 20 шурупов». Наличие товара в магазинах учитывается!
     */
    public Store findStoreWithCheapestShopList(Map<Product, Integer> map) {
        return null;
    }

}
