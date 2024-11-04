---
title: Dictionary
tags:
  - Algorithm
  - Semester-3
  - Informatik
date: 2024-11-04
---

The `Dictionary` interface provided in the Java package `dictionary` defines a custom data structure that stores key-value pairs and offers basic dictionary functionality, such as insertion, search, and removal of entries. This interface uses Java Generics, allowing the dictionary to store keys and values of any specified type.

## Key Features

- **Generic Types**: The dictionary accepts generic types `K` (key type) and `V` (value type), making it flexible to store different types of data.
- **Iterable Interface**: The dictionary extends `Iterable`, allowing for iteration over its entries.
- **Entry Inner Class**: Defines an `Entry` class for individual key-value pairs, encapsulating both key and value with related methods.

## Interface Methods

The interface defines the following methods to manage and interact with dictionary entries:

### 1. `insert(K key, V value)`

This method associates a given value with a specified key in the dictionary. If the key already exists, the method replaces the existing value with the new one and returns the previous value. If the key is new, the method returns `null`.

**Example Usage**:
```java
Dictionary<String, Integer> dict = new MyDictionary<>();
dict.insert("apple", 5); // Inserts 'apple' with value 5
dict.insert("apple", 10); // Updates 'apple' value to 10, returns old value 5
```

### 2. `search(K key)`

Searches for the value associated with the given key. If the key is found, the method returns the value; otherwise, it returns `null`.

**Example Usage**:
```java
Integer value = dict.search("apple"); // Returns the value associated with 'apple', or null if not found
```

### 3. `remove(K key)`

Removes the entry with the specified key from the dictionary. If the key exists, the method returns the value associated with that key before removal. If the key does not exist, the method returns `null`.

**Example Usage**:
```java
Integer removedValue = dict.remove("apple"); // Removes 'apple' entry, returns its value or null if not found
```

### 4. `size()`

Returns the total number of entries currently in the dictionary.

**Example Usage**:
```java
int dictSize = dict.size(); // Returns the number of entries in the dictionary
```

### 5. `iterator()`

Returns an iterator over the entries in the dictionary. This iterator allows for iterating over all key-value pairs stored in the dictionary. No guarantees are made regarding the order of elements unless specified by the implementing class.

**Example Usage**:
```java
for (Dictionary.Entry<String, Integer> entry : dict) {
    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
}
```

## Entry<K, V> Inner Class

The `Entry` class represents a key-value pair in the dictionary and provides methods to access and modify the value.

### Methods in `Entry`:

- **`getKey()`**: Returns the key of this entry.
- **`getValue()`**: Returns the current value of this entry.
- **`setValue(V v)`**: Sets a new value for this entry, returning the old value.

**Example Usage**:
```java
Dictionary.Entry<String, Integer> entry = new Dictionary.Entry<>("apple", 5);
System.out.println("Key: " + entry.getKey());    // Outputs: apple
System.out.println("Value: " + entry.getValue()); // Outputs: 5

entry.setValue(10); // Sets new value
System.out.println("Updated Value: " + entry.getValue()); // Outputs: 10
```

## Summary

The `Dictionary` interface provides a blueprint for creating a dictionary-like data structure in Java, complete with methods to insert, search, remove, and iterate over key-value pairs. This custom dictionary structure, combined with the `Entry` inner class, offers a flexible and type-safe way to manage data in key-value format.