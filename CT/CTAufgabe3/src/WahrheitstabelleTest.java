import org.junit.Test;

import static org.junit.Assert.*;

public class WahrheitstabelleTest {

    //HF Sven

    @Test
    public void testEvaluateTestcases() throws Exception {
        Wahrheitstabelle wt1 = Wahrheitstabelle.create(new int[]{
                1, 0, 0, 0, 1, 0, 1, 0
        }, 3);
        assertEquals(wt1.evaluateTestcases().toString(),"[0, 1, 2, 4, 5, 6, 7]");

        Wahrheitstabelle wt2 = Wahrheitstabelle.create(new int[]{
                0, 1, 0, 0, 0, 0, 0, 0
        }, 3);
        assertEquals(wt2.evaluateTestcases().toString(),"[0, 1, 3, 5]");
    }

    @Test
    public void testFlipBitAtPosition() throws Exception {
        Wahrheitstabelle wt1 = Wahrheitstabelle.create(new int[]{
                0, 1, 1, 0, 1, 0, 1, 0
        }, 3);

        assertTrue(wt1.flipBitAtPosition(1, 3) == 9);
        assertFalse(wt1.flipBitAtPosition(1, 4) == 9);
    }

    @Test
    public void testResolveBitLenghtFault() throws Exception {
        Wahrheitstabelle wt1 = Wahrheitstabelle.create(new int[]{
                0, 1, 1, 0, 1, 0, 1, 0
        }, 3);

        assertTrue(wt1.resolveBitLenghtFault(7).equals("111"));
        assertTrue(wt1.resolveBitLenghtFault(6).equals("110"));
        assertTrue(wt1.resolveBitLenghtFault(5).equals("101"));
        assertTrue(wt1.resolveBitLenghtFault(4).equals("100"));
        assertTrue(wt1.resolveBitLenghtFault(3).equals("011"));
        assertTrue(wt1.resolveBitLenghtFault(2).equals("010"));
        assertTrue(wt1.resolveBitLenghtFault(1).equals("001"));
        assertTrue(wt1.resolveBitLenghtFault(0).equals("000"));

    }

    @Test
    public void test__checkErgebnisvektor() throws Exception {
        Wahrheitstabelle wt1 = Wahrheitstabelle.create(new int[]{
                0, 1, 1, 0, 1, 0, 1, 0
        }, 3);
        assertTrue(wt1.__checkErgebnisvektor());
    }

    @Test(expected = IllegalStateException.class)
    public void test__checkErgebnisvektorException_case1() throws Exception {
        Wahrheitstabelle wt1 = Wahrheitstabelle.create(new int[]{
                0, 1, 1, 0, 1, 0, 1, 0,0,0
        }, 3);

        wt1.__checkErgebnisvektor();
    }

    @Test(expected = IllegalStateException.class)
    public void test__checkErgebnisvektorException_case2() throws Exception {
        Wahrheitstabelle wt2 = Wahrheitstabelle.create(new int[]{

        }, 3);

        wt2.__checkErgebnisvektor();
    }

    @Test(expected = IllegalStateException.class)
    public void test__checkErgebnisvektorException_case3() throws Exception {
        Wahrheitstabelle wt3 = Wahrheitstabelle.create(null, 3);

        wt3.__checkErgebnisvektor();
    }
}