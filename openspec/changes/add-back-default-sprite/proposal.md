## Why

The user wants to add the `back_default` sprite to the Pokémon detailed response. This allows frontend applications to show the back view of a Pokémon, which is useful for battle simulations or complete Pokédex displays.

## What Changes

- **MODIFIED**: `PokeApiPokemonResponse.java` (external DTO) to include the `back_default` field in the `Sprites` class.
- **MODIFIED**: `PokemonResponse.java` (internal DTO) to include the `back_default` field in the `Sprites` class.
- **MODIFIED**: `PokemonService.java` to map the `back_default` field from the external response to the internal response.

## Capabilities

### Modified Capabilities
- `pokemon-detail-enrichment`: The Pokémon detail response now includes the back sprite URL.

## Impact

- `PokeApiPokemonResponse.java`: Schema change for external mapping.
- `PokemonResponse.java`: Schema change for internal response.
- `PokemonService.java`: Mapping logic update.
