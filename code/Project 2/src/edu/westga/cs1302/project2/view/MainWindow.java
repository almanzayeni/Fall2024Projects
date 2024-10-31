package edu.westga.cs1302.project2.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.NameComparator;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileWriter;
import edu.westga.cs1302.project2.model.RecipeLoader;
import edu.westga.cs1302.project2.model.RecipeUtility;
import edu.westga.cs1302.project2.model.TypeComparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
    @FXML private Button addIngredients;
    @FXML private Button addRecipeToBook;
    @FXML private ListView<Ingredient> recipeIngredientsList;
    @FXML private TextField recipeName;
    @FXML private TextArea recipeList;
	
	private ObservableList<Ingredient> ingredients;
	private ObservableList<Ingredient> recipeIngredients;

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
    void addRecipe(ActionEvent event) {
        String recipeNameText = this.recipeName.getText();
        if (recipeNameText == null || recipeNameText.isBlank()) {
            this.showAlert("Invalid Recipe Name", "Please enter a valid recipe name.");
            return;
        }

        if (this.recipeIngredients.isEmpty()) {
            this.showAlert("No Ingredients", "Please add at least one ingredient to the recipe.");
            return;
        }
        List<String> ingredientNames = new ArrayList<>();
        for (Ingredient ingredient : this.recipeIngredients) {
            ingredientNames.add(ingredient.toString());
        }

        try {
            Recipe newRecipe = new Recipe(recipeNameText, ingredientNames);
            RecipeFileWriter fileWriter = new RecipeFileWriter("recipes.txt");
            fileWriter.writeRecipe(newRecipe);

            this.showAlert("Recipe Saved", "Recipe saved successfully!");
            this.recipeName.clear();
            this.recipeIngredients.clear();
        } catch (IOException | IllegalStateException error) {
            this.showAlert("Error Saving Recipe", error.getMessage());
        }
    }

    @FXML
    void addSelectedIngredeints(ActionEvent event) {
        Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
        
        if (selectedIngredient == null) {
            this.showAlert("No Ingredient Selected", "Please select an ingredient to add to the recipe.");
            return;
        }

        if (!this.recipeIngredients.contains(selectedIngredient)) {
            this.recipeIngredients.add(selectedIngredient);
        } else {
            this.showAlert("Duplicate Ingredient", "This ingredient is already added to the recipe.");
        }
    }
    
    @FXML
    void displayRecipies(ActionEvent event) throws FileNotFoundException {
        Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
        if (selectedIngredient == null) {
            this.showAlert("Ingredient Selection", "Please select an ingredient.");
            return;
        }

        List<Recipe> allRecipes = RecipeLoader.loadRecipeFromFile("recipes.txt");
        List<Recipe> filteredRecipes = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            for (String ingredient : recipe.getIngredients()) {
                if (ingredient.split("-")[0].equalsIgnoreCase(selectedIngredient.getName())) {
                    filteredRecipes.add(recipe);
                    break;
                }
            }
        }

        String displayText;
        if (filteredRecipes.isEmpty()) {
            displayText = "No recipes found with the specified ingredient.";
        } else {
            displayText = RecipeUtility.convertRecipesFileToString(filteredRecipes);
        }

        this.recipeList.setText(displayText);
    }
    
	@FXML
	void initialize() {
	    this.ingredientType.getItems().addAll("Vegetable", "Meat", "Bread", "Fruit", "Spice");

	    this.ingredients = FXCollections.observableArrayList();
	    this.recipeIngredients = FXCollections.observableArrayList();

	    this.ingredientsList.setItems(this.ingredients);
	    this.recipeIngredientsList.setItems(this.recipeIngredients);

	    this.sortCriteria.getItems().add(new TypeComparator());
	    this.sortCriteria.getItems().add(new NameComparator());

	    this.sortCriteria.getSelectionModel().selectFirst();
	    this.sortCriteria.setOnAction(event -> this.sortIngredients());

	    this.sortIngredients();

	}
	
    /**
     * Helper method to show alert messages.
     * 
     * @param title the title of the alert
     * @param message the message content
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
