#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sched.h>

int main(int argc, char *argv[])
{
    if (argc != 3)
    {
        printf("Usage: %s <number of pages> <number of trials>\n", argv[0]);
        return 1;
    }

    cpu_set_t cpuset;
    CPU_ZERO(&cpuset);
    CPU_SET(0, &cpuset); // Pin to CPU 0
    if (sched_setaffinity(0, sizeof(cpu_set_t), &cpuset) != 0)
    {
        perror("sched_setaffinity");
        return 1;
    }

    int pageSize = 4096;               // typisch 4 KB
    int jump = pageSize / sizeof(int); // Anzahl der int-Werte pro Seite

    int numPages = atoi(argv[1]);
    int numTrials = atoi(argv[2]);

    if (numPages <= 0 || numTrials <= 0)
    {
        printf("Beide Argumente müssen größer als 0 sein.\n");
        return 1;
    }

    int *array = malloc(numPages * jump * sizeof(int));

    struct timespec start, end;
    if (clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &start) == -1)
    {
        perror("clock_gettime");
        return 1;
    }

    for (int trial = 0; trial < numTrials; trial++)
    {
        for (int i = 0; i < numPages * jump; i += jump)
        {
            array[i] += 1;
        }
    }

    if (clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &end) == -1)
    {
        perror("clock_gettime");
        return 1;
    }

    long seconds = end.tv_sec - start.tv_sec;
    long nanoseconds = end.tv_nsec - start.tv_nsec;
    double elapsed = (seconds * 1e9 + nanoseconds) / (numPages * numTrials);
    printf("%d, %f\n", numPages, elapsed);

    free(array);
    return 0;
}
