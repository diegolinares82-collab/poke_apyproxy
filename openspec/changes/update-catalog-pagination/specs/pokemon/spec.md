## MODIFIED Requirements

### Requirement: GET /pokemon paginado
The system SHALL return a paginated list of Pokémon using a PokeAPI-compatible structure.

#### Scenario: Successful catalog fetch
- **WHEN** a GET request is made to `/pokemon`
- **THEN** the system returns a 200 OK response with the fields `count`, `next`, `previous`, and `results`
- **AND** `results` contains a list of objects with `name` and `url`

### Requirement: GET /type/{idOrName} paginado
The system SHALL return a paginated list of Pokémon filtered by type using the same structure as the main catalog.

#### Scenario: Successful type filter fetch
- **WHEN** a GET request is made to `/type/fire`
- **THEN** the system returns a 200 OK response with the fields `count`, `next`, `previous`, and `results`
- **AND** `results` contains a list of Pokémon belonging to the fire type
