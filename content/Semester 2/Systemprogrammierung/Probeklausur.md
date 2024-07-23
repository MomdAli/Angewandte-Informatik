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
a = 1;
b = &a;

c[0] = 2.3;
c[1] = 4.5;

//d = {109, "Hoersaal", 'C'};
d.zimmer = 109;
d.art = "Hoersaal";
d.gebaeude = 'C';

e = malloc(sizeof(haus));
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

|                                       | <font color="#ffc000">Eingabeparameter</font>                                     | <font color="#92d050">Ausgabeparameter</font>                                                                                                                                                                                                          |
| ------------------------------------- | --------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **<font color="#76923c">Erklärung</font>**                         | **Eingabeparameter** sind Werte, die einer Funktion beim Aufruf übergeben werden. | **Ausgabeparameter** sind Parameter, die verwendet werden, um Ergebnisse oder Ausgaben aus einer Funktion herauszugeben. Dies geschieht typischerweise durch die Übergabe von Zeigern oder Referenzen, die von der Funktion modifiziert werden können. |
| <font color="#31859b">Beispiel</font> | ```int add(int zahl){ return zahl + zahl; }{:c}```                                | `void add(int a, int b, int* res) { *res = a + b;}{:c}`                                                                                                                                                                                                |

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

double quadrat_flaeche(const double seitenlaenge);

#endif
```

```c title="quadrat.c" 
#include "quadrat.h"

static double zumquadrat(const double d) {
	return d * d;
}

double quadrat_flaeche(const double seitenlaenge) {
	return zumquadrat(seitenlaenge);
}
```

```c title="wuerfel.h"
#ifndef WUERFEL_H
#define WUERFEL_H

double wuerfel_oberflaeche(const double kantenlaenge);
double wuerfel_volumen(const double kantenlaenge);

#endif
```

```c title="wuerfel.c"
#include "wuerfel.h"
#include "quadrat.h"

double wuerfel_oberflaeche(const double kantenlaenge) {
	return quadrat_flaeche(kantenlaenge) * 6;
}

double wuerfel_volumen(const double kantenlaenge) {
	return quadrat_flaeche(kantenlaenge) * kantenlaenge;
}
```

**b)**
```c title="wuerfel_test.c"
#include <stdio.h>
#include "wuerfel.h"

int main(int argc, char *argv[]) {
	double k;
	if (argc < 2 || sscanf(argv[1], "%lf", &k) == 0) {
		printf("falsche eingabe!");
		return 1;
	}
	
	double f = wuerfel_oberflaeche(k);
	double v = wuerfel_volumen(k);
	printf("kantenlaenge %f, Oberflaeche %f, Volumen %f\n", k, f, v);
	
	return 0;
}
```

