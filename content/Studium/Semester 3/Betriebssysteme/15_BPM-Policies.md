---
title: 15. BPM - Policies
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-11-18
---
## Virtual Memory: Beyond Physical Memory – Policies

> [!Info]
> This is a summary of the 22nd chapter of the book "Operating Systems: Three Easy Pieces" by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. The chapter discusses page-replacement policies in virtual memory systems, focusing on cache management, replacement policies, types of cache misses, and optimizations to improve performance.
### Introduction

In virtual memory systems, the operating system (OS) must decide which memory pages to retain in physical memory and which to evict to disk when memory is limited. This decision-making process is governed by **page-replacement policies**, which aim to maximize efficiency by reducing the number of cache misses. These policies must balance simplicity, performance, and system constraints to ensure smooth operation, especially under memory pressure.

### Problem Scope

The challenge lies in determining which page(s) to evict when memory is full. Poor decisions can lead to frequent disk access, drastically slowing down performance. The goal is to develop policies that minimize the average memory access time (AMAT) by reducing the probability of a miss (PMiss) while managing memory and disk I/O efficiently.

### Key Concepts

1. **Cache Management**:  
    Physical memory acts as a cache for virtual pages. Replacement policies aim to maximize **cache hits** and minimize **cache misses**. The AMAT is calculated as:  
    **AMAT = TM + (PMiss × TD)**  
    where TM is memory access time, TD is disk access time, and PMiss is the probability of a miss. Disk access being orders of magnitude slower than memory access makes reducing misses critical.
    
2. **Replacement Policies**:
    
    - **Optimal Replacement (MIN)**: Evicts the page that will not be accessed for the longest time. While optimal, it requires future knowledge, making it impractical for real systems but useful as a benchmark.
    - **FIFO (First-In, First-Out)**: Evicts the oldest page. Simple to implement but can perform poorly by discarding frequently accessed pages.
    - **Random**: Evicts a randomly chosen page. Its simplicity can sometimes avoid corner cases but lacks consistent performance.
    - **LRU (Least Recently Used)**: Evicts the least recently used page based on the assumption of temporal locality. It performs well but requires historical tracking, which can be resource-intensive.
3. **Types of Cache Misses**:
    
    - **Compulsory Miss**: Occurs during the first access to a page.
    - **Capacity Miss**: Happens when the cache cannot hold all active pages.
    - **Conflict Miss**: Arises in hardware-limited cache systems but does not occur in fully associative OS page caches.
4. **Locality in Workloads**:
    
    - **Spatial Locality**: Pages near the currently accessed page are likely to be accessed next.
    - **Temporal Locality**: Recently accessed pages are likely to be accessed again soon.

### Challenges

1. **Balancing Complexity and Performance**: Advanced policies like LRU perform better but require significant bookkeeping, increasing computational overhead.
2. **Handling Dirty Pages**: Evicting modified (dirty) pages requires writing them back to disk, increasing I/O costs compared to clean pages.
3. **Thrashing**: When memory demands exceed physical capacity, frequent paging occurs, severely degrading performance.

### Optimizations

1. **Approximations of LRU**:
    
    - **Clock Algorithm**: Uses a circular queue and reference bits to approximate LRU. It clears reference bits as it scans, evicting the first page with a cleared bit.
    - **Enhanced Clock Algorithm**: Prioritizes clean pages to reduce costly writes to disk.
2. **Prefetching and Clustering**:
    
    - **Prefetching**: Predicts future accesses and preloads pages into memory, improving efficiency when predictions are accurate.
    - **Clustering**: Groups multiple writes into a single disk operation, improving I/O efficiency.
3. **Scan-Resistant Algorithms**: Modern algorithms like ARC (Adaptive Replacement Cache) adapt dynamically to workloads, avoiding common pitfalls like thrashing.
    
4. **Admission Control**: Limits the number of active processes to ensure their working sets fit in memory, preventing thrashing.
    

### Conclusion

Page-replacement policies are a cornerstone of virtual memory management, aiming to reduce the impact of limited memory by minimizing cache misses. While optimal replacement serves as an ideal benchmark, practical policies like LRU and Clock balance efficiency and feasibility. Innovations like scan-resistant algorithms and prefetching have improved performance, but the advent of faster storage devices like SSDs continues to reshape the landscape, driving further evolution in these policies.

