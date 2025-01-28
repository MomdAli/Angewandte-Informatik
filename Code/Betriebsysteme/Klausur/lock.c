#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>

#define LOCK 1
#define UNLOCK 0

struct __lock_t
{
    int value;
} typedef lock_t;

lock_t *mutex;
volatile int counter = 0;
int loop = 100;

// int test_and_set(int *ptr, int new)
// {
//     int old = *ptr;
//     *ptr = new;
//     return old;
// }

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

lock_t *lock_init()
{
    lock_t *lock = (lock_t *)malloc(sizeof(lock_t));

    if (!lock)
    {
        fprintf(stderr, "Error: Unable to allocate memory for lock\n");
        exit(1);
    }

    lock->value = UNLOCK;
    return lock;
}

void lock_destroy(lock_t *lock)
{
    free(lock);
}

void acquire(lock_t *lock)
{
    while (test_and_set(&lock->value, LOCK) == LOCK)
        ;
}

void unlock(lock_t *lock)
{
    lock->value = UNLOCK;
}

void *fun()
{
    for (int i = 0; i < loop; i++)
    {
        acquire(mutex);
        counter++;
        unlock(mutex);
    }
}

int main(int argc, char *argv[])
{
    pthread_t t1, t2;

    if ((mutex = lock_init()) == NULL)
    {
        fprintf(stderr, "Error: Unable to initialize lock\n");
        exit(1);
    }

    if (argc != 2)
    {
        fprintf(stderr, "usage: %s <value>\n", argv[0]);
        return 1;
    }

    loop = atoi(argv[1]);

    pthread_create(&t1, NULL, fun, NULL);
    pthread_create(&t2, NULL, fun, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    lock_destroy(mutex);

    printf("%d\n", counter);
    return 0;
}