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
		
		String text = "ITEMS" + System.lineSeparator();
		List<BillItem> items = bill.getItems();
		double subTotal = 0.0;
		
		for (BillItem item : items) {
			text += item.getName() + " - " + item.getAmount() + System.lineSeparator();
			subTotal += item.getAmount();
		}
		
		text += System.lineSeparator();
		text += "SUBTOTAL - $" + subTotal + System.lineSeparator();
		double tax = subTotal * 0.1;
		double tip = subTotal * 0.2;
		text += "TAX - $" + tax + System.lineSeparator();
		text += "TIP - $" + tip + System.lineSeparator();
		text += "TOTAL - $" + (subTotal + tip + tax);
		
		return text;
	}
}
