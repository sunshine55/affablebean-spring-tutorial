# Requirements

## Overview

Add JWT-based authentication and authorization to the Affable Bean (afbb) application, enabling login/logout functionality backed by MongoDB `system_user` data.

## Database

- New `system_user` collection with fields: `name`, `email`, `username`, `password` (bcrypt), `active`, `roles`, `createdAt`, `updatedAt`, `lastLoginAt`
- New `refresh_token` collection for persisting refresh tokens (separate from system_user)
- Seed 2 users: `root` (password: `root123`, roles: `["admin"]`) and `admin` (password: `admin123`, roles: `["user"]`)

## Authentication

- JWT access tokens with configurable expiration (default 3600s)
- Refresh tokens for obtaining new access tokens without re-login
- BCrypt password verification against stored hashes
- Login updates `lastLoginAt` timestamp

## API Endpoints

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| `POST` | `/auth/login` | Public | Authenticate and receive JWT tokens |
| `GET` | `/auth/me` | Bearer token | Get current authenticated user info |
| `POST` | `/auth/logout` | Public | Revoke refresh token |

## Endpoint Security

- `GET` on `/categories` and `/items` — public (no token required)
- `POST` and `DELETE` on `/categories` and `/items` — requires valid Bearer token

## Technology

- Micronaut 5.1.0 framework
- MongoDB via `micronaut-data-mongodb`
- `micronaut-security-jwt` for JWT support
- `spring-security-crypto` for BCrypt
- `micronaut-reactor` for reactive token persistence
