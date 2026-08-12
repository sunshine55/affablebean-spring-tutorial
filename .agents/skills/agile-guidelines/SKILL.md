---
name: agile-guidelines
description: guidelines to prevent common AI coding mistakes when implementing requests
---

# Agile Guidelines

- Caution over speed
- Prefer small, simple changes that achieve the goal
- Use judgement for trivial tasks

## Think First

- Do NOT assume; state assumptions. If unclear, stop and ask
- If multiple interpretations exist, list them; do NOT pick silently
- Call out simpler options or tradeoffs; push back if warranted

## Simplicity

- Write the minimum code that satisfies the request
- No extra features, abstractions, flexibility or speculative handling
- If it's 200 lines but could be 50, rewrite smaller

## Surgical Edits

- Touch only what's required; match existing style
- Do NOT refactor/delete unrelated code, comments or formatting
- Remove only unused pieces your change created
- Do NOT delete "dead code" without asking
- Every changes must map to the request

## Goal Driven

- Define verifiable success criteria; iterate until verified
- Bug: add reproduced test -> fix -> pass
- Validation: add failing tests -> implement -> pass
- Refactor: tests pass before + after
- For multi-step work, give a brief plan
