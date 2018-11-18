package com.maxpav.gui.controller;

import com.ovchingus.service.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TextField textfieldpath;

    @FXML
    private ComboBox<String> comboboxsource;

    @FXML
    private Button buttonapply;

    @FXML
    private Button buttonchoose;

    @FXML
    private Text textactionstoupdate;

    @FXML
    private Button buttoncancel;

    @FXML
    private Text textpath;

    @FXML
    private Button buttonok;

    @FXML
    private Text textsource;

    @FXML
    private TextField textfieldactionstoupdate;


    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void apply(ActionEvent event) {
        if (comboboxsource.getValue().equals("Database")) {
            Settings.setSourceMySQL();
        }
        if (comboboxsource.getValue().equals("Text File") && textfieldpath.getText() != null) {
            Settings.setSourceCSV();
        }
    }

    @FXML
    void ok(ActionEvent event) {
        if (comboboxsource.getValue().equals("Database")) {
            Settings.setSourceMySQL();
        }
        if (comboboxsource.getValue().equals("Text File") && textfieldpath.getText() != null) {
            Settings.setSourceCSV();
        }
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void fillTextField(ActionEvent event) {

    }

    @FXML
    void chooseSource(ActionEvent event) {
        if (comboboxsource.getValue().equals("Text File")) {
            buttonchoose.setDisable(false);
            textfieldpath.setDisable(false);
        }
        if (comboboxsource.getValue().equals("Database")) {
            buttonchoose.setDisable(true);
            textfieldpath.setDisable(true);
            textfieldactionstoupdate.setDisable(true);
        }

    }


    @FXML
    private void choosePath(ActionEvent event) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            textfieldpath.setText(file.getAbsolutePath());
            buttonapply.setDisable(false);
            buttonok.setDisable(false);
        }

    }

    @FXML
    void initialize() {

        assert anchorpane != null : "fx:id=\"anchorpane\" was not injected: check your FXML file 'SettingsScene.fxml'.";
        assert buttonapply != null : "fx:id=\"buttonapply\" was not injected: check your FXML file 'SettingsScene.fxml'.";
        assert buttonchoose != null : "fx:id=\"buttonchoose\" was not injected: check your FXML file 'SettingsScene.fxml'.";
        assert textactionstoupdate != null : "fx:id=\"textactionstoupdate\" was not injected: check your FXML file 'SettingsScene.fxml'.";
        assert buttoncancel != null : "fx:id=\"buttoncancel\" was not injected: check your FXML file 'SettingsScene.fxml'.";
        assert textpath != null : "fx:id=\"textpath\" was not injected: check your FXML file 'SettingsScene.fxml'.";
        assert textfieldpath != null : "fx:id=\"textfieldpath\" was not injected: check your FXML file 'SettingsScene.fxml'.";
        assert buttonok != null : "fx:id=\"buttonok\" was not injected: check your FXML file 'SettingsScene.fxml'.";
        assert textsource != null : "fx:id=\"textsource\" was not injected: check your FXML file 'SettingsScene.fxml'.";
        assert comboboxsource != null : "fx:id=\"comboboxsource\" was not injected: check your FXML file 'SettingsScene.fxml'.";
        assert textfieldactionstoupdate != null : "fx:id=\"textfieldactionstoupdate\" was not injected: check your FXML file 'SettingsScene.fxml'.";

        comboboxsource.getItems().add("Database");
        comboboxsource.getItems().add("Text File");

        if (Settings.isSourceCSV()) {
            comboboxsource.setValue("Text File");
            buttonchoose.setDisable(false);
            textfieldpath.setDisable(false);
        }
        if (Settings.isSourceMySQL()) {
            comboboxsource.setValue("Database");
            buttonchoose.setDisable(true);
            textfieldpath.setDisable(true);
            textfieldactionstoupdate.setDisable(true);
        }

    }
}

