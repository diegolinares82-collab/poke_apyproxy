## ADDED Requirements

### Requirement: Retrieve Species Information
The system SHALL return the species details for a given pokemon ID or name.

#### Scenario: Requesting species by name
- **WHEN** client requests GET `/pokemon-species/{name}`
- **THEN** system returns species details including flavor text and genus

#### Scenario: Requesting species by ID
- **WHEN** client requests GET `/pokemon-species/{id}`
- **THEN** system returns species details including flavor text and genus
