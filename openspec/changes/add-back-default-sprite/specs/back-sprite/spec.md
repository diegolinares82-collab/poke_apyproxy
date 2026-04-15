## NEW Requirements

### Requirement: Sprite Trasero por Defecto
El endpoint de detalle de Pokémon DEBE incluir el campo `back_default` dentro del objeto `sprites`.

#### Scenario: Consulta de detalle de Pokémon con sprite trasero
- **GIVEN** un Pokémon solicitado (ej. "pikachu")
- **WHEN** se consulta `GET /pokemon/pikachu`
- **THEN** el JSON de respuesta contiene un objeto `sprites`
- **AND** el objeto `sprites` contiene el campo `back_default` con la URL de la imagen trasera.
