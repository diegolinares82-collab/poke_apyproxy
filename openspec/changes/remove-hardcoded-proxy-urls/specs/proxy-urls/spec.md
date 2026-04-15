## NEW Requirements

### Requirement: URLs de Proxy Configurables
El sistema NO DEBE contener URLs hardcodeadas (quemadas) en el código fuente.

#### Scenario: Generación de URL de Pokémon
- **GIVEN** `proxy.base-url` está configurado como `https://mi-api.com`
- **WHEN** se solicita el catálogo de Pokémon
- **THEN** los resultados contienen URLs que comienzan con `https://mi-api.com/pokemon/`

#### Scenario: Cambio de puerto
- **GIVEN** el servidor corre en el puerto `9090` y `proxy.base-url` usa `${server.port}`
- **WHEN** se solicita el catálogo de Pokémon
- **THEN** los resultados contienen URLs que usan el puerto `9090`.
