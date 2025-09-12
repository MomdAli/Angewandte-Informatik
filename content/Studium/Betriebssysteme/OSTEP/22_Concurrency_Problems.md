---
{"publish":true,"title":"22. Concurrency Problems","created":"2025-01-11","modified":"2025-09-12T21:25:02.170+02:00","published":"2025-01-11","tags":["Betriebssysteme","Semester-3","Informatik"],"cssclasses":""}
---

> [!info] 
> This is a summary of Chapter 32 from the book _Operating System: Three Easy Pieces_ by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. This chapter examines **Common Concurrency Problems**, including types of concurrency bugs such as **non-deadlock bugs** (atomicity and order violations) and **deadlock bugs**, their causes, and methods to prevent or handle them.

## 1. Introduction

Concurrency bugs are common in complex systems. This chapter categorizes these bugs, explores their origins, and discusses strategies to avoid or mitigate them. Understanding these patterns enables developers to write more robust concurrent code.

### Key Questions:

1. What are the common concurrency bugs in real-world applications?
2. How can we prevent or handle these bugs effectively?

---

## 2. Non-Deadlock Bugs

Non-deadlock bugs are more common than deadlock bugs and are often classified into:

1. **Atomicity Violations**: Failure to execute a sequence of operations as an atomic unit.
2. **Order Violations**: Incorrect order of execution between two operations.

### Atomicity Violation Example:

A thread checks a variable for `NULL` and then uses it, while another thread sets it to `NULL` in between. This causes a crash.

**Fix**: Use a lock to ensure atomic access:

```c
pthread_mutex_lock(&lock);
if (variable) {
    use(variable);
}
pthread_mutex_unlock(&lock);
```

### Order Violation Example:

A thread accesses a variable before another thread initializes it.

**Fix**: Use condition variables to enforce correct order:

```c
pthread_mutex_lock(&lock);
while (!initialized)
    pthread_cond_wait(&cond, &lock);
use(variable);
pthread_mutex_unlock(&lock);
```

---

## 3. Deadlock Bugs

Deadlocks occur when threads hold resources while waiting for others, creating a circular dependency.

### Four Necessary Conditions for Deadlock:

1. **Mutual Exclusion**: Resources are held exclusively.
2. **Hold-and-Wait**: Threads hold resources while waiting for others.
3. **No Preemption**: Resources cannot be forcibly released.
4. **Circular Wait**: A circular chain of resource requests exists.

### Example of Deadlock:

```c
pthread_mutex_lock(&L1);
pthread_mutex_lock(&L2);
```

If another thread locks `L2` before `L1`, both threads can deadlock.

---

## 4. Deadlock Prevention Techniques

1. **Avoid Circular Wait**: Impose a lock acquisition order.
    
    ```c
    if (L1 > L2) {
        pthread_mutex_lock(L1);
        pthread_mutex_lock(L2);
    } else {
        pthread_mutex_lock(L2);
        pthread_mutex_lock(L1);
    }
    ```
    
2. **Eliminate Hold-and-Wait**: Acquire all locks at once.
    
    ```c
    pthread_mutex_lock(prevention_lock);
    pthread_mutex_lock(L1);
    pthread_mutex_lock(L2);
    pthread_mutex_unlock(prevention_lock);
    ```
    
3. **Allow Preemption**: Use `trylock` to avoid indefinite waiting.
    
    ```c
    if (pthread_mutex_trylock(L1) == 0) {
        if (pthread_mutex_trylock(L2) == 0) {
            // Critical Section
            pthread_mutex_unlock(L2);
        }
        pthread_mutex_unlock(L1);
    }
    ```
    
4. **Eliminate Mutual Exclusion**: Use lock-free data structures.
    
    ```c
    void AtomicIncrement(int *value) {
        do {
            int old = *value;
        } while (CompareAndSwap(value, old, old + 1) == 0);
    }
    ```
    

---

## 5. Deadlock Avoidance

Deadlock avoidance requires global knowledge of thread behavior. Algorithms like **Dijkstra’s Banker’s Algorithm** schedule threads in a way that prevents circular waits. However, this approach is limited to environments with predictable behavior.

---

## 6. Deadlock Detection and Recovery

Some systems allow deadlocks to occur but periodically detect and resolve them by:

1. Building a resource graph and identifying cycles.
2. Terminating or restarting affected processes.

---

## 7. Summary

Concurrency problems can be broadly classified into non-deadlock and deadlock bugs. Understanding common bug patterns, such as atomicity and order violations, is critical for prevention. Deadlock requires careful lock acquisition strategies, and modern approaches like lock-free data structures and condition variables offer promising alternatives. However, concurrency challenges often demand deep understanding, systematic debugging, and careful design to avoid pitfalls.

##### Next Chapter: [[Studium/Betriebssysteme/OSTEP/23_IO_Devices\|IO Devices]]