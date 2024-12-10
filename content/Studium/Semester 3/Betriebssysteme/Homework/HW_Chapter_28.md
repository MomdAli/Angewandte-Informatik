---
title: Homework Chapter 28
tags:
  - Betriebssysteme
  - Homework
  - Semester-3
  - Informatik
date: 2024-12-07
---
## Questions:

**1**. Examine flag.s. This code “implements” locking with a single memory flag. Can you understand the assembly? 

Lock if it's unlocked, or spin-wait if it's not. After critical section, check if we have to do this again, if not then stop.


**2**. When you run with the defaults, does flag.s work? Use the -M and -R flags to trace variables and registers (and turn on -c to see their values). Can you predict what value will end up in flag? 

-> Yes it's 2.


**3**. Change the value of the register %bx with the -a flag (e.g., -a bx=2,bx=2 if you are running just two threads). What does the code do? How does it change your answer for the question above?

-> How often a thread repeats the critical section.


**4**. Set bx to a high value for each thread, and then use the -i flag to generate different interrupt frequencies; what values lead to a bad outcomes? Which lead to good outcomes? 

-> 11 leads to good outcome because there are 11 instructions that needs to be completed. 1 leads to bad outcome because both threads lock at the same, which leads the output to be 100 if bx was 100.


**5**. Now let’s look at the program test-and-set.s. First, try to understand the code, which uses the xchg instruction to build a simple locking primitive. How is the lock acquire written? How about lock release?

-> Test and acquire using xch instruction and the release is just setting mutex to 0.


**6**. Now run the code, changing the value of the interrupt interval (-i) again, and making sure to loop for a number of times. Does the code always work as expected? Does it sometimes lead to an inefficient use of the CPU? How could you quantify that? 

-> It is very correct and deterministic.


**7**. Use the -P flag to generate specific tests of the locking code. For example, run a schedule that grabs the lock in the first thread, but then tries to acquire it in the second. Does the right thing happen? What else should you test? 

-> `./x86.py -p test-and-set.s -M mutex -R ax -a bx=10,bx=10 -P 0011 -c` 
	Yes, the right thing does happen.
