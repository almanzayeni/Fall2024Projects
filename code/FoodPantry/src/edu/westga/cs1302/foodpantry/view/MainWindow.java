package edu.westga.cs1302.foodpantry.view;

import edu.westga.cs1302.foodpantry.model.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private ComboBox<String> foodTypeComboBox;

    @FXML
    private ListView<Food> pantryListView;
    
    @FXML
    private TextField quantityField;
    
    private ObservableList<Food> pantryItems;
    
    @FXML
    void initialize() {
        assert this.foodNameField != null : "fx:id=\"foodNameField\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.foodTypeComboBox != null : "fx:id=\"foodTypeComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.pantryListView != null : "fx:id=\"pantryListView\" was not injected: check your FXML file 'MainWindow.fxml'.";
        
        this.foodTypeComboBox.setItems(FXCollections.observableArrayList(
        		"Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient"));
        this.pantryItems = FXCollections.observableArrayList();
        this.pantryListView.setItems(this.pantryItems);

    }
    
    @FXML
    void addFood(ActionEvent event) {
    	String foodName = this.foodNameField.getText();
    	String foodType = this.foodTypeComboBox.getValue();
    	
        if (foodName == null || foodName.trim().isEmpty()) {
            this.showAlert("Invalid Input", "Please enter a valid food name.");
            return;
        }

        if (foodType == null) {
            this.showAlert("Invalid Input", "Please select a food type.");
            return;
        }

        Food newFood = new Food(foodName, foodType);

        this.pantryItems.add(newFood);

        this.foodNameField.clear();
        this.foodTypeComboBox.setValue(null);
    }
   
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    void setQuantity(ActionEvent event) {
    	Food selectedFood = this.pantryListView.getSelectionModel().getSelectedItem();
    	if (selectedFood == null) {
    		this.showAlert("No Selection", "Please select a food item to set it's quantity");
    		return;
    	}
    	String quantityText = this.quantityField.getText();
    	int quantity = 0;
    	try {
    		quantity = Integer.parseInt(quantityText);
    		if (quantity < 0) {
    			throw new NumberFormatException();
    		}
    		
    	} catch (NumberFormatException ne) {
    		this.showAlert("Invalid input" + quantityText, " Please enter a non-negative number");
    	}
    	selectedFood.setQuantity(quantity);
    	this.quantityField.setText(String.valueOf(selectedFood.getQuantity()));
    	this.pantryListView.refresh();
    	this.quantityField.clear();
    }
    
    @FXML
    void incrementQuantity(ActionEvent event) {
    	Food selectedFood = this.pantryListView.getSelectionModel().getSelectedItem();
    	if (selectedFood == null) {
    		this.showAlert("No Selection", "Please select a food item to increase it's quantity");
    		return;
    	}
    	selectedFood.setQuantity(selectedFood.getQuantity() + 1);
    	this.quantityField.setText(String.valueOf(selectedFood.getQuantity()));
    	this.pantryListView.refresh();
    }
    
    @FXML
    void decrementQuantity(ActionEvent event) {
    	Food selectedFood = this.pantryListView.getSelectionModel().getSelectedItem();
    	if (selectedFood == null) {
    		this.showAlert("No Selection", "Please select a food item to decrease it's quantity");
    		return;
    	}
    	if (selectedFood.getQuantity() == 0) {
    		this.showAlert("Invalid Action", "Quantity is already 0. Cannot decrement farther.");
    	}
    	selectedFood.decrementQuantity();
        this.quantityField.setText(String.valueOf(selectedFood.getQuantity()));
        this.pantryListView.refresh();
    }
    
    @FXML
    void removeFood(ActionEvent event) {
    	Food selectedFood = this.pantryListView.getSelectionModel().getSelectedItem();
    	if (selectedFood == null) {
    		this.showAlert("No Selection", "Please select a food item to remove");
    		return;
    	}
    	
    	this.pantryItems.remove(selectedFood);
    	this.foodNameField.clear();
    	this.foodTypeComboBox.setValue(null);
    	this.quantityField.clear();
    	this.pantryListView.refresh();
    }

}

