package edu.ncsu.csc326.coffeemaker;

import junit.framework.TestCase;

/**
 * @author Sarah E. Smith
 */
public class CoffeeMakerTest extends TestCase {
	private CoffeeMaker cm;
	private Inventory i;
	private Recipe r1;
	
	public void setUp() {
		cm = new CoffeeMaker();
		i = cm.checkInventory();
		
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(6);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(0);
	}
	
	public void testAddRecipe() {
		assertTrue(cm.addRecipe(r1));
	}

}
