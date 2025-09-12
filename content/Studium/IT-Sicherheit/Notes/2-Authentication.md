---
{"publish":true,"title":"2. Authentication","created":"2025-07-15","modified":"2025-09-12T21:25:02.301+02:00","published":"2025-07-15","tags":["IT-Sicherheit","Software-Security","Semester-5","Informatik"],"cssclasses":""}
---

## Core Concepts
- Identification is "claiming" an identity (e.g. "I am Mohammed").
- Authentication is **verifying** that claimed identity.
- Authorization is deciding what that verified identity is **allowed** to do.
Together, authentication plus authorization make up access control.

## Three Authentication Categories
- Something you know: This factor includes information that the user has memorized
- Something you have: refers to physical objects that the user possesses, like a smartphone, security token, smart card, or one-time password generator
- Something you are: is based on unique biological characteristics of the user, such as fingerprints, facial recognition, voice recognition, or iris scans

## Password-Based Authentication

User picks a username + password. The system stores only a **hashed** version of the password (never plaintext). On login, the password you enter is hashed and compared to the stored hash.

- Common Attacks:
	- Phishing/Spoofing: fake login pages trick into revealing your password
	- Keylogging: [[Studium/IT-Sicherheit/Notes/8-Malware\|malware]] or hardware records your keystrokes
	- Brute-force/Guessing: systematically trying password combinations
	- Password reuse: same password across sites lets one breach compromise many accounts
- Defenses and Best practices:
	- **Hash + salt + slow KD** so even if attackers steal the hash, they can't crack it quickly
	- Enforce **minimum length** and complexity rules
	- **Rate-limit** or lock out accounts after repeated failures to block brute-force.
	- Provide secure **password recovery** (avoid emailing passwords or "yellow stickers")

## Token-Based and Biometric Authentication
- Token-Based:
	- **Physical tokens** (smart cards, USB keys) store cryptographic secrets inside a tamper-resistant chip
	- **Smartphone apps** (TOTP,  push notifications) also count as "something you have."
	- **Challenges**: distribution, reader infrastructure, recovery when lost
- Biometric
	- Physiological: fingerprint, iris, face
	- Behavioral: voice, typing pattern, gait
	- Accuracy metrics:
		- FAR (False Acceptance Rate)
		- FRR (False Rejection Rate)
		- EER (Equal Error Rate)
	- Limitations: reader quality, enrollment failures, [[Studium/IT-Sicherheit/Notes/5-Privacy\|privacy]] (biometrics can't be "revoked")

## Multi-Factor and Two-Factor Authentication
- Multi-Factor Authentication (MFA):
	- Combines two or more authentication factors (e.g. password + token)
	- Provides stronger security than single-factor methods
	- Examples: password + SMS code, password + fingerprint

- Two-Factor Authentication (2FA):
	- A specific type of MFA that uses exactly two factors
	- Most common: password + something you have (e.g. SMS code, authenticator app)
	- Adds a second layer of security beyond just the password

Security boost: attacker must obtain both factors to break in.
