package edu.ncsu.csc326.coffeemaker;

import org.junit.Before;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * @author Sarah E. Smith
 */
public class CoffeeMakerTestJumble extends TestCase {
	private CoffeeMaker cm;
	private Inventory i;
	private Recipe r1,r2,r3,r4,r5;
	
	public void setUp() {
		cm = new CoffeeMaker();
		i = cm.checkInventory();
		
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(6);
		r1.setAmtMilk(1);
		r1.setAmtSugar(3);
		r1.setAmtChocolate(2);

        r2 = new Recipe();
		r2.setName("Rezept2");
		r2.setPrice(50);
		r2.setAmtCoffee(6);
		r2.setAmtMilk(1);
		r2.setAmtSugar(1);
		r2.setAmtChocolate(0);

        r3 = new Recipe();
        r3.setName("Rezept3");
        r3.setPrice(50);
        r3.setAmtCoffee(6);
        r3.setAmtMilk(1);
        r3.setAmtSugar(1);
        r3.setAmtChocolate(0);

        r4 = new Recipe();
        r4.setName("Rezept4");
        r4.setPrice(50);
        r4.setAmtCoffee(6);
        r4.setAmtMilk(1);
        r4.setAmtSugar(1);
        r4.setAmtChocolate(0);

        r5 = new Recipe();
        r5.setName("Rezept5");
        r5.setPrice(50);
        r5.setAmtCoffee(10000);
        r5.setAmtMilk(1);
        r5.setAmtSugar(1);
        r5.setAmtChocolate(0);
	}

	public void testContructor(){
		CoffeeMaker cmNew = new CoffeeMaker();
		assertTrue(cmNew.addRecipe(r1));
		assertTrue(cmNew.addRecipe(r2));
		assertTrue(cmNew.addRecipe(r3));
		assertTrue(cmNew.addRecipe(r4));
		assertFalse(cmNew.addRecipe(r5));
	}
	
    public void testBezahlZuWenig() {
        cm.addRecipe(r1);       
        assertTrue(cm.makeCoffee(r1, 0) == 0);
    }
    
    public void testZuWenigZutaten() {
        cm.addRecipe(r5);
        assertTrue(cm.makeCoffee(r5, 100) == 100);
    }

	public void testAddRecipe() {
		assertTrue(cm.addRecipe(r1));
		assertFalse(cm.addRecipe(r1));
	}
    public void testAddRecipedouble() {
		assertTrue(cm.addRecipe(r1));
		assertFalse(cm.addRecipe(r1));
	}
    public void testAddRecipeMoreThanMax() {
		assertTrue(cm.addRecipe(r1));
		assertTrue(cm.addRecipe(r2));
		assertTrue(cm.addRecipe(r3));
		assertTrue(cm.addRecipe(r4));
		assertFalse(cm.addRecipe(r5));		
	}
    public void testAddRecipeEditMax() {
		assertTrue(cm.addRecipe(r1));
		assertTrue(cm.addRecipe(r2));
		assertTrue(cm.addRecipe(r3));
		assertTrue(cm.addRecipe(r4));
		assertFalse(cm.editRecipe(r1,r1));
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
        
        inventory.setChocolate(10);
        inventory.setCoffee(10);
        inventory.setMilk(0);
        inventory.setSugar(10);

        assertFalse(inventory.enoughIngredients(recipe));
        
        inventory.setChocolate(10);
        inventory.setCoffee(10);
        inventory.setMilk(10);
        inventory.setSugar(0);

        assertFalse(inventory.enoughIngredients(recipe));

    }
    public void testMakeCoffee(){
    	cm.makeCoffee(r1, 50);
    	Inventory i = cm.checkInventory();
    	
    	assertEquals(21, i.getCoffee()); //False Assert!!!
    	assertEquals(14, i.getMilk()); 
    	assertEquals(12, i.getSugar()); 
    	assertEquals(13, i.getChocolate()); 
    }

