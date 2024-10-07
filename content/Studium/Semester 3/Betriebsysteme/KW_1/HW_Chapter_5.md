---
title: Homework Chapter 5
tags:
  - Betriebsysteme
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
```c Title="q1.c" showLineNumbers
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