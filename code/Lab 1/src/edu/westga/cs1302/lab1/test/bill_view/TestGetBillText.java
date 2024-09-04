package edu.westga.cs1302.lab1.test.bill_view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab1.model.Bill;
import edu.westga.cs1302.lab1.model.BillItem;
import edu.westga.cs1302.lab1.view.BillView;

class TestGetBillText {

    @Test
    public void testGetBillTextWithValidBill() {
        // ARRANGE
        Bill bill = new Bill();
        BillItem item1 = new BillItem("Coffee", 3.50);
        BillItem item2 = new BillItem("Bagel", 2.50);
        bill.addItem(item1);
        bill.addItem(item2);
        
        BillView view = new BillView();

        // ACT
        String result = view.getBillText(bill);

        // ASSERT
        String expected = "ITEMS" + System.lineSeparator()
                        + "Coffee - $3.50" + System.lineSeparator()
                        + "Bagel - $2.50" + System.lineSeparator()
                        + System.lineSeparator()
                        + "SUBTOTAL - $6.00" + System.lineSeparator()
                        + "TAX - $0.60" + System.lineSeparator()
                        + "TIP - $1.20" + System.lineSeparator()
                        + "TOTAL - $7.80";
        
        assertEquals(expected, result, "The bill text should be formatted correctly.");
    }

    @Test
    public void testGetBillTextWithEmptyBill() {
        // ARRANGE
        Bill bill = new Bill();
        BillView view = new BillView();

        // ACT
        String result = view.getBillText(bill);

        // ASSERT
        String expected = "ITEMS" + System.lineSeparator()
                        + System.lineSeparator()
                        + "SUBTOTAL - $0.00" + System.lineSeparator()
                        + "TAX - $0.00" + System.lineSeparator()
                        + "TIP - $0.00" + System.lineSeparator()
                        + "TOTAL - $0.00";

        assertEquals(expected, result, "The bill text should correctly handle an empty bill.");
    }

    @Test
    public void testGetBillTextWithNullBill() {
        // ARRANGE
        BillView view = new BillView();

        // ACT & ASSERT
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            view.getBillText(null);
        });

        assertEquals("bill should not be null", exception.getMessage(), "The method should throw an exception for a null bill.");
    }

}
