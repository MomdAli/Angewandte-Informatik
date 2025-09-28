---
title: 10. Access control
tags:
  - IT-Sicherheit
  - Software-Security
  - Semester-5
  - Informatik
date: 2025-07-16
publish: true
---
## Definitions and Importance

Access control ensures that only authorized subjects, users or processes, can perform actions on protected objects like files, sockets or registry keys. At its heart sits the **reference monitor**, an abstract gatekeeper that mediates every access request against policy and logs attempts . Strong access control prevents data leaks, privilege escalation and resource misuse.

## Major Models

- **Discretionary Access Control (DAC):** Data owners set permissions themselves. A file’s ACL (access control list) names which subjects may read, write or execute it. DAC is flexible and common on commercial OSes, but vulnerable because users can freely re-share rights .
- **Mandatory Access Control (MAC):** Administrators define an overarching policy using labels (e.g., “Secret,” “Top Secret”). Neither users nor processes can override it. A subject can read an object only if its clearance level meets or exceeds the object’s classification (the “simple security” rule) .
- **Role-Based Access Control (RBAC):** Permissions attach to “roles” (e.g., Doctor, Nurse, Student). Users acquire rights by being assigned roles, which can form hierarchies and enforce separation of duties. RBAC scales well in large organizations because policies change at the role level rather than per user .
- **Attribute-Based Access Control (ABAC):** Decisions use attributes of subjects, objects and environment (time, location, device). ABAC supports fine-grained policies such as “allow access if user department = Finance and time between 9 AM-5 PM” .

## Windows and Unix Implementations

**Windows** uses DAC via Security Identifiers (SIDs) for principals and DACLs in each object’s security descriptor. Each process carries an access token listing its SIDs and privileges. When opening a handle, the OS compares the token against the object’s DACL, in ACE order (deny entries first), to grant or deny access .

**Unix** applies a simpler DAC: each file has owner, group and other permission bits (r/w/x). The kernel checks the process’s effective UID/GID against these bits. Default permissions and umask determine new-file rights, and special modes (setuid, setgid, sticky bit) enable controlled privilege elevation .

## Tokens, Privileges and ACL Evaluation

Windows tokens also include privileges (for example SeDebugPrivilege) for system tasks and may be restricted, dropping groups or privileges when launching untrusted code . ACLs are evaluated by scanning ACEs: matches to deny ACEs block access immediately; allow ACEs grant it; and if no ACE applies, access is denied .

## Exam Preparation

Be ready to:
1. **Define** subjects, objects, the reference monitor, and distinguish authentication vs. authorization.
2. **Compare** DAC and MAC: who controls policies, typical use cases, and inherent strengths/weaknesses.
3. **Explain** RBAC vs. ABAC and when each shines in enterprise settings.
4. **Detail** how Windows implements DAC (tokens, SIDs, DACLs, ACE order) and how Unix permissions and umask work.
5. **Sketch** the ACL evaluation algorithm in your own words: deny-first scanning, then allow, then default deny.