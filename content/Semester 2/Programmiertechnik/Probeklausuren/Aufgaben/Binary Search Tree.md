---
publish: true
tags:
  - Programmiertechnik
  - Probeklausur
---
## <font color="#ffc000">WS 18 Aufgabe 5</font>
**a)**
![[Zeichen_ws18.png]]
**b)**
```java
private Node insertR(int x, Node p) {
	if (p == null)
		p = new Node(x);
	else if (x < p.data)
		p.left = insertR(x, p.left);
	else if (x > p.data)
		p.right = insertR(x, p.right);
	else
		insertR(x, p.left);
	return p
}
```

**c)**
```java
public int contains(int x) {
	return containsR(x, root);
}

private int containsR(int x, Node p) {
	if (p == null)
		return 0;
	else if (x < p.data)
		return containsR(x, p.left);
	else if (x > p.data)
		return containsR(x, p.right);
	else 
		return containsR(x, p.left) + containsR(x, p.right) + 1;
}
```

## <font color="#ffc000">WS 19 Aufgabe 5</font>
**a)**
```java
private boolean equalsR(Node l, Node r) {
	if (l == null || r == null)
		return l == r;
	if (l.data == r.data)
		return equalsR(l.left, r.left) && equalsR(l.right, r.right);
	
	return false;
}
```