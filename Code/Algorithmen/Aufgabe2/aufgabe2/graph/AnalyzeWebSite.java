// O. Bittel;
// 2.8.2023

package graph;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;

/**
 * Klasse zur Analyse von Web-Sites.
 *
 * @author Oliver Bittel
 * @since 30.10.2023
 */
public class AnalyzeWebSite {
    public static void main(String[] args) throws IOException {
        // Graph aus Website erstellen und ausgeben:
        //DirectedGraph<String> webSiteGraph = buildGraphFromWebSite("data/WebSiteKlein");
        DirectedGraph<String> webSiteGraph = buildGraphFromWebSite(
                "data/WebSiteGross");

        System.out.println(
                "Anzahl Seiten: \t" + webSiteGraph.getNumberOfVertexes());
        System.out
                .println("Anzahl Links: \t" + webSiteGraph.getNumberOfEdges());
        // System.out.println(webSiteGraph);

        // Starke Zusammenhangskomponenten berechnen und ausgeben
        StrongComponents<String> sc = new StrongComponents<>(webSiteGraph);
        System.out.println("Anzahl der strengen Zusammenhangskomponenten: "
                + sc.numberOfComp());
        // System.out.println(sc);

        // Page Rank ermitteln und Top-100 ausgeben
        pageRank(webSiteGraph);
    }

    /**
     * Liest aus dem Verzeichnis dirName alle Web-Seiten und
     * baut aus den Links einen gerichteten Graphen.
     *
     * @param dirName Name eines Verzeichnis
     * @return gerichteter Graph mit Namen der Web-Seiten als Knoten und Links als gerichtete Kanten.
     */
    private static DirectedGraph<String> buildGraphFromWebSite(String dirName)
            throws IOException {
        File webSite = new File(dirName);
        DirectedGraph<String> webSiteGraph = new AdjacencyListDirectedGraph<String>();

        for (File f : webSite.listFiles()) {
            String from = f.getName();
            LineNumberReader in = new LineNumberReader(new FileReader(f));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains("href")) {
                    String[] s_arr = line.split("\"");
                    String to = s_arr[1];
                    webSiteGraph.addEdge(from, to);
                }
            }
            in.close();
        }
        return webSiteGraph;
    }

    /**
     * pageRank ermittelt Gewichte (Ranks) von Web-Seiten
     * aufgrund ihrer Link-Struktur und gibt sie aus.
     *
     * @param g gerichteter Graph mit Web-Seiten als Knoten und Links als Kanten.
     */
    private static <V> void pageRank(DirectedGraph<V> g) {
        int nI = 10;
        double alpha = 0.5;
        int N = g.getNumberOfVertexes();

        // Definiere und initialisiere rankTable:
        Map<V, Double> rankTable = new HashMap<>();
        for (V v : g.getVertexSet()) {
            rankTable.put(v, 1.0 / N);
        }

        // Iteration:
        for (int i = 0; i < nI; i++) {
            Map<V, Double> newRankTable = new HashMap<>();
            for (V v : g.getVertexSet()) {
                double rank = 0;
                for (V w : g.getPredecessorVertexSet(v)) {
                    rank += rankTable.get(w)
                            / g.getSuccessorVertexSet(w).size();
                }
                newRankTable.put(v, alpha * rank + (1 - alpha) / N);
            }
            rankTable = newRankTable;
        }

        // Rank Table ausgeben (nur für data/WebSiteKlein):
        System.out.println("Rank Table (nur für data/WebSiteKlein):");
        if (g.getNumberOfVertexes() < 10) {
            for (V v : g.getVertexSet()) {
                System.out.printf("%s: %.3f\n", v, rankTable.get(v));
            }
        }

        // Nach Ranks sortieren Top 100 ausgeben (nur für data/WebSiteGross):
        System.out.println("Rank Table (nur für data/WebSiteGross) Top 100:");
        if (g.getNumberOfVertexes() > 10) {
            List<Map.Entry<V, Double>> rankList = new ArrayList<>(
                    rankTable.entrySet());
            rankList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
            for (int i = 0; i < 100; i++) {
                System.out.printf("%s: %.3f\n", rankList.get(i).getKey(),
                        rankList.get(i).getValue());
            }
        }

        // Top-Seite mit ihren Vorgängern und Ranks ausgeben (nur für data/WebSiteGross):
        System.out.println(
                "Top-Seite mit ihren Vorgängern und Ranks"
                        + "(nur für data/WebSiteGross):");
        if (g.getNumberOfVertexes() > 10) {
            V topSite = rankTable.entrySet().stream()
                    .max(Map.Entry.comparingByValue()).get().getKey();
            System.out.println("Top-Seite: " + topSite);
            System.out.println("Vorgänger:");
            for (V v : g.getPredecessorVertexSet(topSite)) {
                System.out.printf("%s: %.3f\n", v, rankTable.get(v));
            }
        }
    }
}
