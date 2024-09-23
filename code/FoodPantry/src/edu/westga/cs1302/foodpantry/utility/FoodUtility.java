package edu.westga.cs1302.foodpantry.utility;

import edu.westga.cs1302.foodpantry.model.Food;

/**
 * Utility class for the food pantry
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class FoodUtility {
	
	/**
	 * Gets the total Quantity of all food items in the pantry
	 * 
	 * @param foodItems and array of Food objects
	 * @return the total quantity of items
	 */
	public static int getTotalItems(Food[] foodItems) {
		int totalQuantity = 0;
		for (Food item: foodItems) {
			totalQuantity += item.getQuantity();
		}
		return totalQuantity;
	}
}
