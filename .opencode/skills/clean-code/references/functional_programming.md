# Functional Programming Paradigm

Key concepts:

- Function: a self-contained block of organized code designed to perform a single, specific task
- Predictability: deterministic behavior, easy to reason about how a function will execute
- Reliability: easy to debug and test
- Modularity: large complex operation can be broken down into smaller, manageable blocks

## Pure Function

Function that produces the same output for the same input and has no observable side effects (like modifying a global variable or performing I/O)

## Immutability

Data cannot be changed after it is created. Instead of modifying existing structures, you create new ones with the desired changes

## First-Class and Higher-Ordered Function

Functions can be assigned to variables, passed as arguments and returned from other functions

## Referential Transparency

A function call can be replaced with its resulting value without changing the program behavior

## Declarative Style

Focuses on "what to solve" rather than "how to solve", often using expressions instead of sequences of commands or statements

## Function Composition

The combination of small, simple functions to build more complex operations

## Recursion Over Loops

Iterative tasks are accomplished through recursive function calls rather than using loops, helping maintain immutability
