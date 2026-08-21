# token-management Specification

## Purpose
Governs the lifecycle of JWT access and refresh tokens - issuance, expiry, persistence, refresh, and revocation - and how the admin UI stores and presents them.

## Requirements

### Requirement: Access token issuance and expiry
The system SHALL issue signed JWT access tokens with a configurable expiry (default 3600 seconds) on successful login, and SHALL reject expired or invalid access tokens on protected requests.

#### Scenario: Fresh token accepted
- **WHEN** a client presents an unexpired access token issued by login
- **THEN** protected requests succeed with the identity from the token

#### Scenario: Expired token rejected
- **WHEN** a client presents an access token past its expiry
- **THEN** protected requests are rejected as unauthenticated

### Requirement: Refresh token persistence
The system SHALL persist every refresh token issued at login in its own store, separate from user accounts.

#### Scenario: Token persisted at issuance
- **WHEN** a login succeeds and tokens are issued
- **THEN** the refresh token is stored and can later be looked up by its value

### Requirement: Refresh exchange
The system SHALL exchange a valid, non-revoked refresh token for a new access token, and SHALL refuse unknown or revoked refresh tokens.

#### Scenario: Valid refresh token exchanged
- **WHEN** a client presents a stored, non-revoked refresh token to the refresh flow
- **THEN** a new access token is returned for the owning user

#### Scenario: Revoked refresh token refused
- **WHEN** a client presents a refresh token marked revoked
- **THEN** no access token is returned

### Requirement: Client-side token storage
The admin UI SHALL keep the access and refresh tokens in `localStorage`, send the access token as an `Authorization: Bearer` header on protected requests, and remove both tokens from storage on logout.

#### Scenario: Protected request carries bearer header
- **WHEN** the admin UI calls a protected API while signed in
- **THEN** the request includes the stored access token as a Bearer Authorization header

#### Scenario: Storage cleared on sign-out
- **WHEN** logout completes
- **THEN** neither token remains in `localStorage`
