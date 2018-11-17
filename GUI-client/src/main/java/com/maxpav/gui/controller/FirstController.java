package com.maxpav.gui.controller;

import com.ovchingus.service.func.model.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstController {

    private Service service = new Service();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textfieldcreatestore1;

    @FXML
    private TextField textfieldcreatestore2;

    @FXML
    private TextField textfieldcreatestore3;

    @FXML
    private TextField textfieldcreateproduct1;

    @FXML
    private TextField textfieldcreateproduct2;

    @FXML
    private ListView<String> listview2;

    @FXML
    private ListView<String> listview1;

    @FXML
    private Tab tab1;

    @FXML
    private TextField textfieldFindStoreWhereProductIsCheapest1;

    @FXML
    private Tab tab2;

    @FXML
    private AnchorPane anchorpane_3;

    @FXML
    private TabPane tabpane;

    @FXML
    private BorderPane borderpane1;

    @FXML
    private TextField textfieldFindStoreWhereCombinationIsCheapest1;

    @FXML
    private BorderPane borderpane2;

    @FXML
    private TextField textfieldCombinations1;

    @FXML
    private Text text;

    @FXML
    private Button button1;

    @FXML
    private Button button4;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button button5;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private TextField textfielddelivery4;

    @FXML
    private Button button8;

    @FXML
    private TextField textfielddelivery3;

    @FXML
    private TextField textfielddelivery2;

    @FXML
    private Button button6;

    @FXML
    private TextField textfielddelivery1;

    @FXML
    private Button button7;

    @FXML
    private TextField textfieldBuy2;

    @FXML
    private TextField textfieldBuy1;

    @FXML
    private TextField textfieldBuy3;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private TextField textfieldchangeprice1;

    @FXML
    private TextField textfieldchangeprice2;

    @FXML
    private TextField textfieldchangeprice3;

    @FXML
    private TextField textfieldchangeprice4;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    void createProduct(ActionEvent event) {
        text1.setText("Сalculation");
        if (!textfieldcreateproduct1.getText().equals("") && !textfieldcreateproduct2.getText().equals("")) {
            service.createProduct();


            text1.setText("Successful");
        } else text1.setText("Error");
    }

    @FXML
    void updateProduct(ActionEvent event) {
        text1.setText("Сalculation");
        if (!textfieldchangeprice1.getText().equals("") && !textfieldchangeprice2.getText().equals("") &&
                !textfieldchangeprice3.getText().equals("") && !textfieldchangeprice4.getText().equals("")) {
            //TODO:


            text1.setText("Successful");
        } else text1.setText("Error");
    }

    @FXML
    void createStore(ActionEvent event) {
        text1.setText("Сalculation");
        if (!textfieldcreatestore1.getText().equals("") && !textfieldcreatestore2.getText().equals("")) {
            //TODO:


            text1.setText("Successful");
        } else text1.setText("Error");
    }

    @FXML
    void delivery(ActionEvent event) {
        text1.setText("Сalculation");
        if (!textfielddelivery1.getText().equals("") && !textfielddelivery2.getText().equals("") &&
                !textfielddelivery3.getText().equals("") && !textfielddelivery4.getText().equals("")) {
            //TODO:


            text1.setText("Successful");
        } else text1.setText("Error");
    }

    @FXML
    void findStoreWhereProductIsCheapest(ActionEvent event) {
        text2.setText("Сalculation");
        if (!textfieldFindStoreWhereProductIsCheapest1.getText().equals("")) {
            //TODO:


            text2.setText("Successful");
        } else text2.setText("Error");
    }

    @FXML
    void combinations(ActionEvent event) {
        text2.setText("Сalculation");
        if (!textfieldCombinations1.getText().equals("")) {
            //TODO:


            text2.setText("Successful");
        } else text2.setText("Error");
    }

    @FXML
    void buy(ActionEvent event) {
        text2.setText("Сalculation");
        if (!textfieldBuy1.getText().equals("") && !textfieldBuy2.getText().equals("") &&
                !textfieldBuy3.getText().equals("")) {
            //TODO:


            text2.setText("Successful");
        } else text2.setText("Error");
    }

    @FXML
    void findStoreWhereCombinationIsCheapest(ActionEvent event) {
        text2.setText("Сalculation");
        if (!textfieldFindStoreWhereCombinationIsCheapest1.getText().equals("")) {
            //TODO:


            text2.setText("Successful");
        } else text2.setText("Error");
    }

    @FXML
    void initialize() {
        service.initialize();
        assert textfieldcreatestore1 != null : "fx:id=\"textfieldcreatestore1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldcreateproduct1 != null : "fx:id=\"textfieldcreateproduct1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert listview2 != null : "fx:id=\"listview2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert listview1 != null : "fx:id=\"listview1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert tab1 != null : "fx:id=\"tab1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldFindStoreWhereProductIsCheapest1 != null : "fx:id=\"textfieldFindStoreWhereProductIsCheapest1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert tab2 != null : "fx:id=\"tab2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane_3 != null : "fx:id=\"anchorpane_3\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert tabpane != null : "fx:id=\"tabpane\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert borderpane1 != null : "fx:id=\"borderpane1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldFindStoreWhereCombinationIsCheapest1 != null : "fx:id=\"textfieldFindStoreWhereCombinationIsCheapest1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert borderpane2 != null : "fx:id=\"borderpane2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldCombinations1 != null : "fx:id=\"textfieldCombinations1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert button1 != null : "fx:id=\"button1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert button4 != null : "fx:id=\"button4\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane != null : "fx:id=\"anchorpane\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert button5 != null : "fx:id=\"button5\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert button2 != null : "fx:id=\"button2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert button3 != null : "fx:id=\"button3\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfielddelivery4 != null : "fx:id=\"textfielddelivery4\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert button8 != null : "fx:id=\"button8\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfielddelivery3 != null : "fx:id=\"textfielddelivery3\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfielddelivery2 != null : "fx:id=\"textfielddelivery2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert button6 != null : "fx:id=\"button6\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfielddelivery1 != null : "fx:id=\"textfielddelivery1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert button7 != null : "fx:id=\"button7\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldBuy2 != null : "fx:id=\"textfieldBuy2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldBuy1 != null : "fx:id=\"textfieldBuy1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldBuy3 != null : "fx:id=\"textfieldBuy3\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert image1 != null : "fx:id=\"image1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert image2 != null : "fx:id=\"image2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane1 != null : "fx:id=\"anchorpane1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldchangeprice1 != null : "fx:id=\"textfieldchangeprice1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldchangeprice2 != null : "fx:id=\"textfieldchangeprice2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldchangeprice3 != null : "fx:id=\"textfieldchangeprice3\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldchangeprice4 != null : "fx:id=\"textfieldchangeprice4\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert text2 != null : "fx:id=\"text2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert text1 != null : "fx:id=\"text1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldcreatestore2 != null : "fx:id=\"textfieldcreatestore2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldcreatestore3 != null : "fx:id=\"textfieldcreatestore3\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert textfieldcreateproduct1 != null : "fx:id=\"textfieldcreateproduct1\" was not injected: check your FXML file 'FirstScene.fxml'.";
    }
}
