---
title: 🔏Klausur 20
tags:
  - Systemprogrammierung
  - Probeklausur
date: 2024-07-24
aliases:
  - probeklausur
  - klausur
  - klausur-20
  - klausur_20
---
## <font color="#e97144">Aufgabe 1</font>

**a)**
- deklarieren Sie zuerst die beiden gezeigten benutzerdefinierten Typen:
```c 
struct node 
{
	struct node* next;
	int n;
};
typedef struct node node;

struct pair 
{
	char x;
	double y;
};
typedef struct pair pair;
```

- definieren Sie dann die Variablen ohne Initialisierung:
```c
char *a[3];
int b[2];
node *c;
pair d;
pair *e;
```

- weisen Sie zum Schluss allen Variablen ihre in der Grafik gezeigten Werte zu:
```c
#include <stdlib>
...
...
...

a[0] = "Datentypen";
a[1] = "und";
a[2] = "Variablen";

b[0] = 21;
b[1] = 5;

c = (node*) malloc(sizeof(node));
c->next = (node*) malloc(sizeof(node));
c->next->next = 0; // Wert 0 bedeutet, der Zeiger zeigt nirgendwohin
c->next->n = 5;
c->n = 21;

d.x = 'a';
d.y = 0.21;

e = &d;

...

free(c->next);
free(c);
```

**b)**

---
## <font color="#e97144">Aufgabe 2</font>


```c title="NoOverFlow.h"
#ifndef NOOVERFLOW_H
#define NOOVERFLOW_H

int increment(const int, int*);
int leftshift(const int, int*);

#endif
```

```c title="NoOverFlow.c"
#include <errno.h>
#include <limits.h>

int increment(const int n, int *ergebnis) 
{
	if (n == INT_MAX) 
	{
		errno = ERANGE;
		return 1;
	} 
	
	*ergebnis = n + 1;
	return 0;
}

int leftshift(const int n, int *ergebnis) 
{
	int m = n << 1;
	if ((n ^ m) < 0) 
	{
		errno = ERANGE;
		return 1;
	} 
	
	*ergebnis = m;
	return 0;
}
```

**b)**
```c title="binary.c"
#include <stdio.h>
#include "NoOverFlow.h"
#include <string.h>
#include <errno.h>

int main(int argc, char *argv[]) 
{
	char *s = argv[1];
	int n = 0;
	for (int i = 0; i < strlen(s); ++i) 
	{
		char c = *(s + i);
		if (c != '0' && c != '1') 
		{
			errno = EINVAL;
			fprintf(stderr, "%s: %d\n", s, errno);
			return 1;
		}
		if (leftshift(n, &n) == 1)
		{
			fprintf(stderr, "%s: %d\n", s, errno);
			return 1;
		}
		
		if (c == '1' && increment(n, &n) == 1) 
		{
			fprintf(stderr, "%s: %d\n", s, errno);
			return 1;
		}
	}
	
	printf("%s: %d\n", s, n);
	return 0;
}
```

---
## <font color="#e97144">Aufgabe 3</font>

```c
char *strchr(const char *s, char c) 
{
    const char *tmp = s;
  
    while (*tmp != '\0') 
    {
        if (*tmp == c)
            return (char *)tmp;
        tmp++;
    }

    if (c != *tmp)
        return NULL;
}
```

---
## <font color="#e97144">Aufgabe 4</font>

**a)**
Da diese Klasse nur ein privates Mitglied `double truth` enthält und keine speziellen Anforderungen für Kopieren, Zuweisen oder Zerstören hat, sind die vom Compiler bereitgestellten Implementierungen ausreichend und korrekt.
```cpp 
~fuzzy() = default; //destructor
fuzzy(const fuzzy&) = default; // copy constructor
fuzzy& operator=(const fuzzy&) = default; //copy assignment constructor 
fuzzy(fuzzy&&) = default; // move constructor
fuzzy& operator=(fuzzy&&) = default; // move assignment constructor
```

**b)**
Erklärung von [[C++ Constructors|Constructors in C++]]
```cpp showLineNumbers
int main()
{
	const fuzzy eher_ja{ 0.8 };
	const fuzzy eher_nein = !eher_ja;
	fuzzy unklar;
	unklar = eher_ja && eher_nein;
	unklar = eher_ja || false;
	unklar = 42.0;
	return 0;
}
```

==Lösung:==
- Zeile 3:
	- `explicit fuzzy(double)` ~
- Zeile 4:
	- `fuzzy operator!() const`
	- `fuzzy(fuzzy&&) = default;` ~~
- Zeile 5:
	- `fuzzy() = default`~
- Zeile 6:
	- `friend fuzzy operator&&(const fuzz&, const fuzzy&)`
	- `fuzzy& operator=(fuzzy&&) = default` ~
- Zeile 7:
	- `fuzzy(boolean)`~
	- `friend fuzzy operator||(const fuzz&, const fuzzy&)`~
	- `fuzzy& operator=(fuzzy&&) = default`
- Zeile 8:
	- `fuzzy(boolean)`~
	- `fuzzy& operator=(fuzzy&&) = default`
- Zeile 9:
	- `~fuzzy() = default` für 8 Objekte!

**c)**
```cpp
friend std::ostream& operator<<(std::ostream&, const fuzzy&);
```

**d)**
```cpp
std::array<fuzzy, 4> a;
```
Man sollte `std::array` verwenden, weil es sicherer ist, mehr Funktionalität bietet und besser mit der C++-Standardbibliothek integriert ist als ein C-Array.

---
## <font color="#e97144">Aufgabe 5</font>

[[Makefile|Makefile Cheatsheet]]
```makefile showLineNumbers
AUFGABE = aufgabe5

%.gz: %
	gzip -f $^

.PHONY: all clean

all: $(AUFGABE).tar.gz

clean: 
	rm -f $(AUFGABE).tar $(AUFGABE).tar.gz

$(AUFGABE).tar: $(AUFGABE) lib$(AUFGABE).so $(AUFGABE).pdf
	tar cf $@ $^
```
