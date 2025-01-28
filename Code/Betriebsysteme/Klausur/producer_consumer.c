#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <assert.h>

#define MAX 3

pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t fill = PTHREAD_COND_INITIALIZER;
pthread_cond_t empty = PTHREAD_COND_INITIALIZER;

int buffer[MAX];
int fill_ptr = 0;
int use_ptr = 0;
int count = 0;
int loop;

void put(int value)
{
    buffer[fill_ptr] = value;
    fill_ptr = (fill_ptr + 1) % MAX;
    count++;
}

int get()
{
    int tmp = buffer[use_ptr];
    use_ptr = (use_ptr + 1) % MAX;
    count--;
    return tmp;
}

void *producer()
{
    for (int i = 0; i < loop; i++)
    {
        pthread_mutex_lock(&lock);

        while (count == MAX) // check for saved signal
        {
            printf("Reached Max Going to Sleep....\n");
            pthread_cond_wait(&empty, &lock); // wait for signal
        }
        put(i);
        pthread_cond_signal(&fill); // send signal that it's filled

        pthread_mutex_unlock(&lock);
    }
}

void *consumer()
{
    for (int i = 0; i < loop; i++)
    {
        pthread_mutex_lock(&lock); // First lock the critical section

        while (count == 0)                   // Check for saved signal
            pthread_cond_wait(&fill, &lock); // wait for signal

        int num = get();

        pthread_cond_signal(&empty); // send signal that its empty
        pthread_mutex_unlock(&lock);

        printf("Got number: %d\n", num);
    }
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Run with %s <loops>\n", argv[0]);
        return 1;
    }

    loop = atoi(argv[1]);

    pthread_t consumer_thread, producer_thread;

    pthread_create(&producer_thread, NULL, producer, NULL);
    pthread_create(&consumer_thread, NULL, consumer, NULL);

    pthread_join(producer_thread, NULL);
    pthread_join(consumer_thread, NULL);

    return 0;
}