---
title: Linear vs Multilevel Page Tables
tags: 
date:
---
## Linear Page Table

A linear page table is a straightforward data structure used in virtual memory systems to map virtual addresses to physical addresses. It consists of a single table where each entry corresponds to a page in the virtual address space.

### Key Formulas and Calculations for Linear Page Table

1. **Number of Bits for the Offset (Page Size):** The offset determines the location within a page. If the page size is $2^k$ bytes:
    
2. **Number of Bits for Virtual Page Number (VPN):** The VPN is the portion of the virtual address used to index into the page table. If the virtual address space is $2^n$ bytes and the page size is $2^k$ bytes:
    
3. **Total Number of Entries in the Page Table:** The total entries correspond to the number of virtual pages:
    
4. **Size of the Page Table:** If each page table entry (PTE) occupies $e$ bytes:
    

### Example Calculation for a Linear Page Table

Let us assume:

- Virtual address space: $2^{32}$ bytes
    
- Physical address space: $2^{28}$ bytes
    
- Page size: $2^{12}$ bytes
    
- Each PTE occupies 4 bytes
    

#### Step-by-Step Calculation

1. **Offset Bits:**
    
2. **VPN Bits:**
    
3. **Total Entries:**
    
4. **Page Table Size:**
    

---

## Multilevel Page Table

A multilevel page table reduces memory overhead by breaking the page table into multiple levels. It divides the VPN into multiple components, each used to index into a separate level of the table.

### Key Formulas and Calculations for Multilevel Page Table

1. **Division of VPN Bits:** If the VPN is divided across $L$ levels and each level's page table can index $2^m$ entries:
    
2. **Size of Each Level:** Each level requires space proportional to the number of entries it indexes:
    
3. **Total Page Table Size:** If there are $L$ levels:
    

### Example Calculation for a Two-Level Page Table

Using the same example parameters:

- Virtual address space: $2^{32}$ bytes
    
- Physical address space: $2^{28}$ bytes
    
- Page size: $2^{12}$ bytes
    
- Each PTE occupies 4 bytes
    

#### Step-by-Step Calculation

1. **Offset Bits:**
    
2. **VPN Bits:**
    
3. **VPN Bits per Level:** Assume a two-level table where each level indexes $2^{10}$ entries:
    
4. **Entries per Level:**
    
5. **Size of Each Level:**
    
6. **Total Page Table Size:** For two levels:
    

---

## Comparison of Linear and Two-Level Page Tables

|Parameter|Linear Page Table|Two-Level Page Table|
|---|---|---|
|Total Page Table Size|4 MB|8 KB|
|Memory Overhead|High|Low|
|Access Complexity|Single Lookup|Two Lookups|

---

## Conclusion

Linear page tables are simpler to implement but can be memory-intensive for large address spaces. Multilevel page tables significantly reduce memory overhead at the cost of additional lookups. The choice depends on the system's constraints and performance requirements.