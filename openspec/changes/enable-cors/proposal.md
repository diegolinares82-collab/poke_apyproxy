## Why

Currently, the PokeProxy API does not have CORS enabled. This prevents web applications hosted on different origins (e.g., a React frontend on localhost:3000) from consuming the API. Enabling CORS is essential for making the API usable by frontend applications.

## What Changes

- **NEW**: `WebConfig.java` in `com.example.pokeproxy.config` package to implement `WebMvcConfigurer` and define CORS mappings.
- **MODIFIED**: `application.properties` to add configurable allowed origins.

## Capabilities

### Modified Capabilities
- `api-accessibility`: The API will now accept requests from configured origins, allowing cross-domain frontend integration.

## Impact

- `WebConfig.java`: New configuration class.
- `application.properties`: New property for CORS allowed origins.
