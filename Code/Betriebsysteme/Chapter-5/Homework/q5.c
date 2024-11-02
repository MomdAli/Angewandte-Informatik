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
        printf("Child hello\n");
    } else { // parent
        int rc_wait = wait(NULL);
        printf("rc_wait: %d\n", rc_wait);
        printf("Parent hello\n");
    }

    return 0;
}