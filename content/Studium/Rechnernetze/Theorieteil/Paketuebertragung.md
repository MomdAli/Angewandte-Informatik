---
{"publish":true,"title":"Paketübertragung","created":"2025-04-15","modified":"2025-09-12T21:25:02.748+02:00","published":"2025-04-15","tags":["Rechnernetze","Theorieteil","Semester-5","Informatik"],"cssclasses":""}
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
![[Studium/Rechnernetze/Bilder/Uebertragungsstrecke.png]]
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

<svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1095.7106994847904 295.6940285664174" width="1095.7106994847904" height="295.6940285664174" filter="invert(93%) hue-rotate(180deg)" class="excalidraw-svg"><!-- svg-source:excalidraw --><metadata><!-- payload-type:application/vnd.excalidraw+json --><!-- payload-version:2 --><!-- payload-start -->eyJ2ZXJzaW9uIjoiMSIsImVuY29kaW5nIjoiYnN0cmluZyIsImNvbXByZXNzZWQiOnRydWUsImVuY29kZWQiOiJ4nO2Z3XPaOFx1MDAxMMDf+1cw3Gtx9f3RtzSFS64hvSElXHUwMDFmvblcdTAwMDeDXHUwMDE1cHBsapuQkOn/fiuTYIMhXHUwMDE333im7Vx1MDAxY86EXHUwMDE5pJW0Xv12tStcdTAwMWXfNFx1MDAxYc30YWqa71x1MDAxYk1zP3RcdTAwMDPfi915861tvzNx4kchdJHse1x1MDAxMs3iYSbpTqfv372LXHUwMDA2ie/5bujcestcdTAwMDEmMLcmTFx1MDAxM1x1MDAxMPlcdTAwMGK+N1x1MDAxYY/ZJ/T4nlx1MDAxZNZ1O3jWYZdHfCqu5kLpXHUwMDAzt/stXHUwMDFimlx0PetcdTAwMTGbYeqGo8DkXfdWXHQsXHUwMDFkpLVEXG4tXHUwMDFmsup+gG7MqYM1x1pzyrDUQqy6576XjkGEcuVcdTAwMTCtYFx1MDAxMo0wk3IlMDb+aJyChORcdTAwMGV0asUkw1x1MDAwNCPNVlwiS4XeN9CqJUnjaGJcdTAwMGWjIIqt1r9hY/9ynVx1MDAwN+5wMoqjWejlMuRaXHUwMDFixnKZaz9cYs7Sh2xmsC9Yv7kx/8WT7mSjfdcoWHA0XHUwMDBlTZKs6Vx1MDAxYU3doZ9mZkJ5q9Vueuxl+/V3rlPs3ppju2HhLFxiVs1+6Jn7bPPR2mqh97TamnRijJetXHUwMDA2ViRcdTAwMTKMverJqdKMb7aeRmFGmCBcdTAwMWFTKjDOl08+XHUwMDAyXmk267VcdTAwMWIkJrez1aFdQC9/ldnUc5dDsGRMYiGQJCQ3ZeCHk03Ng2g4yVfJWr+/3UYzaX1id312cn58c3RPXHUwMDA30fhU928q0IxcdTAwMWRtIWZEMyWJ4us0M53RrFx1MDAxOJiCgqVoiWaMgFaJkdBcdTAwMWFjQUC6zLNijvVcdTAwMTTMXHRXUmlE6+U5jd0wmboxWH5cdTAwMDfTY3c4nsXmXHUwMDE3oFx1MDAxYb+eaqYll4yjbVCLgiZcdTAwMWJQY4rAXHUwMDE5XGLXsl6qOZeKKlRcdTAwMGbV/MvJaZ/2aVx1MDAxOeTU3KdcdTAwMWJcZlx1MDAxM+JcdTAwMDB9QkFg5ZrKXHKE0TPCSlx1MDAwYqZcdTAwMTjVJYRcdGVcdTAwMGUoj8lcdTAwMTZ0qXBsoMeKYsSZUKpcdTAwMDK6y/Phf4MuqVx1MDAxMJApg/NccrNCJMjZpVx1MDAwNO9kV0GYgiBFaL3sXG6BXHUwMDE5XHUwMDFjtVx1MDAxNdjNubQ8wuufwJBGXHUwMDBmN1x1MDAxNrNGr2hcdHf+5WWJ6yhMz/yFfT2KXHUwMDFjXHUwMDAxj8KacFx1MDAwNv5E5ZpUx731XHUwMDAzu1l0bfWDwFx1MDAxZlm7NVx1MDAwM3NdoFxiTJf6kEqtutNomvdcdTAwMGVhPtdcdTAwMGZNXFze0Sj2R37oXHUwMDA2/6K3O0ujnkmWmqfxzFx1MDAxNK1njp5cdTAwMWRcYjvkXHUwMDA1P1x1MDAxN3xBP1x1MDAxZPKrz61cdTAwMGLSompcdTAwMWNcdTAwMWO2rvqvPr005Vx1MDAwZeKc8pdTMfV0eJUzMSZcdTAwMWPBYestjfbZp2L/yfNZXHUwMDA1z1eSI4JEOemynVx1MDAxMGN3XHUwMDFmW4xcdTAwMTBcdIlcXO2uX2MyNlx1MDAxODEyQl0hppetXHUwMDAzPbyco9mk92qc4VxmcoQsev16RYGQovBPylx1MDAxY1tcdTAwMTGOhFx1MDAxMkxSyOXQloNsXHUwMDBm8itA5q9cdTAwMDeZXHUwMDEzLaSStHRWWU1cYma7OIZEhVwiqTH/iTHm3sDzW4eLfnvWXHUwMDFiLII/2sNcdTAwMTFfVK8pqCbwrliv81xmXGI7SmlcYstcdTAwMDQxjIlkJZ73NUWtVH94PdU6MznX26Kz4DuhhryMM1xyWXLNNVx1MDAwNdVcdTAwMTDxVU1UL3xqvoYxK4P8Uk1BsKSicItj94SIZ4RBR4JI+Y5nX1LUQu5hhcRcdTAwMDLwg1ROXHUwMDE0krhCSSFLrSt0hYBChFFSe0CuqaQgWeJdqIRLJUVJ4lx1MDAxNykpSnrXUlKE5KT7efD1a/e8K/XF/bxzgY9cdTAwMWWqX+9KwbMrhY2zi1nHh+OdSMm3uD3hjqJcdTAwMTRSXFyQxITlXHUwMDAxs5SHXHUwMDExOK5cdTAwMTlDhVRtn4et/P5jlbtdSmA3YLe2+D3hdGdBQWCPOMeC/8xcdTAwMDXFpHN+dvrt/PL2pi06kTq5OzrrdapcdTAwMTdcdTAwMTRwRFHJtNrD/Fx1MDAwM2BuV0i/NIKEgGy70Vx1MDAwNVx1MDAxM79wpYtcdTAwMTGWWrOa069aUVx1MDAxNvQm7KHhNFwiycf+cbvlzf6czCtf9Vx1MDAwMMRQdmFB9yj/XHUwMDAwlH+vctGDqGY26myDWVx1MDAxNWLRXHUwMDA2zFx1MDAxNFx1MDAxMalg8E/CMnxmk9pfm89SmFx1MDAxMrqXZINcdTAwMTF97ynNevbNZVtqpjb6XHUwMDE2mrqRZ9qhO1xiNl+ieeeb+YctvFxcZ0/zzZM7WV6MfcXH72++/1x1MDAwM1x1MDAwMP+0fSJ9<!-- payload-end --></metadata><defs><style class="style-fonts">      @font-face { font-family: Cascadia; src: url(data:font/woff2;base64,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); }</style></defs><g stroke-linecap="round" transform="translate(15.199684073698052 54.00001463769527) rotate(0 179.1490499507385 37.99999237060547)"><path d="M0 0 L358.3 0 L358.3 76 L0 76" stroke="none" stroke-width="0" fill="#2f9e44"></path><path d="M0 0 C113.43 0, 226.85 0, 358.3 0 M0 0 C123.25 0, 246.5 0, 358.3 0 M358.3 0 C358.3 27.7, 358.3 55.41, 358.3 76 M358.3 0 C358.3 18.2, 358.3 36.41, 358.3 76 M358.3 76 C218.92 76, 79.53 76, 0 76 M358.3 76 C251.62 76, 144.94 76, 0 76 M0 76 C0 57.21, 0 38.41, 0 0 M0 76 C0 52.21, 0 28.41, 0 0" stroke="#1e1e1e" stroke-width="2" fill="none"></path></g><g stroke-linecap="round" transform="translate(10.00000036857088 49.99999973291014) rotate(0 537.8553495581098 42.00000762939452)"><path d="M0 0 C311.51 0, 623.02 0, 1075.71 0 M0 0 C321.94 0, 643.88 0, 1075.71 0 M1075.71 0 C1075.71 26.72, 1075.71 53.44, 1075.71 84 M1075.71 0 C1075.71 17.22, 1075.71 34.44, 1075.71 84 M1075.71 84 C695.58 84, 315.44 84, 0 84 M1075.71 84 C738.01 84, 400.31 84, 0 84 M0 84 C0 57.41, 0 30.81, 0 0 M0 84 C0 62.32, 0 40.63, 0 0" stroke="#1e1e1e" stroke-width="2" fill="none"></path></g><g transform="translate(20.799659659635523 10) rotate(0 117.28906249999999 18.400009155273438)"><text x="0" y="29.031524861852333" font-family="Cascadia, Segoe UI Emoji" font-size="30.666681925455737px" fill="#000000" text-anchor="start" style="white-space: pre;" direction="ltr" dominant-baseline="alphabetic">Link R1 zu R2</text></g><g stroke-linecap="round" transform="translate(733.1553260736982 54.000013732910105) rotate(0 173.32572249999993 37.99999237060547)"><path d="M0 0 L346.65 0 L346.65 76 L0 76" stroke="none" stroke-width="0" fill="#2f9e44"></path><path d="M0 0 C110.52 0, 221.03 0, 346.65 0 M0 0 C103.09 0, 206.18 0, 346.65 0 M346.65 0 C346.65 17.43, 346.65 34.85, 346.65 76 M346.65 0 C346.65 26.13, 346.65 52.25, 346.65 76 M346.65 76 C276.65 76, 206.65 76, 0 76 M346.65 76 C242.24 76, 137.83 76, 0 76 M0 76 C0 48.42, 0 20.84, 0 0 M0 76 C0 49.85, 0 23.69, 0 0" stroke="#1e1e1e" stroke-width="2" fill="none"></path></g><g stroke-linecap="round" transform="translate(376.777346073698 54.00001411181643) rotate(0 176.57534323651203 37.99999237060547)"><path d="M0 0 L353.15 0 L353.15 76 L0 76" stroke="none" stroke-width="0" fill="#2f9e44"></path><path d="M0 0 C76.61 0, 153.21 0, 353.15 0 M0 0 C104 0, 208.01 0, 353.15 0 M353.15 0 C353.15 20.06, 353.15 40.11, 353.15 76 M353.15 0 C353.15 26.44, 353.15 52.88, 353.15 76 M353.15 76 C250.46 76, 147.78 76, 0 76 M353.15 76 C277.35 76, 201.56 76, 0 76 M0 76 C0 56.41, 0 36.83, 0 0 M0 76 C0 46.41, 0 16.83, 0 0" stroke="#1e1e1e" stroke-width="2" fill="none"></path></g><g stroke-linecap="round" transform="translate(10 201.69401330762835) rotate(0 537.8553495581098 42.00000762939453)"><path d="M0 0 C346.54 0, 693.08 0, 1075.71 0 M0 0 C321.6 0, 643.2 0, 1075.71 0 M1075.71 0 C1075.71 33.36, 1075.71 66.73, 1075.71 84 M1075.71 0 C1075.71 29.18, 1075.71 58.36, 1075.71 84 M1075.71 84 C855.14 84, 634.58 84, 0 84 M1075.71 84 C775.89 84, 476.07 84, 0 84 M0 84 C0 51.39, 0 18.77, 0 0 M0 84 C0 66.1, 0 48.21, 0 0" stroke="#1e1e1e" stroke-width="2" fill="none"></path></g><g transform="translate(20.799659291064643 161.69401357471818) rotate(0 117.2890625 18.400009155273438)"><text x="0" y="29.031524861852333" font-family="Cascadia, Segoe UI Emoji" font-size="30.666681925455737px" fill="#000000" text-anchor="start" style="white-space: pre;" direction="ltr" dominant-baseline="alphabetic">Link R2 zu R3</text></g><g stroke-linecap="round" transform="translate(15.199683733381534 205.69402938107558) rotate(0 112.91687515206219 37.999991361220026)"><path d="M0 0 L225.83 0 L225.83 76 L0 76" stroke="none" stroke-width="0" fill="#2f9e44"></path><path d="M0 0 C83.24 0, 166.47 0, 225.83 0 M0 0 C69.36 0, 138.72 0, 225.83 0 M225.83 0 C225.83 16.02, 225.83 32.04, 225.83 76 M225.83 0 C225.83 21.23, 225.83 42.46, 225.83 76 M225.83 76 C176.01 76, 126.18 76, 0 76 M225.83 76 C148.52 76, 71.2 76, 0 76 M0 76 C0 54.41, 0 32.82, 0 0 M0 76 C0 56.8, 0 37.61, 0 0" stroke="#1e1e1e" stroke-width="2" fill="none"></path></g><g stroke-linecap="round" transform="translate(376.77734633744785 205.69402938107558) rotate(0 112.91687515206223 37.999991361220026)"><path d="M0 0 L225.83 0 L225.83 76 L0 76" stroke="none" stroke-width="0" fill="#2f9e44"></path><path d="M0 0 C61.8 0, 123.6 0, 225.83 0 M0 0 C53.67 0, 107.33 0, 225.83 0 M225.83 0 C225.83 24.17, 225.83 48.34, 225.83 76 M225.83 0 C225.83 19.55, 225.83 39.09, 225.83 76 M225.83 76 C150.46 76, 75.09 76, 0 76 M225.83 76 C158.91 76, 91.99 76, 0 76 M0 76 C0 48.66, 0 21.32, 0 0 M0 76 C0 58.14, 0 40.28, 0 0" stroke="#1e1e1e" stroke-width="2" fill="none"></path></g><g stroke-linecap="round" transform="translate(733.1553259415143 205.69402938107558) rotate(0 112.91687515206223 37.999991361220026)"><path d="M0 0 L225.83 0 L225.83 76 L0 76" stroke="none" stroke-width="0" fill="#2f9e44"></path><path d="M0 0 C87.27 0, 174.54 0, 225.83 0 M0 0 C73.5 0, 147.01 0, 225.83 0 M225.83 0 C225.83 23.31, 225.83 46.63, 225.83 76 M225.83 0 C225.83 29.64, 225.83 59.27, 225.83 76 M225.83 76 C152.27 76, 78.7 76, 0 76 M225.83 76 C150.32 76, 74.8 76, 0 76 M0 76 C0 58.18, 0 40.35, 0 0 M0 76 C0 52.88, 0 29.76, 0 0" stroke="#1e1e1e" stroke-width="2" fill="none"></path></g></svg>

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