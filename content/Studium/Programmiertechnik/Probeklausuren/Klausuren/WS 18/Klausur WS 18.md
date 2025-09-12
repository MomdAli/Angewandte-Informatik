---
{"publish":true,"created":"2024-07-21","modified":"2025-09-12T21:25:02.538+02:00","published":"2024-07-21","tags":["Probeklausur","Programmiertechnik","Java","Semester-2","Informatik"],"cssclasses":""}
---

## <font color="#71e9ac">Aufgabe 6</font>

**a)**
```java
public Set<String> getAlleKinder(String mutter) {
	return mutterVonKind.entrySet().stream()
		.filter(entry -> entry.getValue().equals(mutter))
		.map(entry -> entry.getKey())
		.collect(Collectors.toSet());
}
```

**b)**
```java
public Set<String> getAlleGeschwister(String kind) {
	String mutter = mutterVonKind.get(kind);
	return mutterVonKind.entrySet().stream()
		.filter(entry -> entry.getValue().equals(mutter)
			&& !entry.getKey().equals(kind))
		.map(entry -> entry.getKey())
		.collect(Collectors.toSet());
}
```

**c)**
```java
public boolean istTanteVon(String x, String y) {
	return getAlleGeschwister(mutterVonKind.get(y))
		.contains(x);
}
```

