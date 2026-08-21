## 1. Spec Review

- [x] 1.1 Walk each delta spec against the shipped implementation (code-level verification per user direction - no runtime): login success/failure paths, `/auth/me`, logout revocation, refresh persistence/exchange, public reads vs authenticated writes, UI flows; one planning-doc claim corrected (no unauthenticated-redirect gate exists)
- [x] 1.2 Confirm no planning-doc-only claims leaked into specs (e.g., `username` in login response) and verify with `openspec validate backfill-auth-specs --strict`

## 2. Archive

- [x] 2.1 Run `openspec archive backfill-auth-specs` and verify the three capabilities appear under `openspec/specs/` with Purpose sections intact
- [x] 2.2 Spot-check merged main specs for delta artifacts (no `## ADDED` headers left behind) via `openspec show <capability> --type spec`
