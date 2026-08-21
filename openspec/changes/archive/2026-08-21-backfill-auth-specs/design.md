## Context

The auth system is shipped and verified; `planning/**` documents describe it but predate OpenSpec conventions. `openspec/specs/` is empty. Code was inspected to resolve ambiguities (`AuthController.java`, `CategoryController.java`, `ItemController.java`, `application.yml`). See proposal.md for motivation.

## Goals / Non-Goals

**Goals:**
- Establish living specs that match observed behavior, not aspirational plans
- Organize capabilities by concern so future auth work has a stable home

**Non-Goals:**
- No code changes, no behavior changes
- Not migrating or deleting `planning/**`
- Not documenting implementation internals (framework classes, config keys) in specs

## Decisions

- **Split capabilities by concern, not by repo** (user-selected Option 2): `jwt-authentication`, `token-management`, `endpoint-protection`. Alternative - one spec per repo (`backend-auth`, `frontend-auth`) - was rejected because backend/frontend share one token contract; splitting by repo would duplicate requirements and let them drift.
- **Code wins over planning docs on conflict**: login response is described without a `username` field (the controller returns the framework token payload); the refresh exchange is specified as behavior of the framework-provided flow backed by persisted tokens, since no custom refresh controller exists.
- **Frontend behaviors live inside the same capabilities** as scenarios (UI sign-in/sign-out under `jwt-authentication`, storage rules under `token-management`, header handling under `endpoint-protection`) rather than a separate UI capability, keeping each requirement testable end-to-end.
- **`planning/**` stays untouched** as historical reference; archive provenance comes from this change directory.

## Risks / Trade-offs

- [Specs drift from code if auth evolves] → Future auth changes go through OpenSpec changes targeting these capabilities; specs are now the entry point.
- [Refresh-exchange behavior inferred from framework integration] → Scenario written at behavior level (valid/revoked/unknown), verifiable by black-box test without naming endpoints beyond the framework default.

## Open Questions

(none)
