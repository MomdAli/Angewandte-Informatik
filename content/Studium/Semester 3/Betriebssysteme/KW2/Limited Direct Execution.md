---
title: Limited Direct Execution
tags:
  - Betriebssysteme
  - C
  - Assembly
date: 2024-10-12
aliases: 
cssclasses:
---
## How to efficiently virtualize the CPU with control?
### What is Direct Execution?
Direct Execution means to run a program directly on the CPU. For an OS to not behave as a standard library, it should have *restrictions* on `system calls`.
`System calls` are just normal procedural calls written carefully in Assembly, as they need to carefully follow convention in order to proceed arguments and return values correctly. So what if the process wishes to perform some kind of restricted operation, such as issuing an I/O request to a disk, or gaining access to more system resources such as CPU or memory?

This is where the `Limited` part of Limited Direct Execution comes in. For a more secure system call we are going to implement two new features in the OS:
- User and Kernel Mode
- Using trap table


![[Kernel and User mode.png|center]]

Code that runs in user mode is restricted in what it can do. For example, when running in user mode, a process can’t issue I/O requests; doing so would result in the processor raising an exception; the OS would then likely kill the process. In contrast to user mode is kernel mode, which the operating system (or kernel) runs in. In this mode, code that runs can do what it likes, including privileged operations such as issuing I/O requests and executing all types of restricted instructions.

### Using Protected Control Transfer
To execute a system call, a program must execute a special **trap** instruction. This instruction simultaneously jumps into the kernel and raises the privilege level to kernel mode; once in the kernel, the system can now perform whatever privileged operations are needed (if allowed), and thus do the required work for the calling process. When finished, the OS calls a special **return-from-trap** instruction, which, as you might expect, returns into the calling user program while simultaneously reducing the privilege level back to user mode.

## How to switch between Processes? How to regain control of the CPU?
### Cooperative approach:
give a process control of the CPU and hope it doesn't end in an infinite loop. The process also lets the OS do the System calls.
### How to gain control without cooperation?
A **timer interrupt**. A timer device can be programmed to raise an **interrupt** every so many milliseconds; when the interrupt is raised, the currently running process is halted, and a pre-configured interrupt handler in the OS runs. At this point, the OS has regained control of the CPU, and thus can do what it pleases: stop the current process, and start a different one.
### Dealing with application misbehaviour:
In modern systems, the way the OS tries to handle such malfeasance is to simple terminate the offender. "One strike and you're out!"

### Context-Switching:
Process A is running and then is interrupted by the timer interrupt. The hardware saves its registers (onto its kernel stack) and enters the *Kernel Mode*. In the timer interrupt handler, the Os decides to switch from running process A to process B. At that point, it calls the switch() routine, which carefully saves current register values (into the process structure of A), restores the registers of process B, and then switches contexts, specifically by changing the **stack pointer** to use B's registers and starts running it.

### Baby proofing:
First (during boot time) setting up the trap handler and and storing an interrupt timer, and then by only running processes in a restricted mode. By doing so, the OS can feel quite assured that processes can run efficiently, only requiring OS intervention to perform privileged operations or when they have monopolized the CPU for too long and thus need to be switched out.