    public void testInventory() {
        Inventory inventory = new Inventory();

        inventory.setChocolate(1);
        inventory.setCoffee(2);
        inventory.setMilk(3);
        inventory.setSugar(4);

        assertEquals(inventory.getChocolate(),1);
        assertEquals(inventory.getCoffee(),2);
        assertEquals(inventory.getMilk(),3);
        assertEquals(inventory.getSugar(),4);

    }
    public void testAddInventory() {
    	cm.addInventory(1, 2, -3, 4);
        Inventory inventory = cm.checkInventory();
    	
        assertEquals(16,inventory.getCoffee());
        assertEquals(17,inventory.getMilk());
        assertEquals(12,inventory.getSugar());
        assertEquals(19, inventory.getChocolate());
    }
    public void testInventoryInvalidValues() {
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
        String name1 = r1.getName();
        String name2 = r4.getName();
        
    	cm.addRecipe(r1);
    	cm.addRecipe(r2);
    	cm.addRecipe(r3);
    	cm.addRecipe(r4);
    	
        assertEquals(name1, cm.getRecipeForName(name1).getName());
        assertEquals(name2, cm.getRecipeForName(name2).getName());
    }

    public void testAddInventoryNothing() {
        assertTrue(cm.addInventory(0, 0, 0, 0));
    }
    public void testAddInventorfalsePositiveSugar() {
        assertTrue(cm.addInventory(0, 0, -1, 0));
    }
    public void testAddInventorfalsePositiveMilk() {
        assertFalse(cm.addInventory(0, -1, 0, 0));
    }
    public void testAddInventorfalsePositiveChoco() {
        assertFalse(cm.addInventory(0, 0, 0, -1));
    }
    public void testAddInventorfalsePositiveCoffee() {
        assertFalse(cm.addInventory(-1, 0, 0, 0));
    }

    public void testEditRecipe(){    	
    	cm.deleteRecipe(r1);
    	cm.deleteRecipe(r2);
    	cm.deleteRecipe(r3);
    	cm.deleteRecipe(r4);
    	cm.deleteRecipe(r5);
    	    	 
    	
        Recipe r1New = new Recipe();
        r1New.setName("Coffee");
        r1New.setAmtChocolate(0);
        r1New.setAmtCoffee(3);
        r1New.setAmtMilk(3);
        r1New.setAmtSugar(1);
        r1New.setPrice(100);

        assertFalse(cm.editRecipe(r1New, r1));
        
        cm.addRecipe(r2);
        cm.addRecipe(r4);
        //cm.addRecipe(r5);
        cm.addRecipe(r1);
        //cm.deleteRecipe(r5);//False Assert: Ein leeren Platz lassen
        assertTrue(cm.editRecipe(r1, r1New));
        //assertFalse(cm.editRecipe(r1New, r1)); //False Assert: Platz wird nicht wirklich wieder freigegeben
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

    public void testEditRecipeIn() {
        cm.addRecipe(r1);
        assertTrue(cm.editRecipe(r1, r1));
    }

    public void testDeleteNull() {
        assertFalse(cm.deleteRecipe(null));
    }
    public void testDeleteRecipeDouble() {
        assertFalse(cm.deleteRecipe(r1));
    }
    public void testDeleteRecipe() {
        cm.addRecipe(r1);
        cm.addRecipe(r2);
        cm.addRecipe(r3);
        cm.addRecipe(r4);
        assertTrue(cm.deleteRecipe(r4));
        assertTrue(cm.deleteRecipe(r3));
    }

    public void testInventoryToString() {
    	String result = cm.checkInventory().toString();
    	assertEquals("Coffee: 15\n" +
    			"Milk: 15\n" +
    			"Sugar: 15\n" +
    			"Chocolate: 15\n", result);
    }
    public void testGetRecipes() {
        cm.getRecipes();
    }

    public void testRecipeToString() {
        assertEquals(r1.toString(), "Coffee");
    }
    public void testGetRecipeForNameNo() {
        cm.addRecipe(r1);
        assertTrue(cm.getRecipeForName("trololol").getName() == null);
    }
}
