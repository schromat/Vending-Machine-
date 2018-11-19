package com.techelevator.vendingprogram;

public class Slot {
	
	private String slotNumber;
	private double price;
	private Item item;
	private int quantity;
	
	/**
	 * Creates a Slot object that takes in a slot number, price, and item object.
	 * @param slotNumber
	 * @param price
	 * @param item
	 */
	public Slot(String slotNumber, double price, Item item) {
		this.slotNumber = slotNumber;
		this.price = price;
		this.item = item;
		this.quantity = 5;
	}
	
	public String getSlotNumber() {
		return this.slotNumber;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Reduces the slot quantity by 1 and returns the item in the slot.
	 * @return
	 */
	public Item dispense() {
		this.quantity--;
		return this.item;
	}
	
	/**
	 * Returns the name string parameter of the item in the slot.
	 * @return
	 */
	public String getItemName() {
		return this.item.getName();
	}
	
	/**
	 * Returns the type string parameter of the item in the slot.
	 * @return
	 */
	public String getItemType() {
		return this.item.getType();
	}
	
	/**
	 * Returns the sound string parameter of the item in the slot.
	 * @return
	 */
	public String getItemSound() {
		return this.item.getSound();
	}
	
	
	/**
	 * Returns a string displaying information about this slot in the format: Slot Number | Item Name | Price | Quantity.
	 */
	@Override
	public String toString() {
		String formattedPrice = String.format("%.2f", getPrice());
		String slotNumber = String.format("%-10s", this.slotNumber);
		String name = String.format("%-30s", String.valueOf(getItemName()));
		String price = String.format("%-10s", formattedPrice);
		String quantity = String.format("%-10s", String.valueOf(getQuantity() + " left"));
		String soldOut = String.format("%-10s", "SOLD OUT");
		
		if(this.quantity > 0) {
			return slotNumber + name + "\t" + "$" + price + "\t" + quantity;
		} else {
			return slotNumber + name + "\t" + "$" + price + "\t" + soldOut;
		}
	}
	
}
