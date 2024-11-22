---
title: Abstract Factory
tags:
  - Software-Engineering
  - Design-Pattern
  - Scala
  - Semester-3
  - Informatik
date: 2024-11-22
---
### Abstract Factory Design Pattern

The **Abstract Factory** is a creational design pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes. It enables the creation of objects that belong together, ensuring consistency within their product families.

**Real-World Example:**

Imagine you're furnishing an office. Instead of buying chairs, desks, and tables individually, you hire an interior design company. Depending on the style you choose (modern, classic, industrial), the company provides a consistent set of furniture designed to match that style. The client doesn't need to worry about which chair or desk to pick; everything is handled by the company based on the selected style.

The Abstract Factory pattern groups object creation into families, ensuring that objects created are compatible with one another.

**According to Wikipedia:**

The Abstract Factory pattern provides a way to encapsulate a group of individual factories that have a common theme. It is often implemented by defining a set of interfaces that are used to create related objects, and concrete classes implement those interfaces.

**Example:**

Let's extend the office furniture analogy and implement it in Scala.
```scala
// Abstract Products
trait Chair {
  def material(): String
}

trait Desk {
  def typeOfDesk(): String
}

// Concrete Products
class ModernChair extends Chair {
  def material(): String = "Made of steel and leather."
}

class ModernDesk extends Desk {
  def typeOfDesk(): String = "A sleek, minimalist desk."
}

class ClassicChair extends Chair {
  def material(): String = "Made of oak wood."
}

class ClassicDesk extends Desk {
  def typeOfDesk(): String = "A large, ornate wooden desk."
}

// Abstract Factory
trait FurnitureFactory {
  def createChair(): Chair
  def createDesk(): Desk
}

// Concrete Factories
class ModernFurnitureFactory extends FurnitureFactory {
  def createChair(): Chair = new ModernChair()
  def createDesk(): Desk = new ModernDesk()
}

class ClassicFurnitureFactory extends FurnitureFactory {
  def createChair(): Chair = new ClassicChair()
  def createDesk(): Desk = new ClassicDesk()
}

// Usage
object AbstractFactoryExample {
  def main(args: Array[String]): Unit = {
    val modernFactory: FurnitureFactory = new ModernFurnitureFactory()
    val classicFactory: FurnitureFactory = new ClassicFurnitureFactory()

    val modernChair = modernFactory.createChair()
    val modernDesk = modernFactory.createDesk()
    println(modernChair.material()) // Outputs: Made of steel and leather.
    println(modernDesk.typeOfDesk()) // Outputs: A sleek, minimalist desk.

    val classicChair = classicFactory.createChair()
    val classicDesk = classicFactory.createDesk()
    println(classicChair.material()) // Outputs: Made of oak wood.
    println(classicDesk.typeOfDesk()) // Outputs: A large, ornate wooden desk.
  }
}
```


**When to Use:**

- When a system must be independent of how its objects are created.
- When you need to enforce consistency among products in a family (e.g., all modern furniture should look cohesive).
- When the system involves multiple families of related objects, and these families can evolve independently.

---

**References:**

- [Abstract Factory Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/abstract-factory)
- [Abstract Factory Pattern - Wikipedia](https://en.wikipedia.org/wiki/Abstract_factory_pattern)
- [Abstract Factory Design Pattern in Scala - Source Code Examples](https://www.sourcecodeexamples.net/2023/10/abstract-factory-design-pattern-in-scala.html)