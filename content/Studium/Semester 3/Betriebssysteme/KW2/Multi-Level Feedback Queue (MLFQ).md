---
title: MLFQ
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-10-13
aliases:
  - Multi-Level Feedback Queue
cssclasses: 
---
## Overview

The Multi-Level Feedback Queue (MLFQ) is a sophisticated CPU scheduling algorithm designed to dynamically prioritize jobs based on their observed behavior. The MLFQ scheduler was initially introduced in the Compatible Time-Sharing System (CTSS) and has been refined in various operating systems to balance between optimizing **response time** for interactive jobs and **turnaround time** for CPU-bound jobs.

### Challenges Addressed by MLFQ

1. **Unknown Job Length**: Unlike Shortest Job First (SJF), MLFQ doesn't rely on knowing how long a job will run.
2. **Responsiveness**: It aims to prioritize interactive jobs (e.g., user-facing applications) to minimize response time.
3. **Fairness**: It ensures long-running jobs (e.g., batch processes) eventually make progress and don’t starve.

## MLFQ: Basic Rules

MLFQ uses multiple queues, each with a different priority level. Higher priority jobs are scheduled first, and jobs of the same priority use **Round-Robin (RR)** scheduling.

**Key MLFQ Rules**:

1. **Rule 1**: If `Priority(A) > Priority(B)`, A runs.
2. **Rule 2**: If `Priority(A) = Priority(B)`, A and B run in RR fashion.
3. **Rule 3**: When a job enters the system, it is placed in the highest priority queue.
4. **Rule 4**: A job's priority is reduced if it uses up its allotted time in its current queue.
5. **Rule 5**: After a specific time period (S), all jobs are boosted back to the topmost queue to prevent starvation.

## How MLFQ Works

### Priority Changes

MLFQ dynamically adjusts a job's priority based on its behavior:

- **Interactive jobs** (frequent I/O operations): These jobs relinquish the CPU before using up their allotment and remain at a high priority.
- **CPU-bound jobs**: These jobs use their entire allotment and are demoted to lower-priority queues over time.

MLFQ thus approximates SJF by giving high priority to jobs that seem interactive and demoting long-running jobs to prevent them from monopolizing CPU time.

### Example Scenarios

1. **Long-Running Job**: A long-running job gradually moves down the queue hierarchy as it uses up its time allotment in higher-priority queues.
2. **Interactive Job**: An interactive job stays in the higher-priority queue due to frequent I/O operations, allowing it to maintain responsiveness for user interaction.

## Problems and Refinements

### Starvation

Without intervention, too many high-priority interactive jobs could monopolize CPU time, causing long-running jobs to starve. MLFQ solves this by **boosting all jobs** to the highest-priority queue after a set period (Rule 5), ensuring progress for all jobs.

### Gaming the System

A smart user might "game" the system by relinquishing the CPU just before their allotment expires, keeping their job in a high-priority queue indefinitely. To prevent this, MLFQ tracks CPU usage even across multiple relinquishments and demotes jobs once their total time allotment is exhausted.

### Priority Boosting

Periodic priority boosts ensure that jobs that have been waiting for a long time, especially CPU-bound jobs, get a chance to run, thus preventing starvation. However, determining the right period (S) for boosting jobs is a challenge that depends on the system's workload.

### I/O Considerations

Jobs that frequently perform I/O should not be penalized by lowering their priority, as they are often waiting on input rather than consuming CPU. MLFQ handles this by keeping I/O-bound jobs at their current priority when they relinquish the CPU for I/O operations.

## Parameter Tuning

Tuning MLFQ involves configuring various parameters:

1. **Number of Queues**: More queues allow finer granularity in distinguishing between jobs with different CPU needs.
2. **Time Slices**: Higher-priority queues should have shorter time slices to quickly alternate between interactive jobs. Lower-priority queues may have longer time slices to favor CPU-bound jobs.
3. **Boost Period (S)**: This value determines how frequently jobs are boosted to the highest-priority queue to avoid starvation. Setting it too high may cause starvation, while setting it too low may result in too much time being spent on priority adjustments.

## Tuning Example

- High-priority queues might have time slices of 10 milliseconds, ideal for quick interactive jobs.
- Lower-priority queues might have time slices of 100 milliseconds or more to accommodate long-running CPU-bound jobs.

## Security Concerns

MLFQ must be secure from attacks where users might exploit the scheduling rules to monopolize CPU resources. For instance, datacenters running shared systems must ensure fair CPU allocation among users.

---

## MLFQ: Summary

The Multi-Level Feedback Queue (MLFQ) scheduling algorithm uses feedback from job behavior to dynamically adjust priorities, balancing between:

- **High responsiveness** for short, interactive jobs.
- **Fair progress** for long, CPU-bound jobs.

By learning from a job’s past behavior, MLFQ ensures both efficient CPU utilization and responsiveness, making it suitable for systems requiring multitasking with mixed workloads.

**Key Rules Recap**:

- Rule 1: Prioritize higher-priority jobs.
- Rule 2: Use RR for jobs with equal priority.
- Rule 3: All new jobs start at the highest priority.
- Rule 4: Demote jobs that use their entire time allotment.
- Rule 5: Periodically boost all jobs to the highest priority to prevent starvation.

MLFQ is widely used in modern operating systems like BSD Unix, Solaris, and Windows due to its flexibility and effectiveness in handling diverse workloads.