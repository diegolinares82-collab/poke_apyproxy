## Why

Documentation is a key part of any software project. The user has requested to generate Swagger (OpenAPI) documentation and add docstrings to the code. Currently, the project has partial Swagger annotations and very few docstrings. Adding comprehensive documentation will make the API easier to use for clients and the code easier to maintain for developers.

## What Changes

- **MODIFIED**: `PokemonController.java` to add missing `@Operation`, `@ApiResponse`, and Javadoc to all methods.
- **MODIFIED**: `PokemonService.java` to add Javadoc to the class and all public methods.
- **MODIFIED**: `PokeproxyApplication.java` to add a global `@OpenAPIDefinition` to customize the Swagger UI title and description.
- **MODIFIED**: DTOs (`PokemonResponse`, `PokemonSpeciesResponse`, etc.) to add `@Schema` annotations for better documentation in the Swagger UI.

## Capabilities

### Modified Capabilities
- `api-documentation`: The Swagger UI (accessible at `/swagger-ui.html`) will provide a complete and detailed description of the API.
- `code-documentation`: The source code will follow best practices with standard Javadoc.

## Impact

- `PokemonController.java`: Enhanced documentation annotations.
- `PokemonService.java`: Improved readability with Javadoc.
- `PokeproxyApplication.java`: Metadata for OpenAPI.
- DTOs: Shared schema definitions for the API.
