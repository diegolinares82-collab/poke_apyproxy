## Why

The current API returns a custom paginated structure for the Pokémon catalog that includes extracted IDs and images. The user wants to align this structure with the original PokeAPI format—providing `count`, `next`, and `previous` links—to standardise the response for client-side navigation and filtering.

## What Changes

- **MODIFIED**: `PokemonCatalogResponse` DTO to include `count`, `next`, and `previous` fields.
- **MODIFIED**: `PokemonListResponse` DTO to revert to a simple `name` and `url` pair.
- **MODIFIED**: `PokemonService` and `PokemonController` to populate these new fields, specifically extracting the ID from URLs where necessary but maintaining the link structure.
- **MODIFIED**: `/pokemon` and `/type/{idOrName}` endpoints to return this consistent paginated structure. **BREAKING**

## Capabilities

### Modified Capabilities
- `pokemon`: The catalog and search functionality will now return the new paginated structure.
- `type-filtering`: The type-based Pokémon listing will now return the same paginated structure as the main catalog.

## Impact

- `PokemonController.java`: Return types and logic for `/pokemon` and `/type/{idOrName}`.
- `PokemonService.java`: Logic for calculating `next`/`previous` URLs and mapping results.
- `PokemonCatalogResponse.java` & `PokemonListResponse.java`: Schema changes.
- Existing tests in `PokemonControllerTest.java` will need updates to match the new JSON structure.
