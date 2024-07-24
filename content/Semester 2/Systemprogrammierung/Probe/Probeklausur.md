---
title: 🧑🏼‍💻 Probeklausur
tags:
  - Probeklausur
  - Systemprogrammierung
date: 
aliases:
  - Probeklausur-Systemprogrammierung
  - Probe
  - Probe-Sysprog
  - Systemprogrammierung-Probeklausur
cssclasses: []
---
[[SYPR-Probeklausur.pdf|Link zu Probeklausur]]

---
## <font color="#e97144">Aufgabe 1</font>
**a)**
- deklarieren Sie zuerst eventuelle benutzerdefinierte Typen:
```c
struct haus {
	int zimmer;
	char* art;
	char gebaeude;
};
typedef struct haus haus;
```

- definieren Sie dann die fünf Variablen ohne Initialisierung:
```c
int a;
int* b;

double c[2];

haus d;
haus* e;
```

- weisen Sie zum Schluss allen Variablen die in der Grafik gezeigten Werte zu:
```c
#include <stdlib>
...
...
...

a = 1;
b = &a;

c[0] = 2.3;
c[1] = 4.5;

//d = {109, "Hoersaal", 'C'};
d.zimmer = 109;
d.art = "Hoersaal";
d.gebaeude = 'C';

e = (haus*) malloc(sizeof(haus));
e->zimmer = 124;
e->art = "Buero";
e->gebaeude = 'F';
```

#### Unterschied zwischen stack und heap 
![[Speicher in C.excalidraw.svg]]

[C Language Data Type Models: LP64 and ILP32](https://docs.oracle.com/cd/E19620-01/805-3024/lp64-1/index.html)

| C Type    | ILP32                          | LP64                           |
| :-------- | :----------------------------- | :----------------------------- |
| char      | 1                              | 1                              |
| short     | 2                              | 2                              |
| int       | 4                              | 4                              |
| long      | <font color="#c00000">4</font> | <font color="#c00000">8</font> |
| long long | 8                              | 8                              |
| pointer   | <font color="#c00000">4</font> | <font color="#c00000">8</font> |

**b)**
### <font color="#71e9ac">Ohne Ausrichtung/Padding</font>
#### ILP32:
Zimmer: (int) 4 Bytes
Art: (pointer) 4 Bytes
Gebaeude: (char) 1 byte

Also: $4 + 4 + 1 = 9$ Bytes

#### LP64:
Zimmer: (int) 4 Bytes
Art: (pointer) 8 Bytes
Gebaeude: (char) 1 Byte

Also: $4 + 8 + 1 = 13$ Bytes

### <font color="#71e9ac">Mit Ausrichtung/Padding</font>
#### ILP32:
Zimmer: (int) 4 Bytes
Art: (pointer) 4 Bytes
Gebaeude: (char) 1 Byte
Padding: 3 Bytes

Also: $4 + 4 + 1 + 3 = 12$ Bytes

#### LP64:
Zimmer: (int) 4 Bytes
Padding nach Zimmer: 4 Bytes
Art: (pointer) 8 Bytes
Gebaeude: (char) 1 Byte
Padding nach Gebaeude: 7 Bytes

Also: $4 + 4 + 8 + 1 + 7 = 24$ Bytes

---
## <font color="#e97144">Aufgabe 2</font>

|                                            | <font color="#ffc000">Eingabeparameter</font>                                     | <font color="#92d050">Ausgabeparameter</font>                                                                                                                                                                                                          |
| ------------------------------------------ | --------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **<font color="#76923c">Erklärung</font>** | **Eingabeparameter** sind Werte, die einer Funktion beim Aufruf übergeben werden. | **Ausgabeparameter** sind Parameter, die verwendet werden, um Ergebnisse oder Ausgaben aus einer Funktion herauszugeben. Dies geschieht typischerweise durch die Übergabe von Zeigern oder Referenzen, die von der Funktion modifiziert werden können. |
| <font color="#31859b">Beispiel</font>      | ```int add(int zahl){ return zahl + zahl; }{:c}```                                | `void add(int a, int b, int* res) { *res = a + b;}{:c}`                                                                                                                                                                                                |

---
## <font color="#e97144">Aufgabe 3</font>

**a)**
> [!warning] Wichtig
> - In header **ifndef**, **define** und **endif** nicht vergessen
> - In header files nur öffentliche Funktionen und Variablen.  
> - Funktionennamen mit Klassennamen definieren
> - Eingabeparameter immer `const`
> - In Quelldatei (`.c`) **include header** nicht vergessen
> - In Quelldateien befinden sich **private** Funktionen
> - `static` heißt privat, also static in c ≠ static in java

```c title="quadrat.h"
#ifndef QUADRAT_H
#define QUADRAT_H

double quadrat_flaeche(const double);

#endif
```

```c title="quadrat.c" 
#include "quadrat.h"

static double zumquadrat(const double d) 
{
	return d * d;
}

double quadrat_flaeche(const double seitenlaenge) 
{
	return zumquadrat(seitenlaenge);
}
```

```c title="wuerfel.h"
#ifndef WUERFEL_H
#define WUERFEL_H

double wuerfel_oberflaeche(const double);
double wuerfel_volumen(const double);

#endif
```

