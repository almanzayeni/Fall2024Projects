package edu.westga.cs1302.bill.model;

/**
 * Calculates the Bill
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class BillCalculator {
	
	/**
	 * Calculate the subtotal for the given bills.
	 * 
	 * @precondition bills != null
	 * @postcondition returns sum of all non-null item amounts in array
	 * 				  or 0.0 if there are no items.
	 * @param bills an array of Bill objects
	 * @return the subtotal of the bills
	 */
	public static double calculateSubTotal(Bill[] bills) {
		if (bills == null) {
			throw new IllegalArgumentException("Bill can't be null");
		}
		
		double subTotal = 0.0;
		for (Bill bill : bills) {
			if (bill != null) {
				for (BillItem item : bill.getItems()) {
					if (item != null) {
						subTotal += item.getAmount();
					}
				}
			}
		} return subTotal;
	}
	
	/**
	 * Calculates the tax for the bill
	 * 
	 * @precondition bills!= null
	 * @postcondition return 10% of the bill
	 * 
	 * @param bills an array of Bill objects
	 * @return the tax for the bills 
	 */
	public static double calculateTax(Bill[] bills) {
		return calculateSubTotal(bills) * 0.1;
	}
	
	/**
	 * Calculates the tip for the bill
	 * 
	 * @precondition bills!= null
	 * @postcondition return 20% of the bill
	 * 
	 * @param bills an array of Bill objects
	 * @return the tip for the bills 
	 */
	public static double calculateTip(Bill[] bills) {
		return calculateSubTotal(bills) * 0.2;
	}
	
	/**
	 * Calculates the tip for the bill
	 * 
	 * @precondition bills!= null
	 * @postcondition return 20% of the bill
	 * 
	 * @param bills an array of Bill objects
	 * @return the tip for the bills 
	 */
	public static double calculateTotal(Bill[] bills) {
		return calculateSubTotal(bills) + calculateTax(bills) + calculateTip(bills);
	}
	
}
