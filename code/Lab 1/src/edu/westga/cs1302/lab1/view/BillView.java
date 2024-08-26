package edu.westga.cs1302.lab1.view;

import java.util.List;

import edu.westga.cs1302.lab1.model.Bill;
import edu.westga.cs1302.lab1.model.BillItem;

/**
 * The view class for the Bill. Generate text for a bill
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class BillView {

	/**
	 * Creates the text of the provided bill
	 * 
	 * @precondition bill != null
	 * @postcondiiton noneS
	 * 
	 * @param bill the bill to create text for
	 * @return a String containing the list of bill items and total for the bill
	 */
	public String getBillText(Bill bill) {
		if (bill == null) {
			throw new IllegalArgumentException("bill should not be null");
		}
		
		StringBuilder text = new StringBuilder("ITEMS" + System.lineSeparator());
		List<BillItem> items = bill.getItems();
		double subTotal = 0.0;
		
		for (BillItem item : items) {
			text.append(item.getName())
	            .append(" - $")
	            .append(String.format("%.2f", item.getAmount()))
	            .append(System.lineSeparator());
			subTotal += item.getAmount();
		}
		
		text.append(System.lineSeparator())
	        .append("SUBTOTAL - $")
	        .append(String.format("%.2f", subTotal))
	        .append(System.lineSeparator());
    
		double tax = subTotal * Bill.TAX_RATE;
		double tip = subTotal * Bill.TIP_RATE;
    
		text.append("TAX - $")
        	.append(String.format("%.2f", tax))
        	.append(System.lineSeparator())
        	.append("TIP - $")
        	.append(String.format("%.2f", tip))
        	.append(System.lineSeparator())
        	.append("TOTAL - $")
        	.append(String.format("%.2f", subTotal + tax + tip));
    
    return text.toString();
	}
}
