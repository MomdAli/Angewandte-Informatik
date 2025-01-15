---
title: 20. Conditional Variables
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-12-10
---
> [!info]
> This is a summary of the 30th chapter of the book "Operating System: Three Easy Pieces" by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. This chapter focuses on **Condition Variables**, their usage in concurrent programming, and their application in solving problems like `join`, `bounded buffer`, and `broadcast`.

## 1. Introduction
Locks alone are insufficient for building complex concurrent programs where threads need to wait for specific conditions to be true before proceeding. **Condition Variables (CVs)** address this need by allowing threads to:
1. Wait for a condition to be met (`wait()`).
2. Notify other threads when a condition changes (`signal()` or `broadcast()`). 

### Key Objectives
1. **Mutual Exclusion**: Ensured by locks.
2. **Thread Ordering**: Managed by condition variables and semaphores.

---

## 2. Use Case: `Join`
A **join** ensures a parent thread waits for a child thread to complete before proceeding.

### Example: Spin-Based Approach (Inefficient)
The parent continuously checks if the child is done, wasting CPU cycles.

```c
volatile int done = 0;

void *child(void *arg) {
    printf("child\n");
    done = 1;
    return NULL;
}

int main() {
    printf("parent: begin\n");
    pthread_t c;
    Pthread_create(&c, NULL, child, NULL);
    while (done == 0);  // spin
    printf("parent: end\n");
    return 0;
}
```

### Example: Using Condition Variables
Efficiently waits by sleeping until notified.

```c
int done = 0;
pthread_mutex_t m = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t c = PTHREAD_COND_INITIALIZER;

void thr_exit() {
    Pthread_mutex_lock(&m);
    done = 1;
    Pthread_cond_signal(&c);
    Pthread_mutex_unlock(&m);
}

void thr_join() {
    Pthread_mutex_lock(&m);
    while (done == 0)
        Pthread_cond_wait(&c, &m);
    Pthread_mutex_unlock(&m);
}

int main() {
    pthread_t p;
    printf("parent: begin\n");
    Pthread_create(&p, NULL, child, NULL);
    thr_join();
    printf("parent: end\n");
    return 0;
}
```

---

## 3. The Producer/Consumer Problem (Bounded Buffer)
In this problem, **producers** generate data and place it in a buffer, while **consumers** take data from the buffer. Synchronization ensures:
1. Producers wait if the buffer is full.
2. Consumers wait if the buffer is empty.

### Single Buffer Implementation
#### Routines
```c
int buffer;
int count = 0; // initially empty

void put(int value) {
    assert(count == 0); // ensure buffer is empty
    count = 1;
    buffer = value;
}

int get() {
    assert(count == 1); // ensure buffer is full
    count = 0;
    return buffer;
}
```

#### Thread Code
```c
void *producer(void *arg) {
    for (int i = 0; i < loops; i++) {
        put(i);
    }
}

void *consumer(void *arg) {
    while (1) {
        int tmp = get();
        printf("%d\n", tmp);
    }
}
```

### Using Condition Variables
With condition variables, producers and consumers only proceed when conditions are met.

#### Example: Single CV (Flawed)
Using one condition variable and `if` statements.

```c
cond_t cond;
mutex_t mutex;

void *producer(void *arg) {
    for (int i = 0; i < loops; i++) {
        Pthread_mutex_lock(&mutex);
        if (count == 1)
            Pthread_cond_wait(&cond, &mutex);
        put(i);
        Pthread_cond_signal(&cond);
        Pthread_mutex_unlock(&mutex);
    }
}
```

#### Example: Two CVs (Correct)
Two condition variables improve accuracy and prevent thread contention.

```c
cond_t empty, fill;
mutex_t mutex;

void *producer(void *arg) {
    for (int i = 0; i < loops; i++) {
        Pthread_mutex_lock(&mutex);
        while (count == MAX)
            Pthread_cond_wait(&empty, &mutex);
        put(i);
        Pthread_cond_signal(&fill);
        Pthread_mutex_unlock(&mutex);
    }
}

void *consumer(void *arg) {
    for (int i = 0; i < loops; i++) {
        Pthread_mutex_lock(&mutex);
        while (count == 0)
            Pthread_cond_wait(&fill, &mutex);
        int tmp = get();
        Pthread_cond_signal(&empty);
        Pthread_mutex_unlock(&mutex);
        printf("%d\n", tmp);
    }
}
```

---

## 4. Covering Conditions: Broadcast vs. Signal
Sometimes, `signal()` is insufficient, especially if multiple threads wait for different conditions. Using `broadcast()` ensures all waiting threads are notified.

### Example: Multithreaded Memory Allocator
```c
cond_t c;
mutex_t m;

void *allocate(int size) {
    Pthread_mutex_lock(&m);
    while (bytesLeft < size)
        Pthread_cond_wait(&c, &m);
    bytesLeft -= size;
    Pthread_mutex_unlock(&m);
    return ptr;
}

void free(void *ptr, int size) {
    Pthread_mutex_lock(&m);
    bytesLeft += size;
    Pthread_cond_broadcast(&c);
    Pthread_mutex_unlock(&m);
}
```

---

## 5. Key Takeaways
- Always check the condition in a `while` loop to handle spurious wakeups.
- Use multiple condition variables for distinct conditions (e.g., `empty` and `fill`).
- **Hold locks when signaling or waiting** to avoid race conditions.


##### Next Chapter: [[21_Semaphores|Semaphores]]