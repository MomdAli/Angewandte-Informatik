---
tags:
  - Probeklausur
  - Programmiertechnik
  - Java
date: 2024-07-19
title: Klausur SS 18
---
 
[[Klausur_SS18.pdf#page=2&height=400|{Klausur 18}]]

## Aufgabe 1
**a)**
![[Aufgabe_1a.excalidraw.svg]]
**b)**
```
5, 3, 2,
4, 
1, 2, 
```

## Aufgabe 2
**a)**
```java
Node end = new Node(7, null);
end.next = end;
```
**b)**
```java
end.next = new Node(8, end.next);
end = end.next;
```
**c)**
```java
end.next = end.next.next;
```
**d)**
```java
Node p = end.next;
do {
	System.out.println(p.data);
	p = p.next;
} while (p != end.next)
```

## Aufgabe 3

|   5     |    15     |     2     |    17     |     2     |     1     |     3     |     7     |     4      |     6      |     10     |
| :-------: | :-------: | :-------: | :-------: | :-------: | :-------: | :-------: | :-------: | :--------: | :--------: | :--------: |
|     1     |           |           |           |           |    10     |           |           |            |            | 5   |
|           |     4     |           |           |           |           |           |           |     15     |            |            |
|           |           |           |     3     |           |           |    17     |           |            |            |            |
|           |           |           |           |           | 5  |           |           |            |            |     10     |
|     1     |     4     |     2     |     3     |     2     |           |    17     |     7     |     15     |     6      |     10     |
|     1     |           |     2     |           | 2  |           |    10     |           |     17     |            | 15  |
|           |     2     |     4     |           |           |           |           |           |     6      |     17     |            |
|           |           | 2  |           |     4     |           |           |           |            |            |            |
| 1  | 2  |           | 3  | 4  |           |           |           |            | 15  |     17     |
|           |           |           |           |           |           |    10     |     7     |     6      |            | 17  |
|           |           |           |           |           |           |     6     |    10     |     7      |            |            |
|           |           |           |           |           |           |           | 7  |     10     |            |            |
|           |           |           |           |           |           | 6  |           | 10  |            |            |

## Aufgabe 4
**a)**
```java
public boolean contains(int x) {
	Node p = begin;
	while (p != null) {
		if (p.data == x)
			return true;
		p = p.next;
	}
	return false;
}
```

 **b)**
```java
public void add(int x) {
	if (end == null) {
		begin = end = new Node(null, x);
		return;
	}

	end.next = new Node(null, x);
	end = end.next;
}
```

**c)**
```java
public void add(List l) {
	Node p = l.begin;
	while (p != null) {
		add(p.data);
		p = p.next;
	}
}
```

**d)**
```java
public boolean startsWith(List l) {
	Node p = begin;
	Node q = l.begin;
	
	while (p != null && q != null) {
		if (p.data != q.data) {
			return false;
		}
		p = p.next;
		q = q.next;
	}
	/*if (q != null)
		return false;
		
	return true;*/
	return q == null;
}
```

## Aufgabe 5
**a)**
![[Aufgabe_5a.excalidraw.svg]]

b)
```java
public void prettyPrint() {
	System.out.println(root.data);
	recursivePrint(1, root);
}

public void recursivePrint(int n, Node r) {
	for (Node p : r.children) {
		for (int i = 0; i < n; i++) {
			System.out.print("\t");
		} 
		System.out.println(p.data);
		recursivePrint(n + 1, p);
	}
}
```