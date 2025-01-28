#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
    printf("%s\n", argv[0]);

    int pid = fork();
    int status;

    if (pid == 0)
    { // child
        printf("Child %d has started!\n", getpid());

        char *file = "./Echo1";
        char *const args[] = {"./Echo1", "Hello, ", "My ", "Friend!", NULL};
        execv(file, args);

        fprintf(stderr, "Failure!\n");
    }
    else if (pid > 0)
    { // parent
        printf("Parent %d has started with child %d!\n", getpid(), pid);
        wait(&status);
        printf("Parent has finished!\n");
        // printf("Got ")
    }
    else
    { // Failure
        fprintf(stderr, "Fork Failed\n");
        return 1;
    }

    return 0;
}