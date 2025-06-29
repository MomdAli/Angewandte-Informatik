---
title: Paketübertragung
tags:
  - Rechnernetze
  - Theorieteil
  - Semester-5
  - Informatik
date: 2025-04-15
---
### Was ist das?

**Ende-zu-Ende-Verzögerung** ist die gesamte Zeit, die ein einzelnes Datenpaket benötigt, um vom Sender zum Empfänger über mehrere Netzwerk-Abschnitte (Links) zu reisen.

Diese Verzögerung setzt sich aus **zwei Hauptteilen pro Link** zusammen:

### **Übertragungsverzögerung (Transmission Delay)**

 Zeit, um ein ganzes Paket „in den Draht zu schieben“ (also zu übertragen)
    $$ t_ü = \frac{\text{Paketgröße (in Bit)}}{\text{Übertragungsrate (in Bit/s)}} $$

---
### **Ausbreitungsverzögerung (Propagation Delay)**

 Zeit, die ein Bit braucht, um über die physikalische Strecke zu wandern  $$ t_a = \frac{\text{Strecke}}{\text{Ausbreitungsgeschwindigkeit}} $$

---

### **Ende-zu-Ende-Verzögerung (für mehrere Links)**

> Die Summe der Verzögerungen über alle Links hinweg:

$$ t_{\text{gesamt}} = \sum_{i=1}^{n} (t_{ü,i} + t_{a,i}) $$

Oder für genau 3 Links:

$$ t_{\text{gesamt}} = (t_{ü,1} + t_{a,1}) + (t_{ü,2} + t_{a,2}) + (t_{ü,3} + t_{a,3}) $$

---
### **Physikalische Länge eines Pakets im Kabel**

 Wie lang ein Paket „räumlich“ ist, während es über ein Medium läuft:
$$
\text{Länge}_{\text{phys}} = t_{ü} \cdot v
$$

- $t_{ü}$​ = Übertragungsverzögerung
- $v$ = Ausbreitungsgeschwindigkeit


---

### **Durchsatz (Throughput)**

Effektive Datenmenge pro Sekunde, oft durch den langsamsten Link begrenzt:

$Durchsatz=min⁡(Link1,Link2,…,Linkn)$


---

### Beispiel direkt aus der Aufgabe:

|Link|Übertragungsrate|Länge|Ausbreitungsgeschwindigkeit|
|---|---|---|---|
|1|60 Mbps|15 m|300 000 km/s|
|2|25 Mbps|250 m|200 000 km/s|
|3|20 Gbps|10 000 m|250 000 km/s|

**Paketgröße:** 1500 Byte = `1500 × 8 = 12 000 Bit`

---
#### Berechne die Verzögerungen:(1.1)
#### Link 1



$$ t_{ü1} = \frac{12\,000}{60\,000\,000} = 0{,}0002\,\text{s} = 0{,}2\,\text{ms} $$ $$ t_{a1} = \frac{15}{300\,000\,000} = 5 \cdot 10^{-8}\,\text{s} = 0{,}00005\,\text{ms} $$

---
#### Link 2


$$ t_{ü2} = \frac{12\,000}{25\,000\,000} = 0{,}00048\,\text{s} = 0{,}48\,\text{ms} $$ $$ t_{a2} = \frac{250}{200\,000\,000} = 1{,}25 \cdot 10^{-6}\,\text{s} = 0{,}00125\,\text{ms} $$
---
#### Link 3


$$ t_{ü3} = \frac{12\,000}{20\,000\,000\,000} = 6 \cdot 10^{-7}\,\text{s} = 0{,}0006\,\text{ms} $$ $$ t_{a3} = \frac{10\,000}{250\,000\,000} = 4 \cdot 10^{-5}\,\text{s} = 0{,}04\,\text{ms} $$

---
### Gesamte Ende-zu-Ende-Verzögerung(1.2)


$$ t_{\text{gesamt}} = (0{,}2 + 0{,}00005) + (0{,}48 + 0{,}00125) + (0{,}0006 + 0{,}04) = 0{,}7219\,\text{ms} $$

Die Ende-zu-Ende-Verzögerung beträgt **0,7219 ms**.

---
### Hängt das von der Reihenfolge der Links ab?(1.2)

Nein. Bei einem Paket ist die Reihenfolge nicht relevant. Bei mehreren Paketen ist es abhängig weil es definiert wo es anfängt sich zu stauen, aber bei  Zeit von Sender bis Empfänger reicht schon ein einziger Link der langsamer ist um die gesamte Übertragung zu verzögern

---
## **Paket-Burst & Reihenfolge**


### 20 Pakete direkt nacheinander (Packet Burst)

Frage: Wie lange dauert die Übertragung von **20 Paketen**, wenn du sie direkt hintereinander losschickst?

---

### Idee:

Nur das **erste Paket** muss die ganze Ende-zu-Ende-Verzögerung durchlaufen.  
Die **anderen 19** kommen **nach und nach** hinterher – mit einem Abstand entsprechend der **langsamsten Übertragungsverzögerung.**

---

### Relevant ist:

Welcher Link ist der langsamste beim „Reinschieben“?  
→ Das ist **Link 2: 25 Mbps**

$$ t_{ü,\text{max}} = \frac{12\,000}{25\,000\,000} = 0{,}00048 \, \text{s} = 0{,}48\,\text{ms} $$

---

### Gesamtzeit für 20 Pakete:

| Was?                                                    | Warum?                                                                      |
| ------------------------------------------------------- | --------------------------------------------------------------------------- |
| **tümax** = max. Übertragungsverzögerung auf einem Link | Weil der langsamste Link bestimmt, wie schnell du neue Pakete senden kannst |
| **Bitrate immer in Bit/s**                              | Mbps = "Mega _bit_ per second" = $×10^6$                                    |
| **Paketgröße in Bit umrechnen**                         | 1 Byte = 8 Bit                                                              |
| **Gesamtzeit bei Bursts:**                              | t-gesamt                                                                    |
$$ t_{\text{gesamt}} = t_{\text{e2e}} + (N - 1) \cdot t_{ü,\text{max}} $$ $$ t_{\text{gesamt}} = 0{,}7219 + 19 \cdot 0{,}48 = 0{,}7219 + 9{,}12 = 9{,}8419\,\text{ms} $$

**Antwort Aufgabe 1.3**:  
Gesamtübertragungsdauer für den Burst = **9,84 ms**

---

**Hängt das auch von der Reihenfolge der Links ab?**

> Ja, **noch stärker als bei einem einzelnen Paket.**  
> Denn: Der erste (langsamste) Link bestimmt, wie schnell du **weitere Pakete nachschieben kannst.**


---
![[Uebertragungsstrecke.png]]
Gegeben sei die in Abbildung 1 dargestellte Übertragungsstrecke von einer Quelle Q zu einem
Ziel Z, die über vier Router 𝑅1 bis 𝑅4 verläuft. Die Link-Kapazitäten sowie die Ausbreitungsver-
zögerungen der fünf Links sind in der Abbildung angegeben. Jedes Paket enthält 600 Bytes.
## Aufgabe 2.1)

Bestimmen Sie die Ende-zu-Ende Übertragungsdauer für ein Paket.
Hinweis: Die Übertragungsverzögerung beträgt 4,0 ms für einen 1,20 Mbps Link.

| Link    | Bandbreite | Verzögerung | Rechnung                  | Übertragungszeit |
| ------- | ---------- | ----------- | ------------------------- | ---------------- |
| Q → R1  | 1,20 Mbps  | 4,0 ms      | 4800 Bits / 1.200.000 bps | **4,0 ms**       |
| R1 → R2 | 0,20 Mbps  | 72,0 ms     | 4800 Bits / 200.000 bps   | **24,0 ms**      |
| R2 → R3 | 0,30 Mbps  | 72,0 ms     | 4800 Bits / 300.000 bps   | **16,0 ms**      |
| R3 → R4 | 0,15 Mbps  | 5,0 ms      | 4800 Bits / 150.000 bps   | **32,0 ms**      |
| R4 → Z  | 0,05 Mbps  | 5,0 ms      | 4800 Bits / 50.000 bps    | **96,0 ms**      |

$$
\large\color{#a3be8c}
T_{total} = T_{trans} + T_{prop} = 172 ms + 158 ms = 330 ms
$$

## Aufgabe 2.2)

Die Quelle versendet Pakete mit einem Abstand von 8,00 ms.
a) Bestimmen Sie für jeden Link den prozentualen Anteil der ankommenden Pakete, die langfristig
verloren gehen.

$$
\large\color{#a3be8c}
t_{tx} > 8,0 ms → Verlust
$$

| Link    | Bandbreite | tₜₓ (ms) | Überlast? | Formel                    | Paketverlust (%) |
| ------- | ---------- | -------- | --------- | ------------------------- | ---------------- |
| Q → R1  | 1,20 Mbps  | 4,0      | Nein      | –                         | **0 %**          |
| R1 → R2 | 0,20 Mbps  | 24,0     | Ja        | $1-\frac{8}{24}=0.667$    | **66,67 %**      |
| R2 → R3 | 0,30 Mbps  | 16,0     | Nein      | –                         | **0 %**          |
| R3 → R4 | 0,15 Mbps  | 32,0     | Ja        | $1-\frac{24}{32} = 0.25$  | **25,00 %**      |
| R4 → Z  | 0,05 Mbps  | 96,0     | Ja        | $1-\frac{32}{96} = 0.667$ | **66,67 %**      |

b) Bestimmen Sie die physikalische Länge und die Anzahl gleichzeitiger Pakete für den Link zwischen R1 und R2, wenn die Ausbreitungsgeschwindigkeit 200000km/s beträgt.

