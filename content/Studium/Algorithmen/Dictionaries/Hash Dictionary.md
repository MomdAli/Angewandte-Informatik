---
title: Hash Dictionary
tags:
  - Algorithm
  - Semester-3
  - Informatik
date: 2024-11-04
publish: true
---
A **Hash Dictionary** is a data structure that uses hashing to store key-value pairs. Hashing enables constant-time complexity for search, insertion, and deletion operations on average, making hash dictionaries ideal for scenarios with large datasets and frequent updates.

> [!Info] Efficiency of Hash Dictionaries
> Hash dictionaries offer **O(1)** average time complexity for search, insert, and delete operations. However, in rare cases, collisions can lead to **O(n)** time complexity.

## Key Concepts

- **Hashing**: A process that converts a key into an integer index in an array, allowing constant-time access.
- **Dictionary Interface**: The `Dictionary` interface can be implemented to use a hash map as the underlying structure for managing key-value pairs.


## Operations on a Hash Dictionary

### 1. Inserting a Value

To insert a value, the key is hashed to produce an index. If the index is empty, the key-value pair is stored. If a collision occurs, open addressing or chaining resolves the conflict.

```java title="Pseudocode"
function insert(hashTable, key, value):
    index = hash(key)
    if hashTable[index] is empty:
        hashTable[index] = new Entry(key, value)
    else:
        handleCollision(hashTable, index, key, value)
```

### Time Complexity
- **Average-case**: O(1), as each key is mapped to a unique index.
- **Worst-case**: O(n), if many collisions occur.

### 2. Searching for a Value

To search for a value, the key is hashed to find the index. If there’s no entry at that index, the key isn’t in the dictionary. If a collision resolution method is used, it must be accounted for.

```java title="Pseudocode"
function search(hashTable, key):
    index = hash(key)
    if hashTable[index] contains key:
        return hashTable[index].value
    else:
        return resolveCollision(hashTable, index, key)
```

### Time Complexity
- **Average-case**: O(1).
- **Worst-case**: O(n), with many collisions.

### 3. Removing a Value

To remove an entry, hash to locate the index, then mark the entry as deleted. Collision resolution methods ensure any linked entries are retrievable.

```java title="Pseudocode"
function remove(hashTable, key):
    index = hash(key)
    if hashTable[index] contains key:
        hashTable[index] = null  // Or mark as deleted
    else:
        resolveRemoveCollision(hashTable, index, key)
```

### Time Complexity
- **Average-case**: O(1).
- **Worst-case**: O(n).

## Algorithm Complexity

- **Insertion**: O(1) on average, due to hashing.
- **Search**: O(1) on average, due to direct index access.
- **Deletion**: O(1) on average.

> [!Warning] Limitation of Hash Dictionaries
> Hash dictionaries have **O(1)** average performance, but poor hash functions and collision handling can degrade performance to **O(n)**.

## Example Code in Java

Here’s how a hash dictionary can be implemented to support the `Dictionary` interface:

```java
public class HashDictionary<K, V> implements Dictionary<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private Entry<K, V>[] table;
    private int size;

    public HashDictionary() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    public V insert(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
        } else {
            Entry<K, V> current = table[index];
            while (current != null) {
                if (current.getKey().equals(key)) {
                    V oldValue = current.getValue();
                    current.setValue(value);
                    return oldValue;
                }
                if (current.next == null) break;
                current = current.next;
            }
            current.next = new Entry<>(key, value);
        }
        size++;
        return value;
    }

    public V search(K key) {
        int index = hash(key);
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        Entry<K, V> previous = null;
        while (current != null) {
            if (current.getKey().equals(key)) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.getValue();
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
        public void setValue(V value) { this.value = value; }
    }
}
```

## Summary

A hash dictionary provides fast search, insertion, and deletion with an average **O(1)** complexity due to direct hashing. However, performance depends on a good hash function and effective collision handling. Hash tables are ideal for large datasets where efficient updates are crucial.
