---
{"publish":true,"title":"9. Segmentation","created":"2024-11-03","modified":"2025-09-12T21:25:02.245+02:00","published":"2024-11-03","tags":["Betriebssysteme","Semester-3","Informatik"],"cssclasses":""}
---

> [!Info]
> This is a summary of the 16th chapter of the book "Operating Systems: Three Easy Pieces" by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. The chapter introduces segmentation, a memory management technique that divides a program’s address space into segments like code, stack, and heap. Segmentation allows each segment to be placed separately in physical memory, reducing wasted space and providing finer control over memory access and sharing. The document explains how segmentation works, its advantages and limitations, and how modern systems use segmentation alongside paging for efficient memory management.

## 1. Introduction to Segmentation and the Problem It Solves

- **Base and Bounds Registers**: Initially, operating systems (OS) managed memory by loading the entire address space (all of a program’s code, data, stack, etc.) into physical memory. This was achieved using simple _base_ and _bounds_ registers to define where in memory the program was loaded and its size. However, this approach has limitations.
- **Waste of Memory**: There’s often a large chunk of unused memory between the stack (which grows downwards) and the heap (which grows upwards). When the whole address space is loaded, this unused space wastes physical memory.
- **Challenge of Large Address Spaces**: Imagine a 32-bit address space (4 GB). Most programs don’t use the full 4 GB, but simple base and bounds registers require the entire address space to be allocated, even if large parts go unused. This approach doesn’t work well for programs with large address spaces containing free space.

## 2. Segmentation Basics

- **Concept of Segments**: Segmentation divides a program’s address space into sections called _segments_ (such as code, stack, and heap). Each segment has a different size and can be placed separately in physical memory. Instead of using one base and bounds pair, segmentation uses a pair for each segment.
    
- **Segmented Address Space Example**: With segmentation, only the used portions of memory are loaded into physical memory, reducing wasted space. For example:
    
    - The code segment might start at a specific address with a size limit.
    - The heap could have a different base address and size.
    - The stack segment could be located elsewhere in memory.
- **Hardware Support**: Segmentation requires hardware support to manage multiple base and bounds registers, one for each segment. The OS must update these registers to correctly place and manage segments.
    

## 3. Segmentation in Practice: Example Calculation

- Imagine a virtual address in the code segment (e.g., 100). The hardware takes the base of the code segment (say 32 KB) and adds this address offset to compute the physical address: $100+32 KB=32868100$.
- **Out-of-Bounds Checks**: When accessing a segment, the hardware checks the address against the segment’s bounds to prevent illegal access. If a program tries to access memory beyond its allowed bounds, the system throws a segmentation fault (more on this later).

## 4. Address Translation and Segment Identification

- **Address Translation Mechanism**: The hardware uses _segment registers_ to translate virtual addresses into physical addresses. How does the system know which segment an address belongs to? Two main approaches exist:
    - **Explicit Approach**: Use the top bits of the virtual address to identify the segment. For example, if you have three segments, you need two bits to encode them (00 for code, 01 for heap, 11 for stack). This lets the hardware know which base and bounds to use for translation.
    - **Implicit Approach**: The system determines the segment based on how the address was generated. For example, if it’s based on the program counter, it’s assumed to be in the code segment; if it’s from the stack pointer, it’s in the stack segment.

## 5. Segmentation Faults and Protection Mechanisms

- **Segmentation Fault**: This common error happens when a program accesses an address outside its segment’s bounds. This can occur due to:
    - Accessing an array out of bounds.
    - Dereferencing invalid pointers.
    - Errors in pointer arithmetic.
- **Protection Bits for Memory Sharing**: Some systems allow shared segments (like code) across processes by marking segments with _protection bits_ (read-only, read-write, or executable). For example:
    - A code segment shared between programs can be set to _read-execute_ to prevent modification.
    - Each process thinks it has private access, while in reality, the OS manages shared access.

## 6. Segment Growth and the Stack’s Special Case

- **Stack Growth (Backwards)**: Unlike other segments, stacks usually grow downwards (from high to low addresses). For example, in physical memory, the stack might start at address 28 KB and grow back to 26 KB.
- **Direction of Growth in Hardware**: The hardware must know the direction in which each segment grows. If a segment grows downwards (as in the stack), an extra bit in the hardware indicates this. The translation for the stack differs slightly due to this backward growth.

## 7. Fine-Grained vs. Coarse-Grained Segmentation

- **Coarse-Grained Segmentation**: Most systems use only a few large segments (e.g., one each for code, stack, and heap).
- **Fine-Grained Segmentation**: Early systems (like Multics and the Burroughs B5000) supported many smaller segments. This requires more complex hardware, often involving a _segment table_ to manage the large number of segments. Fine-grained segmentation is flexible but more complex.

## 8. Memory Fragmentation in Segmentation

- **External Fragmentation**: Over time, as segments are allocated and deallocated, gaps (or “holes”) in memory appear, making it hard to find contiguous memory for new segments. This is called _external fragmentation_.
- **Solutions to Fragmentation**:
    - **Compaction**: The OS periodically rearranges segments in memory to create large free spaces. This is resource-intensive but can temporarily solve fragmentation.
    - **Free-List Management**: Instead of compacting, some algorithms manage free memory to keep large chunks available. Examples include _best-fit_, _first-fit_, and _buddy algorithms_.

## 9. Operating System Support for Segmentation

- **Context Switching**: When switching between processes, the OS must save and restore segment registers for each process. This ensures that each process maintains its virtual address space.
- **Segment Resizing**: When a program requests more memory (e.g., calling `malloc` in C), the OS may need to grow the heap segment. This often involves a system call (e.g., `sbrk` in UNIX) to increase the segment size if physical memory is available.

## 10. Advantages and Limitations of Segmentation

- **Advantages**:
    
    - _Efficient Memory Use_: Only segments in use occupy physical memory, reducing wasted space.
    - _Support for Sparse Address Spaces_: Large address spaces with large unused portions (like those between the stack and heap) don’t take up memory.
    - _Sharing and Protection_: Allows code sharing and finer control over read, write, and execute permissions.
- **Limitations**:
    
    - _External Fragmentation_: Despite various algorithms, external fragmentation remains challenging.
    - _Limited Flexibility for Sparse Segments_: If segments are too large or sparse, memory isn’t used efficiently.
    - _Growth Limitations_: The fixed size of segments can prevent programs from growing memory as needed (e.g., if a stack needs more space, but the memory after it is occupied).

## 11. Advanced Considerations and Future Directions

- **Paging as a Complementary Approach**: To overcome segmentation’s limitations, operating systems often use _paging_ (splitting memory into fixed-size pages) alongside segmentation. This can better support dynamic and sparse address spaces without fragmentation, which we'll likely cover in a future lecture.
- **Segmentation in Modern Systems**: Many modern systems use a combination of segmentation and paging, or just paging, since it’s simpler for managing memory at scale and provides finer-grained control.


##### Next Chapter: [[Studium/Betriebssysteme/OSTEP/10_Free-Space Management\|10.Free-Space Management]]