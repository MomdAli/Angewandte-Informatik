---
title: "9. Cryptography: Trusted Computing (TPM)"
tags:
  - IT-Sicherheit
  - Software-Security
  - Semester-5
  - Informatik
date: 2025-07-16
publish: true
---
## Definitions and importance
Trusted computing embeds a hardware-based root of trust into a system so that software integrity and confidentiality can be enforced even if hight-level software is compromised. The cornerstone is the **Trusted Platform Module (TPM)**, a dedicated security chip (or integrated microcontroller) that generates, stores and uses cryptographic keys in a tamper-evident environment. By anchoring trust in hardware, we can ensure that only approved code runs at boot and that sensitive data stays bound to a known, secure configuration.

## Core TPM Services
- **Authenticated Boot**
	At each stage of system startup, the TPM verifies a digital signature on the next component (firmware, bootloader, kernel, etc.) and records measurements in a secure log. If any component isn't "on the approved list" or its signature has been revoked, the system refuses to proceed, preventing malware-in-boot attacks.
- **Certification (attestation)**
	Once the boot sequence completes, the TPM can produce a signed certificate (or "quote") attesting to the measured configuration. Remote parties trusting that TPM's endorsement can verify you're running exactly the code you claim, enabling secure provisioning or access control based on platform integrity. 
- **Encryption Service**
	The TPM holds a unique, master secret and derives keys tied to specific configurations. Data encrypted under those keys can only decrypted on a machine in that exact state, effectively binding files or secrets to both hardware and software versions, and thwarting exfiltration even if storage media are stolen.

## Key Concepts

- **Root of Trust**: The immutable TPM hardware and its built-in bootloader form the foundation that no other component can override.
- **Chain of Trust**: Each stage signs off on the next, extending trust from the TPM through firmware and OS to applications.
- **Platform Configuration Registers (PCRs)**: Secure, append-only registers in the TPM that store hashed measurements of each boot component. These PCR values underpin attestation and sealed storage.

## Exam preparation
You may be asked to name and describe the three TPM services, so be ready to explain in a few sentences how authenticated boot prevents unauthorized code from running, how attestation proves platform integrity to third parties, and how TPM-backed encryption binds data to a known configuration. Drawing a simple diagram of PCR measurement and quote generation can help illustrate those flows under exam conditions.