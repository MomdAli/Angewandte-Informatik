#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>

int main(int argc, char *argv[])
{
    if (argc < 2 || argc > 3)
    {
        fprintf(stderr, "Usage: %s <memory in MB> [time in s]\n", argv[0]);
        return 1;
    }

    long long int size;
    if (atoi(argv[1]) > 0)
    {
        size = atoi(argv[1]);
    }
    else
    {
        fprintf(stderr, "Memory size should be greater than 0\n");
        return 1;
    }

    size *= 1024 * 1024 / sizeof(int);
    int *arr = malloc(size * sizeof(int));

    if (arr == NULL)
    {
        fprintf(stderr, "Failed to allocate memory\n");
        return 1;
    }

    time_t run_time = -1;
    if (argc == 3)
    {
        run_time = atoi(argv[2]);
        if (run_time <= 0)
        {
            fprintf(stderr, "Time should be greater than 0\n");
            return 1;
        }
    }

    printf("Starting the program (PID : %d)\n", getpid());
    time_t start_time = time(NULL);

    while (1)
    {
        for (long long int i = 0; i < size; i++)
        {
            arr[i] = i; // Touching each byte of memory to keep it in use
        }

        if (run_time > 0 && difftime(time(NULL), start_time) >= run_time)
        {
            printf("Completed running for %ld seconds.\n", run_time);
            break;
        }
    }

    free(arr);
    return 0;
}