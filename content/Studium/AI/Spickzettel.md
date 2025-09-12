---
{"publish":true,"draft":true,"created":"2025-07-13T12:29:58.282+02:00","modified":"2025-09-12T21:25:01.258+02:00","published":"2025-09-12T21:25:01.258+02:00","tags":["excalidraw","Informatik"],"cssclasses":""}
---

==⚠  Switch to EXCALIDRAW VIEW in the MORE OPTIONS menu of this document. ⚠== You can decompress Drawing data with the command palette: 'Decompress current Excalidraw file'. For more info check in plugin settings under 'Saving'


# Excalidraw Data

## Text Elements
Suchstrategien ^5IeEbsoB

0 ^omMyAilS

1 ^H0aXVHuK

2 ^2TI3OcTo

3 ^BDOkqzOr

4 ^LriLKd9u

5 ^x0dzzH3z

6 ^G8BTwd7a

Layer 0 ^ZxhSPFTh

Layer 1 ^EHCiKgIS

Layer 2 ^5Wc5EDCt

Output: 0, 1, 2, 3, 4, 5, 6
findet die kürzeste Lösung, benötigt jedoch viel Speicher ^11rxCYd8

0 ^iuDosUSy

1 ^xbQagH2F

3 ^rJgqOba6

4 ^igB8QLiv

2 ^KVEItT9y

5 ^8OlJYalK

6 ^7MCRvzct

Layer 0 ^GIKFcKmW

Layer 1 ^sZvaewgW

Layer 2 ^N2UevXAF

Output: 0, 1, 3, 4, 2, 5, 6
findet nicht zwingend kürzeste Lösungen ^Dxqz0Cvm

Depth-First Search (DFS) ^yOVEgFxt

Breadth-First Search (BFS) ^yEY2ynan

0 ^XaH30yJA

1 ^SXjmflEv

2 ^Y01UvJfp

3 ^RQqdtocl

4 ^Fm6CV1hO

5 ^fHiNQyWE

6 ^NHbfKRDR

7 ^bBnWimun

8 ^hSzPxDwF

9 ^okkMq49L

10 ^AbHYjVor

11 ^AYLVe9j9

12 ^XOXBinAe

13 ^lZD8M4yz

14 ^qKhJ0Czz

Depth ^q3uX9vVV

0    1st Iteration: (0)

1     2nd Iteration: (0, 1, 2, 3)

2    3rd Iteration: (0, 1, 4, 5, 2, 6, 3, 7, 8)

3    4th Iteration: (0, 1, 4, 9, 10, 5, 2, 6, 11, 
                 3, 7, 12, 8, 13, 14) ^ER0u3873

Iterative Deepening Depth-First Search (IDDFS)  ^nxzwkGkw

=> Kombiniert Vorteile von BFS (vollständig, optimal bei gleichen Kosten) und DFS (geringer Speicher). ^QyIFginq

A*-Suche (Heuristic) ^nNNtsXh4

d ^zc38JwJQ

a ^sLe39H5z

b ^RHBXJsBJ

c ^v8mtwDTu

e ^K6zfzsuM

4 ^JW84eDGa

3 ^17dY8WKi

2 ^suGxpNSe

1.5 ^5KOTdWbl

2 ^X5yaljN6

3 ^8NHngHTT

2 ^kPhiE1LA

h(c)=4 ^HfMkJRjU

h(b)=2 ^Dy9a5lZQ

h(a)=4 ^Q9CxWFvc

h(d)=4.5 ^Ubt8bt8m

h(e)=2 ^aWXDIw6V

S ^AbWX6NaQ

1 ^UK4w6uJL

1 ^MEIWAlqW

2 ^1P7pnoMJ

2 ^3wpPbiRk

3 ^GyThlb52

3 ^jJkZBXJv

4 ^9hf63sLp

4 ^ShOTrnlV

G2 ^tMPZPwx7

G1 ^7G60kqbu

5 ^JtBfBjsX

5 ^uJyBSk9a

A ^Be6Szpmr

D ^K0OfrjED

C ^6eqZi1u9

B ^P6ErWAtg

E ^Dr9Nz2uJ

Variables: X = {A, B, C, D, E}
Domains: D = {Red, Green, Blue}
Constraint: benachbarte Länder sollen unterschiedliche Farben haben: ^FNUIcLVj

Heuristics of Backtracking Search ^LFLVhRND

Most Constrained Variable (MRV): Wähle die Variable, die nur noch wenige mögliche Werte hat, um schneller Sackgassen zu finden.

Most Constraining Variable (MCV): Wähle die Variable, die am meisten andere Variablen einschränkt, um den Suchraum schnell zu verkleinern.

Least-Constraining Value (LCV): Nimm für eine Variable den Wert, der für die restlichen Variablen am wenigsten Möglichkeiten wegnimmt.

Forward Checking: Wenn du einer Variable einen Wert gibst, streichst du sofort alle unpassenden Werte aus den Nachbar-Variablen.

Arc Consistency: Prüfe für jedes Wertepaar zweier verbundener Variablen, ob zu jedem Wert mindestens ein passender Gegenwert existiert.
 ^lYG4Tr4u

Reihenfolge der Variablen ^NrnQ6FZy

Reihenfolge der Werte ^QQnoMVmX

Inference, entweder:
1. Weglassen,
2. Forward-Checking oder
3. AC-3-Algorithmus ^yjdODH5A

refutation by resolution: ^eIS1hgLF

Beispiel: ^WxbKaIcv

-5   -4    -3   -2   -1   0      1    2     3    4    5 ^79VhZPZE

5

4

3

2

1   

0


-1

-2

-3

-4

-5 ^P0y1X73o

x ^7tDEC01j

y ^aaaIpad3

In Taschenrechner:
Menu -> Statistik -> y=a+bx -> punkte eingeben
-> OPTN -> Regression ^h9Qz5cX8

Overfitting vermeiden:
- Regularisierung (Ridge-Regression oder Lasso)
- Modellkomplexität: auf ein Polynom niedrigerer Ordnung zurückgehen
- Datenvermehrung: Mehr Daten

Underfitting vermeiden: 
- Modelkomplexität erhöhen: wähle einen höheren Polynomgrad
- Reduzierte Regularisierung
- Mehr Trainingsdaten
- Längeres Training ^WaU248u4

Lineare und logistische Regression ^lKK7WXoG

(1) Most constrained variable first,
(2) Most contrainingvariable first (nur falls (1) mehrere Möglichkeiten zulässt),
(3) Least constraining value first,
(4) Forward-Checking. ^jr2asqmZ

AC-3-Algorithmus, wobei B die Farbe Blau und E die Farbe Rot hat. ^GXJua67F

Perzeptron ^Z0mh5weF

Step function

Sigmoid

Tanh

ReLU ^HOt9VL3g

Mittlerer quadratischer Fehler: ^GhDeMozc

Gradientenabstieg ^gKrrrflr

Standard-Kreuzentropie-Verlustfunktion (cross entropy) ^MfZ0461i

Faltung (Convolution): ^UY2Sw4bH

Faltung ^0SNeiTC0

Kernel ^lve9nPxV

Convolutional Neural Networks (Faltungsnetze) ^5teplYpc

Flattened ^NM0jHfCW

Conv_1 ^4H4zX8Iz

Max_pooling ^al4uI13F

Conv_2 ^a0fYIrsc

Max_pooling ^kN61CxJn

Flentten ^qyW9XBqx

Dense ^8sOdbRuh

X ^T2ptPaGA

W ^35JAPcLe

Q ^6Xj311eW

Q ^EYtGjbE7

X ^D0uR3SYJ

W ^5tIp9zWX

K ^n7VCWrbI

K ^rzaUtzi3

X ^Jzt8n1gF

W ^FNwHnkW0

V ^siqJXeZU

V ^OOPA7RuQ

A ^UZnar7Ao

B ^UtQz40DM

C ^V3kLd7Ts

D ^1nhzRI8X

G ^35tQHZDR

F ^1hJA6msO

E ^Qeuap7FO

A ^06mFJlpt

B ^mbuJsMqj

C ^5ISRCX1x

D ^pwCKC6j1

E ^8atTqLxP

F ^IFGXEYZv

G ^oE5Fjsxa

A ^En4BQFM8

B ^JGHFnzVo

C ^s8hBZuSB

D ^7lwryrsU

E ^vJRYjdXl

F ^2dDwdARG

G ^L0oJV6ol

Parametern berechnen: ^nXNXlh6y

Voll verbunden (Dense): ^rKIqOBCb

Faltung: ^yMYMKUQM

Batch-Normalisierung - wie? ^71FS5S70

Warum? ^Mn96zWG5

Visualisierung ^HWHhS0w1

Class Activation Mapping (CAM) ^hlBpZbhd

CAM erzeugt eine Heatmap, die zeigt welche Regionen im Eingangsbild für ^A0zsM3LZ

eine bestimmte Klasse entscheidend sind. Man führt dazu nach der letzten ^KRyskmxF

Faltungsschicht globales Average Pooling durch und ^FnPWLJCi

Feature Inversion ^0LCeXORr

- Aktivierungen einer Filter Karte zurück in den Pixelraum projizieren ^c12s7bkr

- Zeigt, welche Bild-Patches (z.b. Kanten, Formen) den Filter reizen ^16ieuK6s

Wie funktioniert Dropout und warum setzt man diese Traininghsmethode ein?

Dropout ist eine Regularisierungstechnik, die während des Trainings eines neuronalen Netzwerks verwendet wird. Dabei werden zufällig ausgewählte Neuronen in jeder Trainingsiteration deaktiviert (auf 0 gesetzt) ^Qe1Ban2e

