package edu.westga.cs1302.foodpantry.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * The codebehind class for the food pantry
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class MainWindow {

    @FXML
    private TextField foodNameField;

    @FXML
    private ComboBox<?> foodTypeComboBox;

    @FXML
    private ListView<?> pantryListView;

    @FXML
    void addFood(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert this.foodNameField != null : "fx:id=\"foodNameField\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.foodTypeComboBox != null : "fx:id=\"foodTypeComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.pantryListView != null : "fx:id=\"pantryListView\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }

}

