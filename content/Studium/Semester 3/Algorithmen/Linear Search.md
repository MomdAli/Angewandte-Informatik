---
title: Linear Search
tags:
  - Algorithm
  - Semester-3
  - Informatik
date: 2024-11-04
---

Linear search is a straightforward search algorithm that checks each element in a collection one by one until the target element is found. In the context of a dictionary implemented using the custom `Dictionary` interface, linear search can be adapted to search through entries either by keys or by values.

> [!Info] Note
> Linear search is an **O(n)** operation, where **n** is the number of elements in the dictionary. This means the time complexity is directly proportional to the number of entries, making it less efficient for large dictionaries.

## Key Concepts

- **Linear Search on Keys**: Used to check if a specific key exists in the dictionary.
- **Linear Search on Values**: Used to find a specific value among the dictionary entries.
- **Time Complexity**: O(n), where n is the number of entries in the dictionary.
- **Space Complexity**: O(1), as it only requires constant space for tracking the current position.

## Linear Search on Keys

The goal of a linear search on keys is to check if a particular key exists in the dictionary by iterating through each entry until the key is found.

### Algorithm
1. Start at the beginning of the dictionary.
2. For each entry, compare the current key with the target key.
3. If a match is found, return the associated value.
4. If the end of the dictionary is reached without finding the key, return `null`.

### Pseudocode

```java
function searchKey(dictionary, targetKey):
    for each entry in dictionary:
        if entry.key == targetKey:
            return entry.value
    return null  // Key not found
```

### Time Complexity
- **Worst-case**: O(n), where n is the total number of entries.
- **Best-case**: O(1), if the key is found at the first entry.

### Example Usage

```java
Dictionary<String, Integer> dict = new MyDictionary<>();
dict.insert("apple", 5);
dict.insert("banana", 3);

Integer result = searchKey(dict, "banana"); // Returns 3
Integer notFound = searchKey(dict, "grape"); // Returns null
```

## Linear Search on Values

A linear search on values iterates through each entry to find a specific value. Unlike key search, where each key is unique, multiple keys may map to the same value.

### Algorithm
1. Start from the first entry in the dictionary.
2. For each entry, check if the value matches the target value.
3. If a match is found, return the key associated with this value (or a list of all keys with this value).
4. If the end of the dictionary is reached without finding the value, return `null`.

### Pseudocode

```java
function searchValue(dictionary, targetValue):
    for each entry in dictionary:
        if entry.value == targetValue:
            return entry.key  // Return the associated key (or a list of keys if needed)
    return null  // Value not found
```

### Time Complexity
- **Worst-case**: O(n), where n is the total number of entries.
- **Best-case**: O(1), if the value is found at the first entry.

### Example Usage

```java
Dictionary<String, Integer> dict = new MyDictionary<>();
dict.insert("apple", 5);
dict.insert("banana", 3);

String keyForValue = searchValue(dict, 3); // Returns "banana"
String notFound = searchValue(dict, 10); // Returns null
```

## Algorithm Complexity

- **Time Complexity**: Both key and value searches have an average time complexity of O(n), as each entry may need to be checked.
- **Space Complexity**: O(1), as only a constant amount of extra space is used, regardless of the number of entries.

> [!Warning] Limitations
> - **Inefficiency for Large Data**: Linear search is not efficient for large datasets. Alternative search methods (e.g., binary search on sorted data) or data structures (e.g., hash maps) are generally preferred in cases where efficiency is crucial.
> - **Multiple Values**: If the dictionary has multiple entries with the same value, a single-value search will only return the first match. To retrieve all keys with the same value, modifications to the `searchValue` function may be necessary.

## Example Code in Java (Using the Dictionary Interface)

Here’s how a linear search can be implemented using the `Dictionary` interface:

```java
public static <K, V> V searchKey(Dictionary<K, V> dict, K targetKey) {
    for (Dictionary.Entry<K, V> entry : dict) {
        if (entry.getKey().equals(targetKey)) {
            return entry.getValue();
        }
    }
    return null;  // Key not found
}

public static <K, V> K searchValue(Dictionary<K, V> dict, V targetValue) {
    for (Dictionary.Entry<K, V> entry : dict) {
        if (entry.getValue().equals(targetValue)) {
            return entry.getKey();
        }
    }
    return null;  // Value not found
}
```

### Explanation
- `searchKey()` iterates through the dictionary’s entries to find a specific key.
- `searchValue()` finds the first entry that matches a given value and returns its key.

## Summary

Linear search is a simple, intuitive algorithm for searching dictionaries, especially useful for small datasets or unsorted data. While it has a time complexity of O(n), which is generally inefficient for large dictionaries, it remains a valuable approach for its simplicity and flexibility.
