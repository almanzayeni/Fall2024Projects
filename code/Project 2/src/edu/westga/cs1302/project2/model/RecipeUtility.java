package edu.westga.cs1302.project2.model;

import java.util.List;

/**
 * Utility for formatting recipe object to string
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class RecipeUtility {
	
	/**
	 * Converts the recipe objects to String
	 * 
	 * @param recipe the recipe to be converted
	 * @return recipe objects as String
	 */
	public static String converRecipeToString(Recipe recipe) {
		StringBuilder ingredientsList = new StringBuilder();
		for (int index = 0; index < recipe.getIngredients().size(); index++) {
			ingredientsList.append(recipe.getIngredients().get(index));
			if (index < recipe.getIngredients().size() - 1) {
				ingredientsList.append(", ");
			}
		}
		return recipe.getName() + "\n" + ingredientsList.toString();
	}
	
    /**
     * Converts a list of recipe objects to a String, separated by blank lines.
     * 
     * @param recipes the list of recipes to be converted
     * @return string representation of the list of recipes
     */
    public static String convertRecipesToString(List<Recipe> recipes) {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < recipes.size(); index++) {
            result.append(converRecipeToString(recipes.get(index)));
            if (index < recipes.size() - 1) {
                result.append("\n\n");
            }
        }
        return result.toString();
    }

}
