---
{"publish":true,"title":"24. Raid","created":"2025-01-29","modified":"2025-09-12T21:25:02.184+02:00","published":"2025-01-29","tags":["Betriebssysteme","Semester-3","Informatik"],"cssclasses":""}
---

> [!info]
> This is a summary of Chapter 38 from the book *Operating System: Three Easy Pieces* by Remzi H. Arpaci-Dusseau and Andrea C. Arpaci-Dusseau. This chapter focuses on **Redundant Arrays of Inexpensive Disks (RAID)**, explaining different RAID levels, their advantages, trade-offs, and performance characteristics.

## 1. Introduction

Disks are often slow, limited in capacity, and vulnerable to failure. RAID systems address these issues by combining multiple disks to improve:
1. **Performance** – Faster I/O operations via parallelism.
2. **Capacity** – Larger storage by combining multiple disks.
3. **Reliability** – Fault tolerance by introducing redundancy.

### Key Questions:

- How can RAID enhance disk storage?
- What trade-offs exist between different RAID levels?

---

## 2. RAID Overview

RAID presents a **transparent** interface to the operating system, appearing as a single large disk. Internally, it consists of:
- **Multiple disks** working together.
- **A RAID controller** managing disk interactions.
- **Memory (DRAM/NVRAM)** for caching and buffering.
- **Specialized hardware/software** for redundancy management.

### RAID Benefits:
- **Improves performance** through parallel I/O.
- **Increases storage capacity** by aggregating disks.
- **Enhances reliability** via redundancy mechanisms.

---

## 3. RAID Fault Model

RAID assumes a **fail-stop** model:
- Disks are either **fully operational** or **completely failed**.
- Failures are **immediately detectable** by the RAID controller.
- More complex failures (e.g., silent corruption, latent sector errors) are **not initially considered** but are relevant in real-world scenarios.

---

## 4. RAID Performance Metrics

RAID levels are evaluated based on:
1. **Capacity** – Usable storage after redundancy.
2. **Reliability** – Fault tolerance (number of disks that can fail).
3. **Performance** – Impact on read/write speeds.

---

## 5. RAID Levels and Analysis
### **RAID 0 (Striping)**
- **No redundancy** (not technically a RAID).
- Distributes data across disks to maximize performance.
- **Capacity**: $(N \times B)$
- **Reliability**: **0** (failure of one disk = data loss).
- **Performance**:
  - **Sequential Read/Write**: $(N \times S)$ (uses all disks).
  - **Random Read/Write**: $(N \times R)$.

---

### **RAID 1 (Mirroring)**
- **Each disk has an exact copy** on another disk.
- **Tolerates single disk failure** (or more, if lucky).
- **Capacity**: $(N \times R) / 2$.
- **Reliability**: **1 disk (or more, depending on failures).**
- **Performance**:
  - **Reads**: $(N \times R)$ (can use either copy).
  - **Writes**: $(N/2 \times R)$ (must write to both copies).

---

### **RAID 4 (Parity-Based)**
- Uses **one disk** for parity calculations.
- Recovers lost data using XOR calculations.
- **Capacity**: $((N-1) \times B)$.
- **Reliability**: **1 disk failure tolerance**.
- **Performance**:
  - **Sequential Read/Write**: $((N-1) \times S)$.
  - **Random Read**: $((N-1) \times R)$.
  - **Random Write**: **Severely bottlenecked** by parity disk.

🔴 **Small-Write Problem**: Every write requires updating the parity disk, causing **a bottleneck**.

---

### **RAID 5 (Rotating Parity)**
- **Same as RAID 4, but parity is distributed** across disks.
- Reduces the **parity disk bottleneck**, allowing better performance.
- **Capacity**: $((N-1) \times B)$.
- **Reliability**: **1 disk failure tolerance**.
- **Performance**:
  - **Sequential Read/Write**: $((N-1) \times S)$.
  - **Random Read**: $(N \times R)$.
  - **Random Write**: $(N/4 \times R)$ (better than RAID 4).

---

## 6. Summary Table
| RAID       | Capacity             | Reliability              | Seq. Read          | Seq. Write         | Rand. Read         | Rand. Write                |
| ---------- | -------------------- | ------------------------ | ------------------ | ------------------ | ------------------ | -------------------------- |
| **RAID 0** | $(N \times B)$       | **0**                    | $(N \times S)$     | $(N \times S)$     | $(N \times R)$     | $(N \times R)$             |
| **RAID 1** | $((N \times B) / 2)$ | **1 (or more if lucky)** | $(N \times R)$     | $(N/2 \times S)$   | $(N \times R)$     | $(N/2 \times R)$           |
| **RAID 4** | $((N-1) \times B)$   | **1 disk**               | $((N-1) \times S)$ | $((N-1) \times S)$ | $((N-1) \times R)$ | **$(R/2)$ (Bottlenecked)** |
| **RAID 5** | $((N-1) \times B)$   | **1 disk**               | $((N-1) \times S)$ | $((N-1) \times S)$ | $(N \times R)$     | $(N/4 \times R)$           |

---

## 7. Conclusion
- **RAID 0**: Best performance, no reliability.
- **RAID 1**: Best reliability, expensive (requires 2x storage).
- **RAID 4**: Efficient storage, poor write performance.
- **RAID 5**: Good balance of reliability and storage efficiency.

Choosing the right RAID level depends on whether **performance, reliability, or storage efficiency** is the priority.