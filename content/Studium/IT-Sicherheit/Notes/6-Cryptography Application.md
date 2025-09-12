---
{"publish":true,"title":"6. Cryptography: Application","created":"2025-07-16","modified":"2025-09-12T21:25:02.331+02:00","published":"2025-07-16","tags":["IT-Sicherheit","Software-Security","Semester-5","Informatik"],"cssclasses":""}
---

## Definitions and importance
Cryptography is the art and science of protecting data, whether in transit over an insecure channel or at rest on untrusted media, by ensuring [[Studium/IT-Sicherheit/Notes/1-Security#Traditional Security Goals (CIA)\|CIA]].
## Core Applications
Hash functions compress inputs of arbitrary length into fixed-size digests in a one-way, collision-resistant way. This makes them ideal for verifying message integrity, storing passwords securely, generating message authentication codes, driving digital signature schemes and even underpinning blockchains like Bitcoin.

Asymmetric cryptography uses public/private key pairs so that anyone can encrypt with the public key but only the private-key holder can decrypt, elimination the need for a pre-shared secret. In practice, systems use a hybrid approach: a fast symmetric "session key" is negotiated (for example via [Diffie-Hellman](https://en.wikipedia.org/wiki/Diffie%E2%80%93Hellman_key_exchange)) and then that key encrypts bulk data, combining efficiency with the strong key distribution guarantees of public-key methods.

Electronic signatures marry these two primitives by first hashing a document and then signing the digest with a private key, providing legal-level proof of origin and integrity. They're widely used for secure email (S/MIME, PGP), tax filings, e-invoices and digital certificates.

## Algorithm Choices and key management
- **RSA** relies on the difficulty of factoring large integers; key lengths of 2000-3000 bits are currently recommended
- **Elliptic Curve Cryptography (ECC)** offers comparable security with much shorter keys (hundreds of bits), making it well-suited for constrained devices like smart cards.
- **AES** is the standard symmetric cipher, with 128-bit keys being common; longer keys (192/256 bits) provide additional security margins.
- **SHA-2** and **SHA-3** are the current standard hash functions, with SHA-256 being widely used for integrity checks and digital signatures.
- **Key management**: is critical: symmetric systems require secure out-of-band key distribution, while asymmetric systems demand trusted channels or certificates to ensure public-key authenticity.

## Typical exam questions
You should be ready to:
1. **Explain** how hash functions, symmetric and asymmetric encryption each satisfy the CIA goals and support non-repudiation.
2. **Outline** the two-step digital-signature workflow (digest + sign) and give two real-world examples.
3. **Compare** RSA and ECC in terms of security basis, key sizes, and performance.
4. **Describe** why hybrid encryption is used and how Diffie-Hellman establishes session keys.