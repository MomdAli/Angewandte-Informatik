---
title: Binary Search
tags:
  - Algorithm
  - Semester-3
  - Informatik
  - Java
date: 2025-01-29
publish: true
---
> **Definition**  
> Binary Search is an efficient searching algorithm that finds an element in a **sorted** array by repeatedly dividing the search interval in half.  
> It is significantly **faster than Sequential Search**, particularly for large datasets.

---

## 📌 1. Purpose and Use Cases

### **When to Use Binary Search?**
- ✅ The dataset is **sorted**.
- ✅ The dataset is **large** and requires efficient searching.
- ✅ The dataset allows **random access** (e.g., arrays, but not linked lists).

### **When NOT to Use Binary Search?**
- ❌ The dataset is **unsorted** (Binary Search only works on sorted data).
- ❌ The data structure does not support **random access** (e.g., linked lists).

---

## 📌 2. Implementation

### **Iterative Implementation in Java**
```java
public class BinarySearch {
    public static int search(int[] array, int key) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoids integer overflow

            if (array[mid] == key) {
                return mid; // Element found
            }
            if (array[mid] < key) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        return -1; // Element not found
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

### **Recursive Implementation in Java**
```java
public class BinarySearchRecursive {
    public static int search(int[] array, int key, int left, int right) {
        if (left > right) {
            return -1; // Element not found
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == key) {
            return mid;
        }
        if (array[mid] < key) {
            return search(array, key, mid + 1, right);
        } else {
            return search(array, key, left, mid - 1);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        int key = 30;

        int result = search(numbers, key, 0, numbers.length - 1);
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

### **Time Complexity**
| Case         | Complexity  | Explanation |
|-------------|------------|-------------|
| Best Case   | $O(1)$ | Element is found at the **middle** of the array. |
| Average Case | $O(\log n)$ | The search space is halved in each step. |
| Worst Case  | $O(\log n)$ | Element is at the last possible position or not found. |

### **Space Complexity**
- Iterative Version: $O(1)$ → Uses a few extra variables.
- Recursive Version: $O(\log n)$ → Due to recursive function calls on the call stack.

---

## 📌 4. Advantages and Disadvantages  

### **Advantages**
✔️ Much **faster** than Sequential Search for large datasets.  
✔️ Efficient with a time complexity of $O(\log n)$.  
✔️ Requires **very little memory** for the iterative version.

### **Disadvantages**
❌ Works **only on sorted data**.  
❌ Recursion-based implementation uses **extra space** on the call stack.  
❌ **Not efficient for frequently changing datasets**, as sorting is required before searching.

---

## 📌 5. Iterative vs. Recursive Binary Search

| Feature | Iterative Binary Search | Recursive Binary Search |
|---------|-----------------|-----------------|
| **Performance** | Faster due to no recursion overhead | Slightly slower due to function call overhead |
| **Space Complexity** | $O(1)$ (constant space) | $O(\log n)$ (recursion stack) |
| **Ease of Understanding** | Easier to read and maintain | More elegant, follows mathematical definition |

---

## 📌 6. When to Choose Binary Search?

| **Scenario** | **Best Algorithm** |
|-------------|--------------------|
| Sorted dataset, random access | ✅ Binary Search |
| Unsorted dataset | ❌ Sorting first, then Binary Search |
| Linked lists | ❌ Sequential Search (due to no random access) |
| Large dataset | ✅ Binary Search |

---

## 📌 7. Summary  

- **Binary Search** is an efficient searching algorithm, but **requires sorted data**.  
- It operates in **$O(\log n)$ time**, making it much faster than Sequential Search.  
- **Two implementations exist**: **Iterative** (preferred for efficiency) and **Recursive** (more elegant but memory-intensive).  