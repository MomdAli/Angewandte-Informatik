---
title: Address Spaces
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-10-21
aliases: 
cssclasses:
---

## Early Systems

- Early systems had a single address space for the entire system.
- This was a security risk, as any process could access any memory location.
- This was also a performance issue, as the OS had to manage all memory.

## Multiprogramming and Time Sharing

- Multiprogramming and time sharing introduced the concept of multiple processes.
- Each process had its own address space. 
- This was a security improvement, as processes could not access each other's memory.
- This was also a performance improvement, as the OS only had to manage the memory of the currently running process.

## The Address Space layout

- The address space of a process can be divided into several sections:
  - Code: The executable code of the process.
  - Data: The initialized and uninitialized data of the process.
  - Stack: The stack of the process.
  - Heap: The dynamic memory of the process.

## Virtual Memory

Virtual memory is a memory management technique that provides an idealized abstraction of the storage resources that are actually available on a given machine, which creates the illusion to users of a very large (main) memory. 

![[Memory_Layout.svg|center]]
### Transparency

One major goal of a virtual memory system is to provide transparency to the user. This means that the user does not need to know about the actual physical memory layout of the system. The user can simply assume that there is a large amount of memory available to them, and the system will take care of mapping their virtual addresses to physical addresses.

### Demand Paging

Virtual memory systems use demand paging to load pages into memory only when they are needed. This allows the system to make more efficient use of memory resources, as it does not need to load the entire program into memory at once.

### Efficient Use of Memory

Another goal of VM is to make more efficient use of memory resources. By using demand paging and other techniques, the system can load only the pages that are needed into memory, and swap out pages that are not being used. This allows the system to run more programs concurrently, and to make better use of the available memory.

### Protection

Virtual memory systems also provide protection between processes. Each process has its own virtual address space, and the system can ensure that processes cannot access memory that does not belong to them. This provides a level of security and isolation between processes, which is important for multi-user systems.

### Address Translation

Virtual memory systems use address translation to map virtual addresses to physical addresses. This allows the system to provide the illusion of a large amount of memory to the user, while actually using a smaller amount of physical memory. The translation process is handled by the hardware, which uses a page table to map virtual addresses to physical addresses.

##### Next chapter: [[Memory API]] 