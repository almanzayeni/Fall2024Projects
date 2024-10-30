package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/**
 * Comparator for ingredient name
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class NameComparator implements Comparator<Ingredient> {

	@Override
	public int compare(Ingredient ingredient1, Ingredient ingredient2) {
		return ingredient1.getName().compareTo(ingredient2.getName());
	}
	
	@Override
	public String toString() {
		return "Name";
	}
}
