#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>

struct __sem_t
{
    int value;
} typedef sem_t;

pthread_mutex_t sem_lock = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t sem_cond = PTHREAD_COND_INITIALIZER;
sem_t *semaphore;

volatile int counter = 0;
int loop;

void sem_init(sem_t *s)
{
    s->value = 1;
}

void sem_wait(sem_t *s)
{
    pthread_mutex_lock(&sem_lock);

    while (s->value <= 0)
        pthread_cond_wait(&sem_cond, &sem_lock);

    s->value--;
    pthread_mutex_unlock(&sem_lock);
}

void sem_post(sem_t *s)
{
    pthread_mutex_lock(&sem_lock);
    s->value++;

    pthread_cond_signal(&sem_cond);

    pthread_mutex_unlock(&sem_lock);
}

void *fun(void *arg)
{
    printf("Thread %s Starting...\n", (char *)arg);

    for (int i = 0; i < loop; i++)
    {
        sem_wait(semaphore);
        counter++;
        printf("Thread %s with counter = %d\n", (char *)arg, counter);
        sem_post(semaphore);
    }

    int *result = malloc(sizeof(int));
    *result = counter;
    return result;
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Run with %s <loops>\n", argv[0]);
        return 1;
    }

    pthread_t t1, t2;

    semaphore = (sem_t *)malloc(sizeof(sem_t));
    sem_init(semaphore);

    loop = atoi(argv[1]);

    pthread_create(&t1, NULL, fun, (void *)"A");
    pthread_create(&t2, NULL, fun, (void *)"B");

    void *a;
    void *b;

    pthread_join(t1, &a);
    pthread_join(t2, &b);

    free(semaphore);

    printf("Thread 1 ended with: %d\n", *(int *)a);
    printf("Thread 2 ended with: %d\n", *(int *)b);

    free(a);
    free(b);

    return 0;
}