## MODIFIED Requirements

### Requirement: GET /type simplificado
The system SHALL return a simplified list of Pokémon types.

#### Scenario: Successful type list fetch
- **WHEN** a GET request is made to `/type`
- **THEN** the system returns 200 OK with a JSON containing:
  - `count`: Total number of types.
  - `results`: Array of objects with `name` and `url`.
- **AND** the response MUST NOT include `next` or `previous` fields.
