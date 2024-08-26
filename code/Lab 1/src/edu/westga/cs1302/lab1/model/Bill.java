package edu.westga.cs1302.lab1.model;

import java.util.ArrayList;
import java.util.List;

/** Stores information for a bill.
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class Bill {
	
	public static final double TAX_RATE = 0.10;
	public static final double TIP_RATE = 0.20;
	
	private ArrayList<BillItem> items;
	
	/** Create a new empty Bill
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public Bill() {
		this.items = new ArrayList<BillItem>();
	}
	
	/** Adds the item to the bill
	 * 
	 * @precondition item != null
	 * @postcondition item is added to the list of items in the bill
	 * 
	 * @param item the item to be added to the bill
	 */
	public void addItem(BillItem item) {
		if (item == null) {
			throw new IllegalArgumentException("item must not be null.");
		}
		this.items.add(item);
	}
	
	/** Return a list of items in the bill
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a list of items in the bill
	 */
	public List<BillItem> getItems() {
		return new ArrayList<BillItem>(this.items);
	}
}
