package com.techelevator.vendingprogram;

import java.util.Date;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Logs {

	private String filePath;
	private SortedMap<String, Integer> inventoryQty = new TreeMap<String, Integer>();
	private SortedMap<String, Double> inventoryPrices = new TreeMap<String, Double>();
	
	/**
	 * Our logs constructor takes in a filePath String from our vending machine constructor's setupFile, to
	 * be used for creating and outputting our log files in the same directory as the setupFile of our
	 * vending machine constructor.
	 * @param filePath
	 */
	public Logs(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * Takes in a name and price and adds them to the log objects inventoryQty map and inventoryPrices map.
	 */
	public void setupLogsInventory(String name, double price) {
		this.inventoryQty.put(name, 0);
		this.inventoryPrices.put(name, price);
	}

	/**
	 * Updates the quantity of the value of the passed in name by 1 in the inventoryQty map to keep
	 * track of how many total items by that name have been sold.
	 * @param name
	 */
	public void addToLogsInventory(String name) {
		int oldValue = inventoryQty.get(name);
		int newValue = oldValue + 1;
		inventoryQty.put(name, newValue);
	}
	
	/**
	 * Creates a txt file containing the total quantity sold of each item and total sales combined of all items.
	 */
	public void makeSalesReport() {
		File salesReport = new File(filePath, "SalesReport.txt");
		Set<String> keySet = inventoryQty.keySet();
		double totalSum = 0;
		try(PrintWriter pw = new PrintWriter(salesReport)) {
			pw.println("SALES REPORT");
			pw.println("============");
			for(String name : keySet) {
				int qty = inventoryQty.get(name);
				double price = inventoryPrices.get(name);
				totalSum += qty * price;
				pw.println(name + "|" + qty);
			}
			pw.println();
			pw.println("**TOTAL SALES**  $" + String.format("%.2f", totalSum));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Appends to a txt file with date and time, product name or action taken, funds before and funds after.
	 * @param name
	 * @param slotNumber
	 * @param beforeDbl
	 * @param afterDbl
	 */
	public void writeToLog(String name, String slotNumber, double beforeDbl, double afterDbl) {
		File log = new File(filePath, "Log.txt");
		String before = String.format("%.2f", beforeDbl);
		String after = String.format("%.2f", afterDbl);
		String nameAndSlotNumberFormatted = String.format("%-20s", name + " " +slotNumber);
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		Date date = new Date();
		String dateString = sdf.format(date);
		String logEntry = dateString + " " + nameAndSlotNumberFormatted + "\t" + "$" + before + "\t" + "$" + after;
		try(FileWriter fw = new FileWriter(log, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);) {
			pw.println(logEntry);				
		} catch(IOException ex) {
				ex.printStackTrace();
		}
	}
	
}
