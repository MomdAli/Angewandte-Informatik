---
title: Composite
tags:
  - Software-Engineering
  - Design-Pattern
  - Scala
  - Semester-3
  - Informatik
date: 2024-11-22
---
The **Composite** design pattern is a structural pattern that allows you to compose objects into tree-like structures to represent part-whole hierarchies. This pattern enables clients to treat individual objects and compositions of objects uniformly, simplifying the management of complex structures.


**Real-World Example:**

Consider a company's organizational chart. At the top, there's a CEO. Below the CEO, there are managers, each overseeing their own teams. Each team consists of employees. The entire structure can be viewed as a tree, where each node (whether it's the CEO, a manager, or an employee) can be treated uniformly when performing operations like calculating total salaries or sending announcements.

The Composite pattern allows you to build complex structures by combining objects into tree structures, enabling clients to interact with individual objects and compositions in a consistent manner.

**Example:**

Let's implement a file system where both files and directories can be treated uniformly.
```scala
// Component
trait FileSystemComponent {
  def showDetails(indent: String = ""): Unit
}

// Leaf
case class File(name: String) extends FileSystemComponent {
  def showDetails(indent: String = ""): Unit = {
    println(s"$indent- File: $name")
  }
}

// Composite
case class Directory(name: String) extends FileSystemComponent {
  private val children = scala.collection.mutable.ListBuffer[FileSystemComponent]()

  def add(component: FileSystemComponent): Unit = {
    children += component
  }

  def remove(component: FileSystemComponent): Unit = {
    children -= component
  }

  def showDetails(indent: String = ""): Unit = {
    println(s"$indent+ Directory: $name")
    children.foreach(_.showDetails(indent + "  "))
  }
}

// Usage
object CompositePatternExample extends App {
  val root = Directory("root")
  val home = Directory("home")
  val user = Directory("user")
  val file1 = File("file1.txt")
  val file2 = File("file2.txt")

  user.add(file1)
  home.add(user)
  root.add(home)
  root.add(file2)

  root.showDetails()
}
```

#### Output:
```markdown
+ Directory: root
  + Directory: home
    + Directory: user
      - File: file1.txt
  - File: file2.txt
```

**When to Use:**

- When you need to represent part-whole hierarchies of objects.
- When you want clients to be able to ignore the difference between compositions of objects and individual objects.
- When dealing with tree structures, such as file systems, organizational charts, or graphical scene hierarchies.

---

**References:**

- [Composite Design Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/composite)
- [Composite Pattern - Wikipedia](https://en.wikipedia.org/wiki/Composite_pattern)
- [Understanding the Composite Design Pattern: A Comprehensive Guide with Real-World Applications](https://dev.to/syridit118/understanding-the-composite-design-pattern-a-comprehensive-guide-with-real-world-applications-4855)