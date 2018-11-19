package com.techelevator.vendingprogram;

import java.util.Queue;
import java.util.Scanner;

public class Menu {
	
	//This class deals with most of the user input and checks to make sure that that input is valid.
	
	/**
	 * Used to display a message passed in and then retrieve input from the user.
	 */
	public static String getInputFromUser(String message) {
		Scanner inputReader = new Scanner(System.in);
		System.out.println(message);
		String userInput = inputReader.nextLine();
		return userInput;
	}
	
	/**
	 * When run, takes in user input and checks if that input is a 1, 2, 5, or 10. If it is it adds that number
	 * to the vending machines customerFunds variable, which represents the amount of money the customer can
	 * spend. If the user input is not equal to one of the above numbers we display a message asking for
	 * a valid input.
	 */
	public void addFunds(VendingMachine vm) {
		System.out.println();
		double money;
		String moneyString = getInputFromUser("Please enter how much money you would like to add: $");
		if(moneyString.equals("1")) {
			money = Double.parseDouble(moneyString);
			vm.addFunds(money);
			System.out.println("Added $1.00. Your current balance is $" + String.format("%.2f", vm.getCustomerFunds()));
		} else if(moneyString.equals("2")) {
			money = Double.parseDouble(moneyString);
			vm.addFunds(money);
			System.out.println("Added $2.00. Your current balance is $" + String.format("%.2f", vm.getCustomerFunds()));
		} else if(moneyString.equals("5")) {
			money = Double.parseDouble(moneyString);
			vm.addFunds(money);
			System.out.println("Added $5.00. Your current balance is $" + String.format("%.2f", vm.getCustomerFunds()));
		} else if(moneyString.equals("10")) {
			money = Double.parseDouble(moneyString);
			vm.addFunds(money);
			System.out.println("Added $10.00. Your current balance is $" + String.format("%.2f", vm.getCustomerFunds()));
		} else {
			System.out.println("We only accept denominations of $1, $2, $5 and $10!");
		}
	}
	
	/**
	 * When run, we first print out how many funds the customer has to spend from the customerFunds variable. Next
	 * we print the inventory list and ask for the users input in entering a slot number for a snack. We then check
	 * this input to see if it is a valid key for our inventory using a boolean check method in our vending machine,
	 * if the customer has enough funds to purchase the item and lastly if the item is sold out our not. If all three
	 * of those checks pass then we call our purchase item method from our vending machine.
	 * @param vm
	 */
	public void purchaseMenu(VendingMachine vm) {
		System.out.println("You have $" + String.format("%.2f", vm.getCustomerFunds()) + " left to spend.");
		System.out.println();
		vm.printInventoryList();
		System.out.println();
		String temp = getInputFromUser("Please enter a slot number: ");
		String userSelection = temp.toUpperCase();
		if(!vm.isValidSlot(userSelection)) {
			System.out.println("Please enter a valid slot number!");
		} else if(vm.getCustomerFunds() < vm.getSlotItemPrice(userSelection)) {
			System.out.println("You do not have enough funds! Please add money!");
		} else if(vm.getSlotItemQty(userSelection) == 0) {
			System.out.println("SOLD OUT! Please make a different selection!");
		} else {
			vm.purchaseItem(userSelection);
			System.out.println("Enjoy your " + vm.getSlotItemName(userSelection) + "!");
		}
	}
	
	/**
	 * When run, if the customerFunds variable in the vending machine is greater than 0, we return the customers
	 * change. We then check our customer purchases queue, which stores all the items the customer has purchased,
	 * if it equals 0, we prompt the user to buy something next time, if it is not equal to 0, we remove each item
	 * from the queue and print its related consume sound.
	 * @param vm
	 */
	public void finishTransaction(VendingMachine vm) {
		if(vm.getCustomerFunds() > 0) {
			double change = vm.getCustomerFunds();
			System.out.println("Please take your change: $" + String.format("%.2f", vm.giveChange()) + vm.calculateCoins(change));
		}
		Queue<Item> copyQueue = vm.getCustomerPurchases();
		if(copyQueue.size() == 0) {
			System.out.println("Buy something next time, will ya!");
		} else {
			System.out.println("Enjoy your snacks!");
			int counter = copyQueue.size();
			for(int i = 0; i < counter; i++) {
				String sound = copyQueue.remove().getSound();
				System.out.println(sound);
			}
			vm.clearCustomerPurchases();

		}
	
	}
	
	public void mainMenuGraphic() {
		System.out.println();
		System.out.println("        ********************************************************************");
		System.out.println("        $                            MAIN MENU                             $");
		System.out.println("        $                                                                  $");
		System.out.println("        $                1 >>> Display Vending Machine Items               $");
		System.out.println("        $                2 >>> Purchase                                    $");
		System.out.println("        $                3 >>> Quit                                        $");
		System.out.println("        $                                                                  $");
		System.out.println("        ********************************************************************");

	}
	
	public void purchaseMenuGraphic() {
		System.out.println();
		System.out.println("        ********************************************************************");
		System.out.println("        $                          PURCHASE MENU                           $");
		System.out.println("        $                                                                  $");
		System.out.println("        $                  1 >>> Feed Money                                $");
		System.out.println("        $                  2 >>> Select Product                            $");
		System.out.println("        $                  3 >>> Finish Transaction                        $");
		System.out.println("        $                                                                  $");
		System.out.println("        ********************************************************************");
	}
	
	public void titleCard() {
		System.out.println("                                                                                        ");
		System.out.println(" #     # ####### #     # ######  ### #     #  #####                                     ");
		System.out.println(" #     # #       ##    # #     #  #  ##    # #     #                                    ");
		System.out.println(" #     # #       # #   # #     #  #  # #   # #                                          ");
		System.out.println(" #     # #####   #  #  # #     #  #  #  #  # #  ####                                    ");
		System.out.println("  #   #  #       #   # # #     #  #  #   # # #     #                                    ");
		System.out.println("   # #   #       #    ## #     #  #  #    ## #     #                                    ");
		System.out.println("    #    ####### #     # ######  ### #     #  #####                                     ");
		System.out.println("                                                                                        ");
		System.out.println(" #     #    #     #####  #     # ### #     # #######     #####    ###     ###     ###   ");
		System.out.println(" ##   ##   # #   #     # #     #  #  ##    # #          #     #  #   #   #   #   #   #  ");
		System.out.println(" # # # #  #   #  #       #     #  #  # #   # #          #     # #     # #     # #     # ");
		System.out.println(" #  #  # #     # #       #######  #  #  #  # #####       ###### #     # #     # #     # ");
		System.out.println(" #     # ####### #       #     #  #  #   # # #                # #     # #     # #     # ");
		System.out.println(" #     # #     # #     # #     #  #  #    ## #          #     #  #   #   #   #   #   #  ");
		System.out.println(" #     # #     #  #####  #     # ### #     # #######     #####    ###     ###     ###   ");
		System.out.println("                                                                                        ");
	}

}
