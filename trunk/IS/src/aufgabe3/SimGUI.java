package aufgabe3;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static aufgabe3.SimMain.EdgeColor.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 11.05.14
 * Time: 12:53
 */
public class SimGUI extends JFrame {
    static int[][] points = {{100,310},{100,160},{250,60},
                             {400,160},{400,310},{250,410}};
    static Color NodeColor = new Color(6, 255, 2);
    static Color DefaultEdgeColor = new Color(0, 0, 0);
    static Color PlayerEdgeColor = new Color(3, 0, 255);
    static Color ComputerEdgeColor = new Color(255, 0, 34);
    static Color VerlorenColor = new Color(244, 3, 255);

    Map<String, SimMain.EdgeColor> graph = new HashMap<String, SimMain.EdgeColor>();

    public SimGUI() {
        setSize(640, 480);
        setName("SimGUI");
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D renderer = (Graphics2D) g;
        int nodeX, nodeY;

        for (Map.Entry<String, SimMain.EdgeColor> entry : graph.entrySet()) {
            Color lineColor = DefaultEdgeColor;
            if(entry.getKey() != null && entry.getKey().length() ==2){
                switch (entry.getValue()) {
                    case PLAYER:
                        lineColor = PlayerEdgeColor;
                        break;
                    case COMPUTER:
                        lineColor = ComputerEdgeColor;
                        break;
                }
                renderer.setColor(lineColor);
                renderer.setStroke(new BasicStroke(3));
                nodeX = Integer.valueOf(entry.getKey().substring(0, 1))-1;
                nodeY = Integer.valueOf(entry.getKey().substring(1, 2))-1;
                renderer.drawLine(points[nodeX][0],points[nodeX][1],points[nodeY][0],points[nodeY][1]);
            }
        }

        renderer.setColor(NodeColor);
        g.setFont(new Font("Arial", Font.PLAIN, 22));
        for (int i = 0; i < points.length; i++) {
            if (i < 3) {
                renderer.drawRect(points[i][0]-20, points[i][1]-20, 20, 20);
                renderer.drawString("" + (i + 1), points[i][0] + 5-20, points[i][1] + 18-20);
            }else {
                renderer.drawRect(points[i][0], points[i][1], 20, 20);
                renderer.drawString("" + (i + 1), points[i][0] + 5, points[i][1] + 18);
            }
        }
    }

    public void drawGraph(Map<String, SimMain.EdgeColor> graph) {
        this.graph = new HashMap<String, SimMain.EdgeColor>(graph);
        repaint();
    }

    public static void main(String[] args) {
        new SimGUI();
    }
}
