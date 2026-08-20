# Implementation Plan

## Architecture

```
afbb-gui/admin/
├── lib/
│   └── auth.ts                    # Token storage + authenticatedFetch helper
├── context/
│   └── AuthContext.tsx             # React context: user state, login(), logout()
├── components/
│   ├── Layout.tsx                 # Client wrapper: AuthProvider + conditional chrome
│   ├── Header.tsx                 # Modified: user greeting + logout button
│   └── index.ts                   # Modified: added Layout export
├── app/
│   ├── layout.tsx                 # Modified: wraps children with <Layout>
│   └── login/
│       └── page.tsx               # Login form (username + password)
└── ui/
    ├── CategoryForm.tsx           # Modified: fetch → authenticatedFetch
    └── ItemForm.tsx               # Modified: fetch → authenticatedFetch
```

## Components

### `lib/auth.ts` — Token Utilities

Provides `localStorage`-based token management:
- `getAccessToken()` / `getRefreshToken()` — read tokens
- `setTokens(access, refresh)` — write tokens
- `clearTokens()` — remove tokens
- `authenticatedFetch(url, options)` — wraps `fetch` with `Authorization: Bearer` header

### `context/AuthContext.tsx` — Auth State

React context providing:
- `user: User | null` — current user profile from `GET /auth/me`
- `isAuthenticated: boolean` — derived from user state
- `loading: boolean` — true while initial auth check is in progress
- `login(username, password)` — authenticates, stores tokens, fetches user, redirects to `/`
- `logout()` — revokes refresh token, clears tokens, redirects to `/login`

On mount, validates stored tokens by calling `GET /auth/me`. Invalid/expired tokens result in `user = null`.

### `components/Layout.tsx` — Conditional Layout

Client component that:
1. Wraps children with `<AuthProvider>`
2. Uses `usePathname()` to detect `/login` route
3. On `/login`: renders `<main>` only (no Header, Sidebar, Footer)
4. On other routes: renders full grid layout (Header, Sidebar, main, Footer)

### `app/login/page.tsx` — Login Form

Client component using existing `TextField` component:
- Username and password fields (config-driven, same pattern as `CategoryForm`)
- Error message display on failed login
- Submit button with loading state (`"Signing in..."`)
- Centered card layout (`min-h-screen flex items-center justify-center`)

### `app/layout.tsx` — Root Layout

Modified to import `Layout` client component:
- Wraps `{children}` with `<Layout>` (which provides `AuthProvider` + conditional chrome)
- Metadata and font setup unchanged

### `components/Header.tsx` — Logout

Modified to be a client component:
- Uses `useAuth()` to access `user` and `logout`
- Displays `"Hello, {name}"` and a Logout button
- Logout button calls `logout()` from AuthContext

### `ui/CategoryForm.tsx` / `ui/ItemForm.tsx` — Auth Headers

Modified to import and use `authenticatedFetch` instead of bare `fetch` for:
- `POST` (create/update) requests
- `DELETE` requests

Read-only `GET` requests in page components remain unauthenticated (public endpoints).

## Data Flow

```
┌─────────────────────────────────────────────────────┐
│  RootLayout (server component)                      │
│  └── <Layout> (client component)                    │
│      └── <AuthProvider>                             │
│          ├── pathname === '/login'                   │
│          │   └── <main><LoginPage /></main>          │
│          └── pathname !== '/login'                   │
│              └── <div class="layout">               │
│                  ├── <Header />  (user + logout)    │
│                  ├── <Sidebar /> (nav links)        │
│                  ├── <main>{children}</main>         │
│                  └── <Footer />                     │
└─────────────────────────────────────────────────────┘
```
