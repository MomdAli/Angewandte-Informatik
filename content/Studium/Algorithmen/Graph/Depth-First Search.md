---
{"publish":true,"title":"Depth-First Search","created":"2025-01-31","modified":"2025-09-12T21:25:00.885+02:00","published":"2025-01-31","tags":["Algorithm","Semester-3","Informatik","Java"],"cssclasses":""}
---

**Depth-First Search (DFS)** is a **graph traversal algorithm** that explores as far as possible along a branch before backtracking. It is widely used in **pathfinding, cycle detection, topological sorting**, and solving **connectivity problems**.

---

## **1. Properties of DFS**

- **Explores deep paths first**, then backtracks.
- Can be implemented **recursively** or **iteratively** using a **stack**.
- Works on both **directed and undirected graphs**.
- Used for **graph traversal, cycle detection, connected components, and topological sorting**.

---

## **2. Recursive DFS Implementation (Java)**

```java
void dfsRecursive(Vertex v, Graph g, Set<Vertex> visited) {
    visited.add(v);
    System.out.println(v);
    
    for (Vertex w : g.getNeighbors(v)) {
        if (!visited.contains(w)) {
            dfsRecursive(w, g, visited);
        }
    }
}
```

- **Time Complexity:** $O(V+E)$
- **Space Complexity:** $O(V)$ (due to recursion stack)

---

## **3. Iterative DFS Implementation (Java)**

```java
void dfsIterative(Vertex v, Graph g) {
    Set<Vertex> visited = new HashSet<>();
    Stack<Vertex> stack = new Stack<>();
    stack.push(v);

    while (!stack.isEmpty()) {
        Vertex current = stack.pop();
        if (!visited.contains(current)) {
            visited.add(current);
            System.out.println(current);
            for (Vertex w : g.getNeighbors(current)) {
                stack.push(w);
            }
        }
    }
}
```

- Uses a **stack** instead of recursion.
- **Same complexity** as recursive DFS.

---

## **4. Applications of DFS**

✔ **Cycle detection** in graphs.  
✔ **Finding connected components** in undirected graphs.  
✔ **Topological sorting** for **DAGs (Directed Acyclic Graphs)**.  
✔ **Pathfinding** in mazes or games.

DFS is **efficient for deep traversal** but may be **inefficient for shortest path finding** (use BFS instead).