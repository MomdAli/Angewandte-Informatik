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
struct node {
	struct node* next;
	int n;
};
typedef struct node node;

struct pair {
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
a[0] = "Datentypen";
a[1] = "und";
a[2] = "Variablen";

b[0] = 21;
b[1] = 5;

c = (node*) malloc(sizeof(node));
c->next = (node*) malloc(sizeof(node));
c->next->next = NULL;
c->next->n = 5;
c->n = 21;

d.x = 'a';
d.y = 0.21;

e = &d;

...

free(c->next);
free(c);
```