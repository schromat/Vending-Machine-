package com.techelevator.vendingprogram;

import java.io.File;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class VendingMachine {

	private SortedMap<String, Slot> inventory;
	private Queue<Item> customerPurchases = new LinkedList<Item>();
	private double customerFunds = 0;
	private Logs logs;

	/**
	 * Our Vending Machine constructor takes in a vending machine inventory file, scans it in line by 
	 * line, converts that data to Item and Slot objects, and puts them into our inventory TreeMap
	 * as well as our logs object's inventories.
	 * @param setupFile
	 */
	public VendingMachine(File setupFile) {
		inventory = new TreeMap<String, Slot>();
		logs = new Logs(setupFile.getParent());
		try (Scanner fileScanner = new Scanner(setupFile)) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] itemArray = line.split("\\|");
				Item newItem = new Item(itemArray[1], itemArray[3]);
				Slot newSlot = new Slot(itemArray[0], Double.parseDouble(itemArray[2]), newItem);
				inventory.put(newSlot.getSlotNumber(), newSlot);
				logs.setupLogsInventory(itemArray[1], Double.parseDouble(itemArray[2]));
			}
		} catch (Exception ex) {
			System.out.println("An exception occurred!");
			ex.printStackTrace();
		}
	}
	
	/**
	 * returns the inventory TreeMap object.
	 * @return
	 */
	public Map<String, Slot> getInventory() {
		return this.inventory;
	}
	
	/**
	 * returns the customerFunds balance as a double.
	 * @return
	 */
	public double getCustomerFunds() {
		return this.customerFunds;
	}
	
	/**
	 * returns a queue of item objects that the user has purchased.
	 * @return
	 */
	public Queue<Item> getCustomerPurchases() {
		return customerPurchases;
	}
	
	/**
	 * returns the log object instantiated in the vending machine.
	 * @return
	 */
	public Logs getLogs() {
		return this.logs;
	}
	
	/**
	 * Clears the customer purchases queue
	 */
	public void clearCustomerPurchases() {
		customerPurchases.clear();
	}
	
	/**
	 * Takes a slot number as input and returns the price of the corresponding slot.
	 * @param slotNumber
	 * @return
	 */
	public double getSlotItemPrice(String slotNumber) {
		Slot selectedSlot = inventory.get(slotNumber);
		return selectedSlot.getPrice();
	}

	/**
	 * Takes a slot number as input and returns the item name of the corresponding slot.
	 * @param slotNumber
	 * @return
	 */
	public String getSlotItemName(String slotNumber) {
		Slot selectedSlot = inventory.get(slotNumber);
		return selectedSlot.getItemName();
	}
	
	/**
	 * Takes a slot number as input and returns the quantity variable of the corresponding 
	 * slot.
	 * @param slotNumber
	 * @return
	 * 
	 */
	public int getSlotItemQty(String slotNumber) {
		Slot selectedSlot = inventory.get(slotNumber);
		return selectedSlot.getQuantity();
	}
	
	/**
	 * Takes a slot number as input, returns true if it is valid, false if it is not.
	 * @param userSelection
	 * @return
	 */
	public boolean isValidSlot(String userSelection) {
		Set<String> keySet = inventory.keySet();
		if (keySet.contains(userSelection)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Takes a slot number as input, calls the dispense method to add an item to our customerPurchases queue
	 * and reduce that slot quantity by 1, subtracts the slot price from the customers funds and writes to
	 * our log and sales reports.
	 * @param userSelection
	 */
	public void purchaseItem(String userSelection) {
		double fundsBeforePurchase = getCustomerFunds();
		Slot selection = inventory.get(userSelection);
		customerPurchases.add(selection.dispense());
		customerFunds -= selection.getPrice();
		logs.addToLogsInventory(selection.getItemName());
		logs.writeToLog(selection.getItemName(), selection.getSlotNumber(), fundsBeforePurchase, getCustomerFunds());
	}
	
	/**
	 * Takes a double as input, adds that amount to our customerFunds variable and writes to the log.
	 * @param feedMoney
	 */
	public void addFunds(Double feedMoney) {
		customerFunds += feedMoney;
		logs.writeToLog("FEED MONEY:", "", feedMoney, getCustomerFunds());
	}
	
	/**
	 * Assigns our customer funds to a temporary variable, sets our customer funds to 0, and returns the value
	 * of what was left in customer funds.
	 * @return
	 */
	public double giveChange() {
		double change = customerFunds;
		customerFunds = 0;
		logs.writeToLog("GIVE CHANGE:", "", change, getCustomerFunds());
		return change;
	}
	
	/**
	 * Calculates the amount of coins in a given amount of change passed in.
	 * @param change
	 * @return
	 */
	public String calculateCoins(double change) {
		int quarters = 0;
		int dimes = 0;
		int nickels = 0;
		int coinsLeft = (int)(change * 100);
		if(coinsLeft != 0 && coinsLeft/25 >= 1) {
			quarters = coinsLeft/25;
			coinsLeft -= quarters*25;
		}
		if(coinsLeft != 0 && coinsLeft/10 >= 1) {
			dimes = coinsLeft/10;
			coinsLeft -= dimes*10;
		}
		if(coinsLeft != 0 && coinsLeft/5 >= 1) {
			nickels = coinsLeft/5;
			coinsLeft -= coinsLeft*5;
		}
		return " (" + quarters + " quarters, " + dimes + " dimes & " + nickels + " nickels" + ")";
	}

	/**
	 * Will print out a list of all inventory items, prints out a header and then loops through every 
	 * slot object in the Vending Machine and prints out its toString method.
	 */
	public void printInventoryList() {
		System.out.println("        SLOT\t  NAME\t\t\t\tPRICE\t\tQTY");
		System.out.println("        ====================================================================");
		Set<String> keySet = inventory.keySet();
		for (String key : keySet) {
			Slot newSlot = inventory.get(key);
			System.out.println("        " + newSlot.toString());
		}
	}
}
