---
title: Binary Heaps
tags:
  - Algorithm
  - Semester-3
  - Informatik
  - Java
date: 2025-01-31
publish: true
---
A **Binary Heap** is a **complete binary tree** that satisfies the **heap property**:

- **Min-Heap**: The **smallest** element is at the root, and each parent is **smaller** than its children.
- **Max-Heap**: The **largest** element is at the root, and each parent is **larger** than its children.

Binary heaps are **efficient for priority queues**, providing fast insertions and removals.

---

## **1. Properties of a Binary Heap**

- **Complete Binary Tree**: Every level is completely filled except possibly the last, which is filled from **left to right**.
- **Heap Property**: The parent node is always **greater than (Max-Heap) or smaller than (Min-Heap)** its children.

---

## **2. Operations on a Binary Heap**

### **2.1 Insert a Key**

1. Add the new key at the **end**.
2. **Bubble up** (heapify-up) to maintain the heap property.

#### **Java Implementation**

```java
void insert(int key) {
    heap[size] = key;
    int i = size;
    size++;

    while (i > 0 && heap[parent(i)] > heap[i]) { // Min-Heap condition
        swap(i, parent(i));
        i = parent(i);
    }
}

private int parent(int i) { return (i - 1) / 2; }
private void swap(int i, int j) { int temp = heap[i]; heap[i] = heap[j]; heap[j] = temp; }
```

**Time Complexity:** $O(\log ⁡n)$

---

### **2.2 Extract Minimum (Min-Heap)**

1. Remove the root (minimum element).
2. Replace it with the **last element**.
3. **Heapify-down** to restore order.

#### **Java Implementation**

```java
int extractMin() {
    if (size == 0) return Integer.MAX_VALUE;

    int min = heap[0];
    heap[0] = heap[size - 1];
    size--;

    heapifyDown(0);
    return min;
}

void heapifyDown(int i) {
    int smallest = i;
    int left = 2 * i + 1, right = 2 * i + 2;

    if (left < size && heap[left] < heap[smallest]) smallest = left;
    if (right < size && heap[right] < heap[smallest]) smallest = right;

    if (smallest != i) {
        swap(i, smallest);
        heapifyDown(smallest);
    }
}
```

**Time Complexity:** $O(\log ⁡n)$

---

## **3. Applications of Binary Heaps**

✔ **Priority Queues** (Dijkstra’s algorithm, scheduling).  
✔ **Heap Sort** (efficient sorting algorithm).  
✔ **Graph algorithms** (Prim’s and Dijkstra’s algorithm).

Binary heaps provide **fast insertions and removals**, making them ideal for **priority-based applications**.