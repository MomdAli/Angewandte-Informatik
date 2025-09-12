---
{"publish":true,"draft":true,"created":"2024-11-21T21:22:40.051+01:00","modified":"2025-09-12T21:25:02.901+02:00","published":"2025-09-12T21:25:02.901+02:00","tags":["excalidraw"],"cssclasses":""}
---

==⚠  Switch to EXCALIDRAW VIEW in the MORE OPTIONS menu of this document. ⚠== You can decompress Drawing data with the command palette: 'Decompress current Excalidraw file'. For more info check in plugin settings under 'Saving'


# Excalidraw Data
## Text Elements
Config: 
    - Player Amount (Default = 2)[2 - 4 Players]
    - Max movements (0 if no Max movements)
    - Grid size (Default = 10)[Size > 10]
    - Relic spawn rate (Every 15 moves)
    - Echo incrementor spawn rate (Every 3 moves)
    - Minimum Health (Default = 3)

Win Condition:
    - if player reached n amounts of score
    - when opponent has no health
    - after n amounts of movements, the player 
        with the most relics wins

Stats:
    - Relic counter
    - Echo counter
    - health

Random Generator:
    - generate random movement
    - generate random item position

Grid-Generatros:
    - Wall Generator
    - Relic Generator
    - Random Position Generator

Echo:
    - Radius
    - item steal in radius
    - random movement
    - health decrease on collision
    - SUPERECHO

Relic:
    - Like score
    - Scores: 1, 2, 3, 5
    - the bigger the score, the rare it is ^KDvD8yVy

Design Pattern 7:
Command Pattern where it stores all commands history. Each Command has its own undo (and probably redo). 

For Game Idea:
There could be a time card where if we use it, it time travels (most probably in the past) using the undo command pattern and maybe spawns in an echo? ^XAOciERX

