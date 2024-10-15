#include <stdio.h>
#include <stdint.h>
#include <unistd.h>
#include <x86intrin.h>

#define ITERATIONS 10000000

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

    double avg_time = (end - start) / ITERATIONS;

    printf("Average time per system call: %.6f microseconds\n", avg_time);

    return 0;
}