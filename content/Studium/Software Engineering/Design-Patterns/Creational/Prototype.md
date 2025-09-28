---
title: Prototype
tags:
  - Software-Engineering
  - Design-Pattern
  - Scala
  - Semester-3
  - Informatik
date: 2024-11-22
publish: true
---

> [!TIP] 
> It's all about cloning objects and modifying them

The **Prototype** design pattern is a creational pattern that enables the creation of new objects by copying an existing object, known as the prototype. This approach is particularly useful when creating a new object from scratch is expensive or when objects share similar configurations.

**Real-World Example:**

Imagine Apple making iPhones. Every year, they release a "brand-new" model, but let’s be honest—it’s pretty much the same iPhone with minor tweaks. Instead of designing it from scratch, Apple could just copy last year’s model and modify a few things (like adding a new camera bump or tweaking the screen size). This is basically the Prototype pattern in action: reuse an existing prototype, tweak it a little, and voilà—you have the "latest innovation."

The Prototype pattern allows you to clone existing objects to produce new ones, saving time and resources. Perfect for when your "new" product is suspiciously similar to the old one.

**Example:**

Here’s how Apple could implement their iPhone production using the Prototype pattern.
```scala
// Define a trait for cloning
trait Cloneable[T] {
  def clone(): T
}

// iPhone class implementing Cloneable
case class iPhone(model: String, year: Int, features: List[String]) extends Cloneable[iPhone] {
  override def clone(): iPhone = this.copy()
}

// Usage
object PrototypePatternExample extends App {
  // The "prototype" for iPhones
  val originaliPhone = iPhone("iPhone X", 2017, List("FaceID", "Dual Camera", "iOS 14"))
  
  // Apple decides to make a "new" iPhone
  val newiPhone = originaliPhone.clone().copy(model = "iPhone 11", 
			year = 2019,
			features = 
				originaliPhone.features :+ "Slightly Brighter Screen")
  
  println(s"Original iPhone: $originaliPhone")
  println(s"New iPhone: $newiPhone")
}
```

#### Output:
```sql
Original iPhone: iPhone(iPhone X,2017,List(FaceID, Dual Camera, iOS 14))
New iPhone: iPhone(iPhone 11,2019,List(FaceID, Dual Camera, iOS 14, Slightly Brighter Screen))
```

**When to Use:**

- When creating a new object from scratch is expensive or inefficient.
- When objects are very similar and differ only slightly in their properties.
- When you want to duplicate objects while maintaining independence from their original creation.

---
**References:**

- [Prototype Design Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/prototype)
- [Prototype Pattern - Wikipedia](https://en.wikipedia.org/wiki/Prototype_pattern)
- [Prototype Design Pattern in Scala - Source Code Examples](https://www.sourcecodeexamples.net/2023/10/prototype-design-pattern-in-scala.html)