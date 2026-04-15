## ADDED Requirements

### Requirement: List All Types
The system SHALL retrieve a list of all predefined Pokemon types.

#### Scenario: Requesting all types
- **WHEN** client requests GET `/type`
- **THEN** system returns all available types for use in filter UI

### Requirement: Filter by Type
The system SHALL return the details of a specific type, including the list of Pokemon that belong to it.

#### Scenario: Requesting type by name
- **WHEN** client requests GET `/type/{name}`
- **THEN** system returns the type details indicating which Pokemon have this type

#### Scenario: Requesting type by ID
- **WHEN** client requests GET `/type/{id}`
- **THEN** system returns the type details indicating which Pokemon have this type
