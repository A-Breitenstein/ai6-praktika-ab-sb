/**
 * User: Alex
 * Date: 15.05.2014
 * Time: 23:52
 */
public class MMTestcaseGenerator {
    public static void main(String[] args) {
        int[] ergebnisvektor = new int[]{1, 0, 0, 0, 1, 0, 1, 0};
        Wahrheitstabelle t = Wahrheitstabelle.create(ergebnisvektor);

        t.evaluateTestcases();
        System.out.println(t);

        System.out.println("---Map---");
        t.printAsMap();

        System.out.println("---Map with Testcases---");
        t.printAsMapAndMarkedTestcases();
    }
}
