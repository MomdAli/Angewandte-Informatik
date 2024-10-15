---
title: Process
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-10-01
aliases:
  - Prozesse
  - process
  - Prozess
cssclasses: 
---

> [!attention]
> You need to have read the capital 1-3 from the book in order to understand the following text

### Definition: 
A process is **an instance of a program running in a computer**. It is close in meaning to task , a term used in some operating systems. In UNIX and some other operating systems, a process is started when a program is initiated (either by a user entering a shell command or by another program).

### how does operating system provide the illusion of many CPUs?
The OS provides this abstraction by **virtualizing the CPU**. By running one process, then stopping it and running another, and so forth, the OS can promote the illusion that many virtual CPUs exist when in fact there is only one physical CPU (or a few). This basic technique is known as **time sharing** of the CPU.

> [!tip] 
 > **USE TIME SHARING (AND SPACE SHARING)**
> Time sharing is a basic technique used by an OS to share a resource. By allowing the resource to be used for a little while by one entity, and then a little while by another, and so forth, the resource in question (e.g., the CPU, or a network link) can be shared by many. The counterpart of time sharing is space sharing, where a resource is divided (in space) among those who wish to use it. For example, disk space is naturally a spaceshared resource; once a block is assigned to a file, it is normally not assigned to another file until the user deletes the original file.

### Process API:
Every operating system need to able to **create** a process, **destroy** a process if the user wishes it, **wait** for a process to stop running, **pause** a process and get **status** information of a process.

### The process of transforming a program into a running process involves several steps:
1. **Loading Code and Data into Memory**: The OS first loads the program’s code and static data (e.g., initialized variables) from disk into the process’s memory (*address space*). In modern systems, this loading can be done lazily, meaning only needed parts are loaded during execution.
2. **Allocating Memory for Stack and Heap**: The OS allocates memory for the program’s stack (used for local variables, function parameters, and return addresses) and initializes the stack with arguments (e.g., argc and argv). Additionally, it may allocate space for the heap, which is used for dynamically allocated data requested by the program during runtime.
3. **I/O Setup**: In systems like UNIX, the OS sets up standard input, output, and error file descriptors for the process, enabling easy interaction with the terminal.
4. **Starting Execution**: Finally, the OS starts the program by transferring control of the CPU to the process, beginning execution at the entry point (typically the `main()` function).

### **Process States**: the text outlines the three main states a process can be in during its lifecycle:
1. **Running**: The process is actively executing instructions on the CPU.
2. **Ready**: The process is prepared to run but is waiting for CPU allocation by the operating system.
3. **Blocked**: The process is waiting for some event to occur (such as an I/O operation to complete) before it can continue executing.

![[Figure 4.2.png]]

The operating system moves processes between these states depending on the CPU's availability and external events like I/O completion. The transitions between these states are managed by the OS scheduler, which decides which process should be running at any given time to maximize system efficiency.

### **PCB** (Process Control Block):
- ****Pointer:**** It is a stack pointer that is required to be saved when the process is switched from one state to another to retain the current position of the process.
- ****Process state:**** It stores the respective state of the process.
- ****Process number:**** Every process is assigned a unique id known as process ID or PID which stores the process identifier.
- ****Program counter:**** [Program Counter](https://www.geeksforgeeks.org/what-is-program-counter/) stores the counter, which contains the address of the next instruction that is to be executed for the process.
- ****Register:**** [Registers](https://www.geeksforgeeks.org/different-classes-of-cpu-registers/) in the PCB, it is a data structure. When a processes is running and it’s time slice expires, the current value of process specific registers would be stored in the PCB and the process would be swapped out. When the process is scheduled to be run, the register values is read from the PCB and written to the CPU registers. This is the main purpose of the registers in the PCB.
- ****Memory limits:**** This field contains the information about [memory management system](https://www.geeksforgeeks.org/memory-management-in-operating-system/) used by the operating system. This may include page tables, segment tables, etc.
- ****List of Open files:**** This information includes the list of files opened for a process.

### **Data Structures**: discusses how operating systems manage processes using specific data structures. The key points are:

1. **Tracking Process Information**: The OS needs to track various details about each process, such as its current state, memory usage, CPU registers, and I/O activities. This is done using a data structure called the **Process Control Block (PCB)** or **process descriptor**, which stores all relevant information about each process.
2. **Process List**: The OS maintains a **process list**, which contains all the PCBs for active processes. This list helps the OS manage which processes are running, ready to run, or blocked. For example, if a process is waiting for I/O, it will be marked as "blocked" in this list, and the OS will know when to wake it up once the I/O operation is complete.
3. **Context Switching**: When a process is stopped (e.g., due to a context switch), its register contents are saved into the PCB. Later, when the process is resumed, the OS restores these saved values to the CPU’s registers, allowing the process to continue from where it left off.

The section also provides an example of a process structure in the xv6 kernel, showing fields like process memory, state, open files, and register context.


> [!summary] KEY PROCESS TERMS
> • The **process** is the major OS abstraction of a running program. At any point in time, the process can be described by its state: the contents of memory in its **address space**, the contents of CPU registers (including the **program counter** and **stack pointer**, among others), and information about I/O (such as open files which can be read or written). 
> 
> • The **process API** consists of calls programs can make related to processes. Typically, this includes creation, destruction, and other useful calls. 
> 
> • Processes exist in one of many different **process states**, including running, ready to run, and blocked. Different events (e.g., getting scheduled or descheduled, or waiting for an I/O to complete) transition a process from one of these states to the other. 
> 
> • A **process list** contains information about all processes in the system. Each entry is found in what is sometimes called a **process control block (PCB)**, which is really just a structure that contains information about a specific process.

##### Next chapter: [[Process_API|Process API]]