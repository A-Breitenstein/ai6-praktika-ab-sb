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
        assertTrue(cm.makeCoffee(r1, 50) == 0);

        Recipe recipe = new Recipe();
        recipe.setName("Coffee");
        recipe.setPrice(50);
        recipe.setAmtCoffee(6);
        recipe.setAmtMilk(1);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);

        Inventory inventory = new Inventory();

        assertTrue(inventory.enoughIngredients(recipe));

        inventory.setChocolate(0);
        inventory.setCoffee(0);
        inventory.setMilk(0);
        inventory.setSugar(0);

        assertFalse(inventory.enoughIngredients(recipe));

    }

    public void testInventory() {
        Inventory inventory = new Inventory();

        inventory.setChocolate(-5);
        inventory.setCoffee(-5);
        inventory.setMilk(-5);
        inventory.setSugar(-5);

        assertEquals(inventory.getChocolate(),0);
        assertEquals(inventory.getCoffee(),0);
        assertEquals(inventory.getMilk(),0);
        assertEquals(inventory.getSugar(),0);

    }
    public void testGetRecipeForName() {
        cm.addRecipe(r1);
        assertEquals(r1.getName(),cm.getRecipeForName(r1.getName()).getName());
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
    public void testNegativeRecipeItems(){
        Recipe r3 = new Recipe();
        r3.setAmtChocolate(-1);
        r3.setAmtCoffee(-1);
        r3.setAmtMilk(-1);
        r3.setAmtSugar(-1);
        r3.setPrice(-1);

        assertEquals(0, r3.getAmtChocolate());
        assertEquals(0, r3.getAmtCoffee());
        assertEquals(0, r3.getAmtMilk());
        assertEquals(0, r3.getAmtSugar());
        assertEquals(0, r3.getPrice());
    }
    public void testRecipeEquality(){
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        String recipeName = "Milk Coffee";

        assertFalse(r1.equals(r2));
        r2.setName(recipeName);
        assertFalse(r1.equals(r2));
        r1.setName(recipeName);

        assertTrue(r1.equals(r2));
        r2.setName("Coffee");
        assertFalse(r1.equals(r2));
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
