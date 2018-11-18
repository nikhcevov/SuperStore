package com.maxpav.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static MediaPlayer mediaPlayer;
    public static Media media;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainScene.fxml"));
        primaryStage.getIcons().add(new Image("/images/icon.png"));
        primaryStage.setTitle("SuperStore");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }


}
