// O. Bittel;
// 25.3.2021; jetzt mit IndexMinPq
// 30.06.2024; Anpassung auf ungerichtete Graphen

package shortestPath;

import undirectedGraph.*;
import sim.SYSimulation;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// ...

/**
 * Kürzeste Wege in Graphen mit A*- und Dijkstra-Verfahren.
 *
 * @author Oliver Bittel
 * @since 30.06.2024
 * @param <V>
 *        Knotentyp.
 */
public class ShortestPath<V> {

	SYSimulation sim = null;

	Map<V, Double> dist; // Distanz für jeden Knoten
	Map<V, V> pred; // Vorgänger für jeden Knoten
	IndexMinPQ<V, Double> cand; // Kandidaten als PriorityQueue PQ

	UndirectedGraph<V> g;
	Heuristic<V> h;

	private V goal;

	private final Double INFINITY = Double.POSITIVE_INFINITY;

	/**
	 * Konstruiert ein Objekt, das im Graph g k&uuml;rzeste Wege
	 * nach dem A*-Verfahren berechnen kann.
	 * Die Heuristik h schätzt die Kosten zwischen zwei Knoten ab.
	 * Wird h = null gewählt, dann ist das Verfahren identisch
	 * mit dem Dijkstra-Verfahren.
	 * @param g Gerichteter Graph
	 * @param h Heuristik. Falls h == null, werden kürzeste Wege nach
	 * dem Dijkstra-Verfahren gesucht.
	 */
	public ShortestPath(UndirectedGraph<V> g, Heuristic<V> h) {
		dist = new HashMap<>();
		pred = new HashMap<>();
		cand = new IndexMinPQ<>();

		this.g = g;
		this.h = h;
	}

	/**
	 * Diese Methode sollte nur verwendet werden,
	 * wenn kürzeste Wege in Scotland-Yard-Plan gesucht werden.
	 * Es ist dann ein Objekt für die Scotland-Yard-Simulation zu übergeben.
	 * <p>
	 * Ein typische Aufruf für ein SYSimulation-Objekt sim sieht wie folgt aus:
	 * <blockquote><pre>
	 *    if (sim != null)
	 *       sim.visitStation((Integer) v, Color.blue);
	 * </pre></blockquote>
	 * @param sim SYSimulation-Objekt.
	 */
	public void setSimulator(SYSimulation sim) {
		this.sim = sim;
	}

	/**
	 * Sucht den kürzesten Weg von Starknoten s zum Zielknoten g.
	 * <p>
	 * Falls die Simulation mit setSimulator(sim) aktiviert wurde, wird der Knoten,
	 * der als nächstes aus der Kandidatenliste besucht wird, animiert.
	 * @param start Startknoten
	 * @param goal Zielknoten
	 */
	public void searchShortestPath(V start, V goal) {

		this.goal = goal;
		cand.clear();

		for (V v : g.getVertexSet()) {
			dist.put(v, INFINITY);
			pred.put(v, null);
		}

		dist.put(start, 0.0);
		double startCost = h == null ? 0.0 : h.estimatedCost(start, goal);
		cand.add(start, startCost);

		while (!cand.isEmpty()) {
			V current = cand.removeMin();
			double currentDist = dist.get(current);

			if (sim != null)
				sim.visitStation((Integer) current, java.awt.Color.BLUE);

			// System.out.println("Besuche Knoten " + current + " mit d = "
			// 		+ currentDist);

			if (current.equals(goal))
				return;

			for (V neighbor : g.getNeighborSet(current)) {

				double edgeWeight = g.getWeight(current, neighbor);
				double newDist = currentDist + edgeWeight;

				if (dist.get(neighbor) == INFINITY) { // w noch nicht besucht
					dist.put(neighbor, newDist);
					pred.put(neighbor, current);
					double estimatedCost = h == null ? 0.0
							: h.estimatedCost(neighbor, goal);
					cand.add(neighbor, newDist + estimatedCost);
				} else if (newDist < dist.get(neighbor)) { // d-Wert verbessert sich
					dist.put(neighbor, newDist);
					pred.put(neighbor, current);
					double estimatedCost = h == null ? 0.0
							: h.estimatedCost(neighbor, goal);
					cand.change(neighbor, newDist + estimatedCost);
				}
			}
		}
	}

	/**
	 * Liefert einen kürzesten Weg von Startknoten s nach Zielknoten g.
	 * Setzt eine erfolgreiche Suche von searchShortestPath(s,g) voraus.
	 * @throws IllegalArgumentException falls kein kürzester Weg berechnet wurde.
	 * @return kürzester Weg als Liste von Knoten.
	 */
	public List<V> getShortestPath() {
		List<V> path = new ArrayList<>();
		V current = goal; // Start bei Zielknoten

		while (current != null) {
			path.add(0, current); // Knoten an den Anfang der Liste hinzufügen
			current = pred.get(current); // Zum Vorgänger gehen
		}

		return path;
	}

	/**
	 * Liefert die Länge eines kürzesten Weges von Startknoten s nach Zielknoten g zurück.
	 * Setzt eine erfolgreiche Suche von searchShortestPath(s,g) voraus.
	 * @throws IllegalArgumentException falls kein kürzester Weg berechnet wurde.
	 * @return Länge eines kürzesten Weges.
	 */
	public double getDistance() {
		return dist.getOrDefault(goal, INFINITY);
	}
}
