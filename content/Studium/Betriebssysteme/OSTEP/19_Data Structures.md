---
title: 19. Data Structures
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-12-10
publish: true
---
> [!info]
> This is a summary of the 29th chapter of the book "Operating System: Three Easy Pieces" by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. This chapter focuses on how locks can be used to create thread-safe concurrent data structures like counters, linked lists, queues, and hash tables, while addressing challenges of correctness and scalability.

## 1. Introduction
Lock-based concurrent data structures ensure thread safety by preventing race conditions in critical sections. This chapter covers:
1. **Counters**
2. **Linked Lists**
3. **Queues**
4. **Hash Tables**

---

## 2. Concurrent Counters

### 2.1 Basic Counter Without Locks
A simple counter without locks is thread-unsafe due to race conditions.

```c
typedef struct {
    int value;
} counter_t;

void init(counter_t *c) {
    c->value = 0;
}

void increment(counter_t *c) {
    c->value++;
}

void decrement(counter_t *c) {
    c->value--;
}

int get(counter_t *c) {
    return c->value;
}
```

### 2.2 Counter with a Single Lock
Adding a single lock ensures correctness but sacrifices scalability.

```c
typedef struct {
    int value;
    pthread_mutex_t lock;
} counter_t;

void increment(counter_t *c) {
    pthread_mutex_lock(&c->lock);
    c->value++;
    pthread_mutex_unlock(&c->lock);
}
```

### 2.3 Approximate (Sloppy) Counters
- **Description**: Distributes increments across per-CPU local counters to reduce contention, periodically merging them into a global counter.
- **Key Idea**: Local counters have a threshold \(S\) determining when updates are propagated to the global counter. Higher \(S\) increases scalability but reduces accuracy.

```c
typedef struct {
    int global;
    pthread_mutex_t glock;
    int local[NUMCPUS];
    pthread_mutex_t llock[NUMCPUS];
    int threshold;
} counter_t;

void update(counter_t *c, int threadID, int amt) {
    int cpu = threadID % NUMCPUS;
    pthread_mutex_lock(&c->llock[cpu]);
    c->local[cpu] += amt;
    if (c->local[cpu] >= c->threshold) {
        pthread_mutex_lock(&c->glock);
        c->global += c->local[cpu];
        pthread_mutex_unlock(&c->glock);
        c->local[cpu] = 0;
    }
    pthread_mutex_unlock(&c->llock[cpu]);
}
```

---

## 3. Concurrent Linked Lists

### 3.1 Single-Lock Linked List
All operations are guarded by a single lock, ensuring correctness but limiting concurrency.

```c
typedef struct {
    node_t *head;
    pthread_mutex_t lock;
} list_t;

int List_Insert(list_t *L, int key) {
    pthread_mutex_lock(&L->lock);
    node_t *new = malloc(sizeof(node_t));
    if (!new) return -1;
    new->key = key;
    new->next = L->head;
    L->head = new;
    pthread_mutex_unlock(&L->lock);
    return 0;
}
```

### 3.2 Optimized Linked List
Moves non-critical operations (like `malloc`) outside the lock for better performance.

### 3.3 Hand-over-Hand Locking
- **Description**: Each node has its own lock. A thread locks the next node before releasing the current node’s lock during traversal.
- **Limitation**: Increased lock acquisition/release overhead often outweighs benefits.

---

## 4. Concurrent Queues

### Michael and Scott Queue
- **Key Idea**: Separate head and tail locks to allow enqueue and dequeue operations to proceed concurrently.

```c
typedef struct {
    node_t *head;
    node_t *tail;
    pthread_mutex_t head_lock, tail_lock;
} queue_t;

void Queue_Enqueue(queue_t *q, int value) {
    node_t *tmp = malloc(sizeof(node_t));
    tmp->value = value;
    tmp->next = NULL;

    pthread_mutex_lock(&q->tail_lock);
    q->tail->next = tmp;
    q->tail = tmp;
    pthread_mutex_unlock(&q->tail_lock);
}
```

---

## 5. Concurrent Hash Tables

### Bucket-Based Hash Table
- **Key Idea**: Uses per-bucket locks, enabling multiple threads to update separate buckets concurrently.

```c
#define BUCKETS 101

typedef struct {
    list_t lists[BUCKETS];
} hash_t;

int Hash_Insert(hash_t *H, int key) {
    int bucket = key % BUCKETS;
    return List_Insert(&H->lists[bucket], key);
}
```

### Performance
- Scales better than linked lists, especially under high contention.

---

## 6. Key Takeaways
1. **Correctness First**: Ensure thread safety before optimizing for performance.
2. **Avoid Premature Optimization**: Start with simple solutions; optimize only when necessary.
3. **Concurrency Isn't Always Faster**: Increased complexity may degrade performance.


##### Next Chapter: [[20_Condition Variables|Conditional Variables]]