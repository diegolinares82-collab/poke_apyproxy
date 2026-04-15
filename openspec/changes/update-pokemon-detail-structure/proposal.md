## Why

The current Pokémon detail endpoint returns very limited information (id, name, types, and abilities). The user wants a much richer response that includes height, weight, detailed sprites (including official-artwork and home), detailed types, stats (HP, Attack, etc.), and detailed abilities (including hidden status). This will satisfy the requirements for a high-quality frontend implementation.

## What Changes

- **MODIFIED**: `PokemonResponse` DTO to include `height`, `weight`, `sprites` (nested object), `types` (detailed objects), `stats` (base_stat and name), and `abilities` (detailed objects). **BREAKING**
- **MODIFIED**: `PokeApiPokemonResponse` (external DTO) to include all raw fields from PokeAPI necessary for mapping.
- **MODIFIED**: `PokemonService.java` to handle the complex mapping from the raw PokeAPI response to the new detailed internal DTO.
- **MODIFIED**: `PokemonControllerTest.java` to update mocks and assertions for the new detail structure.

## Capabilities

### Modified Capabilities
- `pokemon`: The detail endpoint `GET /pokemon/{idOrName}` is being heavily modified to return the new detailed JSON structure.

## Impact

- `PokemonResponse.java`: Core data structure change.
- `PokeApiPokemonResponse.java`: External mapping change.
- `PokemonService.java`: Mapping logic update.
- `PokemonControllerTest.java`: Test coverage for the new structure.
