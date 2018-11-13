package com.ovchingus.service.func;



import com.ovchingus.service.model.Product;
import com.ovchingus.service.model.ShopListItem;
import com.ovchingus.service.model.Store;

import java.util.List;

/**
 *
 *
 * @param <T> Обьект - сущность (например таблица бд или файл)
 * @param <MT> Обьект - тип используемых денег
 */
public interface ServiceMethodsInterface<T, MT> {

    /**
     * Создать магазин
     */
    public void createStore(Store store);

    /**
     * Создать товар
     */
    public void createProduct(Product product);

    /**
     * Завезти партию товаров в магазин (набор товар-количество
     * с возможностью установить/изменить цену
     */
    public void insertProductListToStore(List<Product> list);

    /**
     * Найти магазин, в котором определенный товар самый дешевый
     */
    public Store findStoreWithCheapestProduct(Product product);

    /**
     * Понять, какие товары можно купить в магазине на некоторую
     * сумму (например, на 100 рублей можно купить три мороженки
     * или две шоколадки) я так понял тут про уникальный товар а
     * не про набор разных
     */
    public List<Product> findProductListForSum(Double budget);

    /**
     * Купить партию товаров в магазине (параметры - сколько
     * каких товаров купить, метод возвращает общую стоимость
     * покупки либо её невозможность, если товара не хватает)
     */
    public Integer buyListOfProductsInOneStore(Store store, List<ShopListItem> shopList);





}
