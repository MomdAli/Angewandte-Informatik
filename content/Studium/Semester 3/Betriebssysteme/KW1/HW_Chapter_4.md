---
title: Homework Chapter 4
tags:
  - Homework
  - Betriebssysteme
  - Python
  - Semester-3
  - Informatik
date: 2024-10-03
aliases:
  - Homework_Chapter_4
  - HW4
cssclasses:
  - purpleRed
  - wideTable
---
## Question 1: 
Run process-run.py with the following flags: -l 5:100,5:100. What should the CPU utilization be (e.g., the percent of time the CPU is in use?) Why do you know this? Use the -c and -p flags to see if you were right.
### Answer:
The runtime should be 100% as the CPU doesn't waste time on waiting for other process and instead it instantly starts the next one.


## Question 2:
Now run with these flags: ./process-run.py -l 4:100,1:0. These flags specify one process with 4 instructions (all to use the CPU), and one that simply issues an I/O and waits for it to be done. How long does it take to complete both processes? Use -c and -p to find out if you were right.
### Answer:
It takes a total of 11 time. first it ran process 0 4 times at the beginning and then when it was done, it called I/O once then the process was blocked for a total of 5 unit times, and then it ran I/O_end. So calling I/O and ending it took a total of 2 unit times.


## Question 3:
Switch the order of the processes: -l 1:0,4:100. What happens now? Does switching the order matter? Why? (As always, use -c and -p to see if you were right)
### Answer:
So now that the process calls I/O, when it is in the block state it runs the second process while the first process is in the blocked state saving more times running. It took a total of 7 unit times.


## Question 4:
We’ll now explore some of the other flags. One important flag is -S, which determines how the system reacts when a process issues an I/O. With the flag set to SWITCH ON END, the system will NOT switch to another process while one is doing I/O, instead waiting until the process is completely finished. What happens when you run the following two processes (-l 1:0,4:100 -c -S SWITCH ON END), one doing I/O and the other doing CPU work?
### Answer:
Both should have the same total time because the CPU runs the processes asynchronously.


## Question 5:
Now, run the same processes, but with the switching behavior set to switch to another process whenever one is WAITING for I/O (-l 1:0,4:100 -c -S SWITCH ON IO). What happens now? Use -c and -p to confirm that you are right.
### Answer:
It behaves as it use to be before changing the flag. My assumption is that it is the default flag.


## Question 6:
One other important behavior is what to do when an I/O completes. With -I IO RUN LATER, when an I/O completes, the process that issued it is not necessarily run right away; rather, whatever was running at the time keeps running. What happens when you run this combination of processes? (./process-run.py -l 3:0,5:100,5:100,5:100 -S SWITCH ON IO -c -p -I IO RUN LATER) Are system resources being effectively utilized?
### Answer:
No, because the first I/O finishes and waits for all other processes to finish until it starts the next I/O. 


## Question 7:
Now run the same processes, but with -I IO RUN IMMEDIATE set, which immediately runs the process that issued the I/O. How does this behaviour differ? Why might running a process that just completed an I/O again be a good idea?
### Answer:
because running I/O and normal CPU processes simultaneously is the best way to use system resources efficiently.

> [!faq]
> **Two I/O-related Flags:**
> 
>- **`-I IO RUN IMMEDIATE`**: When an I/O completes, the process that initiated the I/O is immediately scheduled to run. The CPU switches back to the process that just finished the I/O.
>
>- **`-I IO RUN LATER`**: When an I/O completes, the process that initiated the I/O does not run immediately. Instead, the CPU continues running the current process, and the I/O-completing process waits in the ready queue until the OS schedules it later.
>
> **Two Scheduling-related Flags:**
>
> - **`-S SWITCH ON IO`**: Whenever a process performs an I/O operation, the OS switches to another process immediately (without waiting for the I/O to complete). This keeps the CPU busy by not idling during the I/O operation.
>
>- **`-S SWITCH ON END`**: The OS doesn’t switch to another process while one is doing I/O; it waits until the process finishes its I/O before continuing with the same process. This could lead to idle CPU time if the process is waiting for I/O.

## Question 8:
Now run with some randomly generated processes using flags -s 1 -l 3:50,3:50 or -s 2 -l 3:50,3:50 or -s 3 -l 3:50, 3:50. See if you can predict how the trace will turn out. What happens when you use the flag -I IO RUN IMMEDIATE versus that flag -I IO RUN LATER? What happens when you use the flag -S SWITCH ON IO versus -S SWITCH ON END?
### Answer:
- **`-I IO RUN IMMEDIATE`**:
 This ensures faster response for I/O-bound processes because they resume execution immediately after their I/O finishes. However, it may interrupt CPU-bound processes more frequently.
- **`-I IO RUN LATER`**:
This allows the current CPU-bound process to continue running uninterrupted, potentially improving throughput for CPU-bound workloads but slowing down I/O-bound processes.
- **`-S SWITCH ON IO`**:
This leads to more efficient use of CPU resources because the CPU will continue running another process while waiting for I/O-bound processes to complete their I/O operations.
- **`-S SWITCH ON END`**:
This may lead to wasted CPU time when the process is blocked on I/O, reducing overall efficiency.


| Flag S        | Flag I        | Time (Seed 1) | Time (Seed 2) | Time (Seed 3) | Average Time |
| ------------- | ------------- | ------------- | ------------- |:-------------:|:------------:|
| SWITCH ON IO  | RUN IMMEDIATE | 15            | 16            |      17       |      16      |
| SWITCH ON IO  | RUN LATER     | 15            | 16            |      18       |     16.3     |
| SWITCH ON END | RUN IMMEDIATE | 18            | 30            |      24       |      24      |
| SWITCH ON END | RUN LATER     | 18            | 30            |      24       |      24      |
