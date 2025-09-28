---
title: Decorator
tags:
  - Software-Engineering
  - Design-Pattern
  - Scala
  - Semester-3
  - Informatik
date: 2024-11-22
publish: true
---
The **Decorator** design pattern is a structural pattern that allows behavior to be added to individual objects, dynamically, without affecting the behavior of other objects from the same class. It provides a flexible alternative to subclassing for extending functionality.


**Real-World Example:**

Imagine ordering a basic pizza. You can enhance it by adding toppings like cheese, onions, or olives. Each topping decorates the original pizza, adding new flavors without altering the pizza itself. Similarly, the Decorator pattern lets you add responsibilities to objects without modifying their structure.

The Decorator pattern allows you to add new functionality to an existing object without altering its structure, enabling you to extend an object's behavior dynamically.

---

**Example:**

Let's implement a simple text editor where we can add functionalities like spell checking and grammar checking to a basic text editor.
```scala
// Component
trait TextEditor {
  def display(): String
}

// Concrete Component
class BasicTextEditor extends TextEditor {
  override def display(): String = "Basic Text Editor"
}

// Decorator
abstract class TextEditorDecorator(editor: TextEditor) extends TextEditor {
  override def display(): String = editor.display()
}

// Concrete Decorators
class SpellCheckDecorator(editor: TextEditor) extends TextEditorDecorator(editor) {
  override def display(): String = super.display() + " with Spell Check"
}

class GrammarCheckDecorator(editor: TextEditor) extends TextEditorDecorator(editor) {
  override def display(): String = super.display() + " with Grammar Check"
}

// Usage
object DecoratorPatternExample extends App {
  val basicEditor = new BasicTextEditor
  println(basicEditor.display()) // Output: Basic Text Editor

  val spellCheckEditor = new SpellCheckDecorator(basicEditor)
  println(spellCheckEditor.display()) // Output: Basic Text Editor with Spell Check

  val grammarCheckEditor = new GrammarCheckDecorator(spellCheckEditor)
  println(grammarCheckEditor.display()) // Output: Basic Text Editor with Spell Check with Grammar Check
}
```

#### Output:
```
Basic Text Editor
Basic Text Editor with Spell Check
Basic Text Editor with Spell Check with Grammar Check
```

**When to Use:**

- When you want to add responsibilities to individual objects dynamically and transparently, without affecting other objects.
- When extending functionality by subclassing is impractical or leads to an explosion of subclasses.
- When you need to add functionalities that can be combined in various ways.

---

**References:**

- [Decorator Design Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/decorator)
- [Decorator Pattern - Wikipedia](https://en.wikipedia.org/wiki/Decorator_pattern)
- [Decorator Design Pattern in Scala - Source Code Examples](https://www.sourcecodeexamples.net/2023/10/decorator-design-pattern-in-scala.html)