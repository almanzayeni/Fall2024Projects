package edu.westga.cs1302.foodpantry.model;

/**
 * Stores information for a food item
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class Food {
	private final String name;
	private final String type;
	private int quantity;
	
	/**
	 * Two-parameter constructor to create a food item
	 * 
	 * @precodnition name != null && name != ""
	 * 				 type != null && name != ''
	 * @postcondition quantity = 0
	 * 
	 * @param name of the food item
	 * @param type of food item 
	 */
	public Food(String name, String type) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name can't be null or empty");
		}
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("type can't be null or empty");
		}
		this.name = name;
		this.type = type;
		this.quantity = 0;
	}

	/**
	 * Gets the name of the food 
	 * 
	 * @precoditon none
	 * @postcondition none
	 * 
	 * @return the name of the food
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the name of the food 
	 * 
	 * @precoditon none
	 * @postcondition none
	 * 
	 * @return the type of food
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Gets the name of the food 
	 * 
	 * @precoditon none
	 * @postcondition none
	 * 
	 * @return the quantity of food
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Sets the quantity of the food 
	 * 
	 * @precondition quantity > 0
	 * @postcondition quantity is updated to a new value
	 * 
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("quantity can't be negative");
		}
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return this.name + " - " + this.quantity;
	}
}
