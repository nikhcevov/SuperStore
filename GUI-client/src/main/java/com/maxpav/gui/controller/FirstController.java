package com.maxpav.gui.controller;

import com.maxpav.gui.controller.customelements.DoubleTextField;
import com.maxpav.gui.controller.customelements.NumberTextField;
import com.maxpav.gui.controller.customelements.WithoutCommaTextField;
import com.ovchingus.service.Service;
import com.ovchingus.service.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class FirstController {

    private Service service = new Service();
    private MediaPlayer mp_successful;
    private MediaPlayer mp_error;
    private Media m_successful;
    private Media m_error;
    private SettingsController children;


    public NumberTextField getTextfieldCreateProduct_ProductID() {
        return textfieldCreateProduct_ProductID;
    }

    public NumberTextField getTextfieldCreateStore_StoreID() {
        return textfieldCreateStore_StoreID;
    }

    @FXML
    private Button buttonsettings;

    @FXML
    private TextField textfieldFindStoreWithCheapestShopList_ProductsAndCount;

    @FXML
    private AnchorPane anchorpane_Administrator;

    @FXML
    private TextField textfieldInsertProductToStore_ProductName;

    @FXML
    private WithoutCommaTextField textfieldCreateProduct_ProductName;

    @FXML
    private TextField textfieldFindProductListForSum_StoreName;

    @FXML
    private NumberTextField textfieldCreateProduct_ProductID;

    @FXML
    private Button buttonFindStoreWithCheapestProduct;

    @FXML
    private Tab tab_Administrator;

    @FXML
    private Button buttonBuyProductsInOneStore;

    @FXML
    private DoubleTextField textfieldInsertProductToStore_Price;

    @FXML
    private DoubleTextField textfieldFindProductListForSum_Price;

    @FXML
    private NumberTextField textfieldBuyProductsInOneStore_Count;

    @FXML
    private Tab tab_Customer;

    @FXML
    private ImageView image_Customer;

    @FXML
    private DoubleTextField textfieldUpdateProduct_Price;

    @FXML
    private TextField textfieldBuyProductsInOneStore_ProductName;

    @FXML
    private TextField textfieldBuyProductsInOneStore_StoreName;

    @FXML
    private TextField textfieldInsertProductToStore_StoreName;

    @FXML
    private BorderPane borderpane_Administrator;

    @FXML
    private NumberTextField textfieldInsertProductToStore_Count;

    @FXML
    private NumberTextField textfieldUpdateProduct_Count;

    @FXML
    private TextField textfieldFindStoreWithCheapestProduct_ProductName;

    @FXML
    private WithoutCommaTextField textfieldCreateStore_StoreName;

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
    private NumberTextField textfieldCreateStore_StoreID;

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

    private boolean Check(String... text) {
        for (int i = 0; i < text.length; i++) {
            if (text[i].equals("")) return false;
        }
        return true;
    }

    @FXML
    private void setSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingsScene.fxml"));
        Parent settings = (Parent) loader.load();
        children = loader.getController();  // Теперь текущий контроллер "знает" о существовании "потомка"
        children.setParent(this);

        Stage stage = new Stage();
        stage.getIcons().add(new Image("/images/settings.png"));
        stage.setTitle("Settings");
        stage.setScene(new Scene(settings, 300, 200));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        Stage primaryStage = (Stage) anchorpane_Main.getScene().getWindow();
        stage.initOwner(primaryStage);
        stage.show();
    }

    @FXML
    private void createProduct(ActionEvent event) {
        console_Administrator.setText("Сalculation");
        boolean check = Settings.isSourceMySQL() && !textfieldCreateProduct_ProductName.getText().equals("");
        if (Check(textfieldCreateProduct_ProductID.getText(), textfieldCreateProduct_ProductName.getText())
                || check) {
            ObservableList<String> Items;
            Items = listview_Administrator.getItems();
            if (Settings.isSourceCSV()) {
                if (service.createProduct(Integer.parseInt(textfieldCreateProduct_ProductID.getText()),
                        textfieldCreateProduct_ProductName.getText())) {
                    Items.add("Product \"" + textfieldCreateProduct_ProductName.getText() + "\" is CREATED. ID: " +
                            textfieldCreateProduct_ProductID.getText());
                    listview_Administrator.setItems(Items);
                    successful_Administrator();
                } else {
                    Items.add("ERROR! Product \"" + textfieldCreateProduct_ProductName.getText() + "\" is NOT CREATED.");
                    listview_Administrator.setItems(Items);
                    error_Administrator();
                }
            }
            if (Settings.isSourceMySQL()) {
                if (service.createProduct(null, textfieldCreateProduct_ProductName.getText())) {
                    Items.add("Product \"" + textfieldCreateProduct_ProductName.getText() + "\" is CREATED. ID: " +
                            textfieldCreateProduct_ProductID.getText());
                    listview_Administrator.setItems(Items);
                    successful_Administrator();
                } else {
                    Items.add("ERROR! Product \"" + textfieldCreateProduct_ProductName.getText() + "\" is NOT CREATED.");
                    listview_Administrator.setItems(Items);
                    error_Administrator();
                }

            }
        } else error_Administrator();
    }

    @FXML
    private void createStore(ActionEvent event) {
        console_Administrator.setText("Сalculation");
        boolean check = Settings.isSourceMySQL() && !textfieldCreateStore_StoreName.getText().equals("")
                && !textfieldCreateStore_StoreAdress.getText().equals("");
        if (Check(textfieldCreateStore_StoreID.getText(), textfieldCreateStore_StoreName.getText(),
                textfieldCreateStore_StoreAdress.getText())
                || check) {
            ObservableList<String> Items;
            Items = listview_Administrator.getItems();
            if (Settings.isSourceCSV()) {
                if (service.createStore(Integer.parseInt(textfieldCreateStore_StoreID.getText()),
                        textfieldCreateStore_StoreName.getText(), textfieldCreateStore_StoreAdress.getText())) {
                    Items.add("Store \"" + textfieldCreateStore_StoreName.getText() + "\", \"" + textfieldCreateStore_StoreAdress.getText() +
                            "\" is CREATED.");
                    listview_Administrator.setItems(Items);
                    successful_Administrator();
                } else {
                    Items.add("ERROR! Store \"" + textfieldCreateStore_StoreName.getText() + "\" is NOT CREATED.");
                    listview_Administrator.setItems(Items);
                    error_Administrator();
                }
            }
            if (Settings.isSourceMySQL()) {
                if (service.createStore(null, textfieldCreateStore_StoreName.getText(), textfieldCreateStore_StoreAdress.getText())) {
                    Items.add("Store \"" + textfieldCreateStore_StoreName.getText() + "\", \"" + textfieldCreateStore_StoreAdress.getText() +
                            "\" is CREATED.");
                    listview_Administrator.setItems(Items);
                    successful_Administrator();
                } else {
                    Items.add("ERROR! Store \"" + textfieldCreateStore_StoreName.getText() + "\" is NOT CREATED.");
                    listview_Administrator.setItems(Items);
                    error_Administrator();
                }
            }
        } else error_Administrator();
    }

    @FXML
    private void insertProductToStore(ActionEvent event) {
        console_Administrator.setText("Сalculation");
        if (Check(textfieldInsertProductToStore_StoreName.getText(), textfieldInsertProductToStore_ProductName.getText(),
                textfieldInsertProductToStore_Price.getText(), textfieldInsertProductToStore_Count.getText())) {
            ObservableList<String> Items;
            Items = listview_Administrator.getItems();
            if (service.insertProductToStore(textfieldInsertProductToStore_StoreName.getText(),
                    textfieldInsertProductToStore_ProductName.getText(),
                    Integer.parseInt(textfieldInsertProductToStore_Count.getText()),
                    Double.parseDouble(textfieldInsertProductToStore_Price.getText()))) {
                Items.add("\"" + textfieldInsertProductToStore_ProductName.getText() + "\" is sold in \"" +
                        textfieldInsertProductToStore_StoreName.getText() + "\" in quantities " +
                        textfieldInsertProductToStore_Count.getText() + " by price " + textfieldInsertProductToStore_Price.getText() + " RUB");
                listview_Administrator.setItems(Items);
                successful_Administrator();
            } else {
                Items.add("ERROR! \"" + textfieldInsertProductToStore_ProductName.getText() + "\" not delivered to \"" + textfieldInsertProductToStore_StoreName.getText() + "\"");
                listview_Administrator.setItems(Items);
                error_Administrator();
            }
        } else error_Administrator();
    }

    @FXML
    private void updateProduct(ActionEvent event) {
        console_Administrator.setText("Сalculation");

        if (Check(textfieldUpdateProduct_StoreName.getText(),
                textfieldUpdateProduct_ProductName.getText(),
                textfieldUpdateProduct_Price.getText(),
                textfieldUpdateProduct_Count.getText())) {
            ObservableList<String> Items;
            Items = listview_Administrator.getItems();
            if (service.updateProduct(textfieldUpdateProduct_StoreName.getText(),
                    textfieldUpdateProduct_ProductName.getText(),
                    Integer.parseInt(textfieldUpdateProduct_Count.getText()),
                    Double.parseDouble(textfieldUpdateProduct_Price.getText()))) {
                Items.add("\"" + textfieldUpdateProduct_ProductName.getText() + "\" is sold in \"" +
                        textfieldUpdateProduct_StoreName.getText() + "\" in quantities " +
                        textfieldUpdateProduct_Count.getText() + " by price " + textfieldUpdateProduct_Price.getText() + " RUB");
                listview_Administrator.setItems(Items);
                successful_Administrator();
            } else {
                Items.add("ERROR! Product \"" + textfieldUpdateProduct_ProductName.getText() + "\" is NOT UPDATED.");
                listview_Administrator.setItems(Items);
                error_Administrator();
            }
        } else error_Administrator();
    }

    @FXML
    private void findStoreWithCheapestProduct(ActionEvent event) {
        console_Customer.setText("Сalculation");
        ObservableList<String> Items = FXCollections.observableArrayList();
        if (Check(textfieldFindStoreWithCheapestProduct_ProductName.getText())) {
            String answer = service.findStoreWithCheapestProduct(textfieldFindStoreWithCheapestProduct_ProductName.getText());
            if (answer != null) {
                Items.add(answer);
                listview_Customer.setItems(Items);
                successful_Customer();
            } else {
                Items.add("Store is NOT FOUND");
                listview_Customer.setItems(Items);
                error_Customer();
            }
        } else {
            Items.add("Not all fields are filled!");
            listview_Customer.setItems(Items);
            error_Customer();
        }
    }

    @FXML
    private void findProductListForSum(ActionEvent event) {
        console_Customer.setText("Сalculation");
        ObservableList<String> Items = FXCollections.observableArrayList();
        if (Check(textfieldFindProductListForSum_StoreName.getText(), textfieldFindProductListForSum_Price.getText())) {
            Map<String, Integer> map = service.findProductListForSum(textfieldFindProductListForSum_StoreName.getText(),
                    Double.parseDouble(textfieldFindProductListForSum_Price.getText()));
            if (!map.isEmpty()) {
                for (Map.Entry<String, Integer> pair : map.entrySet()) {
                    Items.add(pair.getKey() + " : " + pair.getValue());
                    listview_Customer.setItems(Items);
                    successful_Customer();
                }
            } else {
                Items.add("Product list is NOT COMPILED");
                listview_Customer.setItems(Items);
                error_Customer();
            }
        } else {
            Items.add("Not all fields are filled!");
            listview_Customer.setItems(Items);
            error_Customer();
        }
    }

    @FXML
    private void buyProductsInOneStore(ActionEvent event) {
        console_Customer.setText("Сalculation");
        ObservableList<String> Items = FXCollections.observableArrayList();
        if (Check(textfieldBuyProductsInOneStore_StoreName.getText(), textfieldBuyProductsInOneStore_ProductName.getText(),
                textfieldBuyProductsInOneStore_Count.getText())) {
            Double answer = service.buyProductsInOneStore(textfieldBuyProductsInOneStore_StoreName.getText(),
                    textfieldBuyProductsInOneStore_ProductName.getText(),
                    Integer.parseInt(textfieldBuyProductsInOneStore_Count.getText()));
            if (answer != null) {
                Items.add("Sum = " + answer + " RUB");
                listview_Customer.setItems(Items);
                successful_Customer();
            } else {
                Items.add("Purchase of products is NOT MADE");
                listview_Customer.setItems(Items);
                error_Customer();
            }
        } else {
            Items.add("Not all fields are filled!");
            listview_Customer.setItems(Items);
            error_Customer();
        }
    }

    @FXML
    private void findStoreWithCheapestShopList(ActionEvent event) {
        console_Customer.setText("Сalculation");
        ObservableList<String> Items = FXCollections.observableArrayList();
        if (Check(textfieldFindStoreWithCheapestShopList_ProductsAndCount.getText())) {
            String answer = service.findStoreWithCheapestShopList(textfieldFindStoreWithCheapestShopList_ProductsAndCount.getText());
            if (answer != null) {
                Items.add(answer);
                listview_Customer.setItems(Items);
                successful_Customer();
            } else {
                Items.add("Store is NOT FOUND");
                listview_Customer.setItems(Items);
                error_Customer();
            }
        } else {
            Items.add("Not all fields are filled!");
            listview_Customer.setItems(Items);
            error_Customer();
        }
    }

    private void error_Administrator() {
        anchorpane_Administrator.setStyle("-fx-background-color: #f45942");
        mp_error.stop();
        mp_error.play();
        console_Administrator.setText("Unsuccessfully");
    }

    private void successful_Administrator() {
        anchorpane_Administrator.setStyle("-fx-background-color: #3cb371");
        mp_successful.stop();
        mp_successful.play();
        console_Administrator.setText("Successfully");
    }

    private void error_Customer() {
        anchorpane_Customer.setStyle("-fx-background-color: #f45942");

        mp_error.stop();
        mp_error.play();
        console_Customer.setText("Unsuccessfully");
    }

    private void successful_Customer() {
        anchorpane_Customer.setStyle("-fx-background-color: #3cb371");
        mp_successful.stop();
        mp_successful.play();
        console_Customer.setText("Successfully");
    }


    @FXML
    void initialize() {
        if (Settings.isSourceMySQL()) {
            textfieldCreateProduct_ProductID.setDisable(true);
            textfieldCreateStore_StoreID.setDisable(true);
        } else {
            textfieldCreateStore_StoreID.setDisable(false);
            textfieldCreateProduct_ProductID.setDisable(false);
        }

        String path_successful = new File("GUI-client/src/main/resources/media/successful.mp3").getAbsolutePath();
        String path_error = new File("GUI-client/src/main/resources/media/error.mp3").getAbsolutePath();
        m_successful = new Media(new File(path_successful).toURI().toString());
        m_error = new Media(new File(path_error).toURI().toString());
        mp_successful = new MediaPlayer(m_successful);
        mp_error = new MediaPlayer(m_error);
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
        assert buttonsettings != null : "fx:id=\"buttonsettings\" was not injected: check your FXML file 'FirstScene.fxml'.";
    }
}