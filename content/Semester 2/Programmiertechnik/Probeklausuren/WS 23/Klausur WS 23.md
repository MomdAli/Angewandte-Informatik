---
publish: true
date: 2024-07-21
tags:
  - Probeklausur
  - Programmiertechnik
---

## <font color="#71e9ac">Aufgabe 4</font>
**a)**
```java
public boolean contains(int x) {
	for (Node p = head; p != null; p = p.next) {
		if (p.data == x)
			return true;
	}
	return false;
}
```

**b)**
```java
public void add(int x) {
	end.next = new Node(null, x);
	end = end.next;
}
```

**c)**
```java
public List(List l) {
	begin = end = null;
	
	for (Node p = l.begin; p != null; p = p.next)
		this.add(p.data);
}
```

**d)**
```java
public boolean startsWith(List l) {
	if (begin == null || l.begin == null)
		return begin == l.begin;
		
	Node p = begin;
	Node q = l.begin;
	while (p != null && q != null) {
		if (p.data != q.data)
			return false;
		p = p.next;
		q = q.next;
	}
	
	return q == null;
}
```

**e)**
```java
public List add(int x) {
	end.next = new Node(null, x);
	end = end.next;
	return this;
}
```

---

## <font color="#71e9ac">Aufgabe 5</font>
**a)**

|     |     | 11  |     |     |
| --- | --- | --- | --- | --- |
|     | 2   |     |     | 15  |
| 1   |     | 8   | 12  |     |

**b)**
```java
private int heightR(Node p) {
	if (p == null)
		return -1; 
	return Math.max(heightR(p.left), heightR(p.right)) + 1;
}
```

**c)**
```java
public List<Integer> collect(Predicate<Integer> pred) {
	List<Integer> l = List.of();
	collectR(root, pred, l);
	return l;
}

private void collectR(Node p, Predicate<Integer> pred, List<Integer> l) {
	if (p != null) {
		if (pred.test(p.data))
			l.add(p.data)
		collectR(p.left, pred, l);
		collectR(p.right, pred, l);
	}
}
```

**d)**
```java
List<Integer> list = t.collect(x -> x % 2 == 0);
```

---

## <font color="#71e9ac">Aufgabe 6</font>

**a)**
```java
public void addLike(String res, String pers) {
	if (isLikedBy.get(res) == null)
		isLikedBy.put(res, Set.of(pers));
	else
		isLikedBy.get(res).add(pers);
}
```

**b)**
```java
public Set<String> getLikes(String res) {
	return isLikedBy.get(res);
}
```

**c)**
1. Möglichkeit
```java
public Set<String> getRestaurants(String pers) {
	Set<String> s = Set.of();
	for (Map.Entry<String, Set<String> entry : isLikedBy.entrySet()) {
		if (entry.getValue().contains(pers))
			s.add(entry.getKey());
	}
	return s;
}
```

2. Möglichkeit
```java
public Set<String> getRestaurants(String pers) {
	return isLikedBy.entrySet().stream()
		.filter(entry -> entry.getValue().contains(pers))
		.map(entry -> entry.getKey())
		.toSet();
}
```

**d)**


---

## <font color="#71e9ac">Aufgabe 7</font>
**a)**
```java
BiFunction<Messwert, Double, Messwert>
```
**b)**
```java
Messwert[typ="Temp", plz="78464", wert=30.0]
```
**c)**
```java
mLst.replaceAll(x -> incr.apply(x, 1.1));
```