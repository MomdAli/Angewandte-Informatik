---
title: Homework Chapter 8
tags:
  - Betriebssysteme
  - Homework
  - Semester-3
  - Informatik
date: 2024-10-13
aliases: 
cssclasses: 
---
This program, `mlfq.py`, allows you to see how the [[Multi-Level Feedback Queue (MLFQ)|MLFQ]] scheduler presented in this chapter behaves. See the README for details.
## Question 1:
Run a few randomly-generated problems with just two jobs and two queues; compute the MLFQ execution trace for each. Make your life easier by limiting the length of each job and turning off I/Os.
### Answer

| Seed | Job 0 Details                 | Job 1 Details          | Job 0 Stats           | Job 1 Stats            | Avg Response | Avg Turnaround |
| ---- | ----------------------------- | ---------------------- | --------------------- | ---------------------- | ------------ | -------------- |
| 1    | start: 0 <br>run: 4 <br>io: 0 | start: 0 run: 23 io: 0 | start: 0 res: 0 time: 4  | start: 0 res: 4 time: 27  | 2.00         | 15.50          |
| 2    | start: 0 run: 28 io: 0        | start: 0 run: 2 io: 0  | start: 0 res: 0 time: 30 | start: 0 res: 10 time: 12 | 5.00         | 21.00          |
| 3    | start: 0 run: 7 io: 0         | start: 0 run: 11 io: 0 | start: 0 res: 0 time: 7  | start: 0 res: 7 time: 18  | 3.50         | 12.50          |
| 4    | start: 0 run: 7 io: 0         | start: 0 run: 12 io: 0 | start: 0 res: 0 time: 7  | start: 0 res: 7 time: 19  | 3.50         | 13.00          |
| 5    | start: 0 run: 19 io: 0        | start: 0 run: 24 io: 0 | start: 0 res: 0 time: 29 | start: 0 res: 10 time: 43 | 5.00         | 36.00          |
| 6    | start: 0 run: 24 io: 0        | start: 0 run: 15 io: 0 | start: 0 res: 0 time: 39 | start: 0 res: 10 time: 35 | 5.00         | 37.00          |
| 7    | start: 0 run: 10 io: 0        | start: 0 run: 19 io: 0 | start: 0 res: 0 time: 10 | start: 0 res: 10 time: 29 | 5.00         | 19.50          |
| 8    | start: 0 run: 7 io: 0         | start: 0 run: 4 io: 0  | start: 0 res: 0 time: 7  | start: 0 res: 7 time: 11  | 3.50         | 9.00           |
| 9    | start: 0 run: 14 io: 0        | start: 0 run: 5 io: 0  | start: 0 res: 0 time: 19 | start: 0 res: 10 time: 15 | 5.00         | 17.00          |
| 10   | start: 0 run: 17 io: 0        | start: 0 run: 17 io: 0 | start: 0 res: 0 time: 27 | start: 0 res: 10 time: 34 | 5.00         | 30.50          |

## Question 2:
How would you run the scheduler to reproduce each of the examples in the chapter?

### Answer:
```sh
./mlfq.py -l 0,200,0 -q 10 -n 3 -c

./mlfq.py -l 0,200,0:100,20,0 -q 10 -n 3 -c

./mlfq.py -l 0,40,2:0,200,0 -q 10 -n 3 -i 5 -c

./mlfq.py -l 0,150,0:100,50,5:100,50,5 -q 10 -n 3 -i 5 -S -c

./mlfq.py -l 0,150,0:100,50,5:100,50,5 -q 10 -n 3 -i 5 -S -B 50 -c

./mlfq.py -l 0,200,0:30,200,9 -q 10 -n 3 -i 1 -S -c

./mlfq.py -l 0,200,0:30,200,9 -q 10 -n 3 -i 1 -c

./mlfq.py -l 0,200,0:0,200,0 -Q 10,20,40 -n 3 -c
```

## Question 3:
How would you configure the scheduler parameters to behave just like a round-robin scheduler?

### Answer:
by setting the -n flag to 1, so that the scheduler would have only 1 Queue.

## Question 4:
Craft a workload with two jobs and scheduler parameters so that one job takes advantage of the older Rules 4a and 4b (turned on with the -S flag) to game the scheduler and obtain 99% of the CPU over a particular time interval.

### Answer:
```sh
./mlfq.py -l 0,1000,0:300,1000,99 -q 100 -n 3 -i 1 -S -c
```

## Question 5:
Given a system with a quantum length of 10 ms in its highest queue, how often would you have to boost jobs back to the highest priority level (with the -B flag) in order to guarantee that a single long running (and potentially-starving) job gets at least 5% of the CPU?

### Answer:
```sh
./mlfq.py -l 0,200,0:0,100,5:0,100,5 -q 10 -n 3 -i 5 -B 100 -c
```

## Question 6:
One question that arises in scheduling is which end of a queue to add a job that just finished I/O; the -I flag changes this behavior for this scheduling simulator. Play around with some workloads and see if you can see the effect of this flag.

### Answer:
```sh
./mlfq.py -l 0,50,5:0,50,5:0,50,5 -q 10 -n 3 -i 5 -S -c
./mlfq.py -l 0,50,5:0,50,5:0,50,5 -q 10 -n 3 -i 5 -S -I -c
```
If -I is not added, the three tasks are executed alternately. If -I is added, the first and second tasks are executed alternately, and the third task has no chance to be executed.

