---
title: Binary Search Tree
tags:
  - Algorithm
  - Semester-3
  - Informatik
  - Java
date: 2025-01-29
---
## **1. Definition**
A **Binary Search Tree (BST)** is a hierarchical data structure in which each node has at most **two children**. The **left child** contains only values smaller than the parent, and the **right child** contains only values larger than the parent. This structure enables efficient **search, insertion, and deletion** operations.

---

## **2. Properties of a Binary Search Tree**
1. Each node contains a **key** and an optional **value**.
2. **Left subtree** contains only nodes with keys **less than** the parent node.
3. **Right subtree** contains only nodes with keys **greater than** the parent node.
4. The **left and right subtrees** must also be **binary search trees**.
5. **No duplicate keys** (in standard BSTs; variations exist that allow duplicates).

### **Example of a BST**
```
        10
       /  \
      5    15
     / \   / \
    3   7 12 18
```
- All **left children** are smaller than their parent.
- All **right children** are greater than their parent.

---

## **3. Operations on a BST**
### **3.1 Searching for a Key**
Searching follows the same logic as binary search in an array.
- If the key is equal to the root, return it.
- If the key is **less** than the root, search in the **left subtree**.
- If the key is **greater** than the root, search in the **right subtree**.
- If a `null` node is reached, the key is not in the tree.

#### **Implementation in Java**
```java
public class BinarySearchTree {
    static class Node {
        int key;
        Node left, right;

        public Node(int key) {
            this.key = key;
            left = right = null;
        }
    }

    Node root;

    public Node search(Node root, int key) {
        if (root == null || root.key == key) {
            return root; // Key found or tree is empty
        }
        if (key < root.key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }
}
```

**Time Complexity:**
- **Best Case**: $O(1)$ (when the key is at the root)
- **Average Case**: $O(\log n)$
- **Worst Case**: $O(n)$ (if the tree is unbalanced)

---

### **3.2 Inserting a Key**
- Compare the key with the current node.
- If the key is **smaller**, go left.
- If the key is **greater**, go right.
- If the left or right child is `null`, insert the new node there.

#### **Implementation in Java**
```java
public void insert(int key) {
    root = insertRec(root, key);
}

private Node insertRec(Node root, int key) {
    if (root == null) {
        return new Node(key);
    }
    if (key < root.key) {
        root.left = insertRec(root.left, key);
    } else {
        root.right = insertRec(root.right, key);
    }
    return root;
}
```

**Time Complexity:**
- **Best Case**: $O(1)$ (when inserting at the root)
- **Average Case**: $O(\log n)$
- **Worst Case**: $O(n)$ (if the tree is unbalanced)

---

### **3.3 Deleting a Key**
Deletion is more complex as it requires handling three cases:
1. **Node has no children** → Remove it.
2. **Node has one child** → Replace it with its child.
3. **Node has two children** → Find the **smallest node in the right subtree** (successor), replace the node with it, then delete the successor.

#### **Implementation in Java**
```java
public void delete(int key) {
    root = deleteRec(root, key);
}

private Node deleteRec(Node root, int key) {
    if (root == null) {
        return root;
    }

    if (key < root.key) {
        root.left = deleteRec(root.left, key);
    } else if (key > root.key) {
        root.right = deleteRec(root.right, key);
    } else {
        // Node with one or no children
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;

        // Node with two children, get inorder successor (smallest in right subtree)
        root.key = minValue(root.right);
        root.right = deleteRec(root.right, root.key);
    }
    return root;
}

private int minValue(Node root) {
    int minV = root.key;
    while (root.left != null) {
        minV = root.left.key;
        root = root.left;
    }
    return minV;
}
```

**Time Complexity:**
- **Best Case**: $O(1)$ (if deleting the root with no children)
- **Average Case**: $O(\log n)$
- **Worst Case**: $O(n)$ (if the tree is unbalanced)

---

## **4. Traversal Methods**
Traversal refers to visiting all nodes in a tree in a specific order.

### **4.1 Inorder Traversal (Left - Root - Right)**
- Produces nodes in **sorted order**.
```java
public void inorder(Node root) {
    if (root != null) {
        inorder(root.left);
        System.out.print(root.key + " ");
        inorder(root.right);
    }
}
```

### **4.2 Preorder Traversal (Root - Left - Right)**
```java
public void preorder(Node root) {
    if (root != null) {
        System.out.print(root.key + " ");
        preorder(root.left);
        preorder(root.right);
    }
}
```

### **4.3 Postorder Traversal (Left - Right - Root)**
```java
public void postorder(Node root) {
    if (root != null) {
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.key + " ");
    }
}
```

**Time Complexity for all traversals**: $O(n)$

---

## **5. Balanced vs. Unbalanced BSTs**
A **balanced BST** has a height of **$O(\log n)$**, ensuring efficient operations. However, an **unbalanced BST** can degrade to a **linked list** with height $O(n)$.

| Tree Type | Worst-Case Height | Time Complexity |
|-----------|------------------|----------------|
| **Balanced BST** (e.g., AVL, Red-Black) | $O(\log n)$ | $O(\log n)$ |
| **Unbalanced BST** | $O(n)$ | $O(n)$ |

### **How to Keep a BST Balanced?**
- Use **AVL Trees** or **Red-Black Trees**, which perform rotations to maintain balance.
- Use a **Self-Balancing BST** like **B-Trees** for databases.

---

## **6. Advantages and Disadvantages of BST**
### **Advantages**
✔ Efficient searching, insertion, and deletion in $O(\log n)$ for a balanced tree.  
✔ Ordered structure allows **sorted traversal**.  
✔ Useful in **database indexing** and **symbol tables**.

### **Disadvantages**
❌ Can become **unbalanced**, leading to poor performance.  
❌ **More complex** than hash tables for simple lookups.  
❌ **Rotations** in self-balancing trees add overhead.

---

## **7. When to Use a BST?**
| **Scenario** | **Best Choice** |
|-------------|----------------|
| **Fast searches and ordered data needed** | ✅ BST |
| **Unordered, fast lookups needed** | ❌ Hash Table |
| **Frequent insertions/deletions** | ✅ BST (especially self-balancing) |
| **Data must be retrieved in sorted order** | ✅ BST |

---

## **8. Summary**
- **Binary Search Trees (BSTs) provide efficient searching, insertion, and deletion.**
- **Best case**: $O(\log n)$ time complexity, **worst case**: $O(n)$.
- **Balanced BSTs (AVL, Red-Black) prevent performance degradation**.
- **Used in databases, compilers, and memory management**.