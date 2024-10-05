package edu.westga.cs1302.bill.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** Supports saving and loading bill data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillPersistenceManager {
	
	public static final String DATA_FILE = "data.txt";
	
	/** Save the bill!
	 * 
	 * Writes all bill data to DATA_FILE
	 * 
	 * @precondition bill != null
	 * @postcondition none
	 * 
	 * @param bill the bill to save
	 * @throws IOException when format is incorrect
	 */
	public static void saveBillData(Bill bill) throws IOException {
	    if (bill == null) {
	        throw new IllegalArgumentException("Must provide a bill");
	    }
	    
	    try (FileWriter writer = new FileWriter(DATA_FILE)) {
	        writer.write("Server: " + bill.getServerName() + System.lineSeparator());
	        writer.write("ITEMS" + System.lineSeparator());

	        for (BillItem item : bill.getItems()) {
	            if (item != null) {
	                writer.write(item.getName() + "," + String.format("%.2f", item.getAmount()) + System.lineSeparator());
	            }
	        }

	        writer.write(System.lineSeparator());

	        double subTotal = BillCalculator.getSubTotal(bill.getItems());
	        double tax = BillCalculator.getTax(bill.getItems());
	        double tip = BillCalculator.getTip(bill.getItems());
	        double total = BillCalculator.getTotal(bill.getItems());

	        writer.write("SUBTOTAL: " + String.format("%.2f", subTotal) + System.lineSeparator());
	        writer.write("TAX: " + String.format("%.2f", tax) + System.lineSeparator());
	        writer.write("TIP: " + String.format("%.2f", tip) + System.lineSeparator());
	        writer.write("TOTAL: " + String.format("%.2f", total) + System.lineSeparator());
	    }  
	}

	/** Load the bill!
	 * 
	 * Reads from DATA_FILE
	 * File is assumed to use the same format as saveBillData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the bill loaded
	 * @throws IOException when format is incorrect
	 */
	public static Bill loadBillData() throws IOException {
        Bill bill = new Bill();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String serverNameLine = reader.readLine();
            if (serverNameLine == null || !serverNameLine.startsWith("SERVER: ")) {
                throw new IOException("Invalid server name line.");
            }
            String serverName = serverNameLine.substring(8).trim();
            bill.setServerName(serverName);
            
            String itemsLine = reader.readLine();
            if (itemsLine == null || !itemsLine.equals("ITEMS")) {
                throw new IOException("Invalid items line.");
            }

            String itemLine;
            while ((itemLine = reader.readLine()) != null) {
                String[] parts = itemLine.split(",");
                if (parts.length != 2) {
                    throw new IOException("Invalid item format: " + itemLine);
                }
                String itemName = parts[0].trim();
                double itemAmount;
                try {
                    itemAmount = Double.parseDouble(parts[1].trim());
                } catch (NumberFormatException numError) {
                    throw new IOException("Invalid item amount: " + parts[1]);
                }
                BillItem item = new BillItem(itemName, itemAmount);
                bill.addItem(item);
            }
        }

        if (bill.getSize() == 0) {
            throw new IOException("No items found in the bill.");
        }

        return bill;
    }
}
