#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
    // O_CREAT: create file if it does not exist
    // O_WRONLY: open file for writing only
    // O_TRUNC: truncate file if it exists
    // S_IRWXU: read, write, execute for owner
    int fd = open("./test.txt", O_CREAT|O_WRONLY|O_TRUNC, S_IRWXU);

    if (fd == -1) {
        fprintf(stderr, "open failed\n");
        exit(1);
    }

    int rc = fork();
    if (rc < 0) {
        fprintf(stderr, "fork failed\n");
        exit(1);
    } else if (rc == 0) { // child
        printf("child (pid: %d) is writing to test.txt\n", getpid());
        write(fd, "Child was here.\n", 16);
    } else { // parent
        printf("parent (pid: %d) is writing to test.txt\n", getpid());
        write(fd, "Parent was here.\n", 17);
    }

    close(fd);

    return 0;
}