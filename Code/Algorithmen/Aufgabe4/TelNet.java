import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Font;

public class TelNet {

    private Map<TelKnoten, Integer> telKnoten;
    private List<TelVerbindung> optTelNet;
    private int lbg;
    private int size;

    public TelNet(int lbg) {
        telKnoten = new HashMap<>();
        optTelNet = new ArrayList<>();
        this.lbg = lbg;
        size = 0;
    }

    public boolean addTelKnoten(int x, int y) {
        TelKnoten tk = new TelKnoten(x, y);
        if (!telKnoten.containsKey(tk)) {
            telKnoten.put(tk, size++);
            return true;
        }
        return false;
    }

    /**
     * Berechnet ein optimales Telefonnetz als minimal aufspannenden
     * Baum mit dem Algorithmus von Kruskal.
     * @return Liste von Telefonverbindungen.
     */
    public boolean computeOptTelNet() {
        List<TelVerbindung> edges = new ArrayList<>();
        for (TelKnoten tk1 : telKnoten.keySet()) {
            for (TelKnoten tk2 : telKnoten.keySet()) {
                if (tk1.equals(tk2)) {
                    continue;
                }
                int cost = Math.abs(tk1.getX() - tk2.getX())
                        + Math.abs(tk1.getY() - tk2.getY());
                if (cost <= lbg) {
                    edges.add(new TelVerbindung(tk1, tk2, cost));
                }
            }
        }

        edges.sort((e1, e2) -> e1.cost - e2.cost);

        UnionFind<TelKnoten> uf = new UnionFind<>(telKnoten.keySet());
        for (TelVerbindung edge : edges) {
            TelKnoten start = edge.start;
            TelKnoten end = edge.end;
            if (uf.find(start).equals(uf.find(end))) {
                continue;
            }
            uf.union(start, end);
            optTelNet.add(edge);
        }

        return true;
    }

    /**
     * Zeichnet das gefundene optimale Telefonnetz mit der Größe xMax*yMax in ein Fenster.
     * @param xMax Maximale x-Größe
     * @param yMax Maximale y-Größe
     */
    public void drawOptTelNet(int xMax, int yMax, int size) {
        StdDraw.setCanvasSize(size, size);
        StdDraw.setXscale(0, xMax);
        StdDraw.setYscale(0, yMax);

        int step = Math.max(1, Math.max(xMax, yMax) / 10);

        // Drawing the Grid
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005 * (size / 700.0));
        StdDraw.setFont(new Font("Serif", Font.PLAIN, size / 20)); // Set font size for coordinates
        // Draw x and y axis
        StdDraw.line(-0.5, -0.5, xMax + 0.5, -0.5); // x-axis
        StdDraw.line(-0.5, -0.5, -0.5, yMax + 0.5); // y-axis

        // Draw grid lines and labels
        for (int i = 0; i <= xMax + 1; i += step) {
            StdDraw.line(i - 0.5, -0.5, i - 0.5, yMax + 0.5); // vertical grid lines
            if (i > 0) {
                StdDraw.text(i, 0, String.valueOf(i)); // x-axis labels moved upwards
            }
        }

        for (int i = 0; i <= yMax + 1; i += step) {
            StdDraw.line(-0.5, i - 0.5, xMax + 0.5, i - 0.5); // horizontal grid lines
            if (i > 0) {
                StdDraw.text(0, i, String.valueOf(i)); // y-axis labels moved to the right
            }
        }

        StdDraw.setPenRadius(0.005);

        // Draw squares first
        for (TelVerbindung tv : optTelNet) {
            int x1 = tv.start.getX();
            int y1 = tv.start.getY();
            int x2 = tv.end.getX();
            int y2 = tv.end.getY();
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledSquare(x1, y1, .5);
            StdDraw.filledSquare(x2, y2, .5);

            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x1, y1, 0.2);
            StdDraw.filledCircle(x2, y2, 0.2);
        }

        // Draw paths on top
        for (TelVerbindung tv : optTelNet) {
            int x1 = tv.start.getX();
            int y1 = tv.start.getY();
            int x2 = tv.end.getX();
            int y2 = tv.end.getY();
            // Draw Manhattan path
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x1, y1, x2, y1);
            StdDraw.line(x2, y1, x2, y2);
            // Render the cost next to the line
            if (step == 1) {
                double midX = (x1 + x2) / 2.0;
                double midY = (y1 + y2) / 2.0;
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.text(midX + 0.2, midY + 0.3, String.valueOf(tv.cost));
            }
        }
        StdDraw.show(0);
    }

    /**
     * Fügt n zufällige Telefonknoten zum Netz dazu mit x-Koordinate aus [0,xMax] und y-Koordinate aus [0,yMax].
     * @param n
     * @param xMax
     * @param yMax
     */
    public void generateRandomTelNet(int n, int xMax, int yMax) {
        int i = 0;
        while (i < n) {
            int x = (int) (Math.random() * (xMax + 1));
            int y = (int) (Math.random() * (yMax + 1));
            if (addTelKnoten(x, y)) {
                i++;
            }
        }
    }

    public List<TelVerbindung> getOptTelNet() {
        return optTelNet;
    }

    public int getOptTelNetKosten() {
        int cost = 0;
        for (TelVerbindung tv : optTelNet) {
            cost += tv.cost;
        }
        return cost;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return null;
    }

    public static void main(String[] args) {
        test();
        // randomTest();
    }

    private static void test() {
        TelNet tn = new TelNet(7);
        tn.addTelKnoten(1, 1);
        tn.addTelKnoten(3, 1);
        tn.addTelKnoten(4, 2);
        tn.addTelKnoten(3, 4);
        tn.addTelKnoten(2, 6);
        tn.addTelKnoten(4, 7);
        tn.addTelKnoten(7, 6);

        tn.computeOptTelNet();
        System.out.println("Optimales Telefonnetz:");
        for (TelVerbindung tv : tn.getOptTelNet()) {
            System.out.println(tv);
        }
        System.out.println("Kosten: " + tn.getOptTelNetKosten());
        tn.drawOptTelNet(7, 7, 700);
    }

    private static void randomTest() {
        int xMax = 1000;
        int yMax = 1000;
        TelNet tn = new TelNet(100);
        tn.generateRandomTelNet(1000, xMax, yMax);
        tn.computeOptTelNet();
        System.out.println("Optimales Telefonnetz:");
        for (TelVerbindung tv : tn.getOptTelNet()) {
            System.out.println(tv);
        }
        System.out.println("Kosten: " + tn.getOptTelNetKosten());
        tn.drawOptTelNet(xMax, yMax, 800);
    }
}