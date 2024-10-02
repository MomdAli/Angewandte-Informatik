---
title: Prozess
tags:
  - Betriebsysteme
  - Zusammenfassung
date: 2024-10-01
aliases: 
cssclasses:
---

> [!attention]
> You need to have read the capital 1-3 from the book in order to understand the following text

### Definition: 
A process is **an instance of a program running in a computer**. It is close in meaning to task , a term used in some operating systems. In UNIX and some other operating systems, a process is started when a program is initiated (either by a user entering a shell command or by another program).

### how does operating system provide the illusion of many cpus?
The OS provides this abstraction by **virtualizing the CPU**. By running one process, then stopping it and running another, and so forth, the OS can promote the illusion that many virtual CPUs exist when in fact there is only one physical CPU (or a few). This basic technique is known as **time sharing** of the CPU.

> [!tip] 
 > **USE TIME SHARING (AND SPACE SHARING)**
> Time sharing is a basic technique used by an OS to share a resource. By allowing the resource to be used for a little while by one entity, and then a little while by another, and so forth, the resource in question (e.g., the CPU, or a network link) can be shared by many. The counterpart of time sharing is space sharing, where a resource is divided (in space) among those who wish to use it. For example, disk space is naturally a spaceshared resource; once a block is assigned to a file, it is normally not assigned to another file until the user deletes the original file.

### Process API:
Every operating system need to able to **create** a process, **destroy** a process if the user wishes it, **wait** for a process to stop running, **pause** a process and get **status** of a process.

### The process of transforming a program into a running process involves several steps:
1. **Loading Code and Data into Memory**: The OS first loads the program’s code and static data (e.g., initialized variables) from disk into the process’s memory (address space). In modern systems, this loading can be done lazily, meaning only needed parts are loaded during execution.
2. **Allocating Memory for Stack and Heap**: The OS allocates memory for the program’s stack (used for local variables, function parameters, and return addresses) and initializes the stack with arguments (e.g., argc and argv). Additionally, it may allocate space for the heap, which is used for dynamically allocated data requested by the program during runtime.
3. **I/O Setup**: In systems like UNIX, the OS sets up standard input, output, and error file descriptors for the process, enabling easy interaction with the terminal.
4. **Starting Execution**: Finally, the OS starts the program by transferring control of the CPU to the process, beginning execution at the entry point (typically the `main()` function).


### **Process States**: the text outlines the three main states a process can be in during its lifecycle:
1. **Running**: The process is actively executing instructions on the CPU.
2. **Ready**: The process is prepared to run but is waiting for CPU allocation by the operating system.
3. **Blocked**: The process is waiting for some event to occur (such as an I/O operation to complete) before it can continue executing.

![[Figure 4.2.png]]

The operating system moves processes between these states depending on the CPU's availability and external events like I/O completion. The transitions between these states are managed by the OS scheduler, which decides which process should be running at any given time to maximize system efficiency.