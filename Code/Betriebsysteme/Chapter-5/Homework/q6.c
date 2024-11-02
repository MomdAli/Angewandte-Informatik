#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
    int rc = fork();
    int rc2 = fork();
    int status;

    if (rc < 0) {
        fprintf(stderr, "fork failed\n");
        exit(1);
    } else if (rc == 0) { // child 0
        printf("Child 0 hello\n");
    } else if (rc2 == 1) { // child 1
        printf("Child 1 hello\n");
    } else { // parent
        int rc_wait = waitpid(rc, &status, 0);
        printf("rc_wait: %d, status: %d\n", rc_wait, status);
    }

    return 0;
}