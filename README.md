# Library Management System (Java)

A simple console project that shows OOP and Java concepts.

| Concept | Where used |
|---|---|
| Inheritance | `Book`, `Magazine` extend `Item` |
| Method overriding | `lateFee()`, `type()` in `Book` / `Magazine` |
| Interface | `Borrowable` (implemented by `Item`) |
| Collections | `LinkedHashMap`, `ArrayList` in `Library` |
| Multithreading | `AutoSaver` thread, two-thread demo, `synchronized` in `Item` |
| Exception handling | `ItemNotAvailableException`, `NumberFormatException`, `IOException` |
| Java NIO | `Path`, `Paths`, `Files` in `Library` and `Main` |

## Run
```
javac *.java
java Main
```
