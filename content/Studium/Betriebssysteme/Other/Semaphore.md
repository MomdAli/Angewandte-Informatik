---
{"publish":true,"created":"2025-01-25","modified":"2025-09-12T21:25:01.478+02:00","published":"2025-01-25","tags":["Betriebssysteme","Semester-3","Informatik"],"cssclasses":""}
---

This program uses a custom semaphore to synchronize threads incrementing a shared counter. Each thread runs a function that waits for the semaphore (`sem_wait`), increments the counter, and signals the semaphore (`sem_post`). The semaphore ensures mutual exclusion, preventing race conditions. The main function initializes the semaphore, creates threads, waits for them to finish, and prints the final counter value.

```c
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

struct __sem_t
{
    int value;
    pthread_mutex_t mutex;
    pthread_cond_t cond;
} typedef sem_t;

sem_t sem_lock;
volatile int counter = 0;
int loops = 100;

void sem_init(sem_t *s, int v)
{
    s->value = v;
}

void sem_wait(sem_t *s)
{
    pthread_mutex_lock(&s->mutex);

    while (s->value <= 0)
        pthread_cond_wait(&s->cond, &s->mutex);

    s->value--;
    pthread_mutex_unlock(&s->mutex);
}

void sem_post(sem_t *s)
{
    pthread_mutex_lock(&s->mutex);
    s->value++;
    pthread_cond_signal(&s->cond);
    pthread_mutex_unlock(&s->mutex);
}

void *func()
{
    for (int i = 0; i < loops; i++)
    {
        sem_wait(&sem_lock);
        counter++;
        sem_post(&sem_lock);
    }
}

int main(int argc, char *argv[])
{
    if (argc != 3)
    {
        fprintf(stderr, "usage: %s <value> <num_threads>\n", argv[0]);
        exit(1);
    }

    pthread_mutex_init(&sem_lock.mutex, NULL);
    pthread_cond_init(&sem_lock.cond, NULL);

    int num_threads = atoi(argv[2]);
    pthread_t t[num_threads];

    loops = atoi(argv[1]);
    sem_init(&sem_lock, 1);

    for (int i = 0; i < num_threads; i++)
    {
        pthread_create(&t[i], NULL, func, NULL);
    }

    for (int i = 0; i < num_threads; i++)
    {
        pthread_join(t[i], NULL);
    }

    printf("%d\n", counter);
}
```

Usage:
```bash
gcc -pthread -o semaphore semaphore.c
./semaphore 100 2
```

Output:
```bash
200
```
