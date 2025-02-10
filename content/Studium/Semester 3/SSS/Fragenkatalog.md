---
title: Fragenkatalog
tags:
  - SSS
  - Semester-3
  - Informatik
date: 2025-02-09
---
> [!question]- Was ist der Unterschied zwischen einem Sensor und einem Messgerät?
> Messgeräte vergleichen zusätzlich den Ausgangswert des Sensors mit einer Bezugsgröße.

> [!question]- Was ist der systematische Fehler?
> $$
> \Large
> Es = \bar{x} - x_w
> $$

> [!question]- Wie wird ein Messergebnis korrekt angegeben, wenn 15 Einzelmessungen vorliegen?
> $$
> \Large
> x_w=\bar{x}+t * s\bar{x}
> $$

> [!question]- Wie schätzt man den Messfehler und den wahren Wert der Messgröße?
> $$
>\Large
>\begin{align*}
>\bar{x}&=\frac{1}{n}\sum\limits_{i=1}^n{x_i}\\
>s\bar{x}&=\sqrt{\frac{1}{n-1}\sum\limits_{i=1}^n{(\bar{x}-x_i)^2}}=\frac{2}{\sqrt{n}}\\
>x_w&=\bar{x}\pm s\bar{x}
>\end{align*}
>$$

> [!question]- Wie schätzt man den wahren Wert einer Messgröße, wenn mehrere fehlerbehaftete Messungen vorliegen?
> Arithmetisches Mittel aus den Einzelmesswerten

> [!question]- Sie haben 20 Einzelmessungen mit einer Standardabweichung des Mittelwertes von s. Wie groß ist das Vertrauensintervall, in das der wahre Wert der Messgröße mit einer Wahrscheinlichkeit von 95,5% fällt?
> $\pm 2,09 * \text{Standardabweichung für den Mittelwert}$
> ![[Pasted image 20250209102704.png]]

> [!question]- Sie haben eine indirekt gemessene Größe A, die von mehreren Eingangsgrößen B, C, D, ... abhängt, die alle den gleichen Messfehler haben. Welche der Eingangsgrößen hat den größten Einfluss auf den Messfehler von A?
> Die mit der größten Gewichtung.
> Diejenige Eingangsgröße, deren partielle Ableitung von A am größten ist.

> [!question]- Eine indirekte Messgröße A berechnet sich als Differenz zweier direkt gemessener Eingangsgrößen B und C mit absolutem Messfehler $\Delta B$ bzw. $\Delta C$, d.h. $A=B-C$. Wie groß schätzen Sie den absoluten Messfehler $\Delta A$?
> $$
> \Large \Delta A=\Delta B+\Delta C
> $$

> [!question]- Was ist der parallaxenfehler?
> Ablesefehler, bei dem die Nadel beim Drehspulmessinstrument je nach Blickwinkel auf eine andere Zahl zeigt.
> 

> [!question]- Durch was entsteht der Anzeigefehler eines Messinstrumentes?
> Aufgrund von Fertigungstoleranzen, Lagerreibung, Montagevariationen der Skala usw.

> [!question]- Wie schätzen Sie den relativen Fehler $\Delta U$ der Spannungsmessung ab, wenn $\Delta R$ der relative Fehler der Widerstandsmessung und $\Delta I$ der relative Fehler der Strommessung ist?
> $$
> \Large \Delta U = \Delta R + \Delta I
> $$

> [!question]- Warum sollte man immer im oberen Drittel der Anzeigeskala messen?
> Weil absolute Messfehler bei großen Messwerten weniger ins Gewicht fallen als bei kleinen.

> [!question]- Warum muss man bei einem Drehimpulsinstrument jede Messung mit dem größten Messbereich beginnen?
> Da bei zu hoher Stromstärke Messinstrumente im kleinen Messbereich kaputt gehen können.

> [!question]- Auf der Anzeige Ihres analogen Messinstrumentes steht 'KL 1.5'. Was bedeutet das?
> Das bedeutet, dass der Anzeigefehler des Instrumentes bei 1.5% liegt.

> [!question]- Ein Messinstrument hat einen Anzeigefehler von 1% und einen Skalenendwert von 5 A. Im Moment zeigt das Instrument einen Strom von 2 A an. In welchem Bereich liegt der wahre Wert des Stroms?
> $$
> \Large 2 \pm 0.01 * 5 A
> $$

