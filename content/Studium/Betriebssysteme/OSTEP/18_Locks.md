---
title: 18. Locks
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-12-08
---
> [!info] 
> This is a summary of the 28th chapter of the book "Operating System: Three Easy Pieces" by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. It provides an in-depth explanation of locks, their importance in concurrent programming, different types of locks, and mechanisms to ensure synchronization and fairness.

## 1. Criteria for Locks
Locks are designed to manage concurrent access to shared resources. The key evaluation criteria for locks are:

1. **Correctness**:
   - Ensures **mutual exclusion**: Only one thread can be in a critical section at a time.
   - Guarantees **progress**: Deadlocks are avoided, and at least one thread can proceed.
   - Ensures **bounded waiting**: Prevents starvation by allowing every thread to access the lock eventually.

2. **Fairness**:
   - Threads should wait an equal amount of time to acquire the lock.

3. **Performance**:
   - Avoids unnecessary CPU usage (e.g., by reducing spin-waiting).

---

## 2. Solutions for Locks

### 2.1 Controlling Interrupts
- **Description**: Disables interrupts to ensure a critical section runs atomically.
- **Example**:
    ```c
    void lock() {
        DisableInterrupts();
    }
    void unlock() {
        EnableInterrupts();
    }
    ```
- **Problems**:
  - Fails in multiprocessor systems.
  - Relies on trusting applications to use the lock correctly.
  - Can cause lost interrupts, leading to system issues.

---

### 2.2 Software-Based Locks (Peterson's Algorithm)
- **Description**: Uses two shared flags and a "turn" variable to enforce mutual exclusion.
- **Example**:
    ```c
    int turn = 0;
    bool flag[2] = {false, false};

    void acquire(int tid) {
        flag[tid] = true;
        turn = 1 - tid;
        while (flag[1 - tid] && turn == 1 - tid);
    }

    void release(int tid) {
        flag[tid] = false;
    }
    ```
- **Advantages**:
  - Ensures mutual exclusion, progress, and bounded waiting.
- **Problems**:
  - Inefficient on modern hardware due to cache coherence issues.

---

## 3. Locks with Hardware Support

### 3.1 Test-and-Set
- **Description**: An atomic instruction to create simple locks.
- **Example**:
    ```c
    int TestAndSet(int *ptr, int new) {
        int old = *ptr;
        *ptr = new;
        return old;
    }
    ```
- **Spinlock Implementation**:
    ```c
    typedef struct {
        int flag;
    } lock_t;

    void lock(lock_t *lock) {
        while (TestAndSet(&lock->flag, 1) == 1); // spin
    }

    void unlock(lock_t *lock) {
        lock->flag = 0;
    }
    ```
- **Issues**:
  - Wastes CPU cycles during spin-waiting.
  - No fairness guarantees.

---

### 3.2 Compare-And-Swap
- **Description**: Atomically compares and updates a value.
- **Example**:
    ```c
    int CompareAndSwap(int *ptr, int expected, int new) {
        int actual = *ptr;
        if (actual == expected) {
            *ptr = new;
        }
        return actual;
    }
    ```

---

### 3.3 Fetch-And-Add
- **Description**: Atomically increments a value and returns the old value.
- **Example**:
    ```c
    int FetchAndAdd(int *ptr) {
        int old = *ptr;
        *ptr = old + 1;
        return old;
    }
    ```
- **Usage**:
    ```c
    typedef struct {
        int ticket;
        int turn;
    } lock_t;

    void lock(lock_t *lock) {
        int myturn = FetchAndAdd(&lock->ticket, 1);
        while (lock->turn != myturn); // spin
    }

    void unlock(lock_t *lock) {
        lock->turn++;
    }
    ```

---

## 4. Spin Alternatives

### 4.1 Yielding
- **Description**: Gives up the CPU when spinning to let another thread execute.
- **Example**:
    ```c
    void lock() {
        while (TestAndSet(&flag, 1) == 1)
            yield(); // give up the CPU
    }
    ```

---

### 4.2 Using Queues
- **Description**: Threads waiting for a lock are placed in a queue and sleep instead of spinning.
- **Example**:
    ```c
    typedef struct {
        int flag;
        int guard;
        queue_t *q;
    } lock_t;

    void lock(lock_t *m) {
        while (TestAndSet(&m->guard, 1) == 1); // acquire guard
        if (m->flag == 0) {
            m->flag = 1; // acquire lock
            m->guard = 0;
        } else {
            queue_add(m->q, gettid());
            m->guard = 0;
            park(); // sleep
        }
    }

    void unlock(lock_t *m) {
        while (TestAndSet(&m->guard, 1) == 1); // acquire guard
        if (queue_empty(m->q)) {
            m->flag = 0; // release lock
        } else {
            unpark(queue_remove(m->q)); // wake up next thread
        }
        m->guard = 0;
    }
    ```

---

### 4.3 Two-Phase Locks
- **Description**: Spins for a short time, then sleeps if the lock is not acquired.
- **Advantages**:
  - Reduces wasted CPU cycles when the lock is held for long durations.
  - Combines the benefits of spinning and sleeping.

---

## Summary
Locks ensure atomicity and synchronization in concurrent systems. Different locking mechanisms have unique trade-offs between performance, fairness, and complexity. Modern systems often use a mix of hardware and software techniques to optimize lock performance and fairness while avoiding common issues like spinning and starvation.


##### Next Chapter: [[19_Data Structures|Data Structures]]