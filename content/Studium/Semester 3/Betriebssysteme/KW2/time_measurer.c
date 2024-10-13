#include <stdio.h>
#include <unistd.h>
#include <sys/time.h>
#include <sys/wait.h>

#define ITERATIONS 100000

int main() {
	struct timeval start, end;
	long total_time = 0;
	
	// Start timer
	gettimeofday(&start, NULL);
	
	// Perform the system call in a loop
	for (int i = 0; i < ITERATIONS; i++) {
		getpid();
	}
	
	// End timer
	gettimeofday(&end, NULL);
	
	// Calculate total elapsed time (in microseconds)
	total_time = (end.tv_sec - start.tv_sec) * ITERATIONS + (end.tv_sec - 
					start.tv_sec);
	
	// Calculate average time per system call
	double avg_time = (double) total_time / ITERATIONS;
	
	printf("Average time per system call: %.6f microseconds\n", avg_time);
	
	return 0;
}


