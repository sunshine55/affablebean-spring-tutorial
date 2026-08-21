---
name: format-code
description: specific code styles apply to current repo when writing code
---

# Format Code

## When to Use

- Write new code
- Modify existing code

## Java

- DO NOT use wildcard imports
- Always declare access modifier (public/protected/private) explicitly for classes, methods and fields
- 4 spaces indentation

## Java JUnit Tests

- **Organize by method under test:** Group test cases by the method; use `@Nested` class only when that method has **2+ scenarios**; otherwise keep a **flat** single `@Test`
- **Naming rules:** Nested classes are named `<MethodName>Test` (camel case). Inside them, name tests by order (e.g.: `test1()`, `test2()`...) and put the scenario description in `@DisplayName`. For flat single-scenario tests, name the test method **exactly** as the method under test (e.g.: `getCategories()`)
- **DisplayName restriction:** Do NOT add `@DisplayName` to nested classes or to flat single-scenario tests
- **Safety + data + scope:** Refactors for grouping/naming must NOT change assertions/behaviors. If test data is needed, store JSON in `src/test/resources/json` and load them. Keep tests minimal-cover key lines/branches without unnecessary scenarios
- **Build discipline:** Always run Maven clean cycle before testing/compiling.

## ECMAScript, TypeScript and JavaScript

- Follow existing code style in the file being edited
- 2 spaces indentation
- Single quotes for strings, except when the string contains a single quote character
