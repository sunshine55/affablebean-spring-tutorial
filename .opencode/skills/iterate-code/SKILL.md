---
name: iterate-code
description: guidelines to prevent overthinking when analyzing complex requests
---

# Iterate Code

- Caution over speed
- Prefer small, simple changes that achieve the goal
- Use judgement for trivial tasks

## Think First

- DO NOT assume; state assumptions. If unclear, stop and ask
- If multiple interpretations exist, list them; DO NOT pick silently
- Call out simpler options or tradeoffs; push back if warranted

## Simplicity

- Design minimum code that satisfies the request
- No extra features, abstractions, flexibility or speculative handling

## Surgical Edits

- DO NOT refactor/delete unrelated code, comments or format
- DO NOT delete "dead code" without user confirmation
- Touch only what's required; match existing style
- Every changes must map to the request

## Goal Driven

- Define verifiable success criteria; iterate until verified
- Bug: add reproduced test -> fix -> pass
- Validation: add failing tests -> implement -> pass
- Refactor: tests pass before + after
- Give a brief checklist for multistep work
