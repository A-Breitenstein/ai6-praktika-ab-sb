import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Alex
 * Date: 15.05.2014
 * Time: 23:51
 */
public class Wahrheitstabelle {
    private int[] ergebnisVektor;
    private int parameterAnzahl;
    private Set<Integer> testCases;
    private boolean testCasesCreated;

    final String __EXCEPTION_Ergebnisvektorinitialisierung = "ErgebnisVektor ist nicht richtig initialisiert: Ergebnisvektor ist null oder leer";

    private Wahrheitstabelle(int[] ergebnisVektor, int parameterAnzahl) {
        this.ergebnisVektor = ergebnisVektor;
        this.parameterAnzahl = parameterAnzahl;
        this.testCases = new HashSet<Integer>();
        this.testCasesCreated = false;
    }

    /**
     * Creates valid Testcases from information obtained of the ergebnisVektor
     *
     * @return Testcases as Set, or an empty Set if ergebnisVektor is not correct initialized
     */
    public Set<Integer> evaluateTestcases() {
        if (__checkErgebnisvektor()) {
            int currentVal = 0;
            int observedVal = 0;
            int validNeighbourPosition = 0;
            boolean validCurrentValue;

            for (int i = 0; i < this.ergebnisVektor.length - 1; i++) { //length-1 weil der die letzte stelle nicht interessiert

                validCurrentValue = false;
                currentVal = this.ergebnisVektor[i];

                for (int j = 0; j <= this.parameterAnzahl; j++) {
                    try {
                        validNeighbourPosition = flipBitAtPosition(i, j);
                        observedVal = this.ergebnisVektor[validNeighbourPosition];

                        if (currentVal != observedVal) {
                            testCases.add(Integer.valueOf(validNeighbourPosition));
                            validCurrentValue = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }

                if (validCurrentValue)
                    testCases.add(Integer.valueOf(i));
            }
            testCasesCreated = true;
            return testCases;
        }
        return new HashSet<Integer>();
    }

    public static Wahrheitstabelle create(int[] ergebnisvektor, int parameterAnzahl) {
        return new Wahrheitstabelle(ergebnisvektor, parameterAnzahl);
    }

    @Override
    public String toString() {
        return "Wahrheitstabelle{" +
                "ergebnisVektor=" + Arrays.toString(ergebnisVektor) +
                ", parameterAnzahl=" + parameterAnzahl +
                ", testCases=" + testCases +
                '}';
    }

    /**
     * Flips a Bit at a chosen position in the binary presentation
     * Example value = 1 (0b00000001), position = 3 -> return = 9 (0b00001001)
     *
     * @param value is the number which bit will get flipped, lol
     * @param position of the to be flipped bit
     * @return
     */
    public int flipBitAtPosition(int value, int position) {
        return value ^ (1 << position);
    }

    /**
     * For each index the print will look: eg. index = 0, result = 1 -> 1|000
     */
    public void printAsMap() {
        if (__checkErgebnisvektor()) {
            for (int i = 0; i < ergebnisVektor.length; i++) {
                System.out.println("" + ergebnisVektor[i] + "|" + resolveBitLenghtFault(i));
            }
        }
    }

    /**
     * Integer.toBinaryString() just prints relevant binary numbers, without leading zeros
     * This will add leading zeros, regarding the length of parameterAnzahl
     *
     * @param number has to be containing zeros and ones
     * @return String with length equal to the value of parameterAnzahl
     */
    public String resolveBitLenghtFault(int number) {
        String bitString = Integer.toBinaryString(number);
        final int neededZeros = parameterAnzahl - bitString.length();
        String zeros = "";

        for (int i = 0; i < neededZeros; i++) {
            zeros = zeros.concat("0");
        }

        return zeros.concat(bitString);
    }

    /**
     * For each index the print will look: eg. index = 0, result = 1 -> 1|000
     * If index is a Testcase, it will be marked with " -> T" for T is Testcase
     */
    public void printAsMapAndMarkedTestcases() {
        if (__checkErgebnisvektor()) {
            if (!testCasesCreated)
                evaluateTestcases();
            String output;
            for (int i = 0; i < ergebnisVektor.length; i++) {
                output = ergebnisVektor[i] + "|" + resolveBitLenghtFault(i);
                System.out.println(((testCases.contains(Integer.valueOf(i))) ? (output + " -> T") : (output)));
            }
        }
    }

    /**
     * __precondition:
     * Checks ergebnisVektor for faulty initialisations
     *
     * @return true if correct initialized
     */
    public boolean __checkErgebnisvektor() {
        if (ergebnisVektor == null || ergebnisVektor.length == 0) throw new IllegalStateException(__EXCEPTION_Ergebnisvektorinitialisierung);
        return true;
    }
}
