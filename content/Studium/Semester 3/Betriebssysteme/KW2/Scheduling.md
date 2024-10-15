---
title: Scheduling
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-10-13
aliases: 
cssclasses: 
---
## Overview

This documentation provides an introduction to CPU scheduling, a critical function of an operating system (OS). CPU scheduling determines the order in which processes are executed on the CPU, aiming to optimize various performance metrics such as **turnaround time**, **response time**, and **fairness**.

## Workload Assumptions

To simplify initial scheduling policies, the following assumptions are made:

- All jobs (processes) arrive at the same time.
- Each job runs for the same duration.
- Jobs do not perform I/O operations.
- Jobs run to completion once started.
- The run-time of each job is known.

These assumptions are unrealistic but useful for building and understanding basic scheduling algorithms. As we progress, these assumptions are relaxed to address real-world complexities.

## Scheduling Metrics

Scheduling metrics are used to evaluate the performance of different algorithms. Key metrics include:

- **Turnaround Time**: Time from job arrival to completion.
    
    Formula:  
    `Turnaround Time = Completion Time - Arrival Time`
    
- **Response Time**: Time from job arrival to its first execution.
    
    Formula:  
    `Response Time = First Execution Time - Arrival Time`
    
- **Fairness**: Ensuring jobs receive equal access to the CPU.
    

## Scheduling Algorithms

### 1. First-In, First-Out (FIFO)

- **Description**: The simplest scheduling policy where the first job to arrive is the first to run.
- **Pros**: Easy to implement.
- **Cons**: Leads to **convoy effect**, where short jobs wait behind long jobs, causing high average turnaround time for shorter jobs.

### 2. Shortest Job First (SJF)

- **Description**: Jobs are ordered by their run-time, with the shortest jobs executed first.
- **Pros**: Optimizes turnaround time.
- **Cons**: Non-preemptive, meaning long jobs that arrive earlier can still block shorter jobs arriving later.

### 3. Shortest Time-to-Completion First (STCF)

- **Description**: A preemptive version of SJF where shorter jobs can interrupt longer jobs. Also known as **Preemptive Shortest Job First (PSJF)**.
- **Pros**: Reduces turnaround time significantly by allowing short jobs to run before longer ones are completed.
- **Cons**: Does not prioritize response time.

### 4. Round Robin (RR)

- **Description**: Jobs are scheduled in time slices or **quanta**, cycling through all available jobs. Each job is given a short period to run before switching to the next job in the queue.
- **Pros**: Excellent for improving response time.
- **Cons**: Increases average turnaround time, as jobs take longer to finish when sliced into small time periods. Performance depends heavily on the chosen time slice length.

## Trade-offs in Scheduling

There is an inherent trade-off between scheduling algorithms that optimize different metrics:

- **SJF/STCF**: Best for minimizing turnaround time but poor for response time.
- **Round Robin (RR)**: Optimizes response time but leads to high turnaround time.
- **Fairness vs. Performance**: A balance between fairness (ensuring all jobs get CPU time) and performance (minimizing the time it takes for jobs to complete) must be struck.

## Handling I/O and Real-World Scenarios

In real-world systems, jobs perform I/O operations. The scheduler must ensure that the CPU is not left idle while waiting for I/O to complete by scheduling other jobs during I/O operations. This concept, called **overlap**, maximizes CPU utilization.

### Example of Scheduling with I/O

- Job A performs I/O after short CPU bursts, while Job B is CPU-bound. The scheduler must run B while A waits for I/O, improving overall efficiency.

## Limitations and Future Scheduling Strategies

A fundamental limitation of most schedulers is that they require knowledge of job lengths, which is typically unavailable in real-world systems. To address this, future algorithms like [[Multi-Level Feedback Queue (MLFQ)]] will predict future job behavior based on historical execution patterns, providing a more dynamic and efficient scheduling mechanism.


##### Next chapter: [[Multi-Level Feedback Queue (MLFQ)]]