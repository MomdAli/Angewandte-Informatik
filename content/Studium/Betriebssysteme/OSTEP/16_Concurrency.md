---
title: 16. Concurrency
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-12-03
publish: true
---
> [!info]
>  This is a summary of the 27th chapter of the book "Operating System: Three Easy Pieces" by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. The chapter introduces the concept of concurrency, focusing on threads, synchronization issues, and their significance in operating systems.

---

### **Overview**

Concurrency is about managing multiple tasks simultaneously. This chapter introduces threads, explains their differences from processes, and discusses why threads are crucial for modern computing. It also delves into synchronization issues like race conditions and critical sections, which arise in concurrent programming.

---

### **Key Concepts**

- **Threads vs. Processes**:
    - A **thread** is an abstraction for a sequence of instructions, similar to a process but sharing the same address space with other threads in the process.
    - Threads have their own **program counter (PC)** and **registers** but share memory, leading to lightweight context switching compared to processes.
- **Thread States**:
    - Threads maintain individual **stacks** for function calls and local variables.
    - In multi-threaded programs, multiple stacks coexist in the address space, making stack memory allocation thread-specific.

---

### **Why Use Threads?**

1. **Parallelism**:
    
    - Threads enable tasks to run in parallel on multi-core processors, improving performance.
    - Example: Processing large arrays by dividing tasks among threads.
2. **Non-blocking Progress**:
    
    - Threads allow a program to continue computation while waiting for slow I/O operations, utilizing resources efficiently.

---

### **Synchronization Challenges**

- **Race Conditions**:
    
    - When multiple threads access shared resources without proper synchronization, the program’s behavior becomes unpredictable.
    - Example: Two threads updating a global variable may result in incorrect final values due to interleaved instructions.
- **Critical Sections**:
    
    - A block of code accessing shared resources.
    - To avoid issues, only one thread should execute the critical section at a time (**mutual exclusion**).
- **Indeterminate Results**:
    
    - Without proper synchronization, the output of a program varies across executions, violating deterministic behavior.

---

### **Key Terms**

- **Mutual Exclusion**: Ensures only one thread executes a critical section at a time.
- **Atomicity**: An operation is performed as an indivisible unit.
- **Context Switch**: Switching the CPU's focus from one thread to another, saving/restoring thread-specific states.

---

### **Example: Thread Creation**

A simple C program demonstrates thread creation using the `pthread` library:

- Two threads print "A" and "B" concurrently.
- Execution order depends on the scheduler, leading to various possible outcomes.

---

### **The Problem of Shared Data**

- Uncontrolled access to shared variables introduces race conditions.
- Example: Two threads incrementing a counter may not achieve the expected result due to interleaved operations.

---

### **Solutions and Tools**

- **Synchronization Primitives**:
    
    - Locks, mutexes, and semaphores help ensure proper thread coordination.
    - Example: Wrapping critical sections with mutexes to enforce mutual exclusion.
- **Atomic Operations**:
    
    - Hardware-supported operations that guarantee atomic updates to shared variables.

---

### **Relevance to Operating Systems**

Operating systems are inherently concurrent, managing multiple processes and threads. The synchronization principles discussed are foundational to building robust OS components, from handling interrupts to managing kernel data structures.

##### Next Chapter: [[17_Thread API|Thread API]]