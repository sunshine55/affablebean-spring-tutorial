# Code Smell Guidelines

During reviewing/refactoring, pay attention to the low maintainability code classification as below

## Bloaters

- Methods and classes that have increased to such gargantuan proportions that they are hard to work with. These smells usually do not crop up right away, rather they accumulate over time as program evolves and especially when nobody makes effort to eradicate them
- Examples: long method, primitive obsession, data clumps, large class, long parameter list

## Object Oriented Abusers

- These smells are incomplete or incorrect application of object-oriented progamming principles
- Examples: alternative classes with difference interfaces, refused bequest, switch statements, temporary field

## Change Preventers

- These smells mean that if you need to change something in one place in your code, you have to make many changes in other places too. Progam development becomes much more complicated and expensive as a result
- Examples: divergent change, parallel inheritance hiearchy, shotgun surgery

## Dispensables

- Something pointless and unneeded whose absence would make the code cleaner, more efficient and easier to understand
- Examples: comments, duplicate code, data class, dead code, lazy class, speculative generality

## Couplers

- These smells contribute to excessive coupling between classes or show what happens if coupling is replaced by excessive delegation
- Examples: feature envy, inappropriate intimacy, incomplete library class, message chains, middle man
