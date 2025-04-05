---
title: Red-Black Tree
tags:
  - Algorithm
  - Semester-3
  - Informatik
  - Java
date: 2025-01-29
---
# 📌 2-3-4 Tree and Red-Black Tree  

A **2-3-4 Tree** is a **self-balancing multi-way search tree** where each node can have **2, 3, or 4 children** and **1, 2, or 3 keys**. It is equivalent to a **Red-Black Tree**, a **binary search tree (BST)** with additional balancing properties.

---

## **1. 2-3-4 Tree – Structure & Properties**  
- **Nodes can have multiple keys**:
  - **2-node**: 1 key, 2 children  
  - **3-node**: 2 keys, 3 children  
  - **4-node**: 3 keys, 4 children  
- Always **balanced** since all leaf nodes are at the **same depth**.  
- Insertions and deletions may cause **splits** to maintain balance.  

### **Example of a 2-3-4 Tree**
```
       [ 10 | 30 ]
      /    |    \
 [5]   [15 | 25]   [35 | 40 | 50]
```
- A **4-node** can split into **two 2-nodes**, moving a key up to maintain balance.

---

## **2. Red-Black Tree – Binary Representation of a 2-3-4 Tree**  
A **Red-Black Tree** is a **binary search tree (BST)** with **color-based balancing**:
- **Red nodes represent merging in 2-3-4 trees** (temporary multi-key nodes).
- **Black nodes ensure depth balance**.
- Operations maintain balance via **rotations and color flips**.

### **Red-Black Tree Rules**
1. **Each node is either red or black**.  
2. **Root is always black**.  
3. **No two consecutive red nodes** (red nodes must have black parents).  
4. **Every path from root to leaf must have the same number of black nodes**.  

### **Example (Red-Black Equivalent of 2-3-4 Tree)**
```
        (30B)
       /     \
    (10B)   (40B)
   /   \       \
 (5R) (25R)   (50R)
```
- **Red nodes simulate extra keys in 2-3-4 trees**.

---

## **3. Relationship Between 2-3-4 and Red-Black Trees**
| Feature          | **2-3-4 Tree**   | **Red-Black Tree**  |
|-----------------|-----------------|------------------|
| **Structure**    | Multi-key nodes | Binary search tree |
| **Balancing**   | Node splits | Rotations and color flips |
| **Insertion**   | Splitting when full | Rotations to restore balance |
| **Use Case**    | File systems, indexing | In-memory balancing (Java TreeMap) |

---

## **4. When to Use**
- **2-3-4 Trees**: Used in **database indexing, file systems**, where block-based storage is needed.  
- **Red-Black Trees**: Common in **programming libraries (e.g., Java TreeMap, C++ STL)** for **fast in-memory lookups**.  

Both ensure **$O(\log n)$ time complexity** for **search, insertion, and deletion** while maintaining **balance dynamically**.