---
title: 21. Semaphores
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2025-01-11
---
> [!info]
> This is a summary of Chapter 31 from the book *Operating System: Three Easy Pieces* by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. This chapter discusses **Semaphores**, their functionalities, and their applications in solving various concurrency problems like **locks**, **thread synchronization**, **bounded buffer**, and **reader-writer locks**.

## 1. Introduction
Semaphores, introduced by Edsger Dijkstra, are a synchronization primitive used in concurrent programming. They are versatile, capable of implementing both **locks** and **condition variables**, making them a powerful tool for managing shared resources.

### Key Concepts:
1. **Semaphore Operations**:
   - `sem_wait()`: Decrements the semaphore value, blocking if the value is zero or negative.
   - `sem_post()`: Increments the semaphore value, waking one waiting thread if applicable.
2. **Binary Semaphores**: Used as locks with an initial value of 1.
3. **Counting Semaphores**: Used to manage multiple resources, initialized to the number of available resources.

---

## 2. Binary Semaphores (Locks)
Semaphores can act as locks by setting their initial value to 1. The `sem_wait()` call ensures mutual exclusion, and `sem_post()` releases the lock.

### Example:
Two threads alternate in acquiring and releasing the lock, ensuring proper sequencing for accessing a critical section.

---

## 3. Semaphores for Thread Synchronization
Semaphores are used for ordering events in threads. A typical use case involves a parent waiting for a child thread to complete.

### Example:
```c
sem_t s;
sem_init(&s, 0, 0); // Semaphore initialized to 0

void *child(void *arg) {
    printf("child\n");
    sem_post(&s); // Notify parent
    return NULL;
}

int main() {
    sem_init(&s, 0, 0);
    pthread_t c;
    Pthread_create(&c, NULL, child, NULL);
    sem_wait(&s); // Wait for child
    printf("parent: end\n");
    return 0;
}
```

---

## 4. Producer/Consumer Problem (Bounded Buffer)
In this classic problem, **producers** generate data and place it in a buffer, while **consumers** retrieve it. Semaphores manage the synchronization between the two.

### Implementation:
- Two semaphores: `empty` (tracks available slots) and `full` (tracks filled slots).
- Mutex ensures mutual exclusion during buffer access.

---

## 5. Reader-Writer Locks
Reader-writer locks allow multiple readers or a single writer to access shared resources simultaneously. Semaphores are used to manage reader counts and ensure writers have exclusive access when needed.

### Key Operations:
- Readers acquire the lock unless a writer is active.
- Writers block until all readers have exited.

---

## 6. Advanced Use Cases
1. **Thread Throttling**: Limit the number of threads entering a critical region using semaphores.
2. **Dining Philosophers**: Semaphores resolve deadlocks in this concurrency problem by carefully ordering fork acquisition.
3. **Starvation-Free Mutexes**: Ensure fairness so that all threads eventually acquire the lock.

---

## 7. Implementing Semaphores
Semaphores can be implemented using **locks** and **condition variables**. The key challenge is ensuring atomic updates and managing waiting threads efficiently.

### Example:
```c
typedef struct {
    int value;
    pthread_cond_t cond;
    pthread_mutex_t lock;
} Zem_t;

void Zem_wait(Zem_t *s) {
    pthread_mutex_lock(&s->lock);
    while (s->value <= 0)
        pthread_cond_wait(&s->cond, &s->lock);
    s->value--;
    pthread_mutex_unlock(&s->lock);
}

void Zem_post(Zem_t *s) {
    pthread_mutex_lock(&s->lock);
    s->value++;
    pthread_cond_signal(&s->cond);
    pthread_mutex_unlock(&s->lock);
}
```

---

## 8. Key Takeaways
- Semaphores simplify synchronization tasks in concurrent programs.
- Proper initialization is critical: binary semaphores for locks, counting semaphores for managing resources.
- Understanding common patterns (e.g., producer-consumer, reader-writer) aids in solving real-world problems.
- Deadlock and starvation must be carefully addressed in semaphore-based solutions.

This chapter highlights the versatility and power of semaphores while emphasizing the importance of careful design to avoid common pitfalls in concurrent programming.

##### Next Chapter: [[22_Concurrency_Problems|Concurrency Problems]]