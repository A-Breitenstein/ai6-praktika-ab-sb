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

    public void testEnoughIngredients(){
        assertTrue(cm.makeCoffee(r1,50) == 0);
    }
    public void testGetRecipeForName() {
        assertEquals(r1.getName(),cm.getRecipeForName(r1.getName()));
    }

    public void testAddInventoryNothing() {
        cm.addInventory(0, 0, 0, 0);
    }

    public void testEditRecipe(){
        Recipe r3 = new Recipe();
        r3.setName("Latte");
        r3.setAmtChocolate(0);
        r3.setAmtCoffee(3);
        r3.setAmtMilk(3);
        r3.setAmtSugar(1);
        r3.setPrice(100);

        cm.addRecipe(r1);
        assertTrue(cm.editRecipe(r1, r3));
        assertTrue(cm.editRecipe(r3, r1));
    }

    public void testDeleteRecipe() {
        cm.deleteRecipe(r1);
        assertFalse(cm.deleteRecipe(r1));
    }

    public void testInventoryToString() {
        cm.checkInventory().toString();
    }
    public void testGetRecipes() {
        cm.getRecipes();
    }
}
