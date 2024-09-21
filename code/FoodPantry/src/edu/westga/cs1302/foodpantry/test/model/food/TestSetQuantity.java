package edu.westga.cs1302.foodpantry.test.model.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.foodpantry.model.Food;

public class TestSetQuantity {

	@Test
	public void testWithNegativeQuantity() {
		Food nextFood = new Food("Carrot", "Vegetable");
		assertThrows(IllegalArgumentException.class, () -> {
			nextFood.setQuantity(-1);
		});
	}
	
	@Test
	public void testWithZeroQuantity() {
		Food nextFood = new Food("Carrot", "Vegetable");
		nextFood.setQuantity(0);
		assertEquals(0, nextFood.getQuantity());
	}
	
	@Test
	public void testWithValidQuantity() {
		Food nextFood = new Food("Carrot", "Vegetable");
		nextFood.setQuantity(5);
		assertEquals(5, nextFood.getQuantity());
	}
	
	@Test
	public void testWithNewQuantity() {
		Food nextFood = new Food("Carrot", "Vegetable");
		nextFood.setQuantity(5);
		nextFood.setQuantity(8);
		assertEquals(8, nextFood.getQuantity());
	}

}
