## Purpose

Authenticates admin users with username and password against stored user accounts, establishes session identity, and ends sessions via logout - spanning both the auth API and the admin UI flows that consume it.

## ADDED Requirements

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
The admin UI SHALL provide a standalone sign-in page rendered without navigation chrome, and after successful login SHALL store both tokens, load the user profile, and land on the dashboard with full layout.

#### Scenario: Login page renders without chrome
- **WHEN** a user opens `/login`
- **THEN** the sign-in form is shown without Header, Sidebar, or Footer

#### Scenario: Successful sign-in lands on dashboard
- **WHEN** a user submits valid credentials on the login page
- **THEN** both tokens are stored, the profile is fetched, and the dashboard renders with full layout

### Requirement: Admin UI sign-out flow
The admin UI SHALL revoke the stored refresh token server-side, clear all stored tokens, and return to the login page on logout.

#### Scenario: Sign-out clears session
- **WHEN** a logged-in user triggers logout
- **THEN** the refresh token is sent for revocation, stored tokens are cleared, and the browser lands on `/login`
