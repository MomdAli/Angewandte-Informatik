---
{"publish":true,"title":"Adapter","created":"2024-11-22","modified":"2025-09-12T21:25:02.830+02:00","published":"2024-11-22","tags":["Software-Engineering","Design-Pattern","Scala","Semester-3","Informatik"],"cssclasses":""}
---

The **Adapter** design pattern is a structural design pattern that acts as a bridge between two incompatible interfaces. It enables classes with incompatible interfaces to work together by converting one interface into another that a client expects.


**Real-World Example:**

Imagine you have a laptop with a USB-C port, but your favorite mouse uses a USB-A connector. Instead of buying a new mouse, you use a USB-C to USB-A adapter, which allows your mouse to connect to the laptop. The adapter doesn’t change how the mouse or laptop works but makes them compatible.

The Adapter pattern converts the interface of one class into an interface expected by the client, enabling otherwise incompatible classes to work together.


**Example:**

Let’s implement the Adapter pattern by simulating a scenario where we need to charge an iPhone using a standard USB charger.
```scala
// Existing incompatible interfaces
trait LightningPort {
  def chargeWithLightning(): String
}

trait USBPort {
  def chargeWithUSB(): String
}

// Adaptee (the class we want to adapt)
class iPhone extends LightningPort {
  override def chargeWithLightning(): String = "Charging iPhone with Lightning port."
}

// Target (the interface the client expects)
class USBCharger extends USBPort {
  override def chargeWithUSB(): String = "Charging with USB port."
}

// Adapter
class LightningToUSBAdapter(iphone: iPhone) extends USBPort {
  override def chargeWithUSB(): String = iphone.chargeWithLightning()
}

// Usage
object AdapterPatternExample extends App {
  val myiPhone = new iPhone()
  val usbCharger = new USBCharger()
  
  // Using an adapter to charge iPhone with a USB charger
  val adapter = new LightningToUSBAdapter(myiPhone)
  
  println(usbCharger.chargeWithUSB())       // Outputs: Charging with USB port.
  println(adapter.chargeWithUSB())          // Outputs: Charging iPhone with Lightning port.
}
```

#### Output:
```
Charging with USB port.
Charging iPhone with Lightning port.
```

**When to Use:**

- When you want to use an existing class but its interface isn’t compatible with the system.
- When you want to create a reusable class that cooperates with unrelated or unforeseen classes.
- When you need to work with legacy code but want to use it in a new system.

---

**References:**

- [Adapter Design Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/adapter)
- [Adapter Pattern - Wikipedia](https://en.wikipedia.org/wiki/Adapter_pattern)