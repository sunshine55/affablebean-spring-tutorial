# Implementation Plan

## Architecture

```
auth/
├── entity/
│   ├── SystemUserEntity.java        # @MappedEntity("system_user")
│   └── RefreshTokenEntity.java      # @MappedEntity("refresh_token")
├── dao/
│   ├── SystemUserDao.java           # findByUsername()
│   └── RefreshTokenDao.java         # findByRefreshToken()
├── dto/
│   ├── LoginRequest.java            # username + password
│   └── RefreshRequest.java          # refreshToken
├── service/
│   ├── UserPasswordEncoder.java     # BCrypt wrapper
│   ├── AuthenticationProviderUserPassword.java  # HttpRequestAuthenticationProvider
│   └── RefreshTokenPersistenceImpl.java         # RefreshTokenPersistence
└── controller/
    └── AuthController.java          # /auth/login, /auth/me, /auth/logout
```

## Dependencies Added

| Dependency | Scope | Purpose |
|---|---|---|
| `io.micronaut.security:micronaut-security-jwt` | compile | JWT authentication support |
| `io.micronaut.security:micronaut-security-processor` | annotation processor | JSR 250 `@Secured` support |
| `io.micronaut.reactor:micronaut-reactor` | compile | Reactive types for RefreshTokenPersistence |
| `org.springframework.security:spring-security-crypto:6.4.3` | compile | BCrypt password hashing |

## Configuration

### application.yml
```yaml
micronaut:
  security:
    enabled: true
    authentication: bearer
    token:
      jwt:
        signatures:
          secret:
            generator:
              secret: '"${JWT_SECRET:pleaseChangeThisSecretForANewOne}"'
        generator:
          access-token:
            expiration: 3600
          refresh-token:
            secret: '"${JWT_REFRESH_SECRET:pleaseChangeThisSecretForANewOne}"'
```

### application-local.yml
Hardcoded dev secrets for local development.

## Authentication Flow

1. Client sends `POST /auth/login` with `{"username":"...", "password":"..."}`
2. `AuthController` creates `UsernamePasswordCredentials` and delegates to `Authenticator`
3. `Authenticator` runs through `AuthenticationProviderUserPassword`
4. Provider looks up user by username via `SystemUserDao`
5. Verifies `active == true` and BCrypt password match
6. On success: updates `lastLoginAt`, returns `AuthenticationResponse.success(username)`
7. `AuthController` uses `AccessRefreshTokenGenerator` to produce JWT tokens
8. Returns `AccessRefreshToken` with `access_token`, `refresh_token`, `token_type`, `expires_in`

## Refresh Token Flow

1. `RefreshTokenPersistenceImpl` listens for `RefreshTokenGeneratedEvent`
2. Persists token to `refresh_token` collection via `RefreshTokenDao`
3. On refresh request: validates token exists and `revoked == false`
4. Returns `Authentication` for the token owner
5. Logout: sets `revoked = true` on the refresh token document

## Security Annotations

- `@Secured(SecurityRule.IS_ANONYMOUS)` on GET methods (public)
- `@Secured(SecurityRule.IS_AUTHENTICATED)` on POST/DELETE methods (authenticated)
- `@Secured(SecurityRule.IS_ANONYMOUS)` on `AuthController` class (all auth endpoints are public)
- `@Secured(SecurityRule.IS_AUTHENTICATED)` on `AuthController.me()` method only

## Seed Data Changes

- Added `db.refresh_token.drop()` to `init.js`
- Removed `refreshToken` field from `system_user` documents (now in separate collection)
