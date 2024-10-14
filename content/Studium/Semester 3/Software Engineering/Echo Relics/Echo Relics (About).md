---
title: Echo Relics (About)
tags:
  - Software-Engineering
  - Scala
  - Semester-3
date: 2024-10-14
aliases: 
cssclasses:
---
## Concept: 
In _Echo Relics_, two players (or teams) compete to gather ancient artifacts on a mysterious, ever-shifting map. The twist is that every action you take leaves an "echo" behind—a ghostly version of your past moves that either helps or hinders future actions. The objective is to collect the most valuable relics while managing the echoes of your past moves.

### Game Mechanics:

- **Map Layout:** The game takes place on a 5x5 grid of tiles. Each tile hides a relic or a trap, and the map is unknown at the start of the game. The relics have different values, while traps temporarily slow down or immobilize players.
    
- **Turn-Based Movement:** Players move one tile at a time in a chosen direction (north, south, east, west). When a player lands on a tile, they either collect a relic or trigger a trap. The relic is added to their collection and increases their score.
    
- **Echo Mechanic:** Every time a player moves, their "echo" remains on the tile they just left. This echo can interact with the player in future turns. For example:
    
    - If you return to a tile with your own echo, you gain a movement boost (move an extra tile for that turn).
    - If you step on your opponent's echo, you get a temporary debuff (slower movement or reduced score for one turn).
- **Tile Shift:** Every 5 turns, the grid slightly shifts, moving tiles to new locations. This makes the game dynamic, as relics, traps, and echoes change positions. Players must anticipate these shifts and plan their movements accordingly.
    
- **Echo Chains:** If a player revisits tiles with multiple echoes (their own or their opponent’s), they can trigger special chain effects. For example:
    
    - **Positive Chains:** A player’s multiple echoes in sequence can grant extra relics, boost movement speed, or unlock hidden relics on the map.
    - **Negative Chains:** Stepping on several of your opponent's echoes in sequence can cause you to drop relics or trigger hidden traps.
- **Echo Relic:** The rarest and most valuable relic is the "Echo Relic." This relic appears only when a player steps on a tile where both players have left an echo. The first player to grab it scores big points, but triggering the relic could unleash an unpredictable event, such as scrambling the entire map.
    

### Victory Conditions:

- The game ends after a set number of turns (e.g., 30 turns) or when all relics have been collected. The player with the highest score based on the value of their collected relics wins.
- Echo interactions, both positive and negative, play a major role in determining the outcome, making strategy and timing crucial.

### Unique Features:

- The **echo mechanic** adds a layer of long-term planning, as players must consider not just their current moves but how their past moves might help or hinder them in the future.
- The **tile shift** keeps the game dynamic and unpredictable, preventing any player from having a fixed strategy.
- The **relic collection system** rewards exploration and strategy, while the **echo chain system** introduces tactical depth, allowing for combos and planning.