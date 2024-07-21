---
publish: true
date: 2024-07-21
tags:
  - Zusammenfassung
  - Probeklausur
  - Programmiertechnik
title: Zusammenfassung von Programmiertechnik
---

### 1. Binäre Bäume und verkettete Listen
#### Wichtige Punkte:
- **Verkettete Listen**: Konstruktion, Einfügen von Knoten, Löschen von Knoten, Methoden wie `contains`, `add`, `startsWith`.
- **Binäre Suchbäume**: Einfügen und Löschen von Knoten, Höhe des Baums bestimmen, Suchmethoden.
  
#### Beispielaufgabe:
- **Verkettete Liste** (WS 2018/19, Aufgabe 1):
  ```java
  class Node { 
     int data; 
     Node left; 
     Node right; 
     
     Node(int x, Node l, Node r) { 
        data = x;  
        left = l;  
        right = r 
     } 
  }
  // Main-Methode zur Konstruktion und Manipulation von Knoten
  ```
- **Binärer Suchbaum** (WS 2018/19, Aufgabe 5):
  ```java
  public class BinarySearchTree { 
     private static class Node { 
        int data; 
        Node left; 
        Node right; 
        Node(int x) {data = x; left = null; right = null;} 
     } 
     private Node root = null; 

     public void insert(int x) { 
        root = insertR(x,root); 
     }
     private Node insertR(int x, Node p) { 
        if (p == null) 
           p = new Node(x); 
        else if (x <= p.data) 
           p.left = insertR(x,p.left); 
        else 
           p.right = insertR(x,p.right); 
        return p; 
     }
  }
  ```

### 2. Sortieralgorithmen
#### Wichtige Punkte:
- **QuickSort**: Implementierung, Median-Strategie, Aufrufstruktur.
  
#### Beispielaufgabe:
- **QuickSort mit 3-Median** (WS 2018/19, Aufgabe 3):
  ```java
  int[] array = {8, 7, 6, 5, 4, 3, 2, 1, 9};
  // 3-Median Schritt: Median aus a[li], a[m], a[re] bestimmen und mit a[re] vertauschen
  // Partitionierungsschritte durchführen
  ```

### 3. Java Collections und Generics
#### Wichtige Punkte:
- **Map, Set, List**: Hinzufügen und Abrufen von Elementen, Methoden wie `addLike`, `getLikes`, `getRestaurants`, `top10`.
- **Generics**: Nutzung von Wildcards (`? extends`, `? super`).
  
#### Beispielaufgabe:
- **Java Collections** (WS 2017/18, Aufgabe 7):
  ```java
  public void addLike(String res, String pers) {
      if (isLikedBy.get(res) == null)
          isLikedBy.put(res, new TreeSet<>());
      isLikedBy.get(res).add(pers);
  }
  ```

### 4. Lambda-Ausdrücke und Streams
#### Wichtige Punkte:
- **Lambda-Ausdrücke**: Syntax, Verwendung in Methoden wie `replaceAll`, `filter`, `collect`.
- **Streams**: Nutzung von `map`, `reduce`, `filter`, `sorted`, `collect`.
  
#### Beispielaufgabe:
- **Streams** (WS 2023/24, Aufgabe 7):
  ```java
  boolean check = mLst.stream()
      .filter(m -> m.typ().equals("Temp"))
      .allMatch(m -> m.wert() >= a && m.wert() <= b);
  ```

### 5. Rekursion
#### Wichtige Punkte:
- **Rekursive Methoden**: Implementierung und Verwendung in Such- und Sortieralgorithmen.
  
#### Beispielaufgabe:
- **Rekursive Methode** (WS 2018/19, Aufgabe 6):
  ```java
  private int containsR(int x, Node p) { 
      if (p == null) 
          return 0; 
      else if (x < p.data) 
          return containsR(x,p.left); 
      else if (x > p.data) 
          return containsR(x,p.right); 
      else 
          return 1 + containsR(x,p.left); 
  }
  ```

### Zusammenfassung:
- **Verkettete Listen und Bäume**: Konstruktion, Einfügen, Löschen, Suchmethoden.
- **Sortieralgorithmen**: QuickSort mit und ohne Median-Strategie.
- **Java Collections und Generics**: Methoden für Maps, Sets und Lists, Nutzung von Wildcards.
- **Lambda-Ausdrücke und Streams**: Streams API, Lambda-Ausdrücke.
- **Rekursive Methoden**: Implementierung und Anwendung in Algorithmen.