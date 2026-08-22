# Design: admin-login-required

## Context

The admin GUI (`afbb-gui/admin`, Next.js App Router) keeps tokens in localStorage and renders every route regardless of auth state; only APIs return 401s. Auth state lives in `AuthProvider` (`user`, `loading`, `isAuthenticated`), and `Layout.tsx` is already the auth-aware shell that special-cases `/login`. `TextField` supports a `type` prop but only `text | textarea | number` — no password mode. See proposal.md - Why.

## Goals / Non-Goals

**Goals:**

- Redirect unauthenticated visits to protected admin pages to `/login` without rendering protected content.
- Preserve the originally requested path across the redirect and land there after sign-in.
- Mask the password input on `/login` with a show/hide toggle.
- Keep the auth API contract and backend untouched.

**Non-Goals:**

- No payload-level password encryption/hashing on the wire — confidentiality is delegated to HTTPS/TLS (decided with user); backend BCrypt verification over the raw password stays unchanged.
- No migration of tokens from localStorage to httpOnly cookies.
- No role-based page guards (any authenticated user passes; API still enforces authorization).
- No server-side enforcement of the redirect (client guard only).

## Decisions

1. **Guard in `Layout.tsx` via a small `useRequireAuth` hook, not Next.js middleware.**
   Middleware runs server-side and cannot read tokens from localStorage; adopting cookies to enable it would be a cross-cutting change out of scope. A per-page wrapper or route-group layout would touch/move every page file. The existing shell already branches on `/login`, so adding "while `loading` → render `<Spinner/>`; if unauthenticated → redirect" there is the smallest central change covering all pages at once. Alternatives considered and rejected: `middleware.ts` (can't see localStorage), `app/(protected)/layout.tsx` (bulk file moves, same behavior).

2. **Reuse `AuthProvider.loading` as the anti-flash gate.**
   `AuthProvider` initializes `loading = true` and flips it only after `/auth/me` resolves, so the first paint of any protected route shows the existing `Spinner` instead of content — satisfying the no-flash scenario during both hydration and session restore. No new state machine is introduced. An expired access token makes `/auth/me` fail, which naturally routes the visitor to `/login`.

3. **Carry the intended destination as a `returnTo` query parameter.**
   On redirect: `/login?returnTo=<pathname>`. After successful login, the login page navigates to `returnTo` when present, else `/` (dashboard). The value is used only if it is a local path (starts with a single `/`) to rule out open redirects. Alternatives considered: sessionStorage key (invisible in URL, harder to reason about, same effort).

4. **Password masking by extending `TextField`, toggle owned by the field.**
   Add `'password'` to the `TextFieldProps.type` union so the input renders with `type="password"` — no new component. The show/hide control lives inside `TextField` (small icon button beside the input) so any future password field gets consistent behavior; it toggles between `password` and `text` input types and exposes `aria-label`/`aria-pressed`. Alternative considered: a bespoke password input just for the login page — rejected as duplication.

5. **Login request body unchanged.**
   `AuthContext.login` keeps posting `{ username, password }` JSON. No hashing/encryption client-side (would break BCrypt verification and invalidate seeded credentials); TLS provides transport confidentiality.

## Risks / Trade-offs

- [Client-side guard is UX, not a security boundary] → Protected data remains gated by API 401s (`endpoint-protection` spec); document that the guard only prevents rendering, not access.
- [`returnTo` abuse as an open redirect] → Only accept values matching a local-path check before navigating.
- [Guard logic duplicated if another shell appears] → Confine redirect/spinner logic to the single hook used by `Layout.tsx`; revisit only if a second layout is introduced.
- [Masked input toggled to plain text on shared screens] → Toggle is explicit, off by default each mount; acceptable for this admin tool.

## Migration Plan

Frontend-only change in `afbb-gui/admin`; deploy with the next release. Rollback is a revert; no data, config, or backend coordination needed.

## Open Questions

None.
