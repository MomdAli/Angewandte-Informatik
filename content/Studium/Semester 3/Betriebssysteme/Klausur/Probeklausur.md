---
title: 🖥️ Probeklausur
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2025-01-25
---
## Linear Page Table 

Gegeben:
- Address space size: 32m
- Physical memory size: 512m
- Page size: 64k


Page Table: <br>\[90\]  0x80000661<br>\[91\]  0x00000000<br>\[92\]  0x80001ad3<br>\[93\]  0x8000056b<br>\[94\]  0x80001eed

<font color="#FFC2CA">VA 0x005c5bff (decimal:  6052863) --> PA or invalid address?</font>

### Lösung:

Page Size: 64k = $2^{16}$

In Taschenrechner 6052863 $\div R\ 2^{16}$ Eingeben.\
Ergebnis: 
$$
\color{#ef9a79} 92;R=23551
$$

- 92 ist die Page Number
- 23551 ist der Offset

Page Table: \[92\] 0x80001ad3 $\rightarrow$ 1ad3 in Dezimal umwandeln: 6867. 0x8 bedeutet, dass die Seite im RAM liegt.
Also ist die virtuelle Adresse gültig und wir können die physische Adresse berechnen:
$$
\color{#ef9a79} Physical\ Address = 6867 \cdot 2^{16} + 23551 = 450\ 059\ 263
$$

## Multilevel Page Table

![[Multilevel-Page-Table.png]]

$$
\color{#5cf19e}
\begin{align*}

\text{PTEAddr} &= \text{PDE.PFN} << SHIFT + \text{(PTIndex} \cdot \text{sizeof(PTE))}\ \text{or} \\\\
\text{PTEAddr} &= \text{PTBR} + \text{VPN} \cdot \text{PTESize} \\

\end{align*}
$$

