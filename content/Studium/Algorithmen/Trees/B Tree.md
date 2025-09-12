---
{"publish":true,"title":"B Tree","created":"2025-01-29","modified":"2025-09-12T21:25:01.169+02:00","published":"2025-01-29","tags":["Algorithm","Semester-3","Informatik","Java"],"cssclasses":""}
---

## **1. Definition**
A **B-Tree** is a **self-balancing search tree** designed for systems that read and write **large blocks of data**. It is **optimized for disk storage** and is commonly used in **databases and file systems**.

Unlike binary search trees (BSTs) or AVL trees, **B-Trees** allow **multiple keys per node** and can have **more than two children**. This **reduces tree height**, making searches, insertions, and deletions more efficient for large datasets.

---

## **2. Properties of a B-Tree**
1. **Each node contains multiple keys**, up to a maximum defined by the tree's **order**.
2. A node with $k$ keys has $k+1$ children.
3. All **leaves** are at the same level.
4. The **root has at least 2 children**, unless it's a leaf.
5. **Every non-root node must have at least $\lceil m/2 \rceil$ children**, where $m$ is the tree's order.
6. **Keys in each node are sorted** in ascending order.
7. **Efficient disk access**: Each node typically fits within a **disk block**, reducing I/O operations.

### **Example of a B-Tree of Order 3 (m=3)**
```
       [ 20 | 50 ]
      /    |    \
[10]   [30 | 40]   [60 | 70 | 80]
```
- Each **internal node** can hold up to **$m-1$ keys**.
- Each **internal node** has at least **$\lceil m/2 \rceil$ children**.

---

## **3. Operations on a B-Tree**
### **3.1 Searching for a Key**
The search process is similar to **binary search**, but within each node:
1. Compare the key with the **keys inside the node**.
2. If found, return it.
3. If not, move to the **appropriate child** and repeat.
4. If a **leaf is reached** and the key is not found, return **null**.

#### **Implementation in Java**
```java
class BTreeNode {
    int[] keys;
    int degree; // Minimum number of children
    BTreeNode[] children;
    int numKeys;
    boolean isLeaf;

    public BTreeNode(int degree, boolean isLeaf) {
        this.degree = degree;
        this.isLeaf = isLeaf;
        this.keys = new int[2 * degree - 1];
        this.children = new BTreeNode[2 * degree];
        this.numKeys = 0;
    }

    public BTreeNode search(int key) {
        int i = 0;
        while (i < numKeys && key > keys[i]) {
            i++;
        }
        if (i < numKeys && keys[i] == key) {
            return this;
        }
        if (isLeaf) {
            return null;
        }
        return children[i].search(key);
    }
}
```

**Time Complexity:**  
- **Best Case:** $O(1)$ (if the key is in the root)  
- **Average & Worst Case:** $O(\log_m n)$ (where $m$ is the order of the B-Tree)

---

### **3.2 Inserting a Key**
Insertion follows **three rules**:
1. **If the node is not full** → Insert the key in sorted order.
2. **If the node is full** → **Split** it into two nodes and **move the middle key up**.
3. **If the root is split**, a **new root is created**, increasing the tree height.

#### **Java Implementation**
```java
public void insert(int key) {
    if (root == null) {
        root = new BTreeNode(degree, true);
        root.keys[0] = key;
        root.numKeys = 1;
    } else {
        if (root.numKeys == 2 * degree - 1) {
            BTreeNode newRoot = new BTreeNode(degree, false);
            newRoot.children[0] = root;
            splitChild(newRoot, 0, root);
            root = newRoot;
        }
        insertNonFull(root, key);
    }
}

private void insertNonFull(BTreeNode node, int key) {
    int i = node.numKeys - 1;
    if (node.isLeaf) {
        while (i >= 0 && key < node.keys[i]) {
            node.keys[i + 1] = node.keys[i];
            i--;
        }
        node.keys[i + 1] = key;
        node.numKeys++;
    } else {
        while (i >= 0 && key < node.keys[i]) {
            i--;
        }
        i++;
        if (node.children[i].numKeys == 2 * degree - 1) {
            splitChild(node, i, node.children[i]);
            if (key > node.keys[i]) {
                i++;
            }
        }
        insertNonFull(node.children[i], key);
    }
}
```

**Time Complexity:**  
- **Best, Average, Worst Case:** $O(\log_m n)$

---

### **3.3 Deleting a Key**
Deletion follows **three cases**:
1. **If the key is in a leaf** → Simply remove it.
2. **If the key is in an internal node**:
   - Replace it with the **predecessor** or **successor**.
   - If the child does not have enough keys, **merge nodes**.
3. **If a node becomes underfull**, it is **merged with a sibling**.

#### **Java Implementation**
```java
public void delete(int key) {
    if (root == null) return;
    deleteKey(root, key);

    if (root.numKeys == 0) {
        root = root.isLeaf ? null : root.children[0];
    }
}

private void deleteKey(BTreeNode node, int key) {
    int i = 0;
    while (i < node.numKeys && key > node.keys[i]) i++;

    if (i < node.numKeys && node.keys[i] == key) {
        if (node.isLeaf) {
            for (int j = i; j < node.numKeys - 1; j++) {
                node.keys[j] = node.keys[j + 1];
            }
            node.numKeys--;
        } else {
            node.keys[i] = getSuccessor(node.children[i + 1]);
            deleteKey(node.children[i + 1], node.keys[i]);
        }
    } else {
        deleteKey(node.children[i], key);
    }
}
```

**Time Complexity:**  
- **Best, Average, Worst Case:** $O(\log_m n)$

---

## **4. Traversal Methods**
Traversal is similar to BSTs but must account for **multiple keys per node**.

### **4.1 Inorder Traversal (Sorted Order)**
```java
public void inorder(BTreeNode node) {
    if (node != null) {
        for (int i = 0; i < node.numKeys; i++) {
            inorder(node.children[i]);
            System.out.print(node.keys[i] + " ");
        }
        inorder(node.children[node.numKeys]);
    }
}
```

**Time Complexity:** $O(n)$

---

## **5. B-Trees vs. Other Search Trees**
| Feature         | B-Tree | AVL Tree | Binary Search Tree |
|----------------|--------|----------|--------------------|
| **Balancing**  | Always balanced | Self-balancing | Can become unbalanced |
| **Height** | $O(\log_m n)$ | $O(\log n)$ | $O(n)$ (worst-case) |
| **Search Complexity** | $O(\log_m n)$ | $O(\log n)$ | $O(n)$ (worst-case) |
| **Use Case** | Databases, File Systems | In-memory search | In-memory search |

---

## **6. B+ Tree**

A **B+ Tree** is an extension of a **B-Tree** optimized for **range queries** and **sequential access**. Unlike B-Trees, **only leaf nodes store actual data**, while **internal nodes function purely as an index**.

### **Key Differences from B-Tree**

- **Internal nodes contain only keys**, improving search efficiency.
- **Leaf nodes store all data and are linked**, enabling **fast ordered traversal**.
- **Better for disk-based storage** due to fewer disk reads.

### **Use Case**

- Ideal for **databases and file systems** where range queries and sorted access are common.

---
## **7. Summary**
- **B-Trees are self-balancing search trees** optimized for **disk storage**.
- **Each node stores multiple keys**, reducing **tree height**.
- **Search, insert, and delete operations run in $O(\log_m n)$**.
- **Used in databases, filesystems, and indexing algorithms**.