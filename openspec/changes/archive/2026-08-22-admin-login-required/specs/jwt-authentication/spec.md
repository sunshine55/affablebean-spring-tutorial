## MODIFIED Requirements

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

## ADDED Requirements

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
