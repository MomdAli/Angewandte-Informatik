---
{"publish":true,"created":"2024-07-19","modified":"2025-09-12T21:25:02.483+02:00","published":"2024-07-19","tags":["Programmiertechnik","Probeklausur","Zusammenfassung","Java","Semester-2","Informatik"],"cssclasses":""}
---


## <font color="#ffc000">Grundlagen</font>

### <font color="#8db3e2">Was ist der Unterschied zwischen Referenztypen und primitive Datentypen?</font>
Referenz zeigt auf Adresse vom Objekt und primitive Datentypen enthält den Wert und ist Objektunabhängig. 

![[Excalidraw/Programmiertechnik/Datentypen.png]]
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
> ```java
public interface FrequencyTable<T> extends Iterable<Element<T>>{

    int size();
    
    boolean isEmpty();
    
    void clear();
    
    void add(T t, int f);
    
    void add(T t);
    
    void addAll(FrequencyTable<? extends T> fq);
    
    Element<T> get(int pos);
    
    int get(T w);
    
    void collectNMostFrequent(int n, FrequencyTable<? super T> fq);
}
```

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
> ```java
package liste;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
  
/**
 * Realisiert eine Häufigkeitstabelle als Feld.
 * @author Mohammed Ali Al-Saiaf
 */

public class ArrayFrequencyTable<T> extends AbstractFrequencyTable<T> {

    private int size = 0;
    private Element<T>[] fqTable;
    private final int DEFAULT_SIZE = 100;
  

    public ArrayFrequencyTable() {
        clear();
    }

  
    private void moveToLeft(int pos) {
        Element<T> w = fqTable[pos];
        int i = pos - 1;

        while (i >= 0 && fqTable[i].getFrequency() < w.getFrequency()) {
            fqTable[i + 1] = fqTable[i];
            i--;
        }

        fqTable[i + 1] = w;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void clear() {
        fqTable = new Element[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void add(T w, int f) {
        if (size >= fqTable.length)
            fqTable = Arrays.copyOf(fqTable, size * 2);
  
        for (int i = 0; i < size; i++) {
            if (fqTable[i].getElement().equals(w)) {
                fqTable[i].addFrequency(f);
                moveToLeft(i);
                return;
            }
        }

        fqTable[size] = new Element<T>(w, f);
        moveToLeft(size);
        size++;
    }

    @Override
    public Element<T> get(int pos) {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
            
        return fqTable[pos];
    }

    @Override
    public int get(Object w) {
        for (int i = 0; i < size; i++) {
            if (fqTable[i].getElement().equals(w))
                return fqTable[i].getFrequency();
        }
        
        return 0;
    }

    @Override
    public Iterator<Element<T>> iterator() {
        return new ArrayFrequencyTableIterator();
    }

    private class ArrayFrequencyTableIterator implements Iterator<Element<T>> {

        int pos = -1;
        
        @Override
        public boolean hasNext() {
            if (pos + 1 >= size)
                return false;

            return fqTable[pos + 1] != null;
        }

        @Override
        public Element<T> next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return fqTable[++pos];
        }
    }
}
```

> [!Example]- Linked List 
> ```java
package liste;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListFrequencyTable<T> extends AbstractFrequencyTable<T> {

    private Node<T> begin;
    private Node<T> end;
    private int size;
  

    public LinkedListFrequencyTable() {
        clear();
    }

    @Override
    public int size() {
        return size;
    }
  

    @Override
    public final void clear() {
        begin = new Node<T>(new Element<T>(null, 0), null, null);
        end = new Node<T>(new Element<T>(null, 0), null, begin);
        begin.next = end;
        size = 0;
    }
  

    @Override
    public void add(T w, int f) {
        //if List is Empty
        if (size == 0) {
            Node<T> r = new Node<T>(new Element<T>(w, f), end, begin);
            begin.next = r;
            end.prev = r;
            size++;
            return;
        }
  
        // if Element exists
        Node<T> p = begin.next;
        while (p != end) {
            if (p.element.getElement().equals(w)) {
                p.element.addFrequency(f);
                moveToLeft(p);
                return;
            }
            p = p.next;
        }  
        //create new Node and sort it
        p = begin;
        while (p.next != end && p.next.element.getFrequency() > f) {
            p = p.next;
        }  

        Node<T> r = new Node<T>(new Element<T>(w, f), p.next, p);
        r.next.prev = r;
        p.next = r;
        size++;
    }  

    private void moveToLeft(Node<T> r) {  
        //Disconnect Nodes
        r.prev.next = r.next;
        r.next.prev = r.prev;  

        Node<T> p = r.prev;
        while (p != begin && p.element.getFrequency() < r.element.getFrequency()) {
            p = p.prev;
        }  

        r.next = p.next;
        r.prev = p;
        r.next.prev = r;
        p.next = r;
    }

    @Override
    public Element<T> get(int pos) {
        if (pos < 0 || pos > size)
            throw new IndexOutOfBoundsException();

        Node<T> p;
        if (pos <= (size / 2)) {
            p = begin;
            for (int i = 0; i <= pos; i++) {
                p = p.next;
            }
        } else {
            p = end;
            for (int i = 0; i < (size - pos); i++) {
                p = p.prev;
            }
        }

        return p.element;
    } 

    @Override
    public int get(T w) {
        Node<T> p = begin.next;
        while (p.next != null) {
            if (p.element.getElement().equals(w)) {
                return p.element.getFrequency();
            }
            p = p.next;
        }
        return 0;
    }

    private static class Node<T> {
        Element<T> element;
        Node<T> next;
        Node<T> prev;
  
        Node(Element<T> w, Node<T> n, Node<T> p) {
            element = w;
            next = n;
            prev = p;
        }
    }

    @Override
    public Iterator<Element<T>> iterator() {
        return new LinkedListFrequencyTableIterator();
    }
  
    private class LinkedListFrequencyTableIterator implements Iterator<Element<T>> {

        Node<T> current = begin;
  
        @Override
        public boolean hasNext() {
            return current.next != end && current.next != null;
        }

        @Override
        public Element<T> next() {
            if (!hasNext())
                throw new NoSuchElementException();

            current = current.next;
            return current.element;
        }
    }
}
```

---
## <font color="#ffc000">Generische Datentypen und Iteratoren</font>

