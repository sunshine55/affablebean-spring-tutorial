# OOP Paradigm

Key concepts:

- Class and Object: A class is a blueprint/template. An object is an instance of that class. Objects have attributes (data/state) and methods (behavior)
- Encapsulation: Bundle data (variables) and methods together, restrict direct access to some components to protect the object internal state
- Inheritance: Allow a new class (subclass/child) to acquire the properties and methods of an existing class (superclass/parent), promoting code reuse
- Polymorphism: Allow different objects to respond to the same method call in their own specific ways
- Abstraction: Hide complex implementation details and show only necessary features of an object to user

## Single Responsibility Principle (SRP)

- A class should have one reason to change
- Ensure that a class focuses on a single functionality (high cohesion)

## Open/Closed Principle (OCP)

- Entities (classes, modules, functions) should be open for extension but closed for modification
- Ensure that newly added functionalities will not breake existing tested code

## Liskov Substitution Principle (LSP)

- Subtypes must be substitutable for their base types without altering the correctness of the program
- Ensure that a derived class does not break the functionality of the parent class

## Interface Segregation Principle (ISP)

- Clients should not be forced to depend on methods they do not use
- Ensure that small, specific interfaces are created rather than large, general purpose interfaces

## Dependency Inversion Principle (DIP)

- Depend on abstractions (interfaces) rather than concrete implementations (classes)
- Ensure that high-level modules and low-level modules are decoupled (enhance code flexibility)

## You Aren't Gonna Need It (YAGNI)

- Only necessary features are implemented, avoid over-engineering for anticipated future needs
- Ensure simple design, reduce unncessary coupling hiearchies of classes/methods, make code easier to maintain and test

## Don't Repeat Yourself (DRY)

- Every piece of knowledge or logic should have a single, authoritative representation
- Reduce code complexity and improve maintainability, easier to debug
