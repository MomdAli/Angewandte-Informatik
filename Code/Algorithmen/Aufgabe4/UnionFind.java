import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UnionFind<T> {

    // parent is null if element is a root
    private Map<T, T> parent;
    // rank is the height of the tree
    private Map<T, Integer> rank;
    private int size;

    public UnionFind(Set<T> elements) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        for (T element : elements) {
            parent.put(element, null);
            rank.put(element, 0);
        }
        size = elements.size();
    }

    public int size() {
        return size;
    }

    public T find(T set) {
        if (!parent.containsKey(set)) {
            throw new IllegalArgumentException("Menge nicht vorhanden");
        }

        T root = set;

        while (parent.get(root) != null) {
            root = parent.get(root);
        }

        T current = set;

        while (!current.equals(root)) {
            T next = parent.get(current);
            parent.put(current, root);
            current = next;
        }

        return root;
    }

    public void union(T set1, T set2) {
        if (!parent.containsKey(set1) || !parent.containsKey(set2)) {
            throw new IllegalArgumentException("Menge nicht vorhanden");
        }

        T parent1 = find(set1);
        T parent2 = find(set2);

        if (parent1.equals(parent2)) {
            return;
        }

        int rank1 = rank.get(parent1);
        int rank2 = rank.get(parent2);

        if (rank1 < rank2) {
            parent.put(parent1, parent2);
        } else if (rank1 > rank2) {
            parent.put(parent2, parent1);
        } else {
            parent.put(parent1, parent2);
            rank.put(parent2, rank2 + 1);
        }

        size--;
    }

    public void print() {
        for (T element : parent.keySet()) {
            System.out.println(element + " -> " + parent.get(element));
        }
        System.out.println("-------------------------");
    }

    private void writeJsonFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(jsonGraph());
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    private String jsonGraph() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"nodes\": [\n");
        for (T element : parent.keySet()) {
            sb.append("    { \"id\": \"").append(element).append("\" },\n");
        }
        // Remove the last comma and newline
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("\n  ],\n");
        sb.append("  \"links\": [\n");
        for (T element : parent.keySet()) {
            T parentElement = parent.get(element);
            if (parentElement != null) {
                sb.append("    { \"source\": \"").append(element)
                        .append("\", \"target\": \"").append(parentElement)
                        .append("\" },\n");
            }
        }
        // Remove the last comma and newline
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("\n  ]\n");
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        UnionFind<Integer> uf = new UnionFind<>(
                Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(5, 6);
        uf.union(7, 8);
        uf.union(9, 10);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);

        uf.writeJsonFile("output.json");
    }
}
