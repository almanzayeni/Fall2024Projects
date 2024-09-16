package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

class TestCalculateTotal {

    @Test
    void testNullBillArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            BillCalculator.calculateTotal(null);
        });
    }

    @Test
    void testEmptyBillArray() {
        Bill[] bills = {};

        double result = BillCalculator.calculateTotal(bills);

        assertEquals(0.0, result);
    }

    @Test
    void testBillWithNoItems() {
        Bill[] bills = { new Bill() };

        double result = BillCalculator.calculateTotal(bills);

        assertEquals(0.0, result);
    }

    @Test
    void testBillWithOneItem() {
        Bill bill = new Bill();
        BillItem item1 = new BillItem("Item 1", 5.0);
        bill.addItem(item1);

        Bill[] bills = { bill };

        double result = BillCalculator.calculateTotal(bills);

        assertEquals(6.50, result);
    }

    @Test
    void testBillWithMultipleItems() {
        Bill bill = new Bill();
        BillItem item1 = new BillItem("Item 1", 5.0);
        BillItem item2 = new BillItem("Item 2", 10.0);
        bill.addItem(item1);
        bill.addItem(item2);

        Bill[] bills = { bill };

        double result = BillCalculator.calculateTotal(bills);

        assertEquals(19.50, result);
    }

    @Test
    void testMultipleBills() {
        Bill bill1 = new Bill();
        BillItem item1 = new BillItem("Item 1", 5.0);
        bill1.addItem(item1);

        Bill bill2 = new Bill();
        BillItem item2 = new BillItem("Item 2", 10.0);
        bill2.addItem(item2);

        Bill[] bills = { bill1, bill2 };

        double result = BillCalculator.calculateTotal(bills);

        assertEquals(19.50, result);
    }

    @Test
    void testDifferentSubtotalForMultipleBills() {
        Bill bill1 = new Bill();
        BillItem item1 = new BillItem("Item 1", 3.0);
        bill1.addItem(item1);

        Bill bill2 = new Bill();
        BillItem item2 = new BillItem("Item 2", 7.0);
        BillItem item3 = new BillItem("Item 3", 2.0);
        bill2.addItem(item2);
        bill2.addItem(item3);

        Bill[] bills = { bill1, bill2 };

        double result = BillCalculator.calculateTotal(bills);

        assertEquals(15.60, result);
    }

}
