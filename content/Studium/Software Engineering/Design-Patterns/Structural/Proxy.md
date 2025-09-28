---
title: Proxy
tags:
  - Software-Engineering
  - Design-Pattern
  - Scala
  - Semester-3
  - Informatik
date: 2024-11-22
publish: true
---
The **Proxy** design pattern is a structural pattern that provides a surrogate or placeholder for another object to control access to it. This pattern is useful for implementing lazy initialization, access control, logging, and more.

**Real-World Example:**

Imagine you're a celebrity who can't attend every event personally. You hire a representative (proxy) to attend on your behalf, manage interactions, and handle requests. The representative controls access to you, ensuring only appropriate interactions occur.

The Proxy pattern allows you to provide a substitute or placeholder for another object to control access to it.

**Example**:
Let's implement a scenario where we have a `Database` interface, a `RealDatabase` class that performs actual database operations, and a `DatabaseProxy` that controls access to the `RealDatabase`.
```scala
// Subject
trait Database {
  def query(sql: String): String
}

// RealSubject
class RealDatabase extends Database {
  override def query(sql: String): String = {
    // Simulate a time-consuming database operation
    Thread.sleep(1000)
    s"Result for query: '$sql'"
  }
}

// Proxy
class DatabaseProxy extends Database {
  private lazy val realDatabase = new RealDatabase

  override def query(sql: String): String = {
    println(s"Logging: Executing query '$sql'")
    realDatabase.query(sql)
  }
}

// Usage
object ProxyPatternExample extends App {
  val db: Database = new DatabaseProxy
  println(db.query("SELECT * FROM users"))
}
```

#### Output:
```sql
Logging: Executing query 'SELECT * FROM users'
Result for query: 'SELECT * FROM users'
```

**When to Use:**

- When you need to control access to an object.
- When you want to add additional functionality to an object without changing its interface.
- When creating an object is resource-intensive, and you want to delay its creation until it's needed.

---

**References:**

- [Proxy Design Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/proxy)
- [Proxy Pattern - Wikipedia](https://en.wikipedia.org/wiki/Proxy_pattern)
- Proxy Design Pattern in Scala - Source Code Examples