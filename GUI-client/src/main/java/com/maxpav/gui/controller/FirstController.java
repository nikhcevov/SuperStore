package com.maxpav.gui.controller;

import com.ovchingus.service.model.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.util.Map;

public class FirstController {

    private Service service = new Service();

    @FXML
    private TextField textfieldFindStoreWithCheapestShopList_ProductsAndCount;

    @FXML
    private AnchorPane anchorpane_Administrator;

    @FXML
    private TextField textfieldInsertProductToStore_ProductName;

    @FXML
    private TextField textfieldCreateProduct_ProductName;

    @FXML
    private TextField textfieldFindProductListForSum_StoreName;

    @FXML
    private TextField textfieldCreateProduct_ProductID;

    @FXML
    private Button buttonFindStoreWithCheapestProduct;

    @FXML
    private Tab tab_Administrator;

    @FXML
    private Button buttonBuyProductsInOneStore;

    @FXML
    private TextField textfieldInsertProductToStore_Price;

    @FXML
    private TextField textfieldFindProductListForSum_Price;

    @FXML
    private TextField textfieldBuyProductsInOneStore_Count;

    @FXML
    private Tab tab_Customer;

    @FXML
    private ImageView image_Customer;

    @FXML
    private TextField textfieldUpdateProduct_Price;

    @FXML
    private TextField textfieldBuyProductsInOneStore_ProductName;

    @FXML
    private TextField textfieldBuyProductsInOneStore_StoreName;

    @FXML
    private TextField textfieldInsertProductToStore_StoreName;

    @FXML
    private BorderPane borderpane_Administrator;

    @FXML
    private TextField textfieldInsertProductToStore_Count;

    @FXML
    private TextField textfieldUpdateProduct_Count;

    @FXML
    private TextField textfieldFindStoreWithCheapestProduct_ProductName;

    @FXML
    private TextField textfieldCreateStore_StoreName;

    @FXML
    private TextField textfieldUpdateProduct_StoreName;

    @FXML
    private ListView<String> listview_Administrator;

    @FXML
    private TextField textfieldUpdateProduct_ProductName;

    @FXML
    private ImageView image_Administrator;

    @FXML
    private Button buttonCreateStore;

    @FXML
    private Text console_Administrator;

    @FXML
    private Button buttonInsertProductToStore;

    @FXML
    private TextField textfieldCreateStore_StoreAdress;

    @FXML
    private Text label;

    @FXML
    private Button buttonFindProductListForSum;

    @FXML
    private ListView<String> listview_Customer;

    @FXML
    private AnchorPane anchorpane_Main;

    @FXML
    private TabPane tabpane_Main;

    @FXML
    private TextField textfieldCreateStore_StoreID;

    @FXML
    private Text console_Customer;

    @FXML
    private BorderPane borderpane_Customer;

    @FXML
    private AnchorPane anchorpane_Customer;

    @FXML
    private Button buttonCreateProduct;

    @FXML
    private Button buttonUpdateProduct;

    @FXML
    private Button buttonFindStoreWithCheapestShopList;


    @FXML
    void createProduct(ActionEvent event) {
        console_Administrator.setText("Сalculation");
        if (!textfieldCreateProduct_ProductID.getText().equals("") && !textfieldCreateProduct_ProductName.getText().equals("")) {

            //TODO: Если Boolean метод, сделать проверку.
            service.createProduct(Integer.parseInt(textfieldCreateProduct_ProductID.getText()),
                    textfieldCreateProduct_ProductName.getText());
            ObservableList<String> Items;
            Items = listview_Administrator.getItems();
            Items.add("The product " + textfieldCreateProduct_ProductName.getText() + " was created. ID: " +
                    textfieldCreateProduct_ProductID.getText());
            listview_Administrator.setItems(Items);
            console_Administrator.setText("Successful");
        } else console_Administrator.setText("Error");
    }

    @FXML
    void updateProduct(ActionEvent event) {
        console_Administrator.setText("Сalculation");
        if (!textfieldUpdateProduct_StoreName.getText().equals("") && !textfieldUpdateProduct_ProductName.getText().equals("") &&
                !textfieldUpdateProduct_Price.getText().equals("") && !textfieldUpdateProduct_Count.getText().equals("")) {

            //TODO: Если Boolean метод, сделать проверку.
            service.updateProduct(textfieldUpdateProduct_StoreName.getText(), textfieldUpdateProduct_ProductName.getText(),
                    Integer.parseInt(textfieldUpdateProduct_Count.getText()), Double.parseDouble(textfieldUpdateProduct_Price.getText()));

            ObservableList<String> Items;
            Items = listview_Administrator.getItems();
            Items.add("Now the product " + textfieldUpdateProduct_ProductName.getText() + " is sold in the store " +
                    textfieldUpdateProduct_StoreName.getText() + " in quantities " + textfieldUpdateProduct_Count.getText() +
                    " by price " + textfieldUpdateProduct_Price.getText());
            listview_Administrator.setItems(Items);
            console_Administrator.setText("Successful");
        } else console_Administrator.setText("Error");
    }

    @FXML
    void createStore(ActionEvent event) {
        console_Administrator.setText("Сalculation");
        if (!textfieldCreateStore_StoreID.getText().equals("") && !textfieldCreateStore_StoreName.getText().equals("") &&
                !textfieldCreateStore_StoreAdress.getText().equals("")) {

            //TODO: Если Boolean метод, сделать проверку.
            service.createStore(Integer.parseInt(textfieldCreateStore_StoreID.getText()),
                    textfieldCreateStore_StoreName.getText(), textfieldCreateStore_StoreAdress.getText());
            ObservableList<String> Items;
            Items = listview_Administrator.getItems();
            Items.add("The store " + textfieldCreateStore_StoreName.getText() + ", " + textfieldCreateStore_StoreAdress.getText() +
                    " was created. ID: " + textfieldCreateStore_StoreID.getText());
            listview_Administrator.setItems(Items);
            console_Administrator.setText("Successful");
        } else console_Administrator.setText("Error");
    }

    @FXML
    void insertProductToStore(ActionEvent event) {
        console_Administrator.setText("Сalculation");
        if (!textfieldInsertProductToStore_StoreName.getText().equals("") &&
                !textfieldInsertProductToStore_ProductName.getText().equals("") &&
                !textfieldInsertProductToStore_Price.getText().equals("") &&
                !textfieldInsertProductToStore_Count.getText().equals("")) {

            //TODO: Если Boolean метод, сделать проверку.
            service.insertProductToStore(textfieldInsertProductToStore_StoreName.getText(),
                    textfieldInsertProductToStore_ProductName.getText(),
                    Integer.parseInt(textfieldInsertProductToStore_Count.getText()),
                    Double.parseDouble(textfieldInsertProductToStore_Price.getText()));

            ObservableList<String> Items;
            Items = listview_Administrator.getItems();
            Items.add("Now the product + " + textfieldInsertProductToStore_ProductName.getText() + " is sold in the store " +
                    textfieldInsertProductToStore_StoreName.getText() + " in quantities " +
                    textfieldInsertProductToStore_Count.getText() + " by price " + textfieldInsertProductToStore_Price.getText());

            listview_Administrator.setItems(Items);
            console_Administrator.setText("Successful");
        } else console_Administrator.setText("Error");
    }

    @FXML
    void findStoreWithCheapestProduct(ActionEvent event) {
        console_Customer.setText("Сalculation");
        if (!textfieldFindStoreWithCheapestProduct_ProductName.getText().equals("")) {


            //TODO: Если Boolean метод, сделать проверку.
            ObservableList<String> Items = FXCollections.observableArrayList();
            Items.add(service.findStoreWithCheapestProduct(textfieldFindStoreWithCheapestProduct_ProductName.getText()));
            listview_Customer.setItems(Items);
            console_Customer.setText("Successful");
        } else console_Customer.setText("Error");
    }

