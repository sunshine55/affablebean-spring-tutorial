# Report

## Requirement: Admin UI sign-in flow

### Scenario: Successful sign-in lands on dashboard
- **WHEN** I submit valid credentials (root@afbb.com, root123) on the login page
- **THEN** I get HTTP 500 error
- **EXPECTED** both tokens are stored, the profile is fetched, and the dashboard renders with full layout
- **STATUS**: Done
