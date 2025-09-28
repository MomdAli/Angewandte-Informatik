---
title: 1. What is Security?
tags:
  - IT-Sicherheit
  - Software-Security
  - Semester-5
  - Informatik
publish: true
---
## Core definitions and terminology
- **Security** is a non-functional property concerned with preventing *bad events* caused with malicious intent. 
- **Threat**: a potential bad event
- **Attack**: someone intentionally causes the bad event.
- **Vulnerability**: a weakness that enables an attack.
- **Exploit**: the actual implementation of an attack.
- **Risk**: the probability of an attack $\times$ its damage.

## Traditional Security Goals (CIA)
1. **Confidentiality** - keeping secrets safe and preventing unauthorized disclosure.
2. **Integrity** - ensuring only authorized modifications to data or configuration.
3. **Availability** - ensuring services remain up and usable (no denial-of-service).

## Beyond CIA: extended goals
The **CIA** triad is a solid starting point, but modern security also considers: 
- **Privacy** - control over personal data and its use.
- **Accountability/Non-repudiation** - ability to prove who did what and when.
- **Authenticity** - confidence that a message or identity is genuine.
- **Control and Utility** - ensuring systems server their intended purpose without misuse.

## Security vs Safety vs Reliability

|             | Focus                                                                    |
| ----------- | ------------------------------------------------------------------------ |
| Security    | Deals with intelligent, adaptive adversaries, hard to predict and model. |
| Safety      | handles random failures and accidents, easier to analyze statistically   |
| Reliability | focuses on consistent operation under expected conditions.               |

## Economic perspective and risk management
- Security is inherently an economic problem: defenders and attackers compare costs versus gains
	- Attackers seek the weakest link for maximum return on effort.
	- Defenders balance the cost of defences against the risk of loss.
- `Perfect security is the enemy of good security`: avoid over-engineering defences where benefit is marginal.

## Security as a continuous process
- **Proactive** measures: design and implement controls to prevent or mitigate attacks.
- **Reactive** measures: detect incidents and respond (logging, intrusion detection).
- Monitoring audition and contingency planning (incident response, backups) are essential because no defence is foolproof.

## Achieving Security: A Multi-Dimensional approach
1. **Ethics and laws** - regulations that define acceptable/illegal behavior
2. **Organizational** - policies, governance, procedures and training.
3. **Technical** - hardware and software controls like authentication, encryption and network defences.

## Typical exam questions

| Question                                           | Key points to cover                                                                                                |
| -------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| Define "security" and list its basic elements      | Mention threats, attacks, vulnerabilities, exploits and risk calculation                                           |
| Explain the CIA triad                              | One-sentence definitions of confidentiality, integrity, availibility.                                              |
| Name two additional security goals beyond CIA.     | Choose any two: privacy, accountability, authenticity, control and utility                                         |
| Contrast security vs. safety                       | Emphasize adversarial vs. random failure contexts.                                                                 |
| Why is security considered an economic problem     | Discuss cost/benefit trade-offs for attacker and defenders; reference "perfect vs. good security."                 |
| Describe proactive vs. reactive security measures. | Preventive design vs. detection/response, including examples like hardening (proactive) and IDS/logging (reactive) |

