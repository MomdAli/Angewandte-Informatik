---
title: Sorted Array Dictionary
tags:
  - Algorithm
  - Semester-3
  - Informatik
date: 2024-11-04
---
A **Sorted Array Dictionary** is a data structure that uses a sorted array to maintain key-value pairs in order. This approach allows efficient binary search but can have costly insertion and deletion operations due to the need for shifting elements.

> [!Info] Efficiency of Sorted Array Dictionary
> Sorted arrays enable efficient search with **O(log n)** time complexity due to binary search, but insertion and deletion take **O(n)** time because elements need to be shifted.

## Key Concepts

- **Sorted Array**: An array where elements are kept in a specific order, often allowing faster search operations.
- **Dictionary Interface**: The `Dictionary` interface can be implemented to use a sorted array as the underlying structure for managing key-value pairs.

## Operations on a Sorted Array Dictionary

### 1. Inserting a Value

To insert a value while maintaining order, elements must be shifted to make space for the new entry in its correct position.

```java title="Pseudocode"
function insert(array, key, value):
    index = findInsertIndex(array, key)
    shiftElementsRight(array, index)
    array[index] = new Entry(key, value)
```

### Time Complexity
- **Average-case**: O(n), due to shifting elements.
- **Worst-case**: O(n), as all elements may need to shift.

### 2. Searching for a Value

Since the array is sorted, binary search can be used for efficient key lookup.

```java title="Pseudocode"
function search(array, key):
    left = 0
    right = array.length - 1
    while left <= right:
        mid = (left + right) / 2
        if array[mid].key == key:
            return array[mid].value
        else if array[mid].key < key:
            left = mid + 1
        else:
            right = mid - 1
    return null  // Key not found
```

### Time Complexity
- **Average-case**: O(log n), due to binary search.
- **Worst-case**: O(log n).

### 3. Removing a Value

To remove an entry, binary search is used to locate the element. After locating the element, elements are shifted left to fill the gap.

```java title="Pseudocode"
function remove(array, key):
    index = binarySearch(array, key)
    if index != -1:
        shiftElementsLeft(array, index)
```

### Time Complexity
- **Average-case**: O(n), as elements need to be shifted.
- **Worst-case**: O(n).

## Algorithm Complexity

- **Insertion**: O(n) due to shifting.
- **Search**: O(log n) due to binary search.
- **Deletion**: O(n) due to shifting.

> [!Warning] Limitation of Sorted Array Dictionary
> Sorted arrays are efficient for search but inefficient for insertions and deletions. Other data structures, like balanced trees or hash maps, may be more efficient for dynamic data.

## Example Code in Java

Here’s how a sorted array can be implemented to support the `Dictionary` interface:

```java
public class SortedArrayDictionary<K extends Comparable<K>, V> implements Dictionary<K, V> {
    private Entry<K, V>[] array;
    private int size;

    public SortedArrayDictionary(int capacity) {
        array = new Entry[capacity];
        size = 0;
    }

    public V insert(K key, V value) {
        int index = findInsertIndex(key);
        shiftElementsRight(index);
        array[index] = new Entry<>(key, value);
        size++;
        return value;
    }

    private int findInsertIndex(K key) {
        int low = 0, high = size - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key.compareTo(array[mid].key) < 0) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }

    private void shiftElementsRight(int index) {
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
    }

    public V search(K key) {
        int index = binarySearch(key);
        return index != -1 ? array[index].value : null;
    }

    private int binarySearch(K key) {
        int low = 0, high = size - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key.equals(array[mid].key)) return mid;
            if (key.compareTo(array[mid].key) < 0) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    public V remove(K key) {
        int index = binarySearch(key);
        if (index != -1) {
            V removedValue = array[index].value;
            shiftElementsLeft(index);
            size--;
            return removedValue;
        }
        return null;
    }

    private void shiftElementsLeft(int index) {
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
    }
}
```

## Summary

A sorted array dictionary provides efficient searching with binary search, making it useful for read-heavy operations. However, due to the **O(n)** cost for insertion and deletion, it is not ideal for applications requiring frequent updates. For dynamic datasets, data structures like BSTs or hash maps may be preferable.
