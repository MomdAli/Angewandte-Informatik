---
title: Simple Factory
tags:
  - Software-Engineering
  - Design-Pattern
  - Semester-3
  - Informatik
  - Scala
date: 2024-11-22
publish: true
---
In software development, the **Simple Factory** pattern centralizes object creation, allowing clients to request objects without needing to know the specifics of their instantiation. This approach promotes code maintainability and scalability by decoupling object creation from the client code.

**Real-World Example:**

Imagine you're building a house and need doors. Instead of crafting each door yourself, you order them from a factory. You specify the type of door you need—wooden, glass, or metal—and the factory delivers it. This way, you don't need to know the intricate details of door-making; you simply request the type you want, and the factory handles the rest.

The Simple Factory pattern provides a centralized method to create instances of various classes based on input parameters, abstracting the instantiation logic from the client.

**Example:**
```scala
// Define a trait for Door
trait Door {
  def description(): String
}

// Concrete implementations of Door
class WoodenDoor extends Door {
  def description(): String = "I am a wooden door."
}

class GlassDoor extends Door {
  def description(): String = "I am a glass door."
}

// DoorFactory to create doors
object DoorFactory {
  def createDoor(doorType: String): Door = doorType.toLowerCase match {
    case "wooden" => new WoodenDoor()
    case "glass"  => new GlassDoor()
    case _        => throw new IllegalArgumentException("Unknown door type.")
  }
}

// Usage
val door = DoorFactory.createDoor("wooden")
println(door.description()) // Outputs: I am a wooden door.
```
In this example, `DoorFactory` centralizes the creation of `Door` objects. Clients request a door by specifying its type, and the factory returns the appropriate instance, encapsulating the instantiation logic.

**When to Use:**

The Simple Factory pattern is particularly useful when:

- A class cannot anticipate the class of objects it needs to create.
- A class wants its subclasses to specify the objects it creates.
- Classes delegate responsibility to one of several helper subclasses, and you want to localize the knowledge of which helper subclass is the delegate.

By employing the Simple Factory pattern, developers can achieve a cleaner separation of concerns, making the system more adaptable to changes and easier to maintain.