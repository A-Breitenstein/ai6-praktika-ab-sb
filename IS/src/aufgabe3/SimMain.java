package aufgabe3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static aufgabe3.SimMain.EdgeColor.*;
/**
 * Created by abg667 on 08.05.14.
 */
public class SimMain {

    final int __DEPTH_END = 0;
    final int __DEPTH_CALC = 3;
    final int __MINIMUM_EDGES = 1;
    Map<String, EdgeColor> graph = new HashMap<String, EdgeColor>();
    private boolean gewonnen = false;
    SimGUI simGUI;
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

        simGUI = new SimGUI();
        simGUI.drawGraph(graph);
    }

    public void gameLoop() {


        while (!gewonnen) {
            String kante = leseSpielerKante();
            EdgeColor color = graph.get(kante);

            if(color == null) continue;
            if (  color.equals(PLAYER) || color.equals(COMPUTER)) {
                System.out.println("Kante bereits gefärbt");continue;}

            graph.put(kante, PLAYER);
            simGUI.drawGraph(graph);

            if (kante.equals(VERLOREN.name())|| pruefeAufDreieck(kante,graph,PLAYER)) {
                gewonnen = !gewonnen;
                System.out.println("Sie haben verloren, ERROS");
            } else {
                String besteKanteFuerComputer = simuliere(__DEPTH_CALC);
                graph.put(besteKanteFuerComputer, COMPUTER);
                System.out.println("COM malt: "+besteKanteFuerComputer);
            }

            simGUI.drawGraph(graph);


        }

    }

    /**
     * nur zum testen damit der computer irgendwas anmalt.
     * @return
     */
    private String chooseNONEColoredRandomEdge() {

        for (Map.Entry<String, EdgeColor> stringEdgeColorEntry : graph.entrySet()) {
            if(stringEdgeColorEntry.getValue().equals(NONE) && stringEdgeColorEntry.getKey().length() == 2)
                return stringEdgeColorEntry.getKey();

        }
        return "12";
    }
    private String simuliere(int tiefe) {

        String[] bestEdge = new String[1];

        int bestEvaluation = Integer.MIN_VALUE;
        int val;

        int[] alpha = new int[1];
        int[] beta = new int[1];
        alpha[0] = Integer.MIN_VALUE;
        beta[0] = Integer.MAX_VALUE;

        maxAB(new HashMap<String, EdgeColor>(graph), alpha, beta, tiefe, bestEdge);

        //fixed durch openedges <= 1 statt 2
        //fix, aber schlecht, sollte in die maxAB funktion
//        if (bestEdge[0] == null) {
//            for (String edge : retrieveOpenEdges(graph)) {
//                if (!pruefeAufDreieck(edge, graph, COMPUTER)) {
//                    bestEdge[0] = edge;
//                }
//            }
//        }

        return bestEdge[0];
    }

    private List<String> retrieveOpenEdges(Map<String, EdgeColor> graph) {

        List<String> openEdges = new ArrayList<String>();

        for (String key : graph.keySet()) {
            if(graph.get(key).equals(EdgeColor.NONE) && !key.equals(VERLOREN.name())) openEdges.add(key);
        }

        return openEdges;
    }

    private int maxAB(Map<String, EdgeColor> g, int[] alpha, int[] beta, int tiefe, String[] bestEdge) {
        List<String> openEdges = retrieveOpenEdges(g);
        if (tiefe == __DEPTH_END || openEdges.size() <= __MINIMUM_EDGES) {
            return evaluierSituation(g);
        }

        int best = Integer.MIN_VALUE;
        int val;
        boolean allEdgesDreiecks = true;

        Map<String,EdgeColor> newMapSituation;
        for (String openEdge : openEdges) {

            newMapSituation = new HashMap<String, EdgeColor>(g);

            if (!pruefeAufDreieck(openEdge, g, COMPUTER)) {

                allEdgesDreiecks = false;
                newMapSituation.put(openEdge, COMPUTER);
                if (best > alpha[0]) alpha[0] = best;


                val = minAB(newMapSituation, alpha, beta, tiefe - 1);

                if(tiefe == __DEPTH_CALC)
                    System.out.println("Tiefe(" + tiefe + "), Kante: " + openEdge + ", Bewertung: " + val);

                if (val > best) {
                    best = val;
                    bestEdge[0] = openEdge;
                }

                if (best >= beta[0]) return best;
            }
        }

        if (allEdgesDreiecks)
            return evaluierSituation(g); //Fix für leaf, returned sonst best, weil alle sonstigen züge zum verlust führen

        return best;
    }

    private int minAB(Map<String, EdgeColor> g, int[] alpha, int[] beta, int tiefe) {
        List<String> openEdges = retrieveOpenEdges(g);
        if (tiefe == __DEPTH_END || openEdges.size() <= __MINIMUM_EDGES) {
            return evaluierSituation(g);
        }

        int best = Integer.MAX_VALUE;
        int val;
        boolean allEdgesDreiecks = true;

        Map<String,EdgeColor> newMapSituation;

        for (String openEdge : openEdges) {

            newMapSituation = new HashMap<String, EdgeColor>(g);

            if (!pruefeAufDreieck(openEdge, g, PLAYER)) {

                allEdgesDreiecks = false;

                newMapSituation.put(openEdge, PLAYER);
                if (best < beta[0]) beta[0] = best;


                val = maxAB(newMapSituation, alpha, beta, tiefe - 1, new String[1]);

                if(tiefe == __DEPTH_CALC-1)
                    System.out.println("Tiefe(" + tiefe + "), Kante: " + openEdge + ", Bewertung: " + val);

                if (val < best) {
                    best = val;
                }

                if (alpha[0] >= best) return best;
            }
        }

        if (allEdgesDreiecks)
            return evaluierSituation(g); //Fix für leaf, returned sonst best, weil alle sonstigen züge zum verlust führen

        return best;
    }

    private int getMoves(List<String> openEdges, Map<String, EdgeColor> g, EdgeColor edgeColor) {

        int moves = 0;
        for (String openEdge : openEdges) {
            if (!pruefeAufDreieck(openEdge,g, edgeColor)) {
                moves++;
            }
        }

        return moves;
    }

    private int evaluierSituation(Map<String, EdgeColor> g) {
        List<String> openEdges = retrieveOpenEdges(g);

        int playerMoves = 0, comMoves = 0;
        playerMoves = getMoves(openEdges, g, PLAYER);
        comMoves = getMoves(openEdges, g, COMPUTER);

        return comMoves - playerMoves;
    }

    
    private boolean pruefeAufDreieck(String kante, Map<String,EdgeColor> g, EdgeColor edgeColor) {
        int x, y;
        x = Integer.parseInt(kante.substring(0, 1));
        y = Integer.parseInt(kante.substring(1, kante.length()));
        EdgeColor xi,yi;
        boolean esGibtEinDreieck =false;
        for (int i = 1; i < 7; i++) {
            if (!(i == x || i == y)) {
                xi = g.get(createKante(x,i));
                yi = g.get(createKante(y,i));

                if ((esGibtEinDreieck = xi.equals(edgeColor) && yi.equals(edgeColor)) && esGibtEinDreieck) {
//                    System.out.println("ES GIBT EIN DREIECK LOL");
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
