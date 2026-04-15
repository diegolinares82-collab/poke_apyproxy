## Why

To provide a more comprehensive set of Pokémon data to clients, we need new endpoints. The current proxy doesn't support pagination, species information, or filtering by type, which are necessary for a rich user interface (e.g., displaying a catalog, showing type filters, or getting flavor text).

## What Changes

- Add paginated catalog endpoint `GET /pokemon?limit={n}&offset={o}`.
- Add full detail endpoint `GET /pokemon/{id or name}`.
- Add species endpoint `GET /pokemon-species/{id or name}` for flavor text and genus.
- Add type listing endpoint `GET /type` for UI filters.
- Add type details endpoint `GET /type/{id or name}` for Pokémon filtered by type.

## Capabilities

### New Capabilities
- `pokemon-catalog`: Paginated Pokémon list.
- `pokemon-species`: Flavor text and genus information.
- `pokemon-types`: Pokémon types and filtering by type.

### Modified Capabilities
- `pokemon`: Enhancing the existing Pokémon detail endpoint to standardize `id or name` retrieval.

## Impact

- Adds new endpoints to `PokemonController`.
- Expands `PokemonService` and `PokeApiClient` to handle new external API requests to PokeAPI.
- New models (`PokemonSpecies`, `Type`, etc.) may be introduced to structure the responses.
