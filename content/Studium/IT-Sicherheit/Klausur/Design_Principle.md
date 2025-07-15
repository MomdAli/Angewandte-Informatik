---
title: Design Principle
tags:
  - IT-Sicherheit
  - Semester-5
  - Informatik
date: 2025-07-11
---
1. Economy of mechanism: Keep the design as simple and small as possible.
2. Fail-safe defaults: Base access decisions on permission rather than exclusion.
3. Complete mediation: Every access to every object must be checked for authority
4. Open design: The design should not be secret.
5. Separation of privilege: Where feasible, a protection mechanism that requires two keys to unlock it is more robust and flexible than one that allows access to the presenter of only a single key.

	**Application** of design principle: avoid highly privileged accounts like root/administrator that are attractive targets for attacks  
6. Least privilege: Every program and every user of the system should operate using the least set of privileges necessary to complete the job.
7. Least common mechanism: Minimize the amount of mechanism common to more than one user and depended on by all users.

	**Application** of design principle: Reduce amount of privileged code in libraries that needs to be reviewed.
8. Psychological acceptability: It is essential that the human interface be designed for ease of use, so that users routinely and automatically apply the protection mechanisms correctly.
9. Work factor: Compare the cost of circumventing the mechanism with the resources of a potential attacker.

	**Application** of design principle: increase costs to find and exploit software vulnerabilities (costs = training, skills, tools, computation, hardware)
	But: might not hold in software security owing to automation
10. Compromise recording: In computer systems, mechanisms that reliably record that a compromise has occurred are used rarely, since it is difficult to guarantee discovery once security is broken

	**Application** of design principle: enable logging and (automatically) analyse logs to detect attacks