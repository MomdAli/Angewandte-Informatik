---
publish: true
date: 2024-07-20
tags:
  - Probeklausur
  - Programmiertechnik
cssclasses:
  - purpleRed
  - wideTable
  - leftAlign
---
## Aufgabe 1
![[Aufagbe 1.png]]

## Aufgabe 2
**a)**
```java
last = first.next;
last.next = null;
```
**b)**
```java
last.next = new Node(4, null);
last = last.next;
```

## Aufgabe 3

| 16  | 12  | 3   | 20  | 10  | 13  | 8   | 1   | 4   | 11  | 6   | 8   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 8   |     |     |     |     | 16  |     |     |     |     |     | 13  |
|     |     |     | 6   |     |     |     |     |     |     | 20  |     |
|     |     |     |     |     | 11  |     |     |     | 16  |     |     |
|     |     |     |     |     |     |     |     |     | 13  |     | 16  |
| 8   | 12  | 3   | 6   | 10  | 11  | 8   | 1   | 4   |     | 20  | 16  |
| 4   |     |     |     | 10  |     |     |     | 8   |     | 16  | 20  |
|     | 1   |     |     |     |     |     | 12  |     |     |     |     |
|     |     |     |     | 8   |     | 10  |     |     |     |     |     |
|     |     |     |     |     | 8   |     |     | 11  |     |     |     |
| 4   | 1   | 3   | 6   | 8   |     | 10  | 12  | 11  |     |     |     |
| 3   |     | 8   |     | 4   |     | 10  | 11  | 12  |     |     |     |
|     |     | 4   |     | 8   |     |     |     |     |     |     |     |
| 3   | 1   |     | 6   | 8   |     |     |     |     |     |     |     |
| 1   | 3   |     | 6   | 8   |     |     |     |     |     |     |     |
| 1   | 3   | 4   | 6   | 8   | 8   | 10  | 11  | 12  | 13  | 16  | 20  |

![[Aufgabe 3.png]]

## Aufgabe 4
**a)**
1. Möglichkeit
```java
public Integer get(String n) {
	if (head == null)
		return null;
	if (head.name.equals(n))
		return head.value;
		
	Node prevNode = head;
	for (Node p = head; p != null; p = p.next) {
		if (n.equals(p.name)) {
			prevNode.next = p.next;
			p.next = head;
			head = p;
			return p.value;
		}
		prevNode = p;
	}
	return null;
}
```

2. Möglichkeit
```java
public Integer get(String n) {
	if (head == null)
		return null;
	if (head.name.equals(n))
		return head.value;
	
	Node p = head;
	Node prevNode = head;
	while (p != null) {
		if (n.equals(p.name)) {
			prevNode.next = p.next;
			p.next = head;
			head = p;
			return p.value;
		}
		prevNode = p;
		p = p.next;
	}
	return null;
}
```

**b)**
```java
public void add(String n, int v) {
	if (get(n) == null)
		head = new Node(n, v, head);
	else
		head.value = v;
} 
```

**c)**
get: O(n)
add: O(n)
## Aufgabe 5
![[Binary Search Tree#WS 19 Aufgabe 5]]
## Aufgabe 6
**a)**
```java
public static Map<String, Set<Integer>> list2Map(List<Pruefung> l) {
	Map<String, Set<Integer>> map = l.stream()
		.collect(Collectors.groupingBy(x -> x.name, TreeMap::new, 
			Collectors.mapping(x -> x.pruefNr, Collectors.toSet())));
	return map;
}
```

**b)**
```java
public static List<Pruefung> map2List(Map<String, Set<integer>> s2n) {
	List<Pruefung> l = new LinkedList();
	s2n.forEach((key, values) -> {
		for (int v : values)
			l.add(new Pruefung(key, v));
	}) 
	return l;
}
```

**c)**
```java
public static Map<Integer, Set<String>> invertMap(Map<String, Set<Integer>> s2n) {
	Map<Integer, Set<String>> map = s2n.stream()
		.collect(Collectors.groupingBy(x -> x))
}
```