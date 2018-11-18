package com.maxpav.gui.controller;

import com.ovchingus.service.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private Button button;


    @FXML
    private ComboBox<String> combobox;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private ProgressBar progressbar;

    @FXML
    private Text text;

    @FXML
    private AnchorPane anchorpane2;

    @FXML
    private AnchorPane anchorpane1;

    @FXML
    void enter(ActionEvent event) {
        button.setDisable(true);
        progressbar.setProgress(0);
        Thread th = new Thread(new bg_Thread());
        th.start();
    }

    @FXML
    void select(ActionEvent event) {
        if (combobox.getValue().equals("Database")) {
            Settings.setSourceMySQL();
        }
        if (combobox.getValue().equals("Text File")) {
            Settings.setSourceCSV();
        }
        button.setDisable(false);
    }


    class bg_Thread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i <= 100; i++) {
                progressbar.setProgress(i / 100.00);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            button.setStyle("-fx-background-color: #3cb371");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    AnchorPane pane = null;
                    try {
                        pane = FXMLLoader.load(getClass().getResource("/fxml/FirstScene.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    anchorpane1.getChildren().setAll(pane);
                }
            });
            button.setDisable(false);
        }
    }

    @FXML
    void initialize() {
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert progressbar != null : "fx:id=\"progressbar\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane2 != null : "fx:id=\"anchorpane2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane1 != null : "fx:id=\"anchorpane1\" was not injected: check your FXML file 'FirstScene.fxml'.";
        button.setDisable(true);
        combobox.getItems().add("Database");
        combobox.getItems().add("Text File");


    }
}
