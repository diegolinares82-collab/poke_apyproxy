## Why

The user wants to enrich the Pokémon detailed response by including the URL of the stat in the `stats` array. This provides more context about each stat (e.g., HP, Attack) and aligns the proxy's response closer to the original PokeAPI structure for that specific field.

## What Changes

- **MODIFIED**: `PokemonResponse.java` to add a `url` field to the `StatDetail` nested class.
- **MODIFIED**: `PokemonService.java` to map the `url` from the `PokeApiPokemonResponse` to the internal DTO.

## Capabilities

### Modified Capabilities
- `pokemon-detail-enrichment`: The Pokémon detail response now includes URLs for individual stats.

## Impact

- `PokemonResponse.java`: Schema change for `StatDetail`.
- `PokemonService.java`: Mapping logic update.
