package edu.westga.cs1302.project2.view;

import java.util.Comparator;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.NameComparator;
import edu.westga.cs1302.project2.model.TypeComparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML private ComboBox<String> ingredientType;
    @FXML private ComboBox<Comparator<Ingredient>> sortCriteria;
	@FXML private ListView<Ingredient> ingredientsList;
	@FXML private TextField ingredientName;
	
	private ObservableList<Ingredient> ingredients;

	@FXML
	void addIngredient(ActionEvent event) {
		try {
			Ingredient newIngredient = new Ingredient(this.ingredientName.getText(), this.ingredientType.getValue());
			this.ingredients.add(newIngredient);
			this.ingredientName.clear();
			this.ingredientType.getSelectionModel().clearSelection();
			this.sortIngredients();
		} catch (IllegalArgumentException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add ingredient");
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	void removeIngredient(ActionEvent event) {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null) {
			this.ingredientsList.getItems().remove(selectedIngredient);
			this.sortIngredients();
		}
	}

	@FXML
	void initialize() {
		this.ingredientType.getItems().add("Vegetable");
		this.ingredientType.getItems().add("Meat");
		this.ingredientType.getItems().add("Bread");
		this.ingredientType.getItems().add("Fruit");
		this.ingredientType.getItems().add("Spice");
		
		this.ingredients = FXCollections.observableArrayList();
		this.ingredientsList.setItems(this.ingredients);
		
		this.sortCriteria.getItems().add(new TypeComparator());
		this.sortCriteria.getItems().add(new NameComparator());
		
		this.sortCriteria.getSelectionModel().selectFirst();
		this.sortCriteria.setOnAction(event -> this.sortIngredients());
		
		this.sortIngredients();

	}
	
    /**
     * Sorts the list of ingredients using the currently selected sort criterion.
     */
    private void sortIngredients() {
        Comparator<Ingredient> selectedComparator = this.sortCriteria.getSelectionModel().getSelectedItem();
        if (selectedComparator != null) {
            FXCollections.sort(this.ingredients, selectedComparator);
        }
    }
}