    @FXML
    void findProductListForSum(ActionEvent event) {
        console_Customer.setText("Сalculation");
        if (!textfieldFindProductListForSum_StoreName.getText().equals("") &&
                !textfieldFindProductListForSum_Price.getText().equals("")) {


            //TODO: Если Boolean метод, сделать проверку.
            Map<String, Integer> map = service.findProductListForSum(textfieldFindProductListForSum_StoreName.getText(),
                    Double.parseDouble(textfieldFindProductListForSum_Price.getText()));
            ObservableList<String> Items = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> pair : map.entrySet()) {
                Items.add(pair.getKey() + pair.getValue());
            }
            listview_Customer.setItems(Items);
            console_Customer.setText("Successful");
        } else console_Customer.setText("Error");
    }


    @FXML
    void buyProductsInOneStore(ActionEvent event) {
        console_Customer.setText("Сalculation");
        if (!textfieldBuyProductsInOneStore_StoreName.getText().equals("") &&
                !textfieldBuyProductsInOneStore_ProductName.getText().equals("") &&
                !textfieldBuyProductsInOneStore_Count.getText().equals("")) {
            ObservableList<String> Items = FXCollections.observableArrayList();

            //TODO: Если Boolean метод, сделать проверку.
            Integer integer = service.buyProductsInOneStore(textfieldBuyProductsInOneStore_StoreName.getText(),
                    textfieldBuyProductsInOneStore_ProductName.getText(),
                    Integer.parseInt(textfieldBuyProductsInOneStore_Count.getText()));
            Items.add("Sum = " + integer + " RUB");
            listview_Customer.setItems(Items);
            console_Customer.setText("Successful");
        } else console_Customer.setText("Error");
    }

    @FXML
    void findStoreWithCheapestShopList(ActionEvent event) {
        console_Customer.setText("Сalculation");
        if (!textfieldFindStoreWithCheapestShopList_ProductsAndCount.getText().equals("")) {
            ObservableList<String> Items = FXCollections.observableArrayList();

            //TODO: Если Boolean метод, сделать проверку.
            Items.add(service.findStoreWithCheapestShopList(textfieldFindStoreWithCheapestShopList_ProductsAndCount.getText()));
            listview_Administrator.setItems(Items);
            console_Customer.setText("Successful");
        } else console_Customer.setText("Error");
    }

    @FXML
    void initialize() {
        assert textfieldFindStoreWithCheapestShopList_ProductsAndCount != null : "fx:id=\"textfieldFindStoreWithCheapestShopList_ProductsAndCount\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane_Administrator != null : "fx:id=\"anchorpane_Administrator\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldInsertProductToStore_ProductName != null : "fx:id=\"textfieldInsertProductToStore_ProductName\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldCreateProduct_ProductName != null : "fx:id=\"textfieldCreateProduct_ProductName\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldFindProductListForSum_StoreName != null : "fx:id=\"textfieldFindProductListForSum_StoreName\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldCreateProduct_ProductID != null : "fx:id=\"textfieldCreateProduct_ProductID\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert buttonFindStoreWithCheapestProduct != null : "fx:id=\"buttonFindStoreWithCheapestProduct\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert tab_Administrator != null : "fx:id=\"tab_Administrator\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert buttonBuyProductsInOneStore != null : "fx:id=\"buttonBuyProductsInOneStore\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldInsertProductToStore_Price != null : "fx:id=\"textfieldInsertProductToStore_Price\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldFindProductListForSum_Price != null : "fx:id=\"textfieldFindProductListForSum_Price\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldBuyProductsInOneStore_Count != null : "fx:id=\"textfieldBuyProductsInOneStore_Count\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert tab_Customer != null : "fx:id=\"tab_Customer\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert image_Customer != null : "fx:id=\"image_Customer\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldUpdateProduct_Price != null : "fx:id=\"textfieldUpdateProduct_Price\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldBuyProductsInOneStore_ProductName != null : "fx:id=\"textfieldBuyProductsInOneStore_ProductName\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldBuyProductsInOneStore_StoreName != null : "fx:id=\"textfieldBuyProductsInOneStore_StoreName\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldInsertProductToStore_StoreName != null : "fx:id=\"textfieldInsertProductToStore_StoreName\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert borderpane_Administrator != null : "fx:id=\"borderpane_Administrator\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldInsertProductToStore_Count != null : "fx:id=\"textfieldInsertProductToStore_Count\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldUpdateProduct_Count != null : "fx:id=\"textfieldUpdateProduct_Count\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldFindStoreWithCheapestProduct_ProductName != null : "fx:id=\"textfieldFindStoreWithCheapestProduct_ProductName\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldCreateStore_StoreName != null : "fx:id=\"textfieldCreateStore_StoreName\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldUpdateProduct_StoreName != null : "fx:id=\"textfieldUpdateProduct_StoreName\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert listview_Administrator != null : "fx:id=\"listview_Administrator\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldUpdateProduct_ProductName != null : "fx:id=\"textfieldUpdateProduct_ProductName\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert image_Administrator != null : "fx:id=\"image_Administrator\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert buttonCreateStore != null : "fx:id=\"buttonCreateStore\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert console_Administrator != null : "fx:id=\"console_Administrator\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert buttonInsertProductToStore != null : "fx:id=\"buttonInsertProductToStore\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldCreateStore_StoreAdress != null : "fx:id=\"textfieldCreateStore_StoreAdress\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert buttonFindProductListForSum != null : "fx:id=\"buttonFindProductListForSum\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert listview_Customer != null : "fx:id=\"listview_Customer\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane_Main != null : "fx:id=\"anchorpane_Main\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert tabpane_Main != null : "fx:id=\"tabpane_Main\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldCreateStore_StoreID != null : "fx:id=\"textfieldCreateStore_StoreID\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert console_Customer != null : "fx:id=\"console_Customer\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert borderpane_Customer != null : "fx:id=\"borderpane_Customer\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane_Customer != null : "fx:id=\"anchorpane_Customer\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert buttonCreateProduct != null : "fx:id=\"buttonCreateProduct\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert buttonUpdateProduct != null : "fx:id=\"buttonUpdateProduct\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert buttonFindStoreWithCheapestShopList != null : "fx:id=\"buttonFindStoreWithCheapestShopList\" was not injected: check your FXML file 'FirstScene.fxml'.";

    }
}
