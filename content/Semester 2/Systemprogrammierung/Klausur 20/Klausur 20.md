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
```cpp 
~fuzzy() = default; //destructor
fuzzy(const fuzzy&) = default; // copy constructor
fuzzy& operator=(const fuzzy&) = default; //copy assignment constructor 
fuzzy(fuzzy&&) = default; // move constructor
fuzzy& operator=(fuzzy&&) = default; // move assignment constructor
```

**b)**
```cpp showLineNumbers
int main()
{
	const fuzzy eher_ja{ 0.8 };
	const fuzzy eher_nein = !eher_ja;
	fuzzy unklar;
	unklar = eher_ja && eher_nein;
	unklar = eher ka || false;
	unklar = 42.0;
	return 0;
}
```

- Zeile 3:
	- `explicit fuzzy(double)`
- Zeile 4:
	- `fuzzy& operator=(fuzzy&&) = default`
	- `fuzzy operator!() const`
- Zeile 5:
	- `fuzzy() = default`
- Zeile 6:
	- `fuzzy& operator=(fuzzy&&) = default`
	- `friend fuzzy operator&&(const fuzz&, const fuzzy&)`
- Zeile 7:
	- `fuzzy& operator=(fuzzy&&) = default`
	- `friend fuzzy operator||(const fuzz&, const fuzzy&)`
	- `fuzzy(boolean)`
- Zeile 8:
	- `explicit fuzzy(double)`
	- `fuzzy& operator=(fuzzy&&) = default`
- Zeile 9:
	- `~fuzzy() = default` für eher_ja, eher_nein, 