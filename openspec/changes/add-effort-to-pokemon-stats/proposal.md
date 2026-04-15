## Why

The user wants to add the `effort` field to the `stats` array in the Pokémon detailed response. This provides additional technical information about the Pokémon's stats, specifically how much effort value (EV) is gained when defeating this Pokémon, which is a common requirement for Pokémon-related applications.

## What Changes

- **MODIFIED**: `PokeApiPokemonResponse.java` (external DTO) to include the `effort` field in the `StatEntry` class.
- **MODIFIED**: `PokemonResponse.java` (internal DTO) to include the `effort` field in the `StatEntry` class.
- **MODIFIED**: `PokemonService.java` to map the `effort` field from the external response to the internal response.

## Capabilities

### Modified Capabilities
- `pokemon-detail-enrichment`: The Pokémon detail response now includes the `effort` value for each stat.

## Impact

- `PokeApiPokemonResponse.java`: Schema change for external mapping.
- `PokemonResponse.java`: Schema change for internal response.
- `PokemonService.java`: Mapping logic update.
