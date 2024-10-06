---
tags:
  - Programmiertechnik
  - Probeklausur
  - Zusammenfassung
  - Java
date: 2024-07-19
enableToc: false
---

## <font color="#ffc000">Grundlagen</font>

### <font color="#8db3e2">Was ist der Unterschied zwischen Referenztypen und primitive Datentypen?</font>
Referenz zeigt auf Adresse vom Objekt und primitive Datentypen enthält den Wert und ist Objektunabhängig. 

![[Datentypen.png]]
### <font color="#8db3e2">Welche Referenztypen gibt es in Java (2 Angaben)?</font>
String, Arrays:
```java
String wort = new String(); // wort ist ein Refernztyp
int[] zahlen = {1, 2, 3}; // zahlen ist auch ein Refernztyp
```
### <font color="#8db3e2">Erklären Sie die Begriffe statischer und dynamischer Typ einer Variable. Was bedeutet dynamische Bindung (1 Satz)?</font>

Statischer Typ:
```java
static int zahl = 10; // Der statische Typ ist "zahl"
static String text = "Hallo, Welt!"; // Der statische Typ ist "text"
```

Dynamischer Typ:
```java
Tier meinTier = new Hund(); // Der dynamische Typ ist "Hund"
```

Dynamische Bindung:
```java
Tier[] tiere;
..
..
..
tiere[0] = new Hund();
tiere[1] = new Katze();
```
### <font color="#8db3e2">Was ist ein Interface (Schnittstelle)?</font>
Eine Schnittstelle in der Programmiersprache Java ist ein abstrakter Typ, der verwendet wird, um ein Verhalten zu deklarieren, das Klassen implementieren müssen. Sie ähneln Protokollen. Schnittstellen werden mit dem Schlüsselwort **„interface“** deklariert und dürfen nur Methodensignaturen und Konstanten Deklarationen enthalten.

> [!Example]- Beispiel
> ![[Code Beispiele#^83b2b2]]

### <font color="#8db3e2">Was ist eine abstrakte Klasse?</font>
Abstrakte Klassen sind **Klassen, die als Oberklassen dienen und selbst nicht zur Bildung von Objekten herangezogen werden können**. Sie werden oft verwendet, um Eigenschaften und Fähigkeiten einer allgemeinen Typgruppe zu definieren, deren abgeleitete Unterklassen diese dann weiter spezifizieren.

```java
// Abstrakte klasse
abstract class Animal {
  // Abstrakte Methode (hat kein Quellcode)
  public abstract void animalSound();
  public void sleep() {
    System.out.println("Zzz");
  }
}

// Unterklasse (erbt von Animal)
class Dog extends Animal {
  public void animalSound() {
    System.out.println("The dog says: woof woof");
  }
}
```
---
## <font color="#ffc000">Programmiermethodik</font>

### <font color="#8db3e2">Kapselung, Geheimnisprinzip, Verletzung des Geheimnisprinzips: </font>
**Kapselung** bedeutet, dass man die Daten und Methoden, die zu einem Objekt gehören, versteckt, damit andere Teile des Programms sie nicht direkt verändern können. Stell dir vor, du hast eine Schatztruhe (Objekt) und du versteckst den Schlüssel (Daten und Methoden) so, dass nur du den Schatz öffnen und ändern kannst.

**Geheimnisprinzip**: Das Geheimnisprinzip besagt, dass ein Objekt nur das zeigen soll, was andere wirklich wissen müssen. Alles andere bleibt geheim. Das ist wie bei einem Zaubertrick: Du zeigst den Leuten nur den Trick, aber nicht, wie er funktioniert.

**Verletzung des Geheimnisprinzips in Java**: Eine Verletzung des Geheimnisprinzips passiert, wenn du den Schlüssel zur Schatztruhe einfach herumliegen lässt und jeder daran herumpfuschen kann. Zum Beispiel:

```java
public class Person {     
	public String name; // Jeder kann den Namen ändern 
}
```

Das ist wie wenn jeder dein Geheimnis kennt. Besser wäre es, den Namen privat zu machen und spezielle Methoden zu haben, um den Namen zu bekommen oder zu ändern:

```java
public class Person {
	private String name; // Der Name ist versteckt      
	public String getName() {return name; // So holst du den Namen     }

	public void setName(String name) {
		this.name = name; // So änderst du den Namen     
	} 
}
```

So bleibt der Name sicher und nur du kannst ihn kontrollieren.

### <font color="#8db3e2">Immutable Klassen (Unveränderliche Klassen)</font>
Eine immutable Klasse ist eine Klasse, deren Instanzen nach ihrer Erstellung nicht mehr verändert werden können. Das bedeutet, dass einmal festgelegte Werte der Instanzvariablen nicht mehr geändert werden können. Immutable Klassen sind besonders nützlich, weil sie von Natur aus thread-sicher sind und das Debuggen erleichtern.

**Eigenschaften einer immutable Klasse:**

1. **Alle Felder sind final:** Die Variablen der Klasse werden einmal festgelegt und können nicht mehr verändert werden.
2. **Keine Setter-Methoden:** Es gibt keine Methoden, die die Werte der Felder ändern.
3. **Sichere Konstruktoren:** Der Konstruktor setzt alle Felder und macht eine tiefe Kopie von veränderlichen Objekten, wenn nötig.
4. **Veränderliche Objekte werden kopiert:** Falls die Klasse Felder enthält, die veränderliche Objekte sind (wie Arrays oder andere Objekte), werden diese kopiert, um zu verhindern, dass externe Referenzen die Immutable-Objekte verändern.

> [!Example]  **Beispiel für eine immutable Klasse in Java:**
>```java
>public final class ImmutablePerson {
>	private final String name;
>	private final int age;
>	
>	public ImmutablePerson(String name, int age) {
>		this.name = name;
>		this.age = age;
>	}
>	public String getName() {
>		return name;
>	}
>	public int getAge() {
>		return age;
>	} 
>}
>```

**Erklärung:**
1. **final Klassen- und Variablendeklaration:** Die Klasse selbst und alle Variablen sind `final`, was bedeutet, dass sie nicht verändert werden können.
2. **Kein Setter:** Es gibt keine Methoden, um die Werte von `name` und `age` zu ändern.
3. **Konstruktor:** Der Konstruktor setzt die Werte der Variablen nur einmal.
4. **Getter-Methoden:** Es gibt nur Methoden, um die Werte der Variablen zu lesen, aber nicht zu ändern.
---
## <font color="#ffc000">Datentyp Liste, Keller und Schlange</font>

### <font color="#8db3e2">Listen </font>

> [!Example]- Array List
> ![[Code Beispiele#^dcdb5c]]

> [!Example]- Linked List 
> ![[Code Beispiele#^e760e1]]

---
## <font color="#ffc000">Generische Datentypen und Iteratoren</font>

