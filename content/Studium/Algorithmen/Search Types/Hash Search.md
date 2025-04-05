---
title: Hash Search
tags:
  - Algorithm
  - Semester-3
  - Informatik
  - Java
date: 2025-01-29
---
> **Definition**  
> Hash Search is a searching technique that uses a **hash function** to map keys to specific indices in a **hash table**, allowing **constant-time search operations** in most cases.  
> It is one of the most efficient search methods for **large datasets** when implemented correctly.

---

## 📌 1. Purpose and Use Cases

### **When to Use Hash Search?**
- ✅ **Fast searching** is required $O(1)$ in the average case).  
- ✅ The dataset is **large and frequently accessed**.  
- ✅ Insertions, deletions, and searches need to be **fast**.  
- ✅ **Random access** to elements is needed.

### **When NOT to Use Hash Search?**
- ❌ When **ordered data** is required (e.g., range queries, sorted output).  
- ❌ The dataset size is **too small**, making a hash table unnecessary.  
- ❌ If **collisions are not well handled**, performance may degrade to $O(n)$.  
- ❌ When searching in **linked lists** or structures without index-based access.

---

## 📌 2. Implementation

### **Hashing Concept**
A **hash function** $h(k)$ maps a key $k$ to an index in a hash table of size $m$:
$$
h(k) = k \mod m
$$

### **Handling Collisions**
Collisions occur when two keys map to the same index. Common techniques to handle collisions:
- **Chaining (Linked List at each index)**  
- **Open Addressing (e.g., Linear Probing, Quadratic Probing, Double Hashing)**

---

### **📌 Hash Search Implementation in Java**
#### **Chaining (Separate Linked List)**
```java
import java.util.LinkedList;

class HashTable {
    private static final int SIZE = 10;
    private LinkedList<Integer>[] table;

    public HashTable() {
        table = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hashFunction(int key) {
        return key % SIZE;
    }

    public void insert(int key) {
        int index = hashFunction(key);
        table[index].add(key);
    }

    public boolean search(int key) {
        int index = hashFunction(key);
        return table[index].contains(key);
    }

    public void remove(int key) {
        int index = hashFunction(key);
        table[index].remove((Integer) key);
    }

    public static void main(String[] args) {
        HashTable ht = new HashTable();
        ht.insert(10);
        ht.insert(20);
        ht.insert(30);

        System.out.println(ht.search(20)); // Output: true
        System.out.println(ht.search(40)); // Output: false
    }
}
```

---

### **📌 Open Addressing with Linear Probing**
```java
class HashTableLinearProbing {
    private static final int SIZE = 10;
    private Integer[] table;

    public HashTableLinearProbing() {
        table = new Integer[SIZE];
    }

    private int hashFunction(int key) {
        return key % SIZE;
    }

    public void insert(int key) {
        int index = hashFunction(key);
        while (table[index] != null) { 
            index = (index + 1) % SIZE; // Linear probing
        }
        table[index] = key;
    }

    public boolean search(int key) {
        int index = hashFunction(key);
        int start = index;
        while (table[index] != null) {
            if (table[index] == key) return true;
            index = (index + 1) % SIZE;
            if (index == start) return false;
        }
        return false;
    }

    public void remove(int key) {
        int index = hashFunction(key);
        while (table[index] != null) {
            if (table[index] == key) {
                table[index] = null;
                return;
            }
            index = (index + 1) % SIZE;
        }
    }
}
```

---

## 📌 3. Complexity Analysis  

### **Time Complexity**
| Case                | Complexity | Explanation                                                            |
| ------------------- | ---------- | ---------------------------------------------------------------------- |
| 🟢 **Best Case**    | $O(1)$ | No collisions, direct access.                                          |
| 🟡 **Average Case** | $O(1)$ | With a well-distributed hash function, search is nearly constant-time. |
| 🔴 **Worst Case**   | $O(n)$ | All elements collide into a single bucket, degrading to linear search. |

### **Space Complexity**
- **Chaining**: $O(n)$ (extra space required for linked lists).
- **Open Addressing**: $O(m)$ (all elements stored directly in the table).

---

## 📌 4. Advantages and Disadvantages  

### **Advantages**
✔️ **Extremely fast search, insert, and delete operations** $O(1)$ in the average case).  
✔️ **Ideal for large datasets** with frequent access.  
✔️ **Does not require sorting** of data.  
✔️ Can store **key-value pairs** (like dictionaries in Python or HashMaps in Java).

### **Disadvantages**
❌ **Collisions degrade performance** if not handled properly.  
❌ **Does not maintain order**, making range queries inefficient.  
❌ **Requires a good hash function** to distribute keys evenly.  
❌ **Resizing is expensive** (when the load factor exceeds a threshold, rehashing is required).

---

## 📌 5. Types of Hash Functions

| Hash Function | Formula | Notes |
|--------------|---------|-------|
| **Modulo Hashing** | $h(k) = k \mod m$ | Simple, works best when $m$ is a prime number. |
| **Multiplicative Hashing** | $h(k) = \lfloor m (k \cdot A - \lfloor k \cdot A \rfloor) \rfloor$ | A is typically **$( \sqrt{5} - 1 ) / 2$** (golden ratio). |
| **Universal Hashing** | $h(k) = ((a \cdot k + b) \mod p) \mod m$ | Uses a random prime $p$, good for cryptographic applications. |

---

## 📌 6. Chaining vs. Open Addressing

| Feature | Chaining (Linked Lists) | Open Addressing |
|---------|-------------------------|-----------------|
| **Space Complexity** | $O(n)$ | $O(m)$ |
| **Search Performance** | Good when the load factor is low | Degrades with high load factor |
| **Insert Performance** | Fast, but extra memory needed | Slower when the table is full |
| **Load Factor Handling** | Can exceed 1 | Must be $<1$ to maintain efficiency |

---

## 📌 7. When to Choose Hash Search?

| **Scenario** | **Best Algorithm** |
|-------------|--------------------|
| Fast lookup required | ✅ Hash Search |
| Data needs to be sorted | ❌ Binary Search |
| Unsorted, small dataset | ❌ Sequential Search |
| Key-value storage required | ✅ Hash Tables |
| Handling dynamic data | ✅ Hashing with chaining |

---

## 📌 8. Summary  

- **Hash Search** is one of the **fastest search methods** available, with an **average-case complexity of $O(1)$**.  
- It is **ideal for large datasets** where fast lookup is needed.  
- **Collisions** must be handled properly using **chaining** or **open addressing**.  
- **Does not maintain order**, making it unsuitable for range-based queries.  