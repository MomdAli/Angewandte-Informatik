---
title: Binary Search Tree Dictionary
tags:
  - Algorithm
  - Semester-3
  - Informatik
date: 2024-11-04
publish: true
---

A **Binary Search Tree (BST)** is a data structure that maintains elements in sorted order. Each node in a BST has a key, and nodes are organized such that for any given node, the left child contains values less than the parent, and the right child contains values greater than the parent. This structure is useful for quickly searching, inserting, and deleting entries.

> [!Info] Efficiency of BST
> The average time complexity for search, insertion, and deletion operations in a BST is **O(log n)**. However, in the worst case (e.g., unbalanced trees), these operations can degrade to **O(n)**.

## Key Concepts

- **Binary Search Tree (BST)**: A tree structure where each node has up to two children, and nodes follow an order property.
- **Dictionary Interface**: The `Dictionary` interface can be implemented to use a BST as the underlying structure for managing key-value pairs.


## Operations on a BST

### 1. Inserting a Value

To insert a value in a BST, we compare the key with nodes starting from the root. If the key is less, we go to the left child; if it’s greater, we go to the right child. This process is repeated until an empty spot is found.

```java title="Pseudocode"

function insert(node, key, value):
    if node is null:
        return new Node(key, value)
    if key < node.key:
        node.left = insert(node.left, key, value)
    else if key > node.key:
        node.right = insert(node.right, key, value)
    else:
        node.value = value // Update value if key exists
    return node
```

### Time Complexity
- **Average-case**: O(log n), due to tree height.
- **Worst-case**: O(n), when the BST is unbalanced.

### 2. Searching for a Value

To search for a key in a BST, we start from the root and move left or right based on comparisons. This is a recursive or iterative process.

```java title="Pseudocode"
function search(node, key):
    if node is null or node.key == key:
        return node.value
    if key < node.key:
        return search(node.left, key)
    else:
        return search(node.right, key)
```

### Time Complexity
- **Average-case**: O(log n).
- **Worst-case**: O(n).

### 3. Removing a Value

Removing a node in a BST has three cases:
1. **Node is a leaf**: Simply remove the node.
2. **Node has one child**: Replace the node with its child.
3. **Node has two children**: Replace the node with its in-order successor or predecessor.

```java title="Pseudocode"
function remove(node, key):
    if node is null:
        return null
    if key < node.key:
        node.left = remove(node.left, key)
    else if key > node.key:
        node.right = remove(node.right, key)
    else:
        if node.left is null:
            return node.right
        else if node.right is null:
            return node.left
        temp = findMin(node.right)
        node.key = temp.key
        node.value = temp.value
        node.right = remove(node.right, temp.key)
    return node
```

### Time Complexity
- **Average-case**: O(log n).
- **Worst-case**: O(n).

## Algorithm Complexity

- **Insertion**: O(log n) on average, O(n) worst-case.
- **Search**: O(log n) on average, O(n) worst-case.
- **Deletion**: O(log n) on average, O(n) worst-case.

> [!Warning] Limitation of BSTs
> BSTs can become unbalanced, making search and insertion inefficient. Self-balancing trees, like AVL or Red-Black trees, help maintain **O(log n)** operations.

## Example Code in Java

Here’s how a BST can be implemented to support the `Dictionary` interface:

```java
public class BSTDictionary<K extends Comparable<K>, V> implements Dictionary<K, V> {
    private Node root;

    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public V insert(K key, V value) {
        root = insert(root, key, value);
        return value;
    }

    private Node insert(Node node, K key, V value) {
        if (node == null) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = insert(node.left, key, value);
        else if (cmp > 0) node.right = insert(node.right, key, value);
        else node.value = value;
        return node;
    }

    public V search(K key) {
        Node node = search(root, key);
        return node != null ? node.value : null;
    }

    private Node search(Node node, K key) {
        if (node == null || key.equals(node.key)) return node;
        int cmp = key.compareTo(node.key);
        return cmp < 0 ? search(node.left, key) : search(node.right, key);
    }

    public V remove(K key) {
        root = remove(root, key);
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = remove(node.left, key);
        else if (cmp > 0) node.right = remove(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node temp = findMin(node.right);
            node.key = temp.key;
            node.value = temp.value;
            node.right = remove(node.right, temp.key);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }
}
```

## Summary

A Binary Search Tree (BST) provides efficient search, insertion, and deletion for dictionaries in sorted order. However, BST performance depends on tree balance, which affects its time complexity. In practical implementations, self-balancing BSTs are recommended to ensure consistent **O(log n)** operations.
