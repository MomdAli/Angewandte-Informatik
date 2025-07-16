---
title: 3. OS Hardening
tags:
  - IT-Sicherheit
  - Software-Security
  - Semester-5
  - Informatik
date: 2025-07-16
---
## What is OS hardening?
OS hardening is the process of securing an operating system by reducing its attack surface, removing or disabling unneeded features, tightening configurations, and adding protective controls so that potential vulnerabilities cannot be exploited. Think of it as stripping down the OS to only what's necessary for its intended role, then "bolting on" extra defences where needed.

**Why harden an OS?**
To minimize vulnerabilities, Enforce least privilege or even installing layer defences. 

## Core hardening steps
1. Install a "clean" base OS
	- Use minimal installation options; avoid extras like desktop GUIs on a server.
2. Patch immediately
	- Apply all security updates for the OS and bundled third-party components.
3. Remove unnecessary software
	- Uninstall or disable servers, applications, and network protocols not required for the system's purpose.
4. Configure users, groups and permissions
	- Create only the user accounts you need; remove or lock down default/system accounts; assign file and directory permissions per role.
5. Apply resource controls
	- Use OS access-control lists (ACLs) and file permissions to restrict what processes and users can do.
6. Install host-based security tools
	- Anti-virus, host-based firewalls, and instrusion-detection/prevention agents add extra barriers.
7. Test and verify
	- Run vulnerability scans or manual checks to confirm that your hardening steps actually removed or secured the intended features.

## Key techniques and concepts

- Service Reduction
	- Default OS images often ship with dozens of unnecessary services (for example print spoolers on servers). Disable or remove them so they cannot be targeted.
- Principle of Least Privilege
	- Grant users and services only the permissions they need "just in time". Don't let regular users run as root or Administrator.
- Application Whitelisting
	- Instead of blocking known bad software (blacklisting), permit only approved executables to run. You can whitelist by hash, digital signature, file path or directory.
- Baseline Configuration Standards
	- Use published hardening guides (for example CIS Benchmarks or BSI's IT-Grundschutz modules) to ensure consistency across systems.
- Defence in Depth
	- Combine OS hardening with network segmentation, patch management and security monitoring for overlapping layers of protection.

## Typical exam questions
### **Q1**: List and briefly explain four OS hardening strategies.
1. Remove services: Uninstall/disable unneeded daemons to reduce attack surface.
2. Patch promptly: Keep OS and applications up to date to close known CVEs.
3. Least privilege: Restrict user/group permissions so users run with minimal rights.
4. Host-based controls: Deploy firewalls, IDS/IPS and anti-malware agents to detect/block attacks.

### **Q2**: What is application whitelisting, and what's a key challenge?
Application whitelisting forbids execution of any software not explicitly approved, using hashes or signatures to define the white list. A main challenge is maintaining the whitelist as legitimate needs change, adding new apps or updates without interrupting operations