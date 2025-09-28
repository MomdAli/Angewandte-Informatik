---
title: AVL Tree
tags:
  - Algorithm
  - Semester-3
  - Informatik
  - Java
date:
publish: true
---
## **1. Definition**
An **AVL Tree** is a **self-balancing binary search tree (BST)** where the height difference (balance factor) between left and right subtrees is at most **1** for every node. This balance ensures that search, insertion, and deletion operations remain efficient.

The name **AVL** comes from its inventors **Adelson-Velsky and Landis** (1962).

---

## **2. Properties of an AVL Tree**
1. **Binary Search Tree (BST) Properties** apply.
2. For every node, the **balance factor** is:
$$
\text{Balance Factor} = \text{Height of Left Subtree} - \text{Height of Right Subtree}
$$
   which must always be **-1, 0, or +1**.
3. When inserting or deleting a node, if the tree becomes **unbalanced**, **rotations** are used to restore balance.

### **Example of an AVL Tree**
```
       10
      /  \
     5    15
    / \     \
   2   7     18
```
- The height of each subtree differs by at most **1**.

---

## **3. Operations on an AVL Tree**
### **3.1 Searching**
Searching in an AVL tree is the same as in a **BST**:
- Compare the key with the root.
- If **smaller**, search the **left subtree**.
- If **greater**, search the **right subtree**.

#### **Implementation in Java**
```java
public class AVLTree {
    class Node {
        int key, height;
        Node left, right;

        public Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    private Node root;

    public Node search(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        return key < root.key ? search(root.left, key) : search(root.right, key);
    }
}
```

**Time Complexity:**  
- **Best, Average, Worst Case:** $O(\log n)$

---

### **3.2 Inserting a Node**
Insertion follows BST rules but may **unbalance** the tree, requiring **rotation**.

#### **Rotations Used for Rebalancing**
1. **Right Rotation (Single Rotation)**
2. **Left Rotation (Single Rotation)**
3. **Left-Right Rotation (Double Rotation)**
4. **Right-Left Rotation (Double Rotation)**

#### **Java Implementation**
```java
private int height(Node N) {
    return (N == null) ? 0 : N.height;
}

private int getBalance(Node N) {
    return (N == null) ? 0 : height(N.left) - height(N.right);
}

private Node insert(Node node, int key) {
    if (node == null) return new Node(key);

    if (key < node.key) node.left = insert(node.left, key);
    else if (key > node.key) node.right = insert(node.right, key);
    else return node; // No duplicates allowed

    node.height = Math.max(height(node.left), height(node.right)) + 1;

    int balance = getBalance(node);

    // Left Heavy (Right Rotation)
    if (balance > 1 && key < node.left.key) return rotateRight(node);
    // Right Heavy (Left Rotation)
    if (balance < -1 && key > node.right.key) return rotateLeft(node);
    // Left-Right Case
    if (balance > 1 && key > node.left.key) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }
    // Right-Left Case
    if (balance < -1 && key < node.right.key) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    return node;
}
```

**Time Complexity:**  
- **Best, Average, Worst Case:** $O(\log n)$

---

### **3.3 Deleting a Node**
Deletion follows BST rules but may **unbalance** the tree, requiring **rotation**.

#### **Java Implementation**
```java
private Node delete(Node root, int key) {
    if (root == null) return root;

    if (key < root.key) root.left = delete(root.left, key);
    else if (key > root.key) root.right = delete(root.right, key);
    else {
        if (root.left == null || root.right == null) {
            Node temp = (root.left != null) ? root.left : root.right;
            root = (temp != null) ? temp : null;
        } else {
            Node temp = minValueNode(root.right);
            root.key = temp.key;
            root.right = delete(root.right, temp.key);
        }
    }

    if (root == null) return root;

    root.height = Math.max(height(root.left), height(root.right)) + 1;
    int balance = getBalance(root);

    // Left Heavy (Right Rotation)
    if (balance > 1 && getBalance(root.left) >= 0) return rotateRight(root);
    // Right Heavy (Left Rotation)
    if (balance < -1 && getBalance(root.right) <= 0) return rotateLeft(root);
    // Left-Right Case
    if (balance > 1 && getBalance(root.left) < 0) {
        root.left = rotateLeft(root.left);
        return rotateRight(root);
    }
    // Right-Left Case
    if (balance < -1 && getBalance(root.right) > 0) {
        root.right = rotateRight(root.right);
        return rotateLeft(root);
    }

    return root;
}

private Node minValueNode(Node node) {
    Node current = node;
    while (current.left != null) current = current.left;
    return current;
}
```

**Time Complexity:**  
- **Best, Average, Worst Case:** $O(\log n)$

---

## **4. Rotations**
When an insertion or deletion makes a subtree **too tall**, **rotations** are used to restore balance.

### **4.1 Right Rotation (Single)**
Used when the **left subtree is too tall**.
```
        y
       /
      x
     /
    z
```
**Rotates to:**
```
      x
     / \
    z   y
```

### **4.2 Left Rotation (Single)**
Used when the **right subtree is too tall**.
```
    x
     \
      y
       \
        z
```
**Rotates to:**
```
      y
     / \
    x   z
```

### **4.3 Left-Right Rotation (Double)**
Used when the **left subtree is too tall, but the imbalance is in the right subtree**.

### **4.4 Right-Left Rotation (Double)**
Used when the **right subtree is too tall, but the imbalance is in the left subtree**.

---

## **5. Traversal Methods**
AVL trees support **three common traversals**:

| **Type** | **Order** |
|----------|----------|
| **Inorder** | Left → Root → Right |
| **Preorder** | Root → Left → Right |
| **Postorder** | Left → Right → Root |

**Inorder Traversal Example (Sorted Output):**
```java
public void inorder(Node root) {
    if (root != null) {
        inorder(root.left);
        System.out.print(root.key + " ");
        inorder(root.right);
    }
}
```

---

## **6. Advantages and Disadvantages**
### **Advantages**
✔ **Guaranteed** $O(\log n)$ performance.  
✔ **Self-balancing** avoids degenerate BSTs.  
✔ Useful for **ordered datasets** and **range queries**.

### **Disadvantages**
❌ More **complex** than BSTs.  
❌ **Rotations add overhead**, making insertion and deletion **slower** than unbalanced BSTs.  

---

## **7. When to Use an AVL Tree?**
| **Scenario** | **Best Choice** |
|-------------|----------------|
| **Ordered data & fast lookups** | ✅ AVL Tree |
| **Mostly inserts/deletes** | ❌ Red-Black Tree |
| **Only searching needed** | ❌ Hash Table |
| **Must maintain sorted order** | ✅ AVL Tree |

---

## **8. Summary**
- **AVL Trees are self-balancing BSTs**.
- **Ensures $O(\log n)$ complexity** for search, insertion, and deletion.
- **Uses rotations to maintain balance**.
- **Preferred when ordered structure is needed**.