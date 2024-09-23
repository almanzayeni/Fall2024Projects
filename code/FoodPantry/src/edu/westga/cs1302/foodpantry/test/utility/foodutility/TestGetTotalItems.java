package edu.westga.cs1302.foodpantry.test.utility.foodutility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.foodpantry.model.Food;
import edu.westga.cs1302.foodpantry.utility.FoodUtility;

public class TestGetTotalItems {

    @Test
    void testGetTotalItemsWithEmptyArray() {
        Food[] emptyArray = new Food[0];
        
        int total = FoodUtility.getTotalItems(emptyArray);
        assertEquals(0, total, "The total quantity should be zero when the food array is empty");
    }
    
    @Test
    void testGetTotalItemsShouldReturnSum() {
        Food apple = new Food("Apple", "Fruit");
        apple.setQuantity(5);
        
        Food bread = new Food("Bread", "Grain");
        bread.setQuantity(10);
        
        Food chicken = new Food("Chicken", "Meat");
        chicken.setQuantity(8);
        
        Food[] foodArray = new Food[] { apple, bread, chicken };
        
        int total = FoodUtility.getTotalItems(foodArray);
        assertEquals(23, total, "The total quantity should be the sum of all food items' quantities");
    }
    
    @Test
    void testGetTotalItemsWithSingleItemy() {
        Food apple = new Food("Apple", "Fruit");
        apple.setQuantity(7);
        
        Food[] foodArray = new Food[] { apple };
        
        int total = FoodUtility.getTotalItems(foodArray);
        assertEquals(7, total, "The total quantity should be the quantity of the single food item");
    }


}
