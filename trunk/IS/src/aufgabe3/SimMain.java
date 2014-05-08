package aufgabe3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static aufgabe3.SimMain.EdgeColor.*;
/**
 * Created by abg667 on 08.05.14.
 */
public class SimMain {

    Map<String, EdgeColor> graph = new HashMap<String, EdgeColor>();
    private boolean gewonnen = false;

    enum EdgeColor{
        NONE,PLAYER,COMPUTER,VERLOREN
    }

    public SimMain() {
        graph.put("12", NONE);
        graph.put("13", NONE);
        graph.put("14", NONE);
        graph.put("15", NONE);
        graph.put("16", NONE);
        graph.put("23", NONE);
        graph.put("24", NONE);
        graph.put("25", NONE);
        graph.put("26", NONE);
        graph.put("34", NONE);
        graph.put("35", NONE);
        graph.put("36", NONE);
        graph.put("45", NONE);
        graph.put("46", NONE);
        graph.put("56", NONE);
        graph.put(VERLOREN.name(), NONE);
    }

    public void gameLoop() {


        while (!gewonnen) {
            String kante = leseSpielerKante();
            EdgeColor color = graph.get(kante);

            if(color == null) continue;
            if (  color.equals(PLAYER) || color.equals(COMPUTER)) {
                System.out.println("Kante bereits gefärbt");continue;}

            graph.put(kante, PLAYER);

            if (kante.equals(VERLOREN.name())|| pruefeAufDreieck(kante)) {
                gewonnen = !gewonnen;
                System.out.println("Sie haben verloren, ERROS");
            } else {
                String besteKanteFuerComputer = simuliere(3);
                graph.put(besteKanteFuerComputer, COMPUTER);
            }

        }

    }

    private String simuliere(int tiefe) {

        for (Map.Entry<String, EdgeColor> stringEdgeColorEntry : graph.entrySet()) {
            if (stringEdgeColorEntry.getValue().equals(NONE)) {

            }
        }
        return null;
    }
    private int bewerteSpielSitutation(Map graph,EdgeColor fuerWenn) {

    }
    private boolean pruefeAufDreieck(String kante) {
        int x, y;
        x = Integer.parseInt(kante.substring(0, 1));
        y = Integer.parseInt(kante.substring(1, kante.length()));
        EdgeColor xi,yi;
        boolean esGibtEinDreieck =false;
        for (int i = 1; i < 7; i++) {
            if (!(i == x || i == y)) {
                xi = graph.get(createKante(x,i));
                yi = graph.get(createKante(y,i));

                if ((esGibtEinDreieck = xi.equals(PLAYER) && yi.equals(PLAYER)) && esGibtEinDreieck) {
                    System.out.println("ES GIBT EIN DREIECK LOL");
                    break;
                }
            }

        }
        return esGibtEinDreieck;
    }

    private String createKante(int x, int y) {
        return  "" + Math.min(x, y) + Math.max(x, y);
    }
    private String leseSpielerKante() {
        System.out.println("Geben Sie Start und Endknoten einer schwarzen Kante ein \"KnotenNummer;KnotenNummer\": ");
        String spielerKante = VERLOREN.name();
        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferRead.readLine();
            if (s.length() != 3) leseSpielerKante();

            String[] knoten = s.split(";");

           spielerKante = "" + Math.min(Integer.parseInt(knoten[0]), Integer.parseInt(knoten[1])) + Math.max(Integer.parseInt(knoten[0]), Integer.parseInt(knoten[1]));
        }
        catch(Exception e)
        {

        }
        return spielerKante;
    }

    public static void main(String[] args) {
        new SimMain().gameLoop();
    }
}
