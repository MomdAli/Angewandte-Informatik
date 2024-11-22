---
title: Factory Method
tags:
  - Software-Engineering
  - Scala
  - Design-Pattern
  - Semester-3
  - Informatik
date: 2024-11-22
---
### Factory Method Design Pattern

The **Factory Method** is a creational design pattern that provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created. It delegates the instantiation logic to subclasses.


**Real-World Example:**

Imagine a pizza restaurant. The restaurant has a general method to "prepare pizza," but the exact type of pizza (e.g., Margherita, Pepperoni, or Veggie) depends on the branch location. Each branch decides the type of pizza it specializes in but follows the same general preparation process.

The Factory Method pattern allows a class to defer the instantiation of objects to its subclasses, ensuring a consistent interface while enabling customization.


**According to Wikipedia:**

`In class-based programming, the Factory Method pattern uses factory methods to deal with the problem of creating objects without specifying the exact class of the object. This is achieved by defining a method in a base class and letting subclasses override it to specify their object types.`

**Programmatic Example in Scala:**

Let's take the pizza analogy and implement it in Scala.
```scala
// Define a trait for Pizza
trait Pizza {
  def prepare(): String
}

// Concrete implementations of Pizza
class MargheritaPizza extends Pizza {
  def prepare(): String = "Preparing Margherita Pizza."
}

class PepperoniPizza extends Pizza {
  def prepare(): String = "Preparing Pepperoni Pizza."
}

// Abstract Creator
abstract class PizzaStore {
  // Factory Method
  protected def createPizza(): Pizza

  def orderPizza(): String = {
    val pizza = createPizza()
    pizza.prepare()
  }
}

// Concrete Creators
class MargheritaPizzaStore extends PizzaStore {
  protected def createPizza(): Pizza = new MargheritaPizza()
}

class PepperoniPizzaStore extends PizzaStore {
  protected def createPizza(): Pizza = new PepperoniPizza()
}

// Usage
val margheritaStore = new MargheritaPizzaStore()
println(margheritaStore.orderPizza()) // Outputs: Preparing Margherita Pizza.

val pepperoniStore = new PepperoniPizzaStore()
println(pepperoniStore.orderPizza()) // Outputs: Preparing Pepperoni Pizza.
```

**When to Use:**

- When a class wants its subclasses to specify the type of objects it creates.
- When you want to provide a consistent interface for creating objects, but the actual instantiation needs to be flexible or customized.
- When you need to encapsulate object creation and avoid directly calling constructors in client code.

By using the Factory Method pattern, you can adhere to the **Open/Closed Principle**—the system is open to extension but closed to modification. This ensures flexibility and maintainability in the long term.

---

**References:**

- [Factory Method Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/factory-method)
- [Factory Method Pattern - Wikipedia](https://en.wikipedia.org/wiki/Factory_method_pattern)