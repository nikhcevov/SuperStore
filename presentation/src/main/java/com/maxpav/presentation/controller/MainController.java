package com.maxpav.presentation.controller;

import java.io.IOException;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

public class MainController {

    @FXML
    private Button button;

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

    void load() {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/fxml/FirstScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        anchorpane1.getChildren().setAll(pane);
    }


    class bg_Thread implements Runnable{

            @Override
            public void run() {
                for (int i = 0; i <= 100; i++)
                {
                    progressbar.setProgress(i / 100.00);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                load();
            }
    }

    @FXML
    void initialize() {
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert progressbar != null : "fx:id=\"progressbar\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane2 != null : "fx:id=\"anchorpane2\" was not injected: check your FXML file 'FirstScene.fxml'.";
        assert anchorpane1 != null : "fx:id=\"anchorpane1\" was not injected: check your FXML file 'FirstScene.fxml'.";


    }
}
