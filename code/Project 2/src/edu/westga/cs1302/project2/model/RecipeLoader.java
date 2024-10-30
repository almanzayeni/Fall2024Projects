package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**Loads a recipe from a file
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class RecipeLoader {
	
	/**
     * Loads recipes from a specified file.
     *
     * @param filename the name of the file to load recipes from
     * @return a list of recipes loaded from the file
     */
	public static List<Recipe> loadRecipeFromFile(String filename) {
        List<Recipe> recipes = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists() || file.length() == 0) {
            return recipes;
        }

        try (Scanner scanner = new Scanner(file)) {
            String recipeName = null;
            List<String> ingredients = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) { 
                    if (recipeName != null && !ingredients.isEmpty()) {
                        recipes.add(new Recipe(recipeName, ingredients));
                    }
                    // Reset for the next recipe
                    recipeName = null;
                    ingredients = new ArrayList<>();
                } else {
                    if (recipeName == null) {
                        recipeName = line; 
                    } else {
                        ingredients.add(line); 
                    }
                }
            }

            // Add the last recipe if file doesn't end with a blank line
            if (recipeName != null && !ingredients.isEmpty()) {
                recipes.add(new Recipe(recipeName, ingredients));
            }
        } catch (IOException ie) {
            ie.printStackTrace(); 
        }

        return recipes;
    }

    /**
     * Loads recipes with a specified ingredient from a file.
     *
     * @param filename the name of the file to load recipes from
     * @param ingredient the ingredient to search for
     * @return a list of recipes containing the specified ingredient
     */
    public static List<Recipe> loadRecipesWithIngredient(String filename, String ingredient) {
        List<Recipe> recipes = loadRecipeFromFile(filename);
        List<Recipe> filteredRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient)) {
                filteredRecipes.add(recipe);
            }
        }

        return filteredRecipes;
    }
}
