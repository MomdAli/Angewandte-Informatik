---
title: Test-And-Set
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-12-10
---
```c
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#define LOCK 1
#define UNLOCK 0

typedef struct __lock_t
{
    int flag;
} lock_t;

lock_t *local_mutex;
static volatile int counter = 0;

lock_t *initialize_lock()
{
    lock_t *mutex = (lock_t *)malloc(sizeof(lock_t));
    if (mutex == NULL)
    {
        fprintf(stderr, "Failed to allocate memory for mutex\n");
        return NULL;
    }

    mutex->flag = UNLOCK;

    return mutex;
}

int test_and_set(int *ptr, int new)
{
    int old;
    __asm__ volatile(
        "xchg %0, %1"           // Atomically exchange values
        : "=r"(old), "+m"(*ptr) // Output operands
        : "0"(new)              // Input operand
        : "memory"              // Clobbered memory
    );

    return old;
}

void lock(lock_t *mutex)
{
    while (test_and_set(&mutex->flag, LOCK) == LOCK)
        ;
}

void unlock(lock_t *mutex)
{
    mutex->flag = UNLOCK;
}

void *threadFun(void *arg)
{

    char thread_id = (char)arg;

    for (int i = 0; i < 1e7; i++)
    {
        lock(local_mutex);
        counter++;
        unlock(local_mutex);
    }
    return NULL;
}

int main()
{
    pthread_t thread1, thread2;
    if ((local_mutex = initialize_lock()) == NULL)
        return 1;

    printf("Starting...\n");
    pthread_create(&thread1, NULL, threadFun, (void *)'A');
    pthread_create(&thread2, NULL, threadFun, (void *)'B');
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
    printf("Finished: %d\n", counter);
    free(local_mutex);
    return 0;
}
```