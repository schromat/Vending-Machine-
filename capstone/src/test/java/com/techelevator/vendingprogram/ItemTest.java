package com.techelevator.vendingprogram;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	
	Item test;
  
	@Before
    public void startTest() {
		test = new Item("Trident", "Gum");
	}
    
	@After
	public void endTest() {
		test = null;
	}
	@Test
	public void test() {
		assertEquals("Gum should say Chew Chew, Yum!" , "Chew Chew, Yum!", test.getSound());
		test = new Item("Potato chips" , "Chip");
		assertEquals("Chips should say Crunch, Crunch, Yum!" , "Crunch Crunch, Yum!", test.getSound());
		test = new Item("Wonka Bar", "Candy");
		assertEquals("Candy should say Munch Munch, Yum!", "Munch Munch, Yum!", test.getSound());
		test = new Item("Cola", "Drink");
		assertEquals("Drinks should say Glug Glug, Yum!", "Glug Glug, Yum!", test.getSound());
		
	}

}