```c title="wuerfel.c"
#include "wuerfel.h"
#include "quadrat.h"

double wuerfel_oberflaeche(const double kantenlaenge) 
{
	return quadrat_flaeche(kantenlaenge) * 6;
}

double wuerfel_volumen(const double kantenlaenge) 
{
	return quadrat_flaeche(kantenlaenge) * kantenlaenge;
}
```

**b)**
```c title="wuerfel_test.c"
#include <stdio.h>
#include "wuerfel.h"

int main(int argc, char *argv[]) 
{
	double k;
	if (sscanf(argv[1], "%lf", &k) == 0) 
	{
		printf("falsche eingabe!");
		return 1;
	}
	
	double f = wuerfel_oberflaeche(k);
	double v = wuerfel_volumen(k);
	printf("kantenlaenge %f, Oberflaeche %f, Volumen %f\n", k, f, v);
	
	return 0;
}
```



**c)**
Das C-Programm greift auf `argv[1]` zu, um das Argument zu lesen. Wenn das Programm jedoch ohne Argumente gestartet wird, ist `argc` gleich 1 und `argv[1]` existiert nicht. Der Zugriff auf `argv[1]` führt zu einem nicht definierten Verhalten, das in der Regel einen Absturz (Segmentation Fault) des Programms zur Folge hat.

---
## <font color="#e97144">Aufagabe 4</font>

```c 
#include <string.h> // strcpy, strcat
#include <stdlib.h> // malloc, free

int main(int argc, char *argv[]) 
{	
	const char* s = argv[1];
	const char* t = argv[2];
	
	char* st = (char*) malloc(strlen(s) + strlen(t) + 1);
	strcpy(st, s);
	strcat(st, t);
	
	free(st);
	
	return 0;
}
```

---
## <font color="#e97144">Aufgabe 5</font>

**a)**
```cpp configurationHere
~fuzzy() = default; // destructor
fuzzy(const fuzzy&) = default; // copy constructor
fuzzy(fuzzy&&) = default; // move constructor
fuzzy& operator=(const fuzzy&) = default; // copy assignment operator
fuzzy& operator=(fuzzy&&) = default; // move assignment operator
```

Grund für `= default`:
- Da `truth` ein einfacher Datentyp (`double`) ist, funktioniert die Standardkopie und Verschieben korrekt.
- Da `truth` keine komplexen Ressourcen wie dynamischen Speicher oder Datei-Handles enthält, ist der Standard-Destruktor ausreichend.

**b)**
```cpp showLineNumbers wrap=true
int main () 
{
	const fuzzy eher_ja{ 0.8 };
	const fuzzy eher nein = ! eher ja;
	fuzzy f;
	f = eher ja || false;
}
```

- Zeile 3:
	- `explicit fuzzy(double)`  
- Zeile 4:
	- `fuzzy(fuzzy&&)`
	- `fuzzy operator!()`  
- Zeile 5:
	- `fuzzy()`  
- Zeile 6:
	- `fuzzy(fuzzy&&)`
	- `fuzzy(bool)`
	- `friend fuzzy operator||(const fuzzy&, const fuzzy&)`
- Zeile 7:
	- `~fuzzy()` für f, eher_ja, eher_nein, false, und beide von move Konstruktor erstellten Objekte. Also 6 Destrokturen.

**c)**
```cpp
friend std::ostream& operator<<(std::ostream& os, const fuzzy& f) 
{
	os << f.truth; return os; 
}
```

**d)**
```cpp
#include <array>

std::array<fuzzy, 4> a;
```
In modernem C++ sollte man stattdessen `std::array` oder `std::vector` verwenden. Der Grund dafür ist, dass diese Container mehr Funktionalität und Sicherheit bieten als rohe Arrays. Sie verwalten ihre eigene Speicherverwaltung und bieten Methoden zur Größenabfrage, Bounds-Checking und mehr

---
## <font color="#e97144">Aufagbe 6</font>
##### [[Makefile|Makefile cheatsheet]]

```makefile
PDFLATEX = pdflatex
TARGETS = hello.pdf
RM = rm -f

%.pdf: %.tex
	$(PDFLATEX) $<

.PHONY: all clean
all: $(TARGETS)
clean: $(RM) $(TARGETS) $(TARGETS:.pdf=.aux) $(TARGETS:.pdf=.log) 
```

---
## <font color="#e97144">Aufgabe 7</font>
**POSIX-Funktionen** melden Fehler durch Rückgabewerte und die globale Variable `errno`. Es ist wichtig, nach jedem kritischen Funktionsaufruf die Rückgabewerte zu überprüfen und ***bei Fehlern `errno` auszuwerten***, um den genauen Fehler zu bestimmen und geeignete Maßnahmen zu ergreifen.  
Wenn eine POSIX-Funktion einen Fehler meldet, wird die globale Variable `errno` auf einen Fehlercode gesetzt, der den spezifischen Fehler beschreibt.  
`errno` muss direkt nach dem fehlerhaften Funktionsaufruf überprüft werden, da nachfolgende Funktionsaufrufe `errno` überschreiben können. 