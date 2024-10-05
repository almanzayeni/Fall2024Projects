package edu.westga.cs1302.foodpantry.test.model.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.foodpantry.model.Food;

public class TestIncrementQuantity {

	@Test
	public void testIncrementQuantityByOne() {
		Food nextFood = new Food("Carrot", "Vegetable");
		nextFood.setQuantity(2);
		nextFood.incrementQuantity();
		assertEquals(3, nextFood.getQuantity());
	}

	@Test
	public void testIncrementQuantityWhenZero() {
		Food nextFood = new Food("Carrot", "Vegetable");
		nextFood.setQuantity(0);
		nextFood.incrementQuantity();
		assertEquals(1, nextFood.getQuantity());
	}
	
	@Test
	public void testWithMultipleIncrements() {
		Food nextFood = new Food("Carrot", "Vegetable");
		nextFood.setQuantity(2);
		nextFood.incrementQuantity();
		nextFood.incrementQuantity();
		nextFood.incrementQuantity();
		assertEquals(5, nextFood.getQuantity());
	}
}
