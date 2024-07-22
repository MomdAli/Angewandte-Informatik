```java
pulic static Map<Integer, Double> getJahresAusgaben(List<Ausgabe> list) {
	return list.stream()
		.collect(Collectors.groupingBy(e -> e.jahr,
			Collectors.summingDouble(e -> e.kosten)));
}
```

```java
public static void printJahresAusgaben(Map<Integer,Double> jA) {
	jA.entrySet().stream()
		.forEach(x -> {
			System.out.println(x.getKey() + " : " + x.getValue());
		});
}
```

```java
public static void printJahresAusgabenNachKostenSortiert(Map<Integer, Double> jA) {
	jA.entrySet().stream()
		.sorted(Collectors.)
}
```



```java
public static Map<Integer, Double> getJahresAusgaben(List<Ausgabe> list) {
	Map<Integer, Double> map = new HashMap<>();
	for (Ausgabe a : list) {
		/*if (!map.containsKey(a.jahr))
			map.put(a.jahr, 0.0);*/
		map.putIfAbsent(a.jahr, 0.0);
		map.put(a.jahr, map.get(a.jahr) + a.kosten);
	}
	return map;
}
```
