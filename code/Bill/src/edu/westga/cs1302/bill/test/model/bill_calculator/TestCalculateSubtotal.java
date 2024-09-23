package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

class TestCalculateSubtotal {

    @Test
    void testNullBillArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            BillCalculator.calculateSubTotal(null);
        });
    }

    @Test
    void testEmptyBillArray() {
        Bill[] bills = {};

        double result = BillCalculator.calculateSubTotal(bills);

        assertEquals(0.0, result);
    }

    @Test
    void testBillWithNoItems() {
        Bill[] bills = { new Bill() };

        double result = BillCalculator.calculateSubTotal(bills);

        assertEquals(0.0, result);
    }

    @Test
    void testBillWithOneItem() {
        Bill bill = new Bill();
        BillItem item1 = new BillItem("Item 1", 5.0);
        bill.addItem(item1);

        Bill[] bills = { bill };

        double result = BillCalculator.calculateSubTotal(bills);

        assertEquals(5.0, result);
    }

    @Test
    void testBillWithMultipleItems() {
        Bill bill = new Bill();
        BillItem item1 = new BillItem("Item 1", 5.0);
        BillItem item2 = new BillItem("Item 2", 10.0);
        bill.addItem(item1);
        bill.addItem(item2);

        Bill[] bills = { bill };

        double result = BillCalculator.calculateSubTotal(bills);

        assertEquals(15.0, result);
    }

}
