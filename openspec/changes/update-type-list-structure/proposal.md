## Why

The user wants a simplified response for the Pokémon type list endpoint (`GET /type`). Currently, it uses the same paginated structure as the main catalog, which includes `next` and `previous` links. For the type list, which is usually small and non-paginated in this proxy's context, a simpler structure containing only `count` and `results` is preferred.

## What Changes

- **NEW**: `PokemonTypeListResponse.java` DTO with only `count` and `results` fields.
- **MODIFIED**: `PokemonService.java` to return `PokemonTypeListResponse` in the `getPokemonTypes` method.
- **MODIFIED**: `PokemonController.java` to update the return type of the `/type` endpoint.
- **MODIFIED**: `PokemonControllerTest.java` to update mocks and assertions for the new structure.

## Capabilities

### Modified Capabilities
- `pokemon-type-list`: The endpoint `GET /type` will return the new simplified JSON structure.

## Impact

- `PokemonTypeListResponse.java`: New data structure.
- `PokemonController.java`: API contract change for list types.
- `PokemonService.java`: Logic and return type change.
