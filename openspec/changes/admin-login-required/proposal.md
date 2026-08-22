# Proposal: admin-login-required

## Why

The admin GUI currently renders every page regardless of authentication state: only the API returns 401s, so unauthenticated visitors see broken dashboards and raw error states instead of being sent to the login page. Additionally, the login form renders the password field as plain text (`type="text"`), exposing credentials to shoulder-surfing.

## What Changes

- Add a client-side auth guard to the admin GUI: any protected route accessed without an authenticated session redirects to `/login`.
- While the auth state is being restored (token validation in flight), protected pages show a loading state instead of flashing protected content.
- After signing in from a guard-triggered redirect, return the user to the page they originally requested.
- Mask the password input on `/login` (`type="password"`) and add a show/hide toggle.
- Record the decision that password confidentiality on the wire is delegated to HTTPS/TLS: the login request body keeps sending the password property as-is, with no client-side hashing or payload encryption (backend BCrypt verification stays unchanged).

## Capabilities

### New Capabilities

None.

### Modified Capabilities

- `jwt-authentication`: The Admin UI sign-in flow requirement is extended with password masking and a show/hide toggle. A new Admin UI route-protection requirement specifies redirecting unauthenticated visits to `/login`, preserving the intended destination across the redirect, and avoiding flashes of protected content during session restore.

## Impact

- **Code**: `afbb-gui/admin` — new shared guard component or hook wrapping protected pages (App Router layout/client components), `app/login/page.tsx` (masked input + toggle), possibly `components/TextField.tsx` (input type support), `context/AuthContext.tsx` / `lib/auth.ts` (expose loading/authenticated state for the guard, returnTo handling).
- **APIs**: None. No backend changes; `POST /auth/login` request/response contract unchanged.
- **Data**: None. Existing seeded credentials remain valid.
