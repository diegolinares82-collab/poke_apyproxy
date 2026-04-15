## NEW Requirements

### Requirement: Documentación Swagger Completa
El sistema DEBE exponer una interfaz de Swagger UI con información detallada de todos los endpoints.

#### Scenario: Visualización de Swagger UI
- **WHEN** se accede a `/swagger-ui.html`
- **THEN** el sistema muestra la documentación OpenAPI con:
  - Título: "Pokemon Proxy API".
  - Descripción de cada endpoint.
  - Ejemplos de respuestas (200, 404, 502).

### Requirement: Documentación de Código (Javadoc)
El código DEBE contener Javadoc descriptivo en todas sus clases y métodos públicos.

#### Scenario: Lectura de código
- **WHEN** un desarrollador lee `PokemonService.java` o `PokemonController.java`
- **THEN** encuentra bloques de comentarios `/** ... */` explicando la responsabilidad de cada método.
