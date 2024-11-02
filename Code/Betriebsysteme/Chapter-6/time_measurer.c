#include <stdio.h>
#include <stdint.h>
#include <unistd.h>
#include <x86intrin.h>

#define ITERATIONS 1000

int main()
{
    uint64_t start, end;

    // Start timer
    start = __rdtsc();

    // Perform the system call in a loop
    for (int i = 0; i < ITERATIONS; i++)
    {
        read(0, NULL, 0);
    }

    end = __rdtsc();

    uint64_t avg_time = (end - start) / ITERATIONS;

    printf("Average time per system call: %lu microseconds\n", avg_time);

    return 0;
}