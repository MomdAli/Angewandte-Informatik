---
title: Scala cheatsheet
tags:
  - Software-Engineering
  - Scala
  - Semester-3
  - Informatik
date: 2024-11-19
---

## Basics
```scala
// Hello, World!
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, World!")
  }
}
```

---

## Variables and Data Types
- **Immutable variables:** `val`
- **Mutable variables:** `var`
- **Type inference** is automatic.

```scala
val immutableVar: Int = 42
var mutableVar = 10 // type is inferred
val name = "Scala" // type inference
```

### Common Data Types
- `Int`, `Double`, `Boolean`, `String`, `Char`, `Long`, `Short`, `Byte`

---

## Control Structures
### If-else
```scala
val result = if (x > 10) "Greater" else "Smaller"
```

### Loops
- **For loop** with ranges and conditions:
```scala
for (i <- 1 to 10) println(i) // Inclusive
for (i <- 1 until 10) println(i) // Exclusive
for (i <- 1 to 10 if i % 2 == 0) println(i) // Filtered
```

- **While loop**:
```scala
var count = 0
while (count < 5) {
  println(count)
  count += 1
}
```

### Match (Switch)
```scala
x match {
  case 1 => println("One")
  case 2 => println("Two")
  case _ => println("Other")
}
```

---

## Functions
```scala
// Basic function
def add(a: Int, b: Int): Int = a + b

// Function with default parameters
def greet(name: String = "World"): Unit = println(s"Hello, $name!")

// Anonymous function
val square: Int => Int = x => x * x

// Function as a parameter
def operate(a: Int, b: Int, func: (Int, Int) => Int): Int = func(a, b)
```

---

## Collections
### Lists
```scala
val nums = List(1, 2, 3)
nums.map(_ * 2).filter(_ > 2)
```

### Arrays
```scala
val arr = Array(1, 2, 3)
arr.foreach(println)
```

### Maps
```scala
val map = Map("a" -> 1, "b" -> 2)
map("a") // Access value
```

### Sets
```scala
val set = Set(1, 2, 3)
set.contains(2)
```

---

## Object-Oriented Programming
### Classes
```scala
class Person(val name: String, var age: Int) {
  def greet(): String = s"Hello, $name"
}
```

### Objects
```scala
object SingletonObject {
  def greet(): Unit = println("Singleton greeting!")
}
```

### Companion Objects
```scala
class Person(val name: String)
object Person {
  def apply(name: String): Person = new Person(name)
}
val p = Person("John") // Calls apply
```

---

## Traits and Mixins
```scala
trait Greeter {
  def greet(): Unit = println("Hello!")
}
class Person extends Greeter
```

---

## Pattern Matching
```scala
val value: Any = 42
value match {
  case i: Int if i > 0 => println("Positive Integer")
  case s: String => println("String")
  case _ => println("Other")
}
```

---

## Error Handling
```scala
try {
  val result = 10 / 0
} catch {
  case e: ArithmeticException => println("Cannot divide by zero")
} finally {
  println("Execution complete")
}
```

---

## Concurrency
### Futures
```scala
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val future = Future {
  Thread.sleep(1000)
  42
}
future.map(println)
```

### Parallel Collections
```scala
val nums = List(1, 2, 3)
nums.par.map(_ * 2)
```

---

## Advanced Features
### Case Classes
```scala
case class Person(name: String, age: Int)
val p = Person("John", 30)
println(p.name) // Access fields directly
```

### Higher-Order Functions
```scala
def transform(nums: List[Int], func: Int => Int): List[Int] = nums.map(func)
```

### Implicits
```scala
implicit val multiplier: Int = 2
def multiply(x: Int)(implicit by: Int): Int = x * by
multiply(10) // Uses implicit multiplier
```

### Currying
```scala
def add(x: Int)(y: Int): Int = x + y
val addFive = add(5) _
println(addFive(10)) // 15
```
