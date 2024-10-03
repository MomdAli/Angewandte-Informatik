---
title: Process API
tags:
  - Betriebsysteme
date: 
aliases:
  - Prozess_API
  - ProzessAPI
  - ProcessAPI
cssclasses:
---

### The **Process API**:
interlude introduces the fundamental system calls for process creation and management in UNIX systems: `fork()`, `exec()`, and `wait()`. These system calls enable process creation and control, allowing for effective multitasking and process coordination within an operating system.

- **`fork()`**: This system call is used to create a new process, known as the child process, which is almost an identical copy of the calling (parent) process. Both parent and child processes resume execution from the point where `fork()` was called, but they can be differentiated because `fork()` returns a different value in each process. The parent gets the child’s process ID, while the child receives a return value of zero.

- **`wait()`**: The parent process can use the `wait()` system call to pause its execution until the child process finishes. This call ensures that the parent does not proceed before its child has completed its task, allowing for deterministic process synchronization.

- **`exec()`**: The `exec()` family of system calls replaces the current process with a new program. After `fork()`, the child can call `exec()` to run a completely different program. This transformation means the original program is no longer running in the child, and the newly loaded program takes over. This mechanism is essential for process execution in programs like shells, where a child process starts a new executable.

The separation of `fork()` and `exec()` is crucial for UNIX shells, which need to fork a new process, prepare its environment, and then execute a different program within it. This design enables features like input/output redirection and process piping, making it possible to chain commands and manage resources efficiently. UNIX also uses signals and a user-based control system to manage processes securely, allowing only specific users to manipulate certain processes, with the superuser (root) having full control.

These APIs form the backbone of process management in UNIX-like systems, offering flexibility, control, and efficiency in how programs run and interact with system resources.

> [!abstract] KEY PROCESS API TERMS 
> 
> • Each process has a name; in most systems, that name is a number known as a process ID (PID). 
> 
> • The fork() system call is used in UNIX systems to create a new process. The creator is called the parent; the newly created process is called the child. As sometimes occurs in real life, the child process is a nearly identical copy of the parent. 
> 
> • The wait() system call allows a parent to wait for its child to complete execution. • The exec() family of system calls allows a child to break free from its similarity to its parent and execute an entirely new program. 
> 
> • A UNIX shell commonly uses fork(), wait(), and exec() to launch user commands; the separation of fork and exec enables features like input/output redirection, pipes, and other cool features, all without changing anything about the programs being run. 
> 
> • Process control is available in the form of signals, which can cause jobs to stop, continue, or even terminate. 
> 
> • Which processes can be controlled by a particular person is encapsulated in the notion of a user; the operating system allows multiple users onto the system, and ensures users can only control their own processes. 
> 
> • A superuser can control all processes (and indeed do many other things); this role should be assumed infrequently and with caution for security reasons.


