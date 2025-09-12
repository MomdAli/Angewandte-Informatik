---
{"publish":true,"title":"Bridge","created":"2024-11-22","modified":"2025-09-12T21:25:02.838+02:00","published":"2024-11-22","tags":["Software-Engineering","Design-Pattern","Scala","Semester-3","Informatik"],"cssclasses":""}
---

The **Bridge** design pattern is a structural pattern that decouples an abstraction from its implementation, allowing the two to vary independently. It is particularly useful when you want to avoid a permanent binding between an abstraction and its implementation, enabling flexibility and extensibility.


**Real-World Example:**

Imagine you are designing remote controls for TVs. Instead of creating a unique remote control for every TV brand, you create a generic remote (abstraction) that works with a variety of TV brands (implementations). This way, new TV brands or remote control designs can be added without altering existing ones.

The Bridge pattern separates the abstraction (high-level logic) from its implementation (low-level details), allowing both to evolve independently.


**Example:**

Let’s implement a scenario with different device types (e.g., TV and Radio) and various remote controls to operate them.
```scala
// Implementor Interface
trait Device {
  def isEnabled: Boolean
  def enable(): Unit
  def disable(): Unit
  def getVolume: Int
  def setVolume(volume: Int): Unit
}

// Concrete Implementor 1
class TV extends Device {
  private var enabled: Boolean = false
  private var volume: Int = 50

  def isEnabled: Boolean = enabled
  def enable(): Unit = { enabled = true; println("TV is now ON") }
  def disable(): Unit = { enabled = false; println("TV is now OFF") }
  def getVolume: Int = volume
  def setVolume(volume: Int): Unit = { this.volume = volume; println(s"TV volume set to $volume") }
}

// Concrete Implementor 2
class Radio extends Device {
  private var enabled: Boolean = false
  private var volume: Int = 30

  def isEnabled: Boolean = enabled
  def enable(): Unit = { enabled = true; println("Radio is now ON") }
  def disable(): Unit = { enabled = false; println("Radio is now OFF") }
  def getVolume: Int = volume
  def setVolume(volume: Int): Unit = { this.volume = volume; println(s"Radio volume set to $volume") }
}

// Abstraction
abstract class RemoteControl(protected val device: Device) {
  def togglePower(): Unit = {
    if (device.isEnabled) device.disable()
    else device.enable()
  }

  def volumeUp(): Unit = device.setVolume(device.getVolume + 10)
  def volumeDown(): Unit = device.setVolume(device.getVolume - 10)
}

// Refined Abstraction
class AdvancedRemoteControl(device: Device) extends RemoteControl(device) {
  def mute(): Unit = {
    device.setVolume(0)
    println("Device is muted")
  }
}

// Usage
object BridgePatternExample extends App {
  val tv = new TV
  val radio = new Radio

  val tvRemote = new RemoteControl(tv)
  val radioRemote = new AdvancedRemoteControl(radio)

  // Using TV remote
  tvRemote.togglePower() // TV is now ON
  tvRemote.volumeUp()    // TV volume set to 60

  // Using Radio remote
  radioRemote.togglePower() // Radio is now ON
  radioRemote.mute()        // Device is muted
}
```

#### Output:
```
TV is now ON
TV volume set to 60
Radio is now ON
Device is muted
```


**When to Use:**

- When you want to avoid a strong dependency between an abstraction and its implementation.
- When both the abstraction and the implementation need to vary independently.
- When you have multiple abstractions and implementations that can be combined dynamically.

---

**References:**

- [Bridge Design Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/bridge)
- [Bridge Pattern - Wikipedia](https://en.wikipedia.org/wiki/Bridge_pattern)