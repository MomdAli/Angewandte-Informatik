---
title: Endliche Automaten
tags:
  - THGI
  - Semester-3
  - Informatik
date: 2025-02-03
publish: true
---
## Definition
Ein **endlicher Automat** ist ein mathematisches Modell für Berechnungen mit einer **endlichen Anzahl von Zuständen**. Er verarbeitet eine **Eingabezeichenkette** und wechselt zwischen **Zuständen** gemäß einer definierten **Übergangsfunktion**.

---

## Arten von Endlichen Automaten
Es gibt zwei Haupttypen von endlichen Automaten:

1. **Deterministischer Endlicher Automat (DEA)**
   - Für jedes Zeichen gibt es **genau einen** definierten Folgezustand.
   - Keine Mehrdeutigkeiten in den Übergängen.

2. **Nichtdeterministischer Endlicher Automat (NEA)**
   - Ein Zustand kann mehrere mögliche Folgezustände haben.
   - Kann auch ε-Übergänge (Übergänge ohne Eingabe) enthalten.

Jeder **NEA kann in einen DEA umgewandelt** werden (mittels **Potenzmengenkonstruktion**).

---

## Formale Definition eines DEA
Ein deterministischer endlicher Automat (DEA) ist ein **5-Tupel**:

\[
M = (S, Σ, δ, s_0, F)
\]

- **S**: Endliche Menge von Zuständen
- **Σ**: Eingabealphabet
- **δ**: Übergangsfunktion $δ: S × Σ → S$
- **s0**: Startzustand $s0 \in S$
- **F**: Menge der Endzustände $F \subseteq S$

Ein Wort wird akzeptiert, wenn nach dem vollständigen **Einlesen der Zeichenkette** der Automat sich in einem **Endzustand** befindet.

---

## Formale Definition eines NEA
Ein nichtdeterministischer endlicher Automat (NEA) ist ähnlich definiert, jedoch erlaubt die Übergangsfunktion **mehrere mögliche Folgezustände**:

\[
M = (S, Σ, δ, s_0, F)
\]

- Die Übergangsfunktion ist jetzt:  
  \[
  δ: S × Σ → P(S)
  \]
  (d.h. sie kann eine **Menge von Zuständen** zurückgeben)
- **ε-Übergänge** sind erlaubt: Ein Zustand kann ohne Eingabezeichen in einen anderen wechseln.

---

## Darstellungsmöglichkeiten
Endliche Automaten können auf verschiedene Weise dargestellt werden:

1. **Zustandsdiagramm** (Graphische Darstellung)
   - Zustände als Kreise
   - Übergänge als gerichtete Pfeile
   - Endzustände mit einem **Doppelkreis** gekennzeichnet

2. **Zustandstabelle** (Tabellarische Darstellung)
   - Zeigt für jeden Zustand und jedes Eingabesymbol den Folgezustand.

Beispiel für eine Zustandstabelle:

| Zustand | Eingabe `0` | Eingabe `1` |
|---------|------------|------------|
| q0      | q1         | q2         |
| q1      | q1         | q3         |
| q2      | q1         | q2         |
| q3      | q3         | q3         |

---

## Potenzmengenkonstruktion (NEA → DEA)
Jeder **nichtdeterministische Automat (NEA)** kann in einen **deterministischen Automaten (DEA)** umgewandelt werden:

1. **Jede Menge von Zuständen wird zu einem neuen Zustand des DEA.**
2. **Ein Zustand des DEA repräsentiert eine Menge von NEA-Zuständen.**
3. **Alle möglichen Übergänge werden systematisch ermittelt.**
4. **Endzustände des DEA enthalten mindestens einen Endzustand des NEA.**

Dieser Prozess kann zu **exponentiell mehr Zuständen** im DEA führen.

---

## Reguläre Sprachen und Endliche Automaten
- **Jede reguläre Sprache kann durch einen endlichen Automaten erkannt werden.**
- Reguläre Sprachen können auch mit **regulären Grammatiken** oder **regulären Ausdrücken** beschrieben werden.

**Zusammenhänge:**
- **Reguläre Sprachen** ↔ **Endliche Automaten** ↔ **Reguläre Ausdrücke** ↔ **Reguläre Grammatiken**

---

## Eigenschaften von Endlichen Automaten
Endliche Automaten haben einige wichtige Eigenschaften:

✔️ **Speicherlos**: Sie speichern nur den aktuellen Zustand.  
✔️ **Effizient**: Die Laufzeit ist **linear zur Eingabelänge**.  
✔️ **Deterministische Automaten sind effizienter** als nichtdeterministische.  

🚫 **Begrenzte Ausdruckskraft**:  
   - Endliche Automaten **können keine kontextfreien Sprachen** erkennen.  
   - Beispielsweise kann ein endlicher Automat keine Sprache der Form **\(a^n b^n\)** akzeptieren, weil er sich nicht merken kann, wie oft „a“ vorkam.

---

## Beispiel: Akzeptanz einer Sprache
Betrachten wir eine Sprache **L**, die nur Wörter mit gerader Anzahl von `a` akzeptiert:

1. **Alphabet:** $Σ = \{a, b\}$
2. **Zustände:** $S = \{q_0, q_1\}$
3. **Startzustand:** $s_0 = q_0$
4. **Endzustand:** $F = \{q_0\}$
5. **Übergänge:**

   - $δ(q_0, a) = q_1$  
   - $δ(q_1, a) = q_0$  
   - $δ(q_0, b) = q_0$  
   - $δ(q_1, b) = q_1$  

Zustandsdiagramm:

```
(q0) --a--> (q1)
(q1) --a--> (q0)
(q0) --b--> (q0)
(q1) --b--> (q1)
```

Dieser Automat **akzeptiert alle Wörter mit einer geraden Anzahl an `a`**.

---

## Zusammenfassung
- **Endliche Automaten** sind Modelle zur Verarbeitung von Zeichenketten mit einer endlichen Anzahl von Zuständen.
- **DEA** sind deterministisch, **NEA** erlauben mehrere mögliche Folgezustände.
- **Jeder NEA kann in einen DEA umgewandelt werden**.
- **Endliche Automaten sind genau so mächtig wie reguläre Grammatiken und reguläre Ausdrücke**.
- **Sie können keine kontextfreien Sprachen akzeptieren**, sind aber effizient und einfach zu implementieren.

---

**Endliche Automaten bilden die Grundlage für viele Anwendungen in der Informatik, darunter Compilerbau, Mustererkennung und formale Sprachen.** 🚀