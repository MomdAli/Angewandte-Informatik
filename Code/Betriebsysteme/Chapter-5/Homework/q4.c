#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
    int rc = fork();
    if (rc < 0) {
        fprintf(stderr, "fork failed\n");
        exit(1);
    } else if (rc == 0) { // child
        int rc_wait;
        if ((rc_wait = wait(NULL)) < 0) {
            fprintf(stderr, "wait failed\n");
            exit(1);
        }
        printf("Child hello\n");
    } else { // parent
        printf("Parent hello\n");
    }

    return 0;
}