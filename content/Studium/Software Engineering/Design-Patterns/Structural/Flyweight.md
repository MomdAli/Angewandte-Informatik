---
title: Flyweight
tags:
  - Software-Engineering
  - Design-Pattern
  - Scala
  - Semester-3
  - Informatik
date: 2024-11-22
---
The **Flyweight** design pattern is a structural pattern that aims to minimize memory usage by sharing as much data as possible with similar objects. It is particularly useful when dealing with a large number of objects that share common properties, allowing for efficient resource utilization.

**Real-World Example:**

Imagine a text editor displaying a document containing thousands of characters. Instead of creating a unique object for each character, the editor can use a shared object for each character type (e.g., one for 'a', one for 'b', etc.) and store their positions separately. This approach significantly reduces memory consumption.

The Flyweight pattern reduces the number of objects created and decreases memory usage by sharing common parts of the state between multiple objects.

**Example:**

Let's implement a scenario where we need to create a large number of `Tree` objects in a forest. Each tree has a type, color, and texture, which can be shared among trees of the same kind. The unique part is the position of each tree.
```scala
// Flyweight class
case class TreeType(name: String, color: String, texture: String) {
  def draw(x: Int, y: Int): Unit = {
    println(s"Drawing $name tree of color $color at position ($x, $y)")
  }
}

// Flyweight Factory
object TreeFactory {
  private val treeTypes = scala.collection.mutable.Map[String, TreeType]()

  def getTreeType(name: String, color: String, texture: String): TreeType = {
    treeTypes.getOrElseUpdate(s"$name-$color-$texture", TreeType(name, color, texture))
  }
}

// Forest class
class Forest {
  private val trees = scala.collection.mutable.ListBuffer[(Int, Int, TreeType)]()

  def plantTree(x: Int, y: Int, name: String, color: String, texture: String): Unit = {
    val treeType = TreeFactory.getTreeType(name, color, texture)
    trees += ((x, y, treeType))
  }

  def draw(): Unit = {
    trees.foreach { case (x, y, treeType) =>
      treeType.draw(x, y)
    }
  }
}

// Usage
object FlyweightPatternExample extends App {
  val forest = new Forest
  forest.plantTree(1, 2, "Oak", "Green", "Rough")
  forest.plantTree(3, 4, "Pine", "Dark Green", "Smooth")
  forest.plantTree(5, 6, "Oak", "Green", "Rough")
  forest.draw()
}
```

#### Output:
```
Drawing Oak tree of color Green at position (1, 2)
Drawing Pine tree of color Dark Green at position (3, 4)
Drawing Oak tree of color Green at position (5, 6)
```

**When to Use:**

- When an application uses a large number of objects that share common properties, leading to high memory consumption.
- When most of an object's state can be made extrinsic (i.e., moved outside the object).
- When the identity of an object does not matter.

---

**References:**

- [Flyweight Design Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/flyweight)
- [Flyweight Pattern - Wikipedia](https://en.wikipedia.org/wiki/Flyweight_pattern)
- [Flyweight Design Pattern in Scala - Source Code Examples](https://www.sourcecodeexamples.net/2023/10/flyweight-design-pattern-in-scala.html)