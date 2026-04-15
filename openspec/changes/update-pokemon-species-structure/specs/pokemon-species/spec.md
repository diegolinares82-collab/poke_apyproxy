## MODIFIED Requirements

### Requirement: GET /pokemon-species/{idOrName} estructurado
The system SHALL return a list of flavor text entries and genus entries for a given Pokémon species.

#### Scenario: Successful species fetch
- **WHEN** a GET request is made to `/pokemon-species/bulbasaur`
- **THEN** the system returns 200 OK with a JSON containing:
  - `flavor_text_entries`: Array of objects with `flavor_text` and `language.name`.
  - `genera`: Array of objects with `genus` and `language.name`.
- **AND** the JSON keys MUST use snake_case as requested.
