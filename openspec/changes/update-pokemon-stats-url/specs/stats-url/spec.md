## NEW Requirements

### Requirement: URL de Estadísticas en el Detalle
El endpoint de detalle de Pokémon DEBE incluir la URL de cada estadística en el objeto `stat`.

#### Scenario: Consulta de detalle de Pokémon
- **GIVEN** un Pokémon con estadísticas (ej. "pikachu")
- **WHEN** se consulta `GET /pokemon/pikachu`
- **THEN** el JSON de respuesta contiene un arreglo `stats`
- **AND** cada objeto `stat` dentro de `stats` tiene los campos `name` y `url`.
