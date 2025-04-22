# P3 - Turing Machine Simulator

## Authors
- Vladyslav (Vlad) Maliutin
- Reggie Wade

## Overview
This project implements a **bi-infinite deterministic Turing Machine simulator** in Java. It reads a machine definition and input string from a file, simulates execution on a bi-directional tape, and prints the resulting content of the visited tape cells. The machine halts upon reaching the designated halting state.

## Files Included
- `TMSimulator.java` — The main class to execute the simulation.
- `TM.java` — Core class for managing states, transitions, and machine logic.
- `TMState.java` — Represents an individual Turing Machine state with transition mappings.
- `Transition.java` — Represents a transition between states including direction and symbol to write.
- `Tape.java` — Simulates the infinite tape with head movement, reading, and writing.
- `README.md` — This documentation file.

## Features Implemented

- **Bi-infinite Tape Simulation**  
  The tape supports infinite movement in both left and right directions using a `HashMap` to dynamically store visited cells.

- **Deterministic Transition Handling**  
  Each state processes exactly one transition per symbol, enforcing deterministic behavior.

- **TM Configuration Parser**  
  The program reads Turing Machine definitions from a file including state count, input alphabet, transitions, and input string.

- **State and Transition Management**  
  Clean object-oriented modeling of states (`TMState`) and transitions (`Transition`) to maintain modularity.

- **Blank Symbol Support (`0`)**  
  The blank symbol (`0`) is automatically included in the tape alphabet and treated as the default symbol.

- **Halting Detection**  
  The simulator halts upon reaching the final (highest-numbered) state as defined by the TM specification.

- **Output of Visited Tape Content**  
  Only visited (non-default) tape cells are printed, representing the effective result of TM execution.

- **Edge Case Handling**  
  Supports both non-empty and ε (empty) inputs, including machines that halt immediately.

- **Efficient Execution**  
  Optimized data structures (like `LinkedHashMap`, `HashMap`, `Set`) are used to ensure fast lookup and low overhead for large simulations.

## Reflection

Working on this Turing Machine simulator helped reinforce our understanding of automata theory and low-level computation models. By breaking down the simulation into clearly defined classes like `TM`, `TMState`, `Transition`, and `Tape`, we got to apply core object-oriented principles in a meaningful way.

One of the most interesting parts was designing the tape as a bi-infinite structure using a `HashMap`. It made us think about how the tape worked in an intuitive way with the keys being the head as it moved through the turing machine.  The time complexity is at best O(1) to access an element of a hashmap and O(N) time complexity at worst.  We don't think this was the most effective implementation, but it works and it's simple to understand and write.

We thought the hardest part of creating the project was actually the parsing of the text file.  The logic was a bit difficult to get down even though we had a good idea of how the file was structured.  Specifically incrementing the state only after |sigma| number of transition information was consumed was difficult to represent in code.  Overall, since this is only my second semester of using java, the file reading was the hardest.

Surprisingly the easiest part of the code was the actual logic for the turing machine itself, all we had to do was think through the steps and represent each in code.  Once we had everything in its correct place in code, we just had to access them at a certain time.

This project gave us some insight into how turing machines are implemented and increased our understanding of them.  We thought this project was great!


## Compilation Instructions
Make sure all `.java` files are in the same directory. Then compile with:
```bash
javac tm/*.java
```

Run the program with:
```bash
java tm.TMSimulator inputfile.txt
```