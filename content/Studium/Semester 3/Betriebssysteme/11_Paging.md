---
title: 11. Paging
date: 2024-11-17
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
---

> [!Info]
> This is a summary of the 18th chapter of the book "Operating Systems: Three Easy Pieces" by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. The chapter discusses paging, a memory management technique that allows the operating system to manage memory in fixed-size blocks called _pages_. The document explains how paging works, its benefits and challenges, and the key concepts in implementing and optimizing paging systems.

### Introduction

Paging is a memory management technique that divides address spaces into fixed-sized units called pages, and physical memory into page frames. This approach eliminates external fragmentation and simplifies memory allocation, unlike segmentation, which uses variable-sized chunks. Paging ensures flexibility by allowing processes to use sparse address spaces without assumptions about growth patterns of stacks or heaps.

### Problem Scope

The main challenge of paging is to virtualize memory effectively while addressing issues such as fragmentation and efficiency. Paging divides memory into fixed-sized units called pages, preventing external fragmentation common in segmentation. However, implementing this system requires solving critical problems like minimizing memory overhead from large page tables and reducing the performance impact of additional memory references during address translations. Balancing these trade-offs is essential to create a functional and efficient memory virtualization system.

### Key Concepts

- **Pages and Page Frames**: Virtual memory is divided into pages, and physical memory into page frames, both of the same fixed size. Each page frame can hold one virtual page.
- **Page Tables**: A per-process data structure, the page table, maps virtual pages to physical frames. It includes details such as valid bits, protection bits, and more.
- **Address Translation**: Virtual addresses are split into a virtual page number (VPN) and an offset. The VPN maps to a physical frame number (PFN) through the page table, and the offset specifies the byte within the frame.
- **Memory Access Workflow**: Accessing a memory address involves referencing the page table to translate the virtual address into a physical one, followed by accessing the actual data in memory.

### Challenges

1. **Memory Overhead**: Page tables can become very large, particularly for systems with large address spaces and small page sizes.
2. **Performance Overheads**: The need to access page tables for each memory reference introduces additional memory operations, potentially slowing down processes.

### Optimizations

- **Hierarchical Page Tables**: To reduce the size of page tables, hierarchical structures divide the table into multiple levels.
- **Translation Lookaside Buffer (TLB)**: A hardware cache stores recent page table entries to speed up address translations.
- **Swapping and Valid Bits**: Pages not in physical memory are marked invalid, allowing the system to swap them to disk and save physical memory.

### Conclusion

Paging addresses the limitations of segmentation by dividing memory into fixed-sized units, eliminating external fragmentation and enabling sparse address space utilization. However, it introduces challenges in terms of memory and performance overheads. Efficient design of page tables and hardware optimizations like TLBs are crucial for practical and high-performance implementation.

##### Next Chapter: [[12_TLBs|12. TLBs]]