package edu.westga.cs1302.project2.model;

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
}
