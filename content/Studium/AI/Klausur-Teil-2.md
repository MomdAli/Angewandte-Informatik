---
title: Klausur-Teil-2
tags:
  - AI
  - Semester-5
  - Informatik
date: 2025-07-10
publish: true
---
## Aufgabe 1-1
$$
\Large
\hat{y}=2x+0.9
$$

## Aufgabe 1-2

- Weniger Grad Bsp. Grad 3
- Mehr Daten
- Regularisierung

## Aufgabe 1-3

![[SS-Aufgabe-1-3.svg]]

## Aufgabe 1-4
$$
\Large
\begin{align*}
\sigma(z) &= \frac{1}{1+e^{-z}}\\
\sigma(W \cdot x + b) &= \frac{1}{1+e^{-(W \cdot x + b)}}\\
f(x)&=\frac{1}{1+e^{-(3x-8)}}\\\\
f(3.5)&=0.924
\end{align*}
$$

## Aufgabe 1-5

$$
\Large
-\sum\limits^{n}_{i}(y_i^{true}\cdot\log(y_i^{pred}))
$$
$$
\Large
\begin{align*}
i) -1 \cdot\log(0.1) &= 2.302\\
ii) -1 \cdot\log(0.12) &= 2.120
\end{align*}
$$
$2.302 > 2.120$, also i) hat geringere Verlust, weil


## Aufgabe 2-1
$$
\large
\begin{align*}
p &= 784\\
q &= 100\\
\end{align*}
$$
Gewichte: Jedes der q Ausgangsneuronen hat Verbindungen zu allen p Eingangsneuronen.
$$
\Large
\Rightarrow p \times q = 784 \times 100 = 78400\ \text{Gewichtswerte}
$$
- Bias-Terme: Zusätzlich zu den Gewichten gibt es für jedes Ausgangsneuron einen Bias-Term.
$$
\Large b = 100\ \text{Bias-Terme}
$$

**Insgesamt**:
$$
\Large
78\ 400 \text{ Gewichte} + 100 \text{ Bias-Terme} = \color{#008600} \boxed{78\ 500 \text{ Parameter}}
$$

## Aufgabe 2-2

$$
\large
(3 \times 3 \text{ weights}+ 1 \text{ bias}) \times 10 \text{ filters} = \boxed{100 \text{ parameters}}
$$

## Aufgabe 2-3
Die Faltungsnetzwerkvariante ist vorteilhafter, da sie weniger Parameter hat und damit weniger Rechenleistung benötigt. Zudem nutzt sie die räumliche Struktur der Daten aus, was zu einer besseren Generalisierung führt.

## Aufgabe 2-4
1. **Standardisieren** jedes Mini-Batch–Inputs \(x\) auf Mittelwert 0 und Varianz 1: 
$$
\large
x' = \frac{x - \mu_{\rm batch}}{\sqrt{\sigma_{\rm batch}^2 + \varepsilon}}
$$
2. **Skalieren und Verschieben** mit lernbaren Parametern $\alpha,\beta$:
$$
\large
BN(x') = \alpha\,x' + \beta
$$

**Effekt:**
- Verschiebt die Aktivierungen in den „guten Arbeitsbereich“ der Nichtlinearität,
- Beschleunigt das Training und erlaubt höhere Lernraten,
- Verringert interne Kovariatenverschiebung (Internal Covariate Shift).

## Aufgabe 2-5
1. **Feature Visualisierung**: Diese Methode visualisiert die Aktivierungen der Neuronen in den verschiedenen Schichten des Netzwerks.
2. **Class Activation Maps (CAM)**: Diese Methode visualisiert die Regionen im Eingabebild, die für eine bestimmte Klassifikation wichtig sind. Sie wird verwendet, um zu verstehen, welche Teile des Bildes das Netzwerk für die Klassifikation berücksichtigt.
## Aufgabe 2-6
Wie funktioniert Dropout und warum setzt man diese Traininghsmethode ein?

Dropout ist eine Regularisierungstechnik, die während des Trainings eines neuronalen Netzwerks verwendet wird. Dabei werden zufällig ausgewählte Neuronen in jeder Trainingsiteration deaktiviert (auf 0 gesetzt). Dies verhindert, dass das Netzwerk zu stark auf bestimmte Neuronen angewiesen ist und fördert die Robustheit des Modells. Es hilft, Überanpassung (Overfitting) zu vermeiden, indem es das Netzwerk zwingt, redundante Repräsentationen zu lernen.



