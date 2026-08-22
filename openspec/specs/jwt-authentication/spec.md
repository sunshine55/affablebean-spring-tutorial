# jwt-authentication Specification

## Purpose
Authenticates admin users with username and password against stored user accounts, establishes session identity, and ends sessions via logout - spanning both the auth API and the admin UI flows that consume it.

## Requirements

### Requirement: Login with username and password
The system SHALL authenticate users by username and password against persisted user accounts and, on success, return an access token and a refresh token together with the token type and expiry. Successful login SHALL update the user's last-login timestamp.

#### Scenario: Successful login
- **WHEN** a client submits valid credentials for an active user to `POST /auth/login`
- **THEN** the response is `200` with an access token, a refresh token, the token type, and the expiry, and the user's last-login timestamp is updated

#### Scenario: Wrong password
- **WHEN** a client submits credentials whose password does not match the stored hash
- **THEN** the response is `401` and no tokens are issued

#### Scenario: Unknown username
- **WHEN** a client submits a username that does not exist
- **THEN** the response is `401` and no tokens are issued

#### Scenario: Inactive user
- **WHEN** a client submits valid credentials for a user marked inactive
- **THEN** the response is `401` and no tokens are issued

### Requirement: Current user lookup
The system SHALL return the authenticated caller's profile (name, email, username, roles) when presented with a valid bearer token, and SHALL NOT expose the password hash.

#### Scenario: Profile returned without secrets
- **WHEN** a client calls `GET /auth/me` with a valid bearer token
- **THEN** the response is `200` with the user's profile and no password hash

#### Scenario: Missing or invalid token
- **WHEN** a client calls `GET /auth/me` without a bearer token or with an invalid one
- **THEN** the request is rejected as unauthenticated

### Requirement: Logout revokes the refresh token
The system SHALL mark the presented refresh token revoked on `POST /auth/logout`, after which the token MUST NOT yield new access tokens. Logout SHALL succeed even if the token is unknown.

#### Scenario: Revocation succeeds
- **WHEN** a client posts a previously issued refresh token to `POST /auth/logout`
- **THEN** the response is `200` and the token can no longer be used to obtain access tokens

#### Scenario: Unknown refresh token
- **WHEN** a client posts a refresh token that was never issued
- **THEN** the response is `200`

### Requirement: Admin UI sign-in flow
The admin UI SHALL provide a standalone sign-in page rendered without navigation chrome. The password input SHALL be masked by default and SHALL offer a visible control to toggle between masked and plain-text display. After successful login the UI SHALL store both tokens, load the user profile, and navigate to the dashboard, or to the destination carried over from an authentication redirect when one is present.

#### Scenario: Login page renders without chrome
- **WHEN** a user opens `/login`
- **THEN** the sign-in form is shown without Header, Sidebar, or Footer

#### Scenario: Password input is masked by default
- **WHEN** the sign-in page renders
- **THEN** typed password characters are hidden behind a mask instead of being displayed as plain text

#### Scenario: Password visibility can be toggled
- **WHEN** the user activates the show/hide control on the password field
- **THEN** the field alternates between masked and plain-text display of its contents

#### Scenario: Successful sign-in lands on dashboard
- **WHEN** a user submits valid credentials on the login page opened directly, with no redirect origin
- **THEN** both tokens are stored, the profile is fetched, and the dashboard renders with full layout

### Requirement: Admin UI requires authentication
The admin UI SHALL restrict every admin page except `/login` to authenticated sessions. An unauthenticated visit to a protected page SHALL be redirected to `/login`, carrying the originally requested path so it can be restored after sign-in. While session state is still being restored, protected pages SHALL NOT render their content.

#### Scenario: Unauthenticated visit redirects to login
- **WHEN** a user without an authenticated session opens a protected admin route
- **THEN** the browser lands on `/login` and the originally requested path is carried over

#### Scenario: Protected content does not flash during session restore
- **WHEN** the session state is still being restored while a protected route renders
- **THEN** no protected content is shown until authentication is resolved

#### Scenario: Authenticated access renders the page
- **WHEN** an authenticated user opens a protected admin route
- **THEN** the requested page renders normally

#### Scenario: Sign-in returns to the originally requested page
- **WHEN** a user signs in successfully after being redirected from a protected route
- **THEN** the browser lands on the originally requested page

### Requirement: Admin UI sign-out flow
The admin UI SHALL revoke the stored refresh token server-side, clear all stored tokens, and return to the login page on logout.

#### Scenario: Sign-out clears session
- **WHEN** a logged-in user triggers logout
- **THEN** the refresh token is sent for revocation, stored tokens are cleared, and the browser lands on `/login`
