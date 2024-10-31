package edu.westga.cs1302.project2.model;

import java.util.List;

/**
 * Stores information for a recipe
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class Recipe {
	private String name;
	private List<String> ingredients;
	
	/**
	 * Recipe information with name and ingredients
	 * @param name of the recipe
	 * @param ingredients for the recipe
	 */
	public Recipe(String name, List<String> ingredients) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Recipe cannot be null or blank");
		}
		if (ingredients == null || ingredients.isEmpty()) {
			throw new IllegalArgumentException("ingredeints cannot be null or empty");
		}
		this.name = name;
		this.ingredients = ingredients;
	}

	/**
	 * Gets the name fo the recipe
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the ingredients for the recipe
	 * 
	 * @return the ingredients
	 */
	public List<String> getIngredients() {
		return this.ingredients;
	}	
}
