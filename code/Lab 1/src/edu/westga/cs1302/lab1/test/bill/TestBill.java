package edu.westga.cs1302.lab1.test.bill;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab1.model.Bill;
import edu.westga.cs1302.lab1.model.BillItem;

class TestBill {

    @Test
    public void testConstructorCreatesEmptyBill() {
        // ARRANGE
        Bill bill = new Bill();

        // ACT
        List<BillItem> items = bill.getItems();

        // ASSERT
        assertTrue(items.isEmpty(), "The bill should be initialized with an empty list of items.");
    }

    @Test
    public void testAddItemValid() {
        // ARRANGE
        Bill bill = new Bill();
        BillItem item = new BillItem("Coffee", 3.50);

        // ACT
        bill.addItem(item);
        List<BillItem> items = bill.getItems();

        // ASSERT
        assertEquals(1, items.size(), "The bill should contain one item.");
        assertEquals(item, items.get(0), "The item added should be retrievable from the bill.");
    }

    @Test
    public void testAddNullItem() {
        // ARRANGE
        Bill bill = new Bill();

        // ACT & ASSERT
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bill.addItem(null);  // This should throw an exception
        });

        // ASSERT
        assertEquals("item must not be null.", exception.getMessage(), "The exception message should indicate that the item must not be null.");
    }

    @Test
    public void testGetItemsReturnsNewList() {
        // ARRANGE
        Bill bill = new Bill();
        BillItem item = new BillItem("Tea", 2.50);
        bill.addItem(item);

        // ACT
        List<BillItem> items = bill.getItems();
        items.clear();  // Attempt to modify the returned list

        // ASSERT
        assertEquals(1, bill.getItems().size(), "The original list of items in the bill should remain unchanged.");
        assertFalse(items.contains(item), "The retrieved list should not reflect changes to the original list.");
    }

}
