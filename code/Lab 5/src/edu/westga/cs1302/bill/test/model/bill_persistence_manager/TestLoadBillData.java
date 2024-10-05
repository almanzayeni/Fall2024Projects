package edu.westga.cs1302.bill.test.model.bill_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class TestLoadBillData {

    @Test
    void testEmptyFile() throws IOException {
        try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
            writer.write("");
        }
        
        assertThrows(IOException.class, () -> {
            BillPersistenceManager.loadBillData();
        });
    }
    
    @Test
    void testValidBillData() throws IOException {
        try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
            writer.write("SERVER: Bob" + System.lineSeparator());
            writer.write("ITEMS" + System.lineSeparator());
            writer.write("Burger,5.99" + System.lineSeparator());
            writer.write("Fries,2.49" + System.lineSeparator());
            writer.write("Soda,1.50" + System.lineSeparator());
        }
        
        Bill bill = BillPersistenceManager.loadBillData();
        
        assertEquals("Bob", bill.getServerName(), "checking server name");
        assertEquals(3, bill.getSize(), "checking number of items");
        BillItem[] items = bill.getItems();
        assertEquals("Burger", items[0].getName(), "checking first item name");
        assertEquals(5.99, items[0].getAmount(), "checking first item amount");
        assertEquals("Fries", items[1].getName(), "checking second item name");
        assertEquals(2.49, items[1].getAmount(), "checking second item amount");
        assertEquals("Soda", items[2].getName(), "checking third item name");
        assertEquals(1.50, items[2].getAmount(), "checking third item amount");
    }
    
    @Test
    void testInvalidFormat() throws IOException {
        try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
            writer.write("SERVER: Bob" + System.lineSeparator());
            writer.write("ITEMS" + System.lineSeparator());
            writer.write("InvalidFormatLine" + System.lineSeparator());
        }
        
        assertThrows(IOException.class, () -> {
            BillPersistenceManager.loadBillData();
        });
    }
    
    @Test
    void testInvalidItemFormat() throws IOException {
        try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
            writer.write("SERVER: Bob" + System.lineSeparator());
            writer.write("ITEMS" + System.lineSeparator());
            writer.write("Burger,5.99" + System.lineSeparator());
            writer.write("InvalidItemFormat" + System.lineSeparator());
        }
        
        assertThrows(IOException.class, () -> {
            BillPersistenceManager.loadBillData();
        });
    }
    
    @Test
    void testMissingServerName() throws IOException {
        try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
            writer.write("ITEMS" + System.lineSeparator());
        }
        
        assertThrows(IOException.class, () -> {
            BillPersistenceManager.loadBillData();
        });
    }
    
    @Test
    void testNoItems() throws IOException {
        try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
            writer.write("SERVER: Bob" + System.lineSeparator());
            writer.write("ITEMS" + System.lineSeparator());
        }
        
        assertThrows(IOException.class, () -> {
            BillPersistenceManager.loadBillData();
        });
    }
    
    @Test
    void testInvalidAmount() throws IOException {
        try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
            writer.write("SERVER: Bob" + System.lineSeparator());
            writer.write("ITEMS" + System.lineSeparator());
            writer.write("Burger,notANumber" + System.lineSeparator());
        }
        
        assertThrows(IOException.class, () -> {
            BillPersistenceManager.loadBillData();
        });
    }


}
