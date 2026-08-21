# endpoint-protection Specification

## Purpose
Defines which catalog operations on categories and items are publicly readable and which require an authenticated caller, across the API and the admin UI that consumes it.

## Requirements

### Requirement: Public read access to catalog data
The system SHALL allow unauthenticated `GET` requests for categories and items, including lookups by id and by category.

#### Scenario: Anonymous list request
- **WHEN** a client calls `GET /categories` or `GET /items` without any token
- **THEN** the response is `200` with the requested data

#### Scenario: Anonymous filtered lookup
- **WHEN** a client calls item lookup by category or by id without any token
- **THEN** the response is `200` with the matching data

### Requirement: Authenticated writes to catalog data
The system SHALL require a valid bearer token for `POST` and `DELETE` on categories and items, and SHALL reject such requests without one.

#### Scenario: Authenticated create succeeds
- **WHEN** a client posts new category or item data with a valid bearer token
- **THEN** the write is performed and the response is success

#### Scenario: Unauthenticated write rejected
- **WHEN** a client attempts `POST` or `DELETE` on categories or items without a token
- **THEN** the request is rejected as unauthenticated and no data changes

### Requirement: Admin UI honors protection rules
The admin UI SHALL send the Authorization header on all protected calls (create/delete of categories and items) and SHALL NOT send it on public reads.

#### Scenario: Signed-in user creates a category
- **WHEN** a signed-in user submits a new category in the admin UI
- **THEN** the create request carries the Bearer token and succeeds

#### Scenario: Signed-out visitor browses catalog
- **WHEN** an unauthenticated visitor loads category or item listings
- **THEN** the reads succeed without any Authorization header
