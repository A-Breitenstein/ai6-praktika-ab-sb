package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 25.03.14
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class CoffeeMakerTest {

    private CoffeeMaker cm;
    private CoffeeMaker cm_recipe_test;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;

    @Before
    public void setUp() throws Exception {
        cm = new CoffeeMaker();

        //Set up for r1
        r1 = new Recipe();
        r1.setName("Coffee");
        r1.setAmtChocolate("0");
        r1.setAmtCoffee("3");
        r1.setAmtMilk("1");
        r1.setAmtSugar("1");
        r1.setPrice("50");

        //Set up for r2
        r2 = new Recipe();
        r2.setName("Mocha");
        r2.setAmtChocolate("20");
        r2.setAmtCoffee("3");
        r2.setAmtMilk("1");
        r2.setAmtSugar("1");
        r2.setPrice("75");

        //Set up for r3
        r3 = new Recipe();
        r3.setName("Latte");
        r3.setAmtChocolate("0");
        r3.setAmtCoffee("3");
        r3.setAmtMilk("3");
        r3.setAmtSugar("1");
        r3.setPrice("100");

        //Set up for r4
        r4 = new Recipe();
        r4.setName("Hot Chocolate");
        r4.setAmtChocolate("4");
        r4.setAmtCoffee("0");
        r4.setAmtMilk("1");
        r4.setAmtSugar("1");
        r4.setPrice("65");

        cm_recipe_test = new CoffeeMaker();

        cm_recipe_test.addRecipe(r1);
        cm_recipe_test.addRecipe(r2);
        cm_recipe_test.addRecipe(r3);

    }


    @Test
    public void testAddRecipe() throws Exception {
        // recipe must be unique
        assertFalse(cm_recipe_test.addRecipe(r1));
        // added only 3 recipes, so getRecipes must be 3
        assertEquals(3, cm.getRecipes().length);

        // recipe1 price must be 50
        assertEquals(50,r1.getPrice());
        assertEquals(0 ,r1.getAmtChocolate());
        assertEquals(3 ,r1.getAmtCoffee());
        assertEquals(1 ,r1.getAmtMilk());
        assertEquals(1 ,r1.getAmtSugar());

        // status message missing on adding recipes

    }

    @Test
    public void testDeleteRecipe() throws Exception {
        final Recipe[] recipes = cm_recipe_test.getRecipes();
        final int length = recipes.length;
        int itemID = 0;
        for (int i = 0; i < length; i++) {
            if (recipes[i] != null && recipes[i].getName().equals(r1.getName()))
                itemID = i;
        }
        assertEquals(r1.getName(), cm_recipe_test.deleteRecipe(itemID));
        assertTrue(cm_recipe_test.deleteRecipe(itemID) == null);

        // status message missing on deleting recipes
    }

    @Test
    public void testEditRecipe() throws Exception {
        cm.addRecipe(r1);
        String name = cm.editRecipe(0, r2);

        if (name == null) {
            fail("Name should be the name of the edited recipe");
        }

        if (!name.equals(r1.getName())) {
            fail("Name should equal the name of the edited recipe");
        }

        Recipe editedRecipe = cm.getRecipes()[0];

        boolean recipeName = editedRecipe.getName().equals(r1.getName());

        if (!recipeName) {
            fail("Name of the Receipe is not the same as before");
        }

        boolean setAmtChocolate = editedRecipe.getAmtChocolate() == r2.getAmtChocolate();
        boolean setAmtCoffee = editedRecipe.getAmtCoffee() == r2.getAmtChocolate();
        boolean setAmtMilk = editedRecipe.getAmtMilk() == r2.getAmtMilk();
        boolean setAmtSugar = editedRecipe.getAmtSugar() == r2.getAmtSugar();
        boolean setPrice = editedRecipe.getPrice() == r2.getPrice();

        if (!(setAmtChocolate && setAmtCoffee && setAmtMilk && setAmtSugar && setPrice)) {
            fail("Recipe was not edited");
        }
    }

    @Test
    public void testAddInventory() throws Exception {
        try {
            cm.addInventory("4","7","0","9");
        } catch (InventoryException e) {
            fail("InventoryException should not be thrown");
        }

    }
    @Test(expected = InventoryException.class)
    public void testAddInventoryException() throws Exception{
            cm.addInventory("4", "-1", "asdf", "3");
    }

    @Test
    public void testCheckInventory() throws Exception {

    }

    @Test
    public void testMakeCoffee() throws Exception {
        cm.addRecipe(r1);
        assertEquals(25, cm.makeCoffee(0, 75));
    }

    @Test
    public void testGetRecipes() throws Exception {

    }

}
