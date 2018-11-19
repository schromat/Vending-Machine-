package com.techelevator.vendingprogram;

import java.io.File;

public class Program {

	public static void main(String[] args) {
		
		/*
		 * Here, we are instantiating a new menu object, taking in the path to our vending machine file, creating a
		 * new file object that points to that file path and then instantiating a new vending machine object and
		 * passing it that file to create our vending machine.
		 */
		Menu menu = new Menu();
		String path = "/Users/mschroeder/repos/capstoneVend/capstone/vendingmachine.csv";
		File newFile = new File(path);
		VendingMachine vm = new VendingMachine(newFile);
		menu.titleCard();
		
		/*
		 * Here is the main if statements of our program. We take in a user input on which menu option they would
		 * like to go to and then run the appropriate function from our menu object we created above. A few
		 * loops and if statements check to make sure the user has entered a valid selection and that the menu
		 * will continue to loop before the program exits.
		 */
		boolean finished = false;
		while (!finished) {
			
			menu.mainMenuGraphic();
			System.out.println();
			String selection = menu.getInputFromUser("Please select a menu: ");
			if (selection.equals("1")) {
				vm.printInventoryList();
			} else if (selection.equals("2")) {
				boolean done = false;
				while (!done) {
					menu.purchaseMenuGraphic();
					System.out.println();
					String purchaseSelection = menu.getInputFromUser("Please select an option: ");
					if(purchaseSelection.equals("1")) {
						menu.addFunds(vm);
					} else if(purchaseSelection.equals("2")) {
						menu.purchaseMenu(vm);
					} else if(purchaseSelection.equals("3")) {
						menu.finishTransaction(vm);
						done = true;
					} else {
						System.out.println("Invalid selection! Please make a new selection!");
					}
				}
			} else if (selection.equals("3")) {
				System.out.println("Please come again!");
				finished = true;
				vm.getLogs().makeSalesReport();
				System.exit(1);
			} else {
				System.out.println("Invalid selection! Please make a new selection!");
			}
			
		}
	}

}
