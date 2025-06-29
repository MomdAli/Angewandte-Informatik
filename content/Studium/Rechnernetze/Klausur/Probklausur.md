---
title: Probklausur
tags:
  - Rechnernetze
  - Probeklausur
  - Semester-5
  - Informatik
date: 2025-06-27
---
## Paketübertragung
Gegeben sei die in Abbildung 1 dargestellte Übertragungsstrecke von einer Quelle Q zu einem
Ziel Z, die über drei Router $R_1$, $R_2$  und $R_3$ verläuft. Die Link-Kapazitäten sowie die
Ausbreitungsverzögerungen der vier Links sind in der Abbildung angegeben. Ebenso können
Sie der Grafik die Größe der Output-Buffer für alle Links entnehmen. Jedes Paket enthält 720
Bytes.
<u>Hinweis</u>: Geben Sie alle zeitlichen Ergebnisse in Millisekunden an.![[Abbildung 1.png]]

1) Bestimmen Sie die Ende-zu-Ende Übertragungsdauer für ein Paket.
   <u>Hinweis</u>: Die Übertragungsverzögerung beträgt 2 ms für einen 2,88 Mbps Link.

1 Paket = 720 Bytes = 5760 Bits
Rechnung: 
$$
t = \frac{\text{5760 Bits}}{\text{Bandbreite in }bps} 
$$

| Link     | Bandbreite (Mbps) | Verzögerung (ms) | Übertragungsdauer t (ms) |
| -------- | :---------------: | :--------------: | :----------------------: |
| Q -> R1  |       5,76        |       1,1        |            1             |
| R1 -> R2 |       1,92        |       1,5        |            3             |
| R2 -> R3 |       2,88        |       0,2        |            2             |
| R3 -> Z  |       1,92        |       0,2        |            3             |
$$
\large
\begin{align*}
t_{delay} &= 1,1 + 1,5 + 0,2 + 0,2 &= 3\ ms \\
t_{transmission} &= 1 + 3 + 2 + 3 &= 9\ ms \\
t_{total} &= 3 + 9 &= 12 ms
\end{align*}
$$

2) Die Quelle versendet Pakete mit einem Abstand von 0,75 ms.
	a) Bestimmen Sie für jeden Link den prozentualen Anteil der Pakete, die langfristig verloren gehen.
$$
\large\color{Bittersweet}
\begin{align*}
t_{x} &> 0,75\ ms \rightarrow Verlust\\
Formel &= \frac{}{}
\end{align*}
$$

|     |     |
| --- | --- |
|     |     |


b) Bestimmen Sie das erste Paket, das nicht empfangen wird, wenn die Quelle die Übertragung mit Paket $P_1$ beginnt.
c) Bestimmen Sie, wie viele Pakete verloren gehen, wenn die Quelle 70 Pakete überträgt.