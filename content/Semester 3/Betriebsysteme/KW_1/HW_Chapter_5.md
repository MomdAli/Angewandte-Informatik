---
title: Homework Chapter 5
tags:
  - Betriebsysteme
  - Homework
  - Python
date: 2024-10-03
aliases:
  - HW5
  - Homework5
cssclasses:
---
## <font color="#71e9ac">Simulation</font>
This simulation homework focuses on `fork.py`, a simple process creation simulator that shows how processes are related in a single “familial” tree. Read the relevant README for details about how to run the simulator.

### Question 1:
Run ./fork.py -s 10 and see which actions are taken. Can you predict what the process tree looks like at each step? Use the -c flag to check your answers. Try some different random seeds (-s) or add more actions (-a) to get the hang of it.
### Answer:
Action: a forks b
	a
	└── b

Action: a forks c
	a
	└── b
	└── c
	
Action: c EXITS
	a
	└── b

Action: a forks d
	a
	└── b
	└── d
	
Action: a forks e
	a
	└── b
	└── d
	└── e


### Question 2:
One control the simulator gives you is the fork percentage, controlled by the -f flag. The higher it is, the more likely the next action is a fork; the lower it is, the more likely the action is an exit. Run the simulator with a large number of actions (e.g., -a 100) and vary the fork percentage from 0.1 to 0.9. What do you think the resulting final process trees will look like as the percentage changes? Check your answer with -c.