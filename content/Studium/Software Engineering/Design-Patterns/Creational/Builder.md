---
{"publish":true,"title":"Builder","created":"2024-11-22","modified":"2025-09-12T21:25:02.774+02:00","published":"2024-11-22","tags":["Software-Engineering","Design-Pattern","Scala","Semester-3","Informatik"],"cssclasses":""}
---

The **Builder** design pattern is a creational pattern that facilitates the construction of complex objects by separating their creation process from their representation. This approach allows for the step-by-step assembly of objects, enabling the creation of different representations using the same construction process.

**Real-World Example:**

Consider the process of assembling a computer. A customer can choose various components—such as the CPU, GPU, RAM, and storage—to build a customized system. The assembly process remains consistent, but the final product varies based on the selected components. The Builder pattern mirrors this scenario by allowing the construction of complex objects with varying configurations.

The Builder pattern provides a structured approach to construct complex objects step by step, enabling the creation of different representations or configurations of the object.

**Example:**

Let's implement the Builder pattern in Scala to construct a `Computer` object with various optional components.
```scala
// Product
case class Computer(cpu: String,
                    gpu: Option[String],
                    ram: Int,
                    storage: Int,
                    hasWifi: Boolean,
                    hasBluetooth: Boolean)

// Builder
class ComputerBuilder {
  private var cpu: String = _
  private var gpu: Option[String] = None
  private var ram: Int = 8 // Default to 8GB
  private var storage: Int = 256 // Default to 256GB
  private var hasWifi: Boolean = false
  private var hasBluetooth: Boolean = false

  def setCpu(cpu: String): ComputerBuilder = {
    this.cpu = cpu
    this
  }

  def setGpu(gpu: String): ComputerBuilder = {
    this.gpu = Some(gpu)
    this
  }

  def setRam(ram: Int): ComputerBuilder = {
    this.ram = ram
    this
  }

  def setStorage(storage: Int): ComputerBuilder = {
    this.storage = storage
    this
  }

  def setWifi(hasWifi: Boolean): ComputerBuilder = {
    this.hasWifi = hasWifi
    this
  }

  def setBluetooth(hasBluetooth: Boolean): ComputerBuilder = {
    this.hasBluetooth = hasBluetooth
    this
  }

  def build(): Computer = {
    if (cpu == null || cpu.isEmpty) {
      throw new IllegalArgumentException("CPU must be specified")
    }
    Computer(cpu, gpu, ram, storage, hasWifi, hasBluetooth)
  }
}

// Usage
object BuilderPatternExample extends App {
  val customComputer = new ComputerBuilder()
    .setCpu("Intel i9")
    .setGpu("NVIDIA RTX 3080")
    .setRam(32)
    .setStorage(1024)
    .setWifi(true)
    .setBluetooth(true)
    .build()

  println(customComputer)
}
```

#### Output:
```scala
Computer(Intel i9,Some(NVIDIA RTX 3080),32,1024,true,true)
```

**When to Use:**

- When the construction of an object is complex and involves multiple optional parameters.
- When you want to create different representations of the same object.
- When you need to ensure that an object is constructed in a specific sequence.

---

**References:**

- [Builder Design Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/builder)
- [Builder Pattern - Wikipedia](https://en.wikipedia.org/wiki/Builder_pattern)
- [The Builder Pattern in Scala - Baeldung](https://www.baeldung.com/scala/builder-pattern)