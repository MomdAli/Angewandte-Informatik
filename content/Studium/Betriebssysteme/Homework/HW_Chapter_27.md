---
title: Homework Chapter 27
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
  - Homework
date: 2024-12-03
publish: true
---
## Questions

**1**. First build `main-race.c`. Examine the code so you can see the (hopefully obvious) data race in the code. Now run helgrind (by typing valgrind --tool=helgrind main-race) to see how it reports the race. Does it point to the right lines of code? What other information does it give to you?

Because the line `balance++` isn't atomic, that code could be interrupted, which causes the race condition.
Now the *helgrind* tool does give these info:
- Thread announcements: Every time a thread is created, it announces it when running helgrind. Which now we know that even the main root is a thread which gets announced every time.
- Race conditions: The tool detects data races whenever a possible one occurs and also checks if there is a lock held which checks if there is a conflict. Now it does this twice:
	- One for **reading** the data
	- One for **writing** the data

**2**. What happens when you remove one of the offending lines of code? Now add a lock around one of the updates to the shared variable, and then around both. What does helgrind report in each of these cases?

- By removing the offending lines of code, the tool doesn't give any error.
- By locking one of them, it does still give the same error, however it recognizes that one of them has locks on.
- By locking both of the, the tool doesn't complain anymore. (However there is 7 from 7 supressed errors which don't show anything when running with -s)

**3**. Now let’s look at main-deadlock.c. Examine the code. This code has a problem known as deadlock (which we discuss in much more depth in a forthcoming chapter). Can you see what problem it might have?

**Lock Order Violation**:

- Thread #1 locks `m1` first, then `m2`.
- Thread #2 locks `m2` first, then `m1`.
- This is problematic because if:
    - Thread #1 locks `m1` and then waits for `m2`, while
    - Thread #2 locks `m2` and then waits for `m1`,
    - Both threads will be waiting indefinitely for the other to release its lock, causing a **deadlock**.

**4**. Now run helgrind on this code. What does helgrind report?

- `0x10C040` (address of `m1`) and `0x10C080` (address of `m2`) are the mutexes involved.
- Helgrind detected that the order of locking these mutexes is inconsistent across threads, which violates proper lock ordering rules.
- If both threads execute simultaneously, each can lock one mutex and then wait indefinitely for the other mutex, creating a deadlock.

**5**. Now run helgrind on main-deadlock-global.c. Examine the code; does it have the same problem that main-deadlock.c has? Should helgrind be reporting the same error? What does this tell you about tools like helgrind?

- Thread 1 acquires locks in the order: `g`, `m1`, `m2`.
- Thread 2 acquires locks in the order: `g`, `m2`, `m1`.
- The inconsistency in the lock acquisition order of `m1` and `m2` creates a potential **deadlock** scenario:
    - If Thread 1 holds `m1` and waits for `m2`, while Thread 2 holds `m2` and waits for `m1`, both threads will be stuck indefinitely.
- Even though `g` is locked, it does not enforce a consistent locking order for `m1` and `m2`. This results in the same issue as the previous example, where the threads acquire locks in conflicting orders.

- **The Code Is Safe from Deadlock**:
    - Thanks to the `g` mutex, only one thread can execute the lock acquisition logic at a time, eliminating deadlock risk.
- **Helgrind's Report Highlights Best Practices**:
    - Even though deadlock is impossible, the inconsistent lock order (`m1 -> m2` vs. `m2 -> m1`) is flagged because it **violates best practices** for lock ordering, which could lead to issues in more complex scenarios where `g` might not be present.

- Helgrind is useful for catching real concurrency issues, but its *warnings* need to be interpreted in the context of the program's logic.
- In this case, the warning serves as a reminder to consider consistent lock ordering in other parts of the program, even though it's not an issue here.


 **6**. Let’s next look at main-signal.c. This code uses a variable (done) to signal that the child is done and that the parent can now continue. Why is this code inefficient? (what does the parent end up spending its time doing, particularly if the child thread takes a long time to complete?)

- After creating the child thread (`pthread_create`), the parent thread enters this loop:

```c
while (done == 0);
```

- The parent thread continuously checks the value of `done` without doing any meaningful work. This is called **busy-waiting**.

- While the child thread executes, the parent thread:
    - Consumes CPU cycles.
    - Spins in a tight loop, repeatedly reading the value of `done`.

- If the child thread takes a long time to complete, the parent thread wastes significant CPU resources doing nothing productive.

**7**. Now run helgrind on this program. What does it report? Is the code correct?

**Data Race on `done`**:
- **Thread #2** writes to the variable `done` in the `worker` function.
- **Thread #1** reads from the variable `done` in the `main` function's busy-waiting loop.
- Neither thread holds a lock, making the access **unsafe**.

#### Specific Warnings:

- **Write by Thread #2**:
```sh
Possible data race during write of size 4 at 0x10C014 by thread #2
at worker (main-signal.c:9)
```

- **Read by Thread #1**:
```sh
This conflicts with a previous write of size 4 by thread #2
at main (main-signal.c:16)
```

- Helgrind detects that `done` is accessed by both threads without synchronization, leading to the possibility of undefined behavior.

**8**. Now look at a slightly modified version of the code, which is found in main-signal-cv.c. This version uses a condition variable to do the signaling (and associated lock). Why is this code preferred to the previous version? Is it correctness, or performance, or both?

The use of a **condition variable** ensures proper synchronization between the threads. This guarantees that:

- The parent thread (`main`) waits for the child thread (`worker`) to signal its completion using `signal_done`.
- No **data races** occur, as all access to the shared state (`s->done`) is protected by the mutex (`s->lock`).
- In `main-signal.c`, there is a **data race** on the `done` variable because the parent and child threads access it concurrently without synchronization.
- By introducing a condition variable (`Pthread_cond_wait` and `Pthread_cond_signal`) and protecting the shared state with a mutex, `main-signal-cv.c` eliminates this issue.

- In `main-signal-cv.c`, the parent thread uses `Pthread_cond_wait`, which:
	-> **Puts the parent thread to sleep** while waiting for the signal from the child thread.
	-> Allows the CPU to perform other useful tasks instead of spinning in a loop.

**9**. Once again run helgrind on main-signal-cv. Does it report any errors?
Nope