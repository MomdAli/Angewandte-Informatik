---
title: Singleton
tags:
  - Software-Engineering
  - Design-Pattern
  - Scala
  - Semester-3
  - Informatik
date: 2024-11-22
publish: true
---


The **Singleton** design pattern is a creational pattern that ensures a class has only one instance while providing a global point of access to that instance. It’s often used for managing shared resources like configuration settings, database connections, or logging mechanisms.

---

**Real-World Example:**

Imagine you’re the CEO of a company. A company can only have one CEO at a time (hopefully). All major decisions are funneled through this one individual, ensuring consistency across the organization. The Singleton pattern is like the CEO—you only ever have one, and everyone accesses the same "instance."

The Singleton pattern guarantees that a class has only one instance and provides a global access point to it.

---

**Example**

Here’s how you might implement the Singleton pattern in Scala.
```scala
// Singleton in Scala using an object
object DatabaseConnection {
  // Simulate connection setup
  private val connection: String = "Connected to Database"

  def getConnection: String = connection

  // A method to demonstrate functionality
  def query(sql: String): String = s"Executing query: $sql"
}

// Usage
object SingletonPatternExample extends App {
  val connection1 = DatabaseConnection
  val connection2 = DatabaseConnection

  println(connection1.getConnection) // Outputs: Connected to Database
  println(connection2.getConnection) // Outputs: Connected to Database

  // Demonstrating that both variables point to the same instance
  println(connection1 == connection2) // Outputs: true

  // Execute a query
  println(DatabaseConnection.query("SELECT * FROM users")) // Outputs: Executing query: SELECT * FROM users
}
```

#### Output:
```sql
Connected to Database
Connected to Database
true
Executing query: SELECT * FROM users
```

**References:**

- [Singleton Design Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/singleton)
- [Singleton Pattern - Wikipedia](https://en.wikipedia.org/wiki/Singleton_pattern)
- [Singleton in Scala - Baeldung](https://www.baeldung.com/scala/creating-singletons)