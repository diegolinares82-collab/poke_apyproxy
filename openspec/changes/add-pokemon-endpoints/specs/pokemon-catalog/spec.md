## ADDED Requirements

### Requirement: Paginated Catalog Retrieval
The system SHALL retrieve a paginated catalog of Pokemon using limit and offset parameters.

#### Scenario: Requesting first page
- **WHEN** client requests GET `/pokemon?limit=20&offset=0`
- **THEN** system returns list of 20 pokemon with their basic navigation data

#### Scenario: Omitting pagination params
- **WHEN** client requests GET `/pokemon` without limit and offset
- **THEN** system uses predefined defaults (e.g. limit=20, offset=0)
