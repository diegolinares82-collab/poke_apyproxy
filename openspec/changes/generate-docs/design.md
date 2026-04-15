## Context

The project uses `springdoc-openapi-starter-webmvc-ui` for Swagger documentation. We need to complete the annotations and add Javadoc.

## Goals / Non-Goals

**Goals:**
- Add `@OpenAPIDefinition` to the main class.
- Add Javadoc to all public methods in `PokemonService` and `PokemonController`.
- Complete Swagger annotations (`@Operation`, `@ApiResponse`) in the controller.
- Add `@Schema` to DTO fields where descriptive text is needed.

**Non-Goals:**
- Changing API logic.
- Adding third-party documentation tools (like Asciidoctor).

## Decisions

### 1. Global API Metadata
We will use `@OpenAPIDefinition` in `PokeproxyApplication.java` to set the API title, version, and description.

### 2. Controller Annotations
Each endpoint in `PokemonController` will have:
- `@Operation`: Summary and description.
- `@ApiResponse`: For 200, 404, and 502 status codes.

### 3. Javadoc Policy
- Classes: Description of the purpose.
- Public Methods: Description, parameters (`@param`), and return type (`@return`).

## Risks / Trade-offs

- **[Trade-off]** Adding many annotations to the controller makes it more verbose. However, this is the standard way to provide rich OpenAPI documentation in Spring Boot projects.
