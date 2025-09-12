---
{"publish":true,"title":"Complexity Time Table","created":"2025-02-02","modified":"2025-09-12T21:25:00.876+02:00","published":"2025-02-02","tags":["Algorithm","Semester-3","Informatik"],"cssclasses":""}
---

### **Time Complexity of Data Structures**
| **Data Structure**                | **Search**    | **Insertion** | **Deletion**  | **Delete Max**             | **Delete Min**             |
| --------------------------------- | ------------- | ------------- | ------------- | -------------------------- | -------------------------- |
| **Array (unsorted)**              | $O(n)$        | $O(1)$        | $O(n)$        | $O(n)$                     | $O(n)$                     |
| **Array (sorted)**                | $O(\log n)$   | $O(n)$        | $O(n)$        | $O(1)$                     | $O(1)$                     |
| **Singly Linked List (Unsorted)** | $O(n)$        | $O(1)$        | $O(n)$        | $O(n)$                     | $O(n)$                     |
| **Singly Linked List (Sorted)**   | $O(n)$        | $O(n)$        | $O(n)$        | $O(n)$                     | $O(1)$                     |
| **Doubly Linked List (Unsorted)** | $O(n)$        | $O(1)$        | $O(n)$        | $O(n)$                     | $O(n)$                     |
| **Doubly Linked List (Sorted)**   | $O(n)$        | $O(n)$        | $O(n)$        | $O(1)$                     | $O(1)$                     |
| **Stack (LIFO)**                  | $O(n)$        | $O(1)$        | $O(1)$        | $O(1)$                     | $O(n)$                     |
| **Queue (FIFO)**                  | $O(n)$        | $O(1)$        | $O(1)$        | $O(n)$                     | $O(n)$                     |
| **Deque (Double-Ended Queue)**    | $O(n)$        | $O(1)$        | $O(1)$        | $O(1)$                     | $O(1)$                     |
| **Hash Table**                    | $O(1)$        | $O(1)$        | $O(1)$        | $O(n)$                     | $O(n)$                     |
| **Binary Search Tree (BST)**      | $O(\log n)$   | $O(\log n)$   | $O(\log n)$   | $O(\log n)$                | $O(\log n)$                |
| **AVL Tree**                      | $O(\log n)$   | $O(\log n)$   | $O(\log n)$   | $O(\log n)$                | $O(\log n)$                |
| **Red-Black Tree**                | $O(\log n)$   | $O(\log n)$   | $O(\log n)$   | $O(\log n)$                | $O(\log n)$                |
| **B-Tree (order m)**              | $O(\log_m n)$ | $O(\log_m n)$ | $O(\log_m n)$ | $O(\log_m n)$              | $O(\log_m n)$              |
| **B+ Tree**                       | $O(\log_m n)$ | $O(\log_m n)$ | $O(\log_m n)$ | $O(1)$ (linked leaf nodes) | $O(1)$ (linked leaf nodes) |
| **2-3-4 Tree**                    | $O(\log n)$   | $O(\log n)$   | $O(\log n)$   | $O(\log n)$                | $O(\log n)$                |
| **Binary Heap**                   | $O(n)$        | $O(\log n)$   | $O(\log n)$   | $O(\log n)$                | $O(\log n)$                |
| **Binomial Heap**                 | $O(\log n)$   | $O(\log n)$   | $O(\log n)$   | $O(\log n)$                | $O(\log n)$                |
| **Fibonacci Heap**                | $O(1)$        | $O(1)$        | $O(\log n)$   | $O(\log n)$                | $O(\log n)$                |

---

### **Key Takeaways**
- **Hash tables** are fastest for **search, insert, and delete**, but have slow min/max operations.
- **Balanced trees (AVL, Red-Black, B-Trees)** ensure **$O(\log n)$ performance** for all operations.
- **Heaps** are optimal for **priority queues**, providing efficient **delete max/min**.
- **B+ Trees** are **best for range queries**, as they store all data in leaf nodes.
- **Binomial & Fibonacci Heaps** are useful in **graph algorithms (Dijkstra, Prim's algorithm)**.



