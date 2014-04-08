package corrected;

import corrected.exceptions.InventoryException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 25.03.14
 * Time: 14:41
 */
public class CoffeeMakerTestCorrected {

    private CoffeeMaker cm;
    private CoffeeMaker cm_recipe_test;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;

    @Before
    public void setUp() throws Exception {
        cm = new CoffeeMaker();

        // /Set up for r1
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
        // added only 3 recipes, so getRecipes must be 4
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
        assertEquals(cm_recipe_test.deleteRecipe(itemID),"");

        // status message missing on deleting recipes
    }

    @Test
    public void editRecipeNameNotNull(){
        cm.addRecipe(r1);
        String name = cm.editRecipe(0, r2);
        assertNotNull(name);
    }

    @Test
    public void editRecipeNameEqual(){
        cm.addRecipe(r1);
        String name = cm.editRecipe(0, r2);
        assertEquals(r1.getName(),name);
    }

    @Test
    public void editRecipeNameSameAsBefore(){
        cm.addRecipe(r1);
        cm.editRecipe(0, r2);
        Recipe editedRecipe = cm.getRecipes()[0];
        assertEquals(r1.getName(), editedRecipe.getName());
    }

    @Test
    public void editRecipeEdited(){
        cm.addRecipe(r1);
        cm.editRecipe(0, r2);

        Recipe editedRecipe = cm.getRecipes()[0];

        boolean setAmtChocolate = editedRecipe.getAmtChocolate() == r2.getAmtChocolate();
        boolean setAmtCoffee    = editedRecipe.getAmtCoffee() == r2.getAmtCoffee();
        boolean setAmtMilk      = editedRecipe.getAmtMilk() == r2.getAmtMilk();
        boolean setAmtSugar     = editedRecipe.getAmtSugar() == r2.getAmtSugar();
        boolean setPrice        = editedRecipe.getPrice() == r2.getPrice();

        assertTrue(setAmtChocolate);
        assertTrue(setAmtCoffee   );
        assertTrue(setAmtMilk     );
        assertTrue(setAmtSugar    );
        assertTrue(setPrice       );
    }


    @Test
    public void addInventoryAddNothing() throws Exception {
        cm.addInventory("0", "0", "0", "0");
    }
    @Test
    public void addInventoryAddCoffee() throws Exception {
        cm.addInventory("1","0","0","0");
    }
    @Test
    public void addInventoryAddMilk() throws Exception {
        cm.addInventory("0","1","0","0");
    }

    @Test(expected = InventoryException.class)
    public void addInventoryAddNegativeMilk() throws Exception {
        cm.addInventory("0","-1","0","0");
    }
    @Test
    public void addInventoryAddPositiveSugar() throws Exception {
            cm.addInventory("0","0","1","0");
    }

    @Test(expected = InventoryException.class)
    public void addInventoryAddNegativeSugar() throws Exception {
            cm.addInventory("0","0","-1","0");
    }

    @Test(expected = InventoryException.class)
    public void addInventoryAddNegativeCoffee() throws Exception {
            cm.addInventory("-1","0","0","0");
    }

    @Test
    public void addInventoryAddChocolate() throws Exception {
        cm.addInventory("0","0","0","1");
    }
    @Test(expected = InventoryException.class)
    public void addInventoryAddNegativeChocolate() throws Exception {
        cm.addInventory("0","0","0","-1");
    }

    @Test(expected = InventoryException.class)
    public void addInventoryAddEmptyStrings() throws Exception {
        cm.addInventory("","","","");
    }

    @Test(expected = InventoryException.class)
    public void addInventoryAddNull() throws Exception {
        cm.addInventory(null,null,null,null);
    }

    @Test
    public void addInventoryAddMaxInt() throws Exception {
        final int max_int = Integer.MAX_VALUE;
        String str_int = String.valueOf(max_int);

        cm.addInventory(str_int, str_int, str_int, str_int);
    }

    @Test(expected = InventoryException.class)
    public void testAddInventoryCharacters() throws Exception {
        cm.addInventory("a","b","c","d");
    }

    @Test(expected = InventoryException.class)
    public void testAddInventoryExceptionQuatsch() throws Exception{
            cm.addInventory("4", "-1", "asdf", "3");
    }

    @Test
    public void testCheckInventory() throws Exception {
        assertEquals("Coffee: 15\n" +
                "Milk: 15\n" +
                "Sugar: 15\n" +
                "Chocolate: 15\n", cm.checkInventory());
    }
    @Test
    public void testCheckInventoryAdd() throws Exception {
        cm.addInventory("1","2","3","4");
        assertEquals("Coffee: 16\n" +
                "Milk: 17\n" +
                "Sugar: 18\n" +
                "Chocolate: 19\n", cm.checkInventory());
    }

    @Test
    public void testMakeCoffee() throws Exception {
        cm.addRecipe(r1);
        assertEquals(Integer.MAX_VALUE-50, cm.makeCoffee(0, Integer.MAX_VALUE));
        assertEquals("Coffee: 12\n" +
                "Milk: 14\n" +
                "Sugar: 14\n" +
                "Chocolate: 15\n", cm.checkInventory());
    }
    @Test
    public void testMakeCoffee1() throws Exception {
        Recipe[] a = cm.getRecipes();
        assertEquals(0, cm.makeCoffee(2, 100));
        assertEquals("Coffee: 12\n" +
                "Milk: 12\n" +
                "Sugar: 14\n" +
                "Chocolate: 15\n", cm.checkInventory());
    }

    @Test
   public void testMakeCoffeeLessMoney() throws Exception {
        cm.addRecipe(r1);
        assertEquals(30, cm.makeCoffee(0, 30));
        assertEquals("Coffee: 15\n" +
                "Milk: 15\n" +
                "Sugar: 15\n" +
                "Chocolate: 15\n", cm.checkInventory());
    }

    @Test
   public void testMakeCoffeeWrongMoney() throws Exception {
        cm.addRecipe(r1);
        assertEquals(-3, cm.makeCoffee(0, -3));
        assertEquals("Coffee: 15\n" +
                "Milk: 15\n" +
                "Sugar: 15\n" +
                "Chocolate: 15\n", cm.checkInventory());
    }

   @Test
   public void TestMakeCoffeeLessInventory() throws Exception {
        cm.addRecipe(r1);
        cm.makeCoffee(0, 50);
        cm.makeCoffee(0, 50);
        cm.makeCoffee(0, 50);
        cm.makeCoffee(0, 50);
        cm.makeCoffee(0, 50);

        assertEquals(50, cm.makeCoffee(0, 50));

        assertEquals("Coffee: 0\n" +
                "Milk: 10\n" +
                "Sugar: 10\n" +
                "Chocolate: 15\n", cm.checkInventory());
    }



    @Test
    public void testGetRecipes() throws Exception {

    }

}
