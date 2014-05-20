/**
 * User: Alex
 * Date: 15.05.2014
 * Time: 23:52
 */
class MMTestcaseGenerator {
    public static void main(String[] args) {
        int[] ergebnisvektor = new int[]{1, 0, 0, 0, 1, 0, 1, 1};
        Wahrheitstabelle t = Wahrheitstabelle.create(ergebnisvektor);

        t.evaluateMehrfachTestcases();
        t.evaluateEinfachTestcases();
        System.out.println(t);

        System.out.println("---Map---");
        t.printAsMap();

        System.out.println("---Map with Testcases---");
        t.printAsMapAndMarkedTestcases();

        System.out.println("\nAbdeckung: " + t.prozentualeAbdeckung());
    }
}
