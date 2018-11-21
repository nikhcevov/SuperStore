package com.maxpav.gui.controller;

import com.maxpav.gui.MainApp;
import com.ovchingus.service.Service;
import com.ovchingus.service.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.io.File;
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

    private void selectColor(int i) {
        switch (i) {
            case 0:
                anchorpane1.setStyle("-fx-background-color: #3cb371");
                button.setStyle("-fx-background-color: #f45942");
                text.setText("THIS IS THE BEST APP TO WORK WITH THE STORE!");
                break;
            case 1:
                anchorpane1.setStyle("-fx-background-color: #f45942");
                button.setStyle("-fx-background-color: #4286f4");
                text.setText("IS THE BEST APP TO WORK WITH THE STORE! THIS ");
                break;
            case 2:
                anchorpane1.setStyle("-fx-background-color: #4286f4");
                button.setStyle("-fx-background-color: #3cb371");
                text.setText("THE BEST APP TO WORK WITH THE STORE! THIS IS ");
                break;
            case 3:
                anchorpane1.setStyle("-fx-background-color: #3cb371");
                button.setStyle("-fx-background-color: #f45942");
                text.setText("BEST APP TO WORK WITH THE STORE! THIS IS THE ");
                break;
            case 4:
                anchorpane1.setStyle("-fx-background-color: #f45942");
                button.setStyle("-fx-background-color: #4286f4");
                text.setText("APP TO WORK WITH THE STORE! THIS IS THE BEST ");
                break;
            case 5:
                anchorpane1.setStyle("-fx-background-color: #4286f4");
                button.setStyle("-fx-background-color: #3cb371");
                text.setText("TO WORK WITH THE STORE! THIS IS THE BEST APP ");
                break;

            case 6:
                anchorpane1.setStyle("-fx-background-color: #3cb371");
                button.setStyle("-fx-background-color: #f45942");
                text.setText("WORK WITH THE STORE! THIS IS THE BEST APP TO ");
                break;
            case 7:
                anchorpane1.setStyle("-fx-background-color: #f45942");
                button.setStyle("-fx-background-color: #4286f4");
                text.setText("WITH THE STORE! THIS IS THE BEST APP TO WORK ");
                break;
            case 8:
                anchorpane1.setStyle("-fx-background-color: #4286f4");
                button.setStyle("-fx-background-color: #3cb371");
                text.setText("THE STORE! THIS IS THE BEST APP TO WORK WITH ");
                break;

            case 9:
                anchorpane1.setStyle("-fx-background-color: #f45942");
                button.setStyle("-fx-background-color: #4286f4");
                text.setText("STORE! THIS IS THE BEST APP TO WORK WITH THE ");
                break;

            default:
                break;
        }
    }


    @FXML
    private void enter(ActionEvent event) {
        button.setDisable(true);
        anchorpane1.getChildren().remove(combobox);
        text.setLayoutX(15.00);
        progressbar.setProgress(0);

        Thread th = new Thread(new bg_Thread());
        th.start();
        MainApp.mediaPlayer.stop();
        MainApp.mediaPlayer.play();
    }

    @FXML
    private void select(ActionEvent event) {
        if (combobox.getValue().equals("Database")) {
            Settings.setSourceMySQL();
        }
        if (combobox.getValue().equals("Text File")) {
            Settings.setSourceCSV();
        }
        button.setDisable(false);
    }


    private class bg_Thread implements Runnable {
        Thread loader = new Thread(new Runnable() {
            @Override
            public void run() {
                Service.load();
            }
        });

        @Override
        public void run() {
            int color;
            loader.start();
            for (int i = 0; i <= 100; i++) {
                progressbar.setProgress(i / 100.00);
                try {
                    Thread.sleep(90);
                    color = i % 10;
                    selectColor(color);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            DropShadow ds = new DropShadow();
            button.setStyle("-fx-background-color: #3cb371");
            text.setLayoutX(210.00);
            text.setText("ARE YOU READY?");
            button.setEffect(ds);
            anchorpane1.setStyle("-fx-background-color: #3cb371");
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
        String path = new File("GUI-client/src/main/resources/media/load.mp3").getAbsolutePath();
        MainApp.media = new Media(new File(path).toURI().toString());
        MainApp.mediaPlayer = new MediaPlayer(MainApp.media);

    }
}
