package com.ovchingus.service.func;


import java.util.List;
import java.util.Map;

public interface ServiceMethods {

    /**
     * Создать магазин
     */
    public void createStore(String store);

    /**
     * Создать товар
     */
    public void createProduct(String product);

    /**
     * Создать список товаров из одьектов ProductJdbc
     */
    public List<String> createShopList(List<String> list);

    /**
     * Завезти партию товаров в магазин (набор товар-количество
     * с возможностью установить/изменить цену
     */
    public void insertProductListToStore(String store, String list);

    /**
     * Найти магазин, в котором определенный товар самый дешевый
     */
    public String findStoreWithCheapestProduct(String product);

    /**
     * Понять, какие товары можно купить в магазине на некоторую
     * сумму (например, на 100 рублей можно купить три мороженки
     * или две шоколадки) я так понял тут про уникальный товар а
     * не про набор разных
     */
    public Map<String, Integer> findProductListForSum(Double budget);

    /**
     * Купить партию товаров в магазине (параметры - сколько
     * каких товаров купить, метод возвращает общую стоимость
     * покупки либо её невозможность, если товара не хватает)
     */
    public Integer buyListOfProductsInOneStore(String store, Map<String, Integer> map);

    /**
     * Найти, в каком магазине партия товаров (набор товар-количество) имеет
     * наименьшую сумму (в целом). Например, «в каком магазине дешевле всего купить
     * 10 гвоздей и 20 шурупов». Наличие товара в магазинах учитывается!
     */
    public String findStoreWithCheapestShopList(Map<String, Integer> map);

}
