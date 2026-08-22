## 1. Auth guard (route protection)

- [x] 1.1 Add `useRequireAuth` hook in `afbb-gui/admin` that consumes `AuthProvider` state and redirects to `/login?returnTo=<current-path>` when `loading` is false with no user; verify with `npm run build` in `afbb-gui/admin` (type check passes)
- [x] 1.2 Wire the hook into `components/Layout.tsx`: render the existing `Spinner` while session restore is in flight on protected routes, render children only when authenticated; verify by clearing localStorage, opening `/` and `/categories`, and observing redirect to `/login?returnTo=...` with no flash of protected content
- [x] 1.3 In `app/login/page.tsx`, read `returnTo` via `useSearchParams`, pass it to `login`, and navigate to it after success only when it starts with a single `/`; verify: redirected sign-in from `/categories` lands on `/categories`, direct `/login` sign-in still lands on `/`

## 2. Password masking

- [x] 2.1 Extend `TextFieldProps.type` union with `'password'` and add a show/hide toggle button inside `TextField` for password fields (`aria-label`, `aria-pressed`) that switches the input between `password` and `text`; verify with `npm run build` and by checking the input type toggles in dev
- [x] 2.2 Set the login page's Password field to `type: 'password'`; verify typed characters are masked on mount and the toggle shows/hides them

## 3. End-to-end verification

- [x] 3.1 Run `npm run build` in `afbb-gui/admin` and walk all scenarios in `specs/jwt-authentication/spec.md` of this change against a running stack (`docker compose up` in `afbb-db`, API + admin GUI) covering: unauthenticated redirect, no-flash during restore, returnTo landing, masked default, show/hide toggle
