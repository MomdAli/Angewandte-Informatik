---
date: 2024-07-20
tags:
  - Probeklausur
  - Programmiertechnik
  - Java
---
## <font color="#de7802">Let's say we have a List of Cars and we want to filter it using Java Stream API, this is how we do it:</font>

```java
public class Main {

	record Car(String type, String make, String model, Integer engineCapacity)
	
	public static void main(String[] args) {
		List<Car> cars = List.of(
			new Car("sedan", "BMW", "530", 1998),
			new Car("sedan", "Audi", "A5", 1998),
			new Car("sedan", "Mercedes", "E-Class", 2500),
			new Car("hatchback", "Skoda", "Octavia", 1600),
			new Car("hatchback", "Toyota", "TypeR", 1450),
		);
		
		...
	}
}
```

### <font color="#92d050">How do we extract only the cars which have the type equal "sedan"?</font>
Using filter:
```java
List<Car> sedanCars = cars.stream().filter(c -> c.type.equals("sedan")).toList();
```
The filter method accepts a predicate as a parameter which means if the result of the lambda expression returns true or false. But before we use filter we have to use the `.stream()` method which converts the list object to a stream object which takes the elements and allows using Java Stream APIs. After filtering the list we have to convert it back to a List\<Car\> Object using the `.toList()` method. However you can also use `.collect(Collectors.toList())`.


---
### <font color="#92d050">Well how about converting the type of the Stream?</font>
Using map:
```java
List<String> carMakeList = cars.stream().map(c -> c.make).toList();
```
The map method accepts a 'Function' object named mapper as a parameter which accepts to generic types, one for input and one for output:
```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```
and as a result it converts the type of the list from the type T to type R.

> [!Tip] 
> If we try to use the `.filter()` operation now the method would filter only Strings because of the output of the `.map()` operation which converted the List from Cars to Strings.


---

### <font color="#92d050">And how do we extract the list of both the make and the model of each Car?</font>
One way we can do it is to map the list and instead of converting it to a `String` object we convert it to a `List<String>` Object:
```java
List<List<String>> carMakeModelList = cars.stream()
			.map(c -> List.of(c.make, c.mopdel)).toList();
```

> [!example]
> `[Audi, A5, Mercedes, E-Class]`

However we don't want that, we want to have it as a `List<String>` object so to do this we have to use the `.flatMap()` operator:
```java
List<String> carMakeModelList = cars.stream()
			.flatMap(c -> Stream.of(c.make, c.mopdel)).toList();
```

The `.flatMap()` method flattens the result into a String and like the `.map()` method it accepts a `Function` object as a parameter and also it accepts a `Stream` object as a return type:
```java
<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
```

---
## <font color="#de7802">Stream Lazy Evaluation</font>
let's say we have a code like this:
```java
Stream<Integer> integerStream = Stream.of(10, 11, 12, 13, 14);

Stream<Integer> filteredIntegerStream = integerStream.filter(i -> {
		System.out.println("Filtering integer");
		return i % 2 == 0;		
});

System.out.println("Count = " + filteredIntegerStream.count());
```
now you would think the execution order would be the same as we write it however if we run the code the order would be the first, last and then the code in the middle. This is because Streams are only executed after a terminal operation e.g. `.count()`, `collect()`, `min()`, `max()` and so on.
These operators return a specific type of some kind, however, all other stream operators return a stream object and as a result they are executed only after a terminal operator which is why they are called **"lazy"** in term of their execution.

---
## <font color="#de7802">Partitioning by Collector</font>
now back to the car example, how do we split the cars into 2 or more types and list each of them with their other attribute.

### <font color="#92d050">How to split Car list into two buckets (sedan or not)?</font>
now to this we can also use filter or map operators however we want to store the list into a `Map<K, V>` object and to this we need to use `.collect()` operator:
```java
Map<boolean, List<Car>> partitionedCars = cars.stream()
	.collect(Collectors.partitioningBy(c -> c.type.equals("sedan")))
```
because the `.collect()` method returns a map and not a stream, it is then called a terminal operator which is why we call another method after it.

```java
public interface Collector<T, A, R> { ... }
```
The `Collector` interface takes all the elements in a stream and generate a collection, a map or a well-defined data structure from those elements with these types:
`T: type of the input elements`, in this case Car.
`A: type being used by collector to accumulate elements in a container`
`R: resulting type`

```java
public static <T>
    Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(Predicate<? super T> predicate) {
        return partitioningBy(predicate, toList());
    }
```
the `.partitionungBy()` method looks like this where it accepts a predicate as a parameter and has the type T as an input with a map of Boolean and a list of type T.


---

### <font color="#92d050">Let's partition the cars in a more complex way:</font>

```java
// (type, (make, engineCapacity))
Map<String, Map<String, Integer>> groupedCars = cars.stream()
	.collect(Collectors.groupingBy(c -> c.type,
		Collectors.toMap(c -> c.model, c -> c.engineCapacity)));
```

```java
public static <T, K, A, D>
Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier,
        Collector<? super T, A, D> downstream) {
        return groupingBy(classifier, HashMap::new, downstream);
    }
```

All credits goes to this ![video](https://www.youtube.com/watch?v=2StXP1XaU04)