---
{"publish":true,"draft":true,"created":"2024-11-22T19:06:39.813+01:00","modified":"2025-09-12T21:25:02.765+02:00","published":"2025-09-12T21:25:02.765+02:00","cssclasses":""}
---

1. **Observer Pattern**:
    
    - **Classes**: `EventManager`, `EventListener`, `Controller`, `TUI`
    - **Usage**:
        - `EventManager` and `EventListener`: Lines 1-20
        - `Controller`: Lines 1-5, 13-15
        - `TUI`: Lines 1-5, 18-20, 33-36
2. **State Pattern**:
    
    - **Classes**: `GameManager`, `RunningManager`, `PausedManager`, `EndManager`, `GridSizeManager`
    - **Usage**:
        - `GameManager`: Lines 1-25
        - `RunningManager`: Lines 1-20, 85-90
        - `PausedManager`: Lines 1-20
        - `EndManager`: Lines 1-20
        - `GridSizeManager`: Lines 1-26
3. **Command Pattern**:
    
    - **Classes**: `Command`, `InputHandler`
    - **Usage**:
        - `Command`: Lines 1-5
        - `InputHandler`: Lines 6-37
4. **Factory Pattern**:
    
    - **Classes**: `GameManager`, `Config`
    - **Usage**:
        - `GameManager`: Lines 1-25
        - `Config`: Lines 1-20, 77-90
5. **Singleton Pattern**:
    
    - **Classes**: `Config`
    - **Usage**:
        - `Config`: Lines 1-20
6. **Strategy Pattern**:
    
    - **Classes**: `DisplayRenderer`
    - **Usage**:
        - `DisplayRenderer`: Lines 1-20, 26-30, 43-50, 64-70
7. **Builder Pattern**:
    
    - **Classes**: `LineReaderBuilder`
    - **Usage**:
        - `LineReaderBuilder`: Lines 12-20