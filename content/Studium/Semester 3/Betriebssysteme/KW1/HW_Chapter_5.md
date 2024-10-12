---
title: Homework Chapter 5
tags:
  - Betriebssysteme
  - Homework
  - Python
  - Semester-3
date: 2024-10-03
aliases:
  - HW5
  - Homework5
cssclasses:
---
## <font color="#71e9ac">Simulation</font>
This simulation homework focuses on `fork.py`, a simple process creation simulator that shows how processes are related in a single “familial” tree. Read the relevant README for details about how to run the simulator.

### Question 1:
Run ./fork.py -s 10 and see which actions are taken. Can you predict what the process tree looks like at each step? Use the -c flag to check your answers. Try some different random seeds (-s) or add more actions (-a) to get the hang of it.
### Answer:
Action: a forks b
	a
	└── b

Action: a forks c
	a
	└── b
	└── c
	
Action: c EXITS
	a
	└── b

Action: a forks d
	a
	└── b
	└── d
	
Action: a forks e
	a
	└── b
	└── d
	└── e


### Question 2:
One control the simulator gives you is the fork percentage, controlled by the -f flag. The higher it is, the more likely the next action is a fork; the lower it is, the more likely the action is an exit. Run the simulator with a large number of actions (e.g., -a 100) and vary the fork percentage from 0.1 to 0.9. What do you think the resulting final process trees will look like as the percentage changes? Check your answer with -c.


### Question 3:
Now, switch the output by using the -t flag (e.g., run ./fork.py -t). Given a set of process trees, can you tell which actions were taken?
### Answer:
Start:
							a
a forks b:
                               a
                               └── b
a forks c:
                               a
                               ├── b
                               └── c
a forks d:
                               a
                               ├── b
                               ├── c
                               └── d
a forks e:
                               a
                               ├── b
                               ├── c
                               ├── d
                               └── e
e EXITS:
                               a
                               ├── b
                               ├── c
                               └── d

### Question 4:
One interesting thing to note is what happens when a child exits; what happens to its children in the process tree? To study this, let’s create a specific example: ./fork.py -A a+b,b+c,c+d,c+e,c-. This example has process ’a’ create ’b’, which in turn creates ’c’, which then creates ’d’ and ’e’. However, then, ’c’ exits. What do you think the process tree should like after the exit? What if you use the -R flag? Learn more about what happens to orphaned processes on your own to add more context.
### Answer:
when c exits, the remaining processes become the children of the first process which is a. However, when using the -R flag, the processes become the children of the parent of that parent that exited, which in this example was c.

### Question 5

### Answer:
Action: a forks b
Action: a forks c
Action: c forks d
Action: b EXITS
Action: c forks e

Result:
							   a
                               └── c
	                           -----└── d
	                           -----└── e



## <font color="#71e9ac">Homework (Code)</font>
In this homework, you are to gain some familiarity with the process management APIs about which you just read. 

### Question 1:
Write a program that calls fork(). Before calling fork(), have the main process access a variable (e.g., x) and set its value to something (e.g., 100). What value is the variable in the child process? What happens to the variable when both the child and parent change the value of x?

### Answer:
```c title="q1.c" showLineNumbers
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
```
#### Output:
```console {3,6} showLineNumbers=false
parent pid: 23449
before Changing x: 100
after Changing x: 25
child pid: 23450
before Changing x: 100
after changing x: 50
```

Each process has their own `x` variable and when changing it, it doesn't affect the other variable.

### Question 2:
Write a program that opens a file (with the open() system call) and then calls fork() to create a new process. Can both the child and parent access the file descriptor returned by open()? What happens when they are writing to the file concurrently, i.e., at the same time?

### Answer:
```c title="q2.c"
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
```

#### Output:
```console title="test.txt"
Parent was here.
Child was here.
```

- Both parent and child processes can access the same file descriptor after `fork()`.
- When they write to the file concurrently, the writes can interleave if both processes write at the same time.
- File writes are not automatically synchronized between processes, so without explicit synchronization mechanisms, data from one process can overwrite or mix with data from the other process.


### Question 3:
Write another program using fork(). The child process should print “hello”; the parent process should print “goodbye”. You should try to ensure that the child process always prints first; can you do this without calling wait() in the parent?

### Answer:
```c title="q3.c" {14}
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    int rc = fork();
    if (rc < 0) {
        fprintf(stderr, "fork failed\n");
        exit(1);
    } else if (rc == 0) { // child
        printf("hello\n");
    } else { // parent
        sleep(1);
        printf("goodbye\n");
    }

    return 0;
}
```

#### Output:
```frame="terminal"
hello
goodbye
```

The parent process doesn't explicitly wait for the child to complete using `wait()`, but instead uses a small delay `sleep(1)` to let the child process print first. 

### Question 4:
Write a program that calls fork() and then calls some form of exec() to run the program /bin/ls. See if you can try all of the variants of exec(), including (on Linux) execl(), execle(), execlp(), execv(), execvp(), and execvpe(). Why do you think there are so many variants of the same basic call?

### Answer:
> [!summary]-
> ![[Exec Family]]

### Question 5:
Now write a program that uses wait() to wait for the child process to finish in the parent. What does wait() return? What happens if you use wait() in the child?

### Answer:
```c title="q5.c"
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
```
**`wait()`** returns the process ID (PID) of the child process that finished. If there's an error, it returns `-1` and sets `errno` accordingly.

#### Output:
```console
Parent hello
wait failed
```

If you call `wait()` in the **child process**, it will fail because the child has no child processes to wait for. In this case, `wait()` will return `-1`.


### Question 6:
Write a slight modification of the previous program, this time using waitpid() instead of wait(). When would waitpid() be useful?
### Answer:
**`waitpid()`**: This function allows more control over which child process to wait for. Unlike `wait()`, which waits for _any_ child process, `waitpid()` lets you specify a particular process to wait for, using the child's process ID (`pid`).