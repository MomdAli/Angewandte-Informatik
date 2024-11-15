---
title: Implementation
tags:
  - Software-Engineering
  - Scala
  - UML
  - Semester-3
  - Informatik
date: 2024-10-14
aliases: 
cssclasses: 
---
### **Main Components of the Diagram:**
### Model
- **Player.scala**: Represents a player in the game. Holds basic player information.
- **Tile.scala**: Defines the content of a tile (e.g., empty, wall, player, out).
- **Grid.scala**: Represents the game grid and its layout.
- **GameManager.scala**: Manages game state, players, and grid interactions.
- **GameState.scala**: Enum for different game states (e.g., NotStarted, Playing).
- **Position.scala**: Utility to manage positions on the grid.

### View
- **DisplayRenderer.scala**: Handles rendering the game state in a visual manner.
- **TUI.scala**: Text-based User Interface that displays the game to the user. It also implements an `Observer` to reflect game state changes.

### Controller
- **Controller.scala**: Acts as a middleman between the game model (`GameManager`) and the views (`TUI`). Controls the game flow and user actions.
- **InputHandler.scala**: Processes user input and passes it to the appropriate game logic.

### Utilities / Shared
- **Observable.scala**: Implements the Observer pattern, enabling views to react to changes in the model.
- **Direction.scala**: Enumerates directions for player movement.
- **echorelics.scala**: The main entry point that sets up the game.

![[Diagram.png]]