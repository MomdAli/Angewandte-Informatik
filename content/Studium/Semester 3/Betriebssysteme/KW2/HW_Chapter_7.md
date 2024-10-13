---
title: Homework Chapter 7
tags:
  - Betriebssysteme
  - Homework
  - Semester-3
  - Python
date: 2024-10-13
aliases: 
cssclasses:
---
This program, scheduler.py, allows you to see how different schedulers perform under [[Scheduling]] metrics such as response time, turnaround time, and total wait time. See the README for details.
## Question 1:
Compute the response time and turnaround time when running three jobs of length 200 with the SJF and FIFO schedulers.
### Answer:
```sh
./scheduler.py -p SJF -j 3 -l 200,200,200
```
and 
```sh
./scheduler .py -p FIFO 3 -l 200,200,200
```

### Output:
Both <font color="#d99694">SJF</font> and <font color="#d99694">FIFO</font>:
- Job 0: 
	- w = 0
	- r = 0
	- t = 200
- Job 1:
	- w = 200
	- r = 200
	- t = 400
- Job 2: 
	- w = 400
	- r = 400
	- t = 600
	
$\overline w = 200$
$\overline r = 200$
$\overline t = 400$

## Question 2:
Now do the same but with jobs of different lengths: 100, 200, and 300.

### Answer:
```sh
./scheduler.py -p SJF/FIFO -j 3 -l 100,200,300
```

### Output:
Both <font color="#d99694">SJF</font> and <font color="#d99694">FIFO</font>:
- Job 0: 
	- w = 0
	- r = 0
	- t = 100
- Job 1:
	- w = 100
	- r = 100
	- t = 300
- Job 2: 
	- w = 300
	- r = 300
	- t = 600
	
$\overline w = 133$
$\overline r = 133$
$\overline t = 333$

## Question 3:
Now do the same, but also with the RR scheduler and a time-slice of 1.

### Answer:
```sh
./scheduler.py -p RR -j 3 -l 100,200,300 -q 1
```

### Output:
<font color="#d99694">Round Robin</font>:
- Job 0: 
	- w = 198
	- r = 0
	- t = 298
- Job 1:
	- w = 299
	- r = 1
	- t = 499
- Job 2: 
	- w = 300
	- r = 2
	- t = 600
	
$\overline w = 265$
$\overline r = 1$
$\overline t = 465$

## Question 4:
For what types of workloads does SJF deliver the same turnaround times as FIFO?

### Answer:
whenever the order of jobs is from shortest to longest or all jobs have the same length.

## Question 5:
For what types of workloads and quantum lengths does SJF deliver the same response times as RR?

### Answer:
SJF and RR can deliver the same response times when:

- All jobs arrive at the same time.
- The quantum in RR is large enough for each job to finish in its first slice, effectively behaving like a non-preemptive scheduler similar to SJF.

For example, if each job takes less than or equal to the length of the time quantum, both algorithms will have the same response times.

## Question 6:
What happens to response time with SJF as job lengths increase? Can you use the simulator to demonstrate the trend?

### Answer:
As job lengths increase in SJF, **response time increases** because longer jobs are placed later in the queue and must wait for shorter jobs to finish. In non-preemptive SJF, jobs arriving later or with longer execution times experience significant delays before they start running, leading to higher response times for longer jobs.

## Question 7:
What happens to response time with RR as quantum lengths increase? Can you write an equation that gives the worst-case response time, given N jobs?

### Answer:
As the quantum length increases in RR, **Response time increases**, especially for jobs that are far back in the queue. This is because jobs have to wait longer to get their turn, resulting in slower response times for all jobs.

The **worst-case response time** for a job in RR is when it is the last job in the queue, requiring multiple cycles to be scheduled:

Worst-case response time = `(N - 1) * quantum`