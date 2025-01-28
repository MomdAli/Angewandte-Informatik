#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

pthread_t p1, p2, p3;
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
int counter = 0;

void *workingThread(void *arg)
{
    int f = atoi(arg);
    for (int i = 0; i < f; i++)
    {
        pthread_mutex_lock(&lock);
        counter++;
        pthread_mutex_unlock(&lock);
    }
}

int main(int argc, char *argv[])
{
    pthread_create(&p1, NULL, workingThread, argv[1]);
    pthread_create(&p2, NULL, workingThread, argv[1]);
    pthread_create(&p3, NULL, workingThread, argv[1]);

    pthread_join(p1, NULL);
    pthread_join(p2, NULL);
    pthread_join(p3, NULL);

    pthread_mutex_destroy(&lock);
    printf("%d\n", counter);
    return 0;
}