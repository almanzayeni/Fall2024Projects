package edu.westga.cs1302.lab1.test.bill_term;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab1.model.BillItem;

/** 
 * Unit tests for the BillItem constructor.
 * 
 * @author 
 * @version Fall 2024
 */
public class TestConstructor {

    @Test
    public void testConstructorValidArguments() {
       BillItem item = new BillItem("Coffee", 5.80);

       String name = item.getName();
       double amount = item.getAmount();

        assertEquals("Coffee", name, "The name should be set correctly.");
        assertEquals(5.80, amount,"The amount should be set correctly.");
    }

    @Test
    public void testConstructorNullName() {
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BillItem(null, 5.80);
        });

        assertEquals("name must not be null.", exception.getMessage());
    }

    @Test
    public void testConstructorNegativeAmount() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BillItem("Coffee", -5.80);
        });

        assertEquals("amount must be positive, but was " + -5.80, exception.getMessage());
    }

    @Test
    public void testConstructorZeroAmount() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BillItem("Bob", 0.00);
        });

        assertEquals("amount must be positive, but was " + 0.00, exception.getMessage());
    }

}
