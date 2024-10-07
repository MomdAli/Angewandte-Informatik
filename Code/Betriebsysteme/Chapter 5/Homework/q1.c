#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    int x = 100;

    int rc = fork();
    if (rc < 0) {
        fprintf(stderr, "fork failed\n");
        exit(1);
    } else if (rc == 0) { // child
        printf("child pid: %d\n", getpid());
        printf("before Changing x: %d\n", x);
        x = 50;
        printf("after changing x: %d\n", x);
    } else { // parent
        printf("parent pid: %d\n", getpid());
        printf("before Changing x: %d\n", x);
        x = 25;
        printf("after Changing x: %d\n", x);
    }

    return 0;
}