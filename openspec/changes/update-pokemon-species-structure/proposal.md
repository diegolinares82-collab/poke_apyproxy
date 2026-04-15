## Why

The current `PokemonSpeciesResponse` simplifies the species data into a single flavor text and a single genus. However, users may need access to the descriptions and genera in multiple languages or multiple versions. The requested change will expose the raw lists of flavor text entries and genera, allowing the client to filter or display the information as needed.

## What Changes

- **MODIFIED**: `PokemonSpeciesResponse.java` to include `flavor_text_entries` and `genera` as lists of objects, replacing the simple `flavorText` and `genus` fields. **BREAKING**
- **MODIFIED**: `PokemonService.java` to update the mapping logic in `getPokemonSpecies` to move the lists from the external PokeAPI response to the internal response.
- **MODIFIED**: `PokemonControllerTest.java` to update tests and assertions for the new species structure.

## Capabilities

### Modified Capabilities
- `pokemon-species`: The endpoint `GET /pokemon-species/{idOrName}` will return a more detailed response with arrays of entries.

## Impact

- `PokemonSpeciesResponse.java`: Structure change.
- `PokemonService.java`: Mapping logic change.
- `PokemonControllerTest.java`: Testing update.
