---
title: Facade
tags:
  - Software-Engineering
  - Design-Pattern
  - Scala
  - Semester-3
  - Informatik
date: 2024-11-22
---
The **Facade** design pattern is a structural pattern that provides a simplified interface to a complex subsystem, making it easier for clients to interact with the system. By encapsulating the complexities of the subsystem, the Facade pattern promotes loose coupling and enhances code readability.

**Real-World Example:**

Imagine you're planning a vacation. Instead of booking flights, hotels, and activities separately, you use a travel agency. The agency acts as a facade, handling all the intricate details and providing you with a simple package, saving you time and effort.

The Facade pattern offers a unified and straightforward interface to a set of interfaces in a subsystem, simplifying the interaction for the client.

**Example:**

Let's implement a home theater system where various components like the DVD player, projector, and sound system are controlled through a single interface.
```scala
// Subsystem classes
class DvdPlayer {
  def on(): Unit = println("DVD Player is on")
  def play(movie: String): Unit = println(s"Playing movie: $movie")
  def stop(): Unit = println("Stopping DVD")
  def off(): Unit = println("DVD Player is off")
}

class Projector {
  def on(): Unit = println("Projector is on")
  def off(): Unit = println("Projector is off")
}

class SoundSystem {
  def on(): Unit = println("Sound System is on")
  def setVolume(level: Int): Unit = println(s"Setting volume to $level")
  def off(): Unit = println("Sound System is off")
}

// Facade class
class HomeTheaterFacade(dvd: DvdPlayer, projector: Projector, sound: SoundSystem) {
  def watchMovie(movie: String): Unit = {
    println("Get ready to watch a movie...")
    projector.on()
    sound.on()
    sound.setVolume(5)
    dvd.on()
    dvd.play(movie)
  }

  def endMovie(): Unit = {
    println("Shutting movie theater down...")
    dvd.stop()
    dvd.off()
    sound.off()
    projector.off()
  }
}

// Usage
object FacadePatternExample extends App {
  val dvdPlayer = new DvdPlayer
  val projector = new Projector
  val soundSystem = new SoundSystem

  val homeTheater = new HomeTheaterFacade(dvdPlayer, projector, soundSystem)

  homeTheater.watchMovie("Inception")
  // Output:
  // Get ready to watch a movie...
  // Projector is on
  // Sound System is on
  // Setting volume to 5
  // DVD Player is on
  // Playing movie: Inception

  homeTheater.endMovie()
  // Output:
  // Shutting movie theater down...
  // Stopping DVD
  // DVD Player is off
  // Sound System is off
  // Projector is off
}
```

**When to Use:**

- When you want to provide a simple interface to a complex subsystem.
- To decouple clients from complex subsystems, reducing dependencies.
- When you need to structure a system into layers, using facades to define entry points to each subsystem.

---

**References:**

- [Facade Design Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/facade)
- [Facade Pattern - Wikipedia](https://en.wikipedia.org/wiki/Facade_pattern)
- Facade Design Pattern in Scala - Source Code Examples