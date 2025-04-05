---
title: Sensoren und Messung
tags:
  - SSS
  - Informatik
  - Semester-3
date: 2024-10-22
aliases: 
cssclasses:
---

## Sensoren

#### Definition:
Sensoren sind Geräte, die physikalische (z.B. Temperatur, Druck) oder chemische Eigenschaften (z.B. Feuchtigkeit, pH-Wert) ihrer Umgebung erfassen und diese in elektrische Signale umwandeln. Diese Signale können dann von Maschinen oder Computern verarbeitet werden.

#### Beispiele:

- **Aktive Sensoren**: Ein Widerstandsthermometer verändert seinen elektrischen Widerstand je nach Temperatur, benötigt aber eine externe Stromquelle, um die Temperaturänderungen zu erfassen.
- **Passive Sensoren**: Ein Piezoelement erzeugt Spannung, wenn es Druck ausgesetzt wird, ohne externe Energiezufuhr.

Sensoren sind in vielen Alltagsgeräten zu finden, z.B. in Smartphones (Gyroskop), in Autos (Geschwindigkeitssensoren) oder in der Umwelttechnik (Temperatursensoren).

## Signale

#### Definition:
Signale tragen Informationen und können in verschiedenen Formen vorliegen: als elektrische Spannung, als Licht, als Schall oder in anderen Formen. Signale können analog oder digital sein.

- **Analoge Signale**: Diese Signale sind kontinuierlich und ändern sich stufenlos, z.B. die Temperaturmessung eines Thermometers, die in eine kontinuierliche Spannungsänderung umgewandelt wird.
- **Digitale Signale**: Diese Signale sind diskret und in Bits codiert, z.B. ON/OFF-Signale in einem Computer.

Warum verwenden wir elektromagnetische Signale?
Elektromagnetische Signale können extrem schnell und verlustfrei über große Entfernungen transportiert werden, z.B. durch Kabel oder drahtlos via Antennen. Sie sind auch sehr energieeffizient, was wichtig für moderne Mikroelektronik ist.

## Messung

#### Definition:
Messen bedeutet, eine physikalische Größe (wie z.B. Länge, Temperatur oder elektrische Spannung) quantitativ mit einer bekannten Referenzgröße zu vergleichen. Ein Messsystem besteht aus einem Sensor, der die physikalische Größe erfasst, und einem Gerät, das diese Information in eine lesbare Form umwandelt.

#### Messkette:
Ein Sensor nimmt eine Primärgröße auf (z.B. Temperatur), wandelt sie in ein Signal um (z.B. Spannung), das dann durch verschiedene Stufen des Messgeräts verarbeitet wird, bis ein Endwert (z.B. Temperaturanzeige auf einem Bildschirm) herauskommt.

#### Messfehler:
Messungen sind nie perfekt und können durch verschiedene Faktoren beeinflusst werden, z.B. durch Rauschen, Ungenauigkeiten des Sensors oder des Messgeräts, oder durch Umwelteinflüsse. Es ist wichtig, die Unsicherheiten einer Messung zu kennen und zu berücksichtigen.

#### Reproduzierbarkeit:
Eine Messung ist reproduzierbar, wenn sie unter denselben Bedingungen wiederholt werden kann und zu ähnlichen Ergebnissen führt. Reproduzierbarkeit ist wichtig, um die Gültigkeit einer Messung zu überprüfen.

### Wichtige Messprinzipien
- **Amplitudenanalog**: Hier wird das Eingangssignal als Spannung oder Strom proportional zur Stärke des gemessenen Stimulus dargestellt. Ein analoges Signal ist kontinuierlich und repräsentiert den Stimulus durch die Amplitude (Höhe des Signals). Ein Beispiel wäre die Temperaturmessung durch einen Thermistor, der eine kontinuierliche Spannung ausgibt, die proportional zur Temperatur ist.
- **Frequenzanalog**: Hier ändert sich die Frequenz eines Signals in Abhängigkeit von der Größe des Stimulus. Dies wird oft in Tachometern verwendet, um Drehzahlen zu messen, indem die Anzahl der Schwingungen pro Sekunde gezählt wird.
- **Digital**: Ein digitaler Sensor gibt das Messsignal in diskreten Schritten oder Bits aus, z.B. durch Zählen von Impulsen. Diese Signale sind unempfindlich gegenüber Rauschen und werden in Computern am häufigsten verwendet.


## Messgeräte

#### Elektrische Messgrößen:
Da viele physikalische Größen in elektrische Signale umgewandelt werden, spielen elektrische Messgeräte eine zentrale Rolle. Diese Geräte erfassen indirekt Größen wie Spannung oder Strom durch ihre Auswirkungen auf andere physikalische Phänomene, wie Magnetfelder oder Wärme.

#### Drehspulinstrument:
Dieses Messgerät verwendet eine Spule, die sich im Magnetfeld eines Dauermagneten dreht. Je stärker der elektrische Strom, desto größer die Drehung, und der angeschlossene Zeiger zeigt einen entsprechenden Wert auf der Skala an. Das ist ein klassisches Prinzip bei analogen Amperemeter oder Voltmeter.

## Kalibrierung und Justierung

#### Kalibrierung:
Dies bedeutet, die Genauigkeit eines Messgeräts zu überprüfen und gegebenenfalls zu korrigieren. Dies wird durch Vergleich mit einer bekannten Referenzgröße durchgeführt, um sicherzustellen, dass das Messgerät korrekte Werte anzeigt.

#### Justierung:
Hier wird das Messgerät aktiv eingestellt, um Messfehler zu minimieren. Dabei wird am Gerät selbst eine Korrektur vorgenommen, um z.B. eine systematische Abweichung zu korrigieren.

## Fehlerklassen von Messinstrumenten

Messgeräte haben immer einen gewissen Fehlerbereich, der durch Fertigungstoleranzen, mechanische Reibung oder Temperaturveränderungen verursacht wird. Fehlerklassen geben an, wie groß die Abweichung eines Messgeräts vom wahren Wert sein kann. Je niedriger die Fehlerklasse (z.B. 0.1), desto genauer ist das Gerät.

**Praktisches Beispiel**: Ein Gerät mit Fehlerklasse 2.5 und einem Skalenendwert von 10 A könnte einen Wert zwischen 9.75 A und 10.25 A anzeigen, wenn der tatsächliche Wert 10 A beträgt.

## Normale
 **Messnormale**: Ein Normal ist ein Referenzgerät oder eine Referenzgröße, mit der andere Messgeräte kalibriert oder verglichen werden. Es gibt verschiedene Arten von Normalen, z.B. Primärnormale, die die höchste Genauigkeit aufweisen, und Gebrauchsnormale, die im täglichen Gebrauch zur Kalibrierung von Messgeräten verwendet werden.
 
 **Beispiel**: Ein Platin-Iridium-Stab, der früher in Paris aufbewahrt wurde, diente als Referenz für die Längeneinheit Meter. Heutzutage wird die Definition des Meters durch die Lichtgeschwindigkeit im Vakuum bestimmt.