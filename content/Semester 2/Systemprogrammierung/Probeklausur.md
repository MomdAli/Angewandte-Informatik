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
