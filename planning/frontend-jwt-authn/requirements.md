# Requirements

## Overview

Add JWT-based login/logout to the `afbb-gui/admin` frontend, consuming the backend auth API (`POST /auth/login`, `GET /auth/me`, `POST /auth/logout`).

## Authentication Flow

1. User visits any page → app checks for stored JWT access token
2. If no token or token invalid → show login page (no Header/Sidebar/Footer)
3. User submits username/password → `POST /auth/login`
4. On success → store `access_token` and `refresh_token` in `localStorage`, fetch user profile via `GET /auth/me`
5. Redirect to dashboard (`/`) with full layout (Header, Sidebar, Footer)
6. Logout → `POST /auth/logout` with refresh token → clear `localStorage` → redirect to `/login`

## API Endpoints Consumed

| Method | Endpoint | Auth | Request | Response |
|---|---|---|---|---|
| `POST` | `/auth/login` | Public | `{"username","password"}` | `{"access_token","refresh_token","token_type","expires_in"}` |
| `GET` | `/auth/me` | Bearer token | — | `{id, name, email, username, roles, ...}` |
| `POST` | `/auth/logout` | Public | `{"refreshToken"}` | 200 OK |

## Token Management

- Access token and refresh token stored in `localStorage`
- Access token sent as `Authorization: Bearer <token>` header on protected requests
- Refresh token sent in `POST /auth/logout` body for server-side revocation
- Tokens cleared from `localStorage` on logout

## Pages

| Route | Description | Auth Required |
|---|---|---|
| `/login` | Login form (centered card, no chrome) | No |
| `/` | Dashboard (with Header, Sidebar, Footer) | Yes |
| `/categories/*` | Existing CRUD pages (with Header, Sidebar, Footer) | Yes |

## Protected API Calls

- `POST /categories`, `DELETE /categories` — require `Authorization` header
- `POST /items`, `DELETE /items` — require `Authorization` header

## Seed Users

| Username | Password | Roles |
|---|---|---|
| `root` | `root123` | `["admin"]` |
| `admin` | `admin123` | `["user"]` |

## Technology

- Next.js 16.3.1 (App Router)
- React 19.2.8
- Tailwind CSS v4
- Zod 4.4.3 (existing form validation)
