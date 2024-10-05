package edu.westga.cs1302.foodpantry.test.model.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.foodpantry.model.Food;

public class TestDecrementQuantity {

	@Test
	public void testDecrementToZero() {
		Food nextFood = new Food("Carrot", "Vegetable");
		nextFood.setQuantity(1);
		nextFood.decrementQuantity();
		assertThrows(IllegalArgumentException.class, () -> {
			nextFood.decrementQuantity();
		});
	}
	
	@Test
	public void testDecrementValidQuantity() {
		Food nextFood = new Food("Carrot", "Vegetable");
		nextFood.setQuantity(5);
		nextFood.decrementQuantity();
		assertEquals(4, nextFood.getQuantity());
	}
	
	@Test
	public void testDecrementMultipleValidQuantity() {
		Food nextFood = new Food("Carrot", "Vegetable");
		nextFood.setQuantity(5);
		nextFood.decrementQuantity();
		nextFood.decrementQuantity();
		nextFood.decrementQuantity();
		assertEquals(2, nextFood.getQuantity());
	}

}
