## NEW Requirements

### Requirement: Effort en las Estadísticas
El endpoint de detalle de Pokémon DEBE incluir el campo `effort` dentro de cada objeto del arreglo `stats`.

#### Scenario: Consulta de detalle de Pokémon con effort
- **GIVEN** un Pokémon solicitado (ej. "pikachu")
- **WHEN** se consulta `GET /pokemon/pikachu`
- **THEN** el JSON de respuesta contiene un arreglo `stats`
- **AND** cada entrada en `stats` contiene el campo `effort` con un valor entero.
