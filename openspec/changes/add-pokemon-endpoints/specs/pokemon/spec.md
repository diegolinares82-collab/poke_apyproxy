## MODIFIED Requirements

### Requirement: Retrieve Pokemon Full Detail
The system SHALL return simplified information of a Pokémon by name or ID.

#### Scenario: Successful retrieval by name
- **WHEN** a client makes a valid GET request to /pokemon/{name}
- **THEN** the API SHALL return a 200 HTTP status code with the Pokémon's simplified information

#### Scenario: Successful retrieval by ID
- **WHEN** a client makes a valid GET request to /pokemon/{id}
- **THEN** the API SHALL return a 200 HTTP status code with the Pokémon's simplified information

#### Scenario: Pokémon Not Found
- **WHEN** a requested Pokémon does not exist (by name or id)
- **THEN** the API SHALL return a 404 HTTP status code with a descriptive error message
