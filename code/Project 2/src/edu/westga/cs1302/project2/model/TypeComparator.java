package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/**
 * Comparator for ingredient type
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class TypeComparator implements Comparator<Ingredient> {

	@Override
	public int compare(Ingredient ingredient1, Ingredient ingredient2) {
		return ingredient1.getType().compareTo(ingredient2.getType());
	}
}
