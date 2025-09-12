---
{"publish":true,"title":"Sequential Search","created":"2025-01-29","modified":"2025-09-12T21:25:01.153+02:00","published":"2025-01-29","tags":["Algorithm","Semester-3","Informatik","Java"],"cssclasses":""}
---

> **Definition**:  
> Sequential Search (also called *Linear Search*) is a basic algorithm used to find a specific element in a collection by checking each element one by one.  
> It is most effective for **small datasets** or **unsorted data**.

---

## 📌 1. Purpose and Use Cases

🔹 **When to use Sequential Search?**  
✅ When the dataset is **unsorted**.  
✅ When the dataset is **small**.  
✅ When there is **no efficient index structure** to optimize searching.  

🔹 **When NOT to use it?**  
❌ When working with **large datasets** (better alternatives exist, like **Binary Search** or **Hash Tables**).  

---

## 📌 2. Implementation

### **📌 Iterative Implementation in Java**
```java
public class SequentialSearch {
    public static int search(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i; // Return index if found
            }
        }
        return -1; // Return -1 if not found
    }

    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        int key = 30;
        
        int result = search(numbers, key);
        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found.");
        }
    }
}
```

---

## 📌 3. Complexity Analysis  

### **📌 Time Complexity**
| Case | Complexity | Explanation |
|------|------------|-------------|
| 🟢 **Best Case** | $O(1)$ | Element is found at the **first position** |
| 🟡 **Average Case** | $O(n)$ | On average, half the elements are checked |
| 🔴 **Worst Case** | $O(n)$ | Element is at the **last position** or **not found** |

### **📌 Space Complexity**
- $O(1)$ → Uses only a few extra variables, making it memory-efficient.

---

## 📌 4. Advantages and Disadvantages  

> 💡 **Pros**  
✔️ Simple to implement.  
✔️ Works on **both sorted and unsorted lists**.  
✔️ Does **not require additional memory**.

> ⚠️ **Cons**  
❌ **Inefficient for large datasets**.  
❌ **Slower than Binary Search** when searching in a sorted dataset.  
❌ Not suitable when **frequent searches** are needed in a large dataset.

---

## 📌 5. Variations of Sequential Search
🔹 **Sentinel Search**: Improves performance by placing a "sentinel" at the end to eliminate extra boundary checks.  
🔹 **Self-Organizing Search**: Moves frequently searched elements to the front for **better future performance**.  

---

## 📌 6. When to Choose Sequential Search?

| **Scenario** | **Best Algorithm** |
|-------------|--------------------|
| 🔍 **Small dataset** | ✅ **Sequential Search** |
| 📋 **Unsorted data** | ✅ **Sequential Search** |
| 🏎️ **Sorted data** | ❌ **Binary Search** |
| 🏢 **Large dataset** | ❌ **Hashing or Tree-based Search** |

---

## 📌 7. Summary  

🔹 **Sequential Search** is the simplest searching method, but **not efficient** for large datasets.  
🔹 Suitable for **small, unsorted collections** where search operations are **infrequent**.  
🔹 **Time Complexity:** $O(n)$, **Space Complexity:** $O(1)$.  

---

🚀 **Want to learn more?**  
Next, check out **[Binary Search](#)** for an optimized searching method for sorted lists!  
