## Why

JWT authentication for the Affable Bean app (backend `afbb-ws`, frontend `afbb-gui/admin`) is fully implemented and tested, but its behavior lives only in `planning/` documents that do not follow OpenSpec conventions. The project has an empty `openspec/specs/` tree, so there is no living source of truth for what the auth system must do.

## What Changes

- Backfill current-state specs from `planning/backend-jwt-authn/` and `planning/frontend-jwt-authn/` into three new capability specs, split by concern rather than by repo:
  - `jwt-authentication` — credential validation, login, session identity, logout
  - `token-management` — access token properties, refresh token lifecycle, client-side token storage
  - `endpoint-protection` — public reads vs authenticated writes on categories/items
- Backend and frontend behaviors appear as scenarios within these capabilities (the monorepo is one system).
- No code changes: this change documents already-shipped behavior. All requirements are `## ADDED` deltas sourced from verified implementation (code inspected: `AuthController.java`, `CategoryController.java`, `ItemController.java`, `application.yml`).

## Capabilities

### New Capabilities

- `jwt-authentication`: Username/password login against MongoDB `system_user`, current-user lookup (`GET /auth/me`), and refresh-token revocation on logout (`POST /auth/logout`), plus the admin UI login/logout flow.
- `token-management`: JWT access token issuance/expiry, bearer usage, refresh token persistence/validation/revocation, and localStorage handling in the admin UI.
- `endpoint-protection`: Public GET access to categories/items; authenticated POST/DELETE; admin UI attaching Authorization headers to protected calls.

### Modified Capabilities

(none — spec tree is empty today)

## Impact

- Docs only: creates `openspec/specs/{jwt-authentication,token-management,endpoint-protection}/spec.md` when archived.
- No application code, API contracts, dependencies, or data change.
- Source material `planning/**` remains untouched; it becomes historical reference.
