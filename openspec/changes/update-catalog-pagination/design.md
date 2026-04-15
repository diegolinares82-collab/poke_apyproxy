## Context

The current API implementation uses a custom simplified pagination structure. The user wants to switch to a format that mirrors PokeAPI's pagination (count, next, previous) but likely pointing to our proxy endpoints. We also need to fix a broken DTO (`PokemonPageResponse.java`) that the user partially edited.

## Goals / Non-Goals

**Goals:**
- Implement a standardised pagination structure across `/pokemon` and `/type/{idOrName}`.
- Provide valid `next` and `previous` links pointing to the proxy service.
- Maintain the name and detail URL structure in results.
- Fix compilation errors in `PokemonPageResponse.java`.

**Non-Goals:**
- Adding filtering beyond what's already implemented.
- Changing the detail endpoint structure (`/pokemon/{idOrName}`).

## Decisions

### 1. DTO Unified Structure
We will modify `PokemonCatalogResponse` and `PokemonListResponse` to match the requested JSON. 
- `PokemonCatalogResponse`: `count`, `next`, `previous`, `results`.
- `PokemonListResponse`: `name`, `url`.

### 2. URL Generation in Service
The `PokemonService` will be responsible for constructing the `next` and `previous` strings. 
- It will detect the current requested URL pattern (e.g., `/pokemon` or `/type/fire`).
- It will append the correct `limit` and `offset` parameters for the adjacent pages.
- The base URL for these links will be dynamically derived or hardcoded to `http://localhost:8080` for now, but configured to be easily changed.

### 3. Fixing the external DTO
The user left `PokemonPageResponse.java` (external mapping) in an inconsistent state. We will fix it to correctly map PokeAPI's raw response (count, next, previous, results) so we can use its data (especially `count`) to build our own response.

## Risks / Trade-offs

- **[Risk]** Breaking changes for current clients of the `/pokemon` endpoint. → **[Mitigation]** This is an explicit request for a structure change, so it's accepted as necessary.
- **[Risk]** Hardcoded local URLs in `next`/`previous`. → **[Mitigation]** We'll use a property or base path logic to make it configurable later, but start with localhost for testing.
