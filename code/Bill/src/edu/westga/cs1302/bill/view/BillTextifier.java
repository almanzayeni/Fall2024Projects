package edu.westga.cs1302.bill.view;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

/**
 * Generates text to summarize a Bill
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillTextifier {

    /**
     * Return a String summarizing the bill.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return a String summarizing the bill
     * @param bills the array of bills to be summarized
     */
    public static String getText(Bill[] bills) {
        if (bills == null) {
            throw new IllegalArgumentException("Must provide a valid Bill array");
        }

        String text = "ITEMS" + System.lineSeparator();

        for (Bill bill : bills) {
            if (bill != null) {
                for (BillItem item : bill.getItems()) {
                    if (item != null) {
                        text += item.getName() + " - " + BillTextifier.convertToCurrency(item.getAmount())
                                + System.lineSeparator();
                    }
                }
            }
        }

		text += System.lineSeparator();
		text += "SUBTOTAL - " + BillTextifier.convertToCurrency(BillCalculator.calculateSubTotal(bills)) + System.lineSeparator();
		text += "TAX - " + BillTextifier.convertToCurrency(BillCalculator.calculateTax(bills)) + System.lineSeparator();
		text += "TIP - " + BillTextifier.convertToCurrency(BillCalculator.calculateTip(bills)) + System.lineSeparator();
		text += "TOTAL - " + BillTextifier.convertToCurrency(BillCalculator.calculateTotal(bills));

		return text;
	}

	private static String convertToCurrency(double amount) {
		String result = "$";
		int number = ((int) (amount * 100));
		int dollars = number / 100;
		result += dollars;
		result += ".";
		int pennies = number - (dollars * 100);
		result += pennies;
		if (pennies == 0) {
			result += "0";
		}
		return result;
	}

}
