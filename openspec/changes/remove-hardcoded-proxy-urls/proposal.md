## Why

The `PokemonService` currently contains several hardcoded URLs (e.g., `http://localhost:8080/pokemon/`). This is problematic because:
- It breaks if the server port changes.
- It is and incorrect practice when deploying to staging or production environments.
- It makes the code harder to maintain and test.

## What Changes

- **MODIFIED**: `PokemonService.java` to stop using hardcoded strings for proxy URLs.
- **MODIFIED**: `application.properties` to add a `proxy.base-url` property.
- **MODIFIED**: `PokemonService.java` to use `@Value` to inject the base URL or dynamic URI building.

## Capabilities

### Modified Capabilities
- `environment-agnostic-urls`: The API responses will now contain URLs that match the current environment's configuration instead of always pointing to localhost.

## Impact

- `PokemonService.java`: Logic update to use dynamic URL building or injected configuration.
- `application.properties`: Configuration management update.
