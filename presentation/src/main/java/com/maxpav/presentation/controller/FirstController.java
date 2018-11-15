package com.maxpav.presentation.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FirstController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private AnchorPane anchorpane2;

    @FXML
    private AnchorPane anchorpane1;

    @FXML
    void initialize() {
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane2 != null : "fx:id=\"anchorpane2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane1 != null : "fx:id=\"anchorpane1\" was not injected: check your FXML file 'FirstScene.fxml'.";

    }
}
