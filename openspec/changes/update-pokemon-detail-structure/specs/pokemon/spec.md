## MODIFIED Requirements

### Requirement: GET /pokemon/{idOrName} detallado
The system SHALL return a detailed Pokémon response matching a specific JSON structure.

#### Scenario: Successful detail fetch
- **WHEN** a GET request is made to `/pokemon/pikachu`
- **THEN** the system returns 200 OK with a JSON containing:
  - `id`, `name`, `height`, `weight`
  - `sprites` with `front_default`, `other.official-artwork.front_default`, and `other.home.front_default`
  - `types` as an array of objects with `slot` and `type` (name/url)
  - `stats` as an array of objects with `base_stat` and `stat` (name)
  - `abilities` as an array of objects with `ability` (name) and `is_hidden`
