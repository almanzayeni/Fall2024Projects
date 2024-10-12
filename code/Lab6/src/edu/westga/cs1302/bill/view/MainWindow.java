package edu.westga.cs1302.bill.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillAscendingComparator;
import edu.westga.cs1302.bill.model.BillDescendingComparator;
import edu.westga.cs1302.bill.model.BillPersistenceManager;
import edu.westga.cs1302.bill.model.CSVBillPersistenceManager;
import edu.westga.cs1302.bill.model.TSVBillPersistenceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The codebehind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	private Bill bill;

	@FXML
	private TextField name;
	@FXML
	private TextField amount;
	@FXML
	private TextArea receiptArea;
	@FXML
	private ComboBox<String> serverName;
	@FXML
	private ComboBox<BillPersistenceManager> format;
    @FXML
    private ComboBox<Comparator<BillItem>> order;

	@FXML
	void addItem(ActionEvent event) {
		try {
			BillItem item = new BillItem(this.name.getText(), Double.parseDouble(this.amount.getText()));
			this.bill.addItem(item);
			this.name.clear();
			this.amount.clear();
			this.updateReceipt();
		} catch (NumberFormatException numError) {
			this.displayErrorPopup("Invalid amount provided, please correct and try again.");
		} catch (IllegalArgumentException argError) {
			this.displayErrorPopup("Unable to add new bill item");
		}
	}

	private void updateReceipt() {
		this.receiptArea.setText(BillTextifier.getText(this.bill));
	}

	@FXML
	void selectServer(ActionEvent event) {
		String name = this.serverName.getValue();
		if (name != null) {
			this.bill.setServerName(name);
			this.updateReceipt();
		}
	}

	@FXML
	void saveBillData(ActionEvent event) {
		BillPersistenceManager manager = this.format.getValue();
		if (manager == null) {
			this.displayErrorPopup("Please select a file format to save the bill data");
			return;
		}
		
		try {
			manager.saveBillData(this.bill);
		} catch (IOException writeError) {
			this.displayErrorPopup("Unable to save data to file!");
		}
	}

	private void displayErrorPopup(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
    @FXML
    void changeFormat(ActionEvent event) {
    	this.saveBillData(event);
    }
    
    @FXML
    void changeOrder(ActionEvent event) {
    	this.sortBillItems();
    	this.updateReceipt();
    }

	@FXML
	void initialize() {
		this.serverName.getItems().addAll("Bob", "Alice", "Trudy");

		this.format.getItems().addAll(new CSVBillPersistenceManager(), new TSVBillPersistenceManager());

		this.bill = new CSVBillPersistenceManager().loadBillData();
		this.updateReceipt();
		
		this.order.getItems().add(new BillAscendingComparator());
	    this.order.getItems().add(new BillDescendingComparator());
		this.order.setValue(this.order.getItems().get(0));
	}
	
	private void sortBillItems() {
        BillItem[] items = this.bill.getItems();
        Arrays.sort(items, this.order.getValue());

        this.bill = new Bill();
        for (BillItem item : items) {
            this.bill.addItem(item);
        }
    }
}
