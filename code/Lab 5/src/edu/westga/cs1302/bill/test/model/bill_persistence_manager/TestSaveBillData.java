package edu.westga.cs1302.bill.test.model.bill_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class TestSaveBillData {

	
    @Test
    void testNullBill() {
        assertThrows(IllegalArgumentException.class, () -> {
            BillPersistenceManager.saveBillData(null);
        });
    }
	
	@Test
	void testEmptyBill() throws IOException {
	    Bill bill = new Bill();
	    bill.setServerName("Bob");
	    BillPersistenceManager.saveBillData(bill);

	    File inputFile = new File(BillPersistenceManager.DATA_FILE);
	    try (Scanner reader = new Scanner(inputFile)) {
	        assertEquals("Server: Bob", reader.nextLine(), "checking server name");
	        assertEquals("ITEMS", reader.nextLine(), "checking items header");
	        assertEquals("", reader.nextLine(), "checking that there's a blank line after items");
	        assertEquals("SUBTOTAL: 0.00", reader.nextLine(), "checking subtotal");
	        assertEquals("TAX: 0.00", reader.nextLine(), "checking tax");
	        assertEquals("TIP: 0.00", reader.nextLine(), "checking tip");
	        assertEquals("TOTAL: 0.00", reader.nextLine(), "checking total");
	        assertFalse(reader.hasNextLine(), "checking that we are at the end of the file");
	    }
	}
	
    @Test
    void testOneBillItem() throws IOException {
        Bill bill = new Bill();
        bill.setServerName("Alice");
        bill.addItem(new BillItem("Burger", 10.00));
        BillPersistenceManager.saveBillData(bill);

        File inputFile = new File(BillPersistenceManager.DATA_FILE);
        try (Scanner reader = new Scanner(inputFile)) {
            assertEquals("Server: Alice", reader.nextLine(), "checking server name");
            assertEquals("ITEMS", reader.nextLine(), "checking items header");
            assertEquals("Burger,10.00", reader.nextLine(), "checking first item");
            assertEquals("", reader.nextLine(), "checking that there's a blank line after items");
            assertEquals("SUBTOTAL: 10.00", reader.nextLine(), "checking subtotal");
            assertEquals("TAX: 1.00", reader.nextLine(), "checking tax");
            assertEquals("TIP: 2.00", reader.nextLine(), "checking tip");
            assertEquals("TOTAL: 13.00", reader.nextLine(), "checking total");
            assertFalse(reader.hasNextLine(), "checking that we are at the end of the file");
        }
    }
    
	@Test
	void testMultipleBillItems() throws IOException {
	    Bill bill = new Bill();
	    bill.setServerName("Trudy");
	    bill.addItem(new BillItem("Burger", 10.0)); 
	    bill.addItem(new BillItem("Fries", 5.5));
	    BillPersistenceManager.saveBillData(bill);

	    File inputFile = new File(BillPersistenceManager.DATA_FILE);
	    try (Scanner reader = new Scanner(inputFile)) {
	        assertEquals("Server: Trudy", reader.nextLine(), "checking server name");
	        assertEquals("ITEMS", reader.nextLine(), "checking items header");
	        assertEquals("Burger,10.00", reader.nextLine(), "checking first item");
	        assertEquals("Fries,5.50", reader.nextLine(), "checking second item");
	        assertEquals("", reader.nextLine(), "checking that there's a blank line after items");
	        assertEquals("SUBTOTAL: 15.50", reader.nextLine(), "checking subtotal");
	        assertEquals("TAX: 1.55", reader.nextLine(), "checking tax");
	        assertEquals("TIP: 3.10", reader.nextLine(), "checking tip");
	        assertEquals("TOTAL: 20.15", reader.nextLine(), "checking total");
	        assertFalse(reader.hasNextLine(), "checking that we are at the end of the file");
	    }
	}

}