$$
\large\color{#a3be8c}
\begin{align*}
L_{phys} = 200 \frac{km}{ms} * 72\ ms &= 14400\ km\\
N_{Pakete} = \frac{72\ ms}{24\ ms} &= 3\ Pakete
\end{align*}
$$
## Aufgabe 2.3)

Skizzieren Sie die physikalische Ausdehnung der Pakete (physikalische Länge und Abstand) für die
Links zwischen R1 und R2 sowie R2 und R3. Die Linie entspricht der physikalischen Länge der Links.

![[Skizze-1.svg]]

## Aufgabe 2.4)

Die Quelle Q versendet Pakete zu den folgenden Zeitpunkten: 0,0 ms(900,0 B); 2,0 ms(600,0 B); 3,0
ms(900,0 B); 4,0 ms(900,0 B); 5,0 ms(900,0 B); 7,0 ms(1200,0 B); 9,0 ms(900,0 B); 24,0 ms(1200,0 B);
26,0 ms(1200,0 B); 27,0 ms(1200,0 B)

Bestimmen Sie mit Hilfe einer Ereignistabelle, welche Pakete zwischen Quelle Q und Router R1 verloren gehen, wenn der Aufgangs-Puffer von Q zu R1 3 Pakete aufnehmen kann.


| Größe \[B\]                    | 600 | 900 | 1200 |
| ------------------------------ | --- | --- | ---- |
| Übertragungsverzögerung \[ms\] | 4   | 6   | 8    |

| Pakete      | 1   | 2   | 3   | 4   | 5   | 6    | 7   | 8    | 9    | 10   |
| ----------- | --- | --- | --- | --- | --- | ---- | --- | ---- | ---- | ---- |
| Größe \[B\] | 900 | 600 | 900 | 900 | 900 | 1200 | 900 | 1200 | 1200 | 1200 |

| Zeit (ms) | Paket # | Ereignis                        | Puffer vorher | Aktion                  | Puffer nachher | Verloren? |
| --------- | ------- | ------------------------------- | ------------- | ----------------------- | -------------- | --------- |
| 0.0       | 1       | Paket 1 kommt an                | `[]`          | direkt senden           | `[]`           | Nein      |
| 2.0       | 2       | Paket 2 kommt an                | `[]`          | kommt in Puffer         | `[2]`          | Nein      |
| 3.0       | 3       | Paket 3 kommt an                | `[2]`         | kommt in Puffer         | `[2, 3]`       | Nein      |
| 4.0       | 4       | Paket 4 kommt an                | `[2, 3]`      | kommt in Puffer         | `[2, 3, 4]`    | Nein      |
| 5.0       | 5       | Paket 5 kommt an                | `[2, 3, 4]`   | **kein Platz → DROP**   | `[2, 3, 4]`    | **Ja**    |
| 6.0       | —       | Paket 1 fertig, Paket 2 startet | `[2, 3, 4]`   | Paket 2 raus aus Puffer | `[3, 4]`       | —         |
| 7.0       | 6       | Paket 6 kommt an                | `[3, 4]`      | kommt in Puffer         | `[3, 4, 6]`    | Nein      |
| 10.0      | —       | Paket 2 fertig, Paket 3 startet | `[3, 4, 6]`   | Paket 3 raus aus Puffer | `[4, 6]`       | —         |
| 11.0      | 7       | Paket 7 kommt an                | `[4, 6]`      | kommt in Puffer         | `[4, 6, 7]`    | Nein      |
| 16.0      | —       | Paket 3 fertig, Paket 4 startet | `[4, 6, 7]`   | Paket 4 raus aus Puffer | `[6, 7]`       | —         |
| 22.0      | —       | Paket 4 ferig, Paket 6 startet  | `[6, 7]`      | Paket 6 raus aus Puffer | `[7]`          | —         |
| 24.0      | 8       | Paket 8 kommt an                | `[7]`         | kommt in Puffer         | `[7, 8]`       | Nein      |
| 26.0      | 9       | Paket 9 kommt an                | `[7, 8]`      | kommt in Puffer         | `[7, 8, 9]`    | Nein      |
| 27.0      | 10      | Paket 10 kommt an               | `[7, 8, 9]`   | **kein Platz → DROP**   | `[7, 8, 9]`    | **Ja**    |
| 30.0      | —       | Paket 6 fertig, Paket 7 startet | `[7, 8, 9]`   | Paket 7 raus aus Puffer | `[8, 9]`       | —         |
| 36.0      | —       | Paket 7 fertig, Paket 8 startet | `[8, 9]`      | Paket 8 raus aus Puffer | `[9]`          | —         |
| 44.0      | —       | Paket 8 fertig, Paket 9 startet | `[9]`         | Paket 9 raus aus Puffer | `[]`           | —         |
| 52.0      | —       | Paket 9 fertig                  | `[]`          | —                       | `[]`           | —         |

**Verloren gehen Paket 5 und Paket 10.**