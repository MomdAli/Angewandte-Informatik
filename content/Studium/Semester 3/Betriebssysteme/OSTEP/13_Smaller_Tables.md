---
title: 13. Smaller Tables
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-11-17
---

> [!Info]
> This is a summary of the 20th chapter of the book "Operating Systems: Three Easy Pieces" by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. The chapter discusses techniques for reducing the size of page tables to improve memory management efficiency. It covers methods such as inverted page tables, and multi-level page tables, exploring their benefits and trade-offs in different system contexts.

### Introduction

Linear page tables, which map each virtual page to a physical page using a single contiguous data structure, are memory-intensive and inefficient for large address spaces. For example, a 32-bit address space with 4KB pages requires approximately 1 million entries, resulting in a 4MB page table per process. With hundreds of active processes, the memory consumed by page tables alone can reach hundreds of megabytes. As modern systems increasingly rely on large address spaces and numerous processes, techniques to reduce the size and overhead of page tables are crucial.

### Problem Scope

The challenge arises from the excessive memory consumption of linear page tables, which allocate space for all virtual pages, including unused regions. This approach leads to wasted memory and inefficiencies, especially for sparse address spaces. The goal is to develop compact page table designs that minimize memory usage while maintaining efficient address translation and supporting the dynamic nature of modern workloads.

### Key Concepts

1. **Larger Pages**:  
    Increasing the page size reduces the number of page table entries required. For example, switching from 4KB to 16KB pages reduces the page table size by a factor of four. However, larger pages result in internal fragmentation, where allocated pages may not be fully utilized, leading to wasted memory. This trade-off makes larger pages impractical for general-purpose memory usage but beneficial for specific applications like databases, where large, contiguous data structures are common.
    
2. **Hybrid Paging and Segmentation**:  
    This approach combines paging with segmentation to reduce memory overhead. Instead of maintaining a single page table for the entire address space, separate page tables are maintained for individual segments (e.g., code, heap, and stack). This reduces wasted memory by tailoring the size of each page table to the actual usage of its segment. However, this method reintroduces the complexity of segmentation, such as managing segment boundaries and potential external fragmentation in physical memory.
    
3. **Multi-Level Page Tables**:  
    Multi-level page tables break the linear page table into smaller chunks organized in a hierarchical tree structure. For example, a two-level page table uses a top-level directory to point to smaller page tables, each covering a portion of the address space. If a region of the address space is unused, the corresponding lower-level page table is not allocated, saving memory. However, address translation requires additional memory accesses: one for the top-level directory and another for the actual page table entry.
    
4. **Inverted Page Tables**:  
    Unlike traditional page tables that map virtual pages to physical frames, inverted page tables map physical frames to virtual pages. A single global inverted table is maintained, significantly reducing memory usage, as only one table is needed for the entire system. However, finding the correct mapping requires searching or hashing, adding complexity and potentially slowing down address translation.
    

### Challenges

1. **Fragmentation**:
    
    - **Internal Fragmentation**: Larger page sizes lead to wasted memory within allocated pages.
    - **External Fragmentation**: Hybrid approaches that allocate variable-sized page tables can lead to difficulties in finding contiguous memory blocks for these tables.
2. **Increased Lookup Costs**:
    
    - Multi-level page tables require multiple memory accesses for address translation.
    - Inverted page tables rely on hashing or other search mechanisms, adding latency.
3. **Complexity of Implementation**:
    
    - Multi-level and hybrid designs require careful management of page table structures and additional hardware/software support.
    - TLB misses become more costly due to the increased number of lookups.
4. **Time-Space Trade-offs**:  
    Reducing memory usage often increases translation time, creating a trade-off between space efficiency and performance.
    

### Optimizations

1. **Multi-Level Page Tables**:  
    By allocating memory only for valid regions of the address space, multi-level tables save substantial memory while maintaining flexibility. For example, a sparse address space with only a few active regions can drastically reduce the memory required for page tables.
    
2. **Context-Specific Larger Pages**:  
    Many architectures support multiple page sizes. Applications can request large pages (e.g., 4MB instead of 4KB) for frequently accessed, contiguous data, reducing TLB misses and improving performance. This approach is common in database management systems.
    
3. **Swapping Page Tables**:  
    In memory-constrained systems, page tables themselves can be placed in virtual memory. This allows inactive page tables to be swapped to disk, freeing up physical memory for other uses. However, this adds overhead when these tables are needed again.
    
4. **Inverted Tables with Hashing**:  
    Hashing mechanisms speed up lookups in inverted page tables, making them feasible for systems with many processes and large address spaces.


---

### Hardware-Managed TLBs

- **Responsibility for Management**:
    
    - The **hardware (MMU - Memory Management Unit)** automatically handles TLB misses.
    - When a TLB miss occurs, the hardware walks the page table to find the translation and updates the TLB.
- **Mechanism**:
    
    - The CPU contains specialized circuits to perform the page table walk and insert the appropriate entry into the TLB.
    - This process is transparent to the operating system.
- **Advantages**:
    
    - **Speed**: Hardware-managed TLBs are very fast because they operate directly at the hardware level.
    - **Simplicity**: The OS does not need to implement TLB miss handling logic, simplifying the OS design.
- **Disadvantages**:
    
    - **Lack of Flexibility**: The page table format and TLB management policies are constrained by the hardware implementation.
    - **Hardware Complexity**: Requires more complex hardware, increasing cost and power consumption.

---

### Software-Managed TLBs

- **Responsibility for Management**:
    
    - The **operating system** handles TLB misses.
    - When a TLB miss occurs, the CPU traps into the OS, which uses software to look up the page table and update the TLB.
- **Mechanism**:
    
    - The CPU triggers a trap (e.g., `TLB_MISS`) when the requested virtual-to-physical address translation is not in the TLB.
    - The OS then performs a page table lookup and inserts the corresponding entry into the TLB.
- **Advantages**:
    
    - **Flexibility**: The OS can define its own page table structures and policies for TLB management.
    - **Customizability**: Different page table formats or optimization techniques can be implemented in software.
- **Disadvantages**:
    
    - **Slower**: Software management introduces overhead because a trap into the OS is required on every TLB miss.
    - **Complex OS Code**: The OS must implement efficient TLB miss handling, which can be complex.

---

### Key Differences at a Glance

| Aspect                      | Hardware-Managed TLB                | Software-Managed TLB              |
| --------------------------- | ----------------------------------- | --------------------------------- |
| **Who handles TLB misses?** | Handled by hardware (MMU)           | Handled by the OS in software     |
| **Page table walk**         | Performed automatically by hardware | Performed by the OS               |
| **Performance**             | Faster (direct hardware operation)  | Slower (requires OS intervention) |
| **Flexibility**             | Limited by hardware design          | Highly flexible (OS-defined)      |
| **Complexity**              | More complex hardware               | More complex OS code              |

---

### When are they used?

- **Hardware-Managed TLBs**: Common in traditional desktop and server CPUs, where performance is critical.
- **Software-Managed TLBs**: Found in systems that prioritize flexibility or have simpler hardware, such as some RISC architectures (e.g., MIPS, SPARC).

### Conclusion

Reducing page table size is essential for efficient memory management in modern systems. Techniques such as multi-level page tables, hybrid paging, and inverted page tables address the limitations of linear designs, offering substantial memory savings while supporting sparse address spaces. Each method comes with trade-offs in complexity, performance, and implementation effort. The choice of page table design depends on system constraints, workload characteristics, and the desired balance between memory efficiency and translation speed.

##### Next Chapter: [[14_BPM|14. Beyond Physical Memory]]