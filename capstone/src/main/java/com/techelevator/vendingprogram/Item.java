package com.techelevator.vendingprogram;

public class Item {

	private String name;
	private String type;
	
	public Item(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getSound() {
		if (type.equals("Chip")) {
			return "Crunch Crunch, Yum!";
		}
		if (type.equals("Candy")) {
			return "Munch Munch, Yum!";
		}
		if (type.equals("Drink")) {
			return "Glug Glug, Yum!";
		}
		if (type.equals("Gum")) {
			return "Chew Chew, Yum!";
		}
		return "";
	}

}
	
