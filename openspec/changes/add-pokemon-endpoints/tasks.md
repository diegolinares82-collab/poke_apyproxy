## 1. DTO Generation

- [x] 1.1 Create `PokemonCatalogResponse` and `PokemonListItem` DTOs for the catalog endpoint.
- [x] 1.2 Create `PokemonSpecies` DTO containing flavor text entries and genus data.
- [x] 1.3 Create `TypeResource` and `PokemonTypeDetails` DTOs for type responses.

## 2. API Client Extensions

- [x] 2.1 Add `getPokemonCatalog(limit, offset)` to `PokeApiClient`.
- [x] 2.2 Add `getPokemonSpecies(nameOrId)` to `PokeApiClient`.
- [x] 2.3 Add `getPokemonTypes()` to `PokeApiClient`.
- [x] 2.4 Add `getPokemonTypeDetails(nameOrId)` to `PokeApiClient`.

## 3. Service Layer Updates

- [x] 3.1 Update `PokemonService.getPokemon()` to support both ID and name gracefully.
- [x] 3.2 Add `getPokemonCatalog(limit, offset)` in `PokemonService` with default limits.
- [x] 3.3 Add `getPokemonSpecies(nameOrId)` in `PokemonService`.
- [x] 3.4 Add `getPokemonTypes()` in `PokemonService`.
- [x] 3.5 Add `getPokemonTypeDetails(nameOrId)` in `PokemonService`.

## 4. Controller Layer Updates

- [x] 4.1 Update `PokemonController.getPokemon()` mapping to clarify it supports `{idOrName}`.
- [x] 4.2 Add `GET /pokemon?limit={limit}&offset={offset}` mapping in `PokemonController`.
- [x] 4.3 Add `GET /pokemon-species/{idOrName}` mapping in `PokemonController`.
- [x] 4.4 Add `GET /type` mapping in `PokemonController`.
- [x] 4.5 Add `GET /type/{idOrName}` mapping in `PokemonController`.

## 5. Testing and Validation

- [x] 5.1 Write tests for catalog endpoint in `PokemonControllerTest`.
- [x] 5.2 Write tests for species endpoint in `PokemonControllerTest`.
- [x] 5.3 Write tests for type endpoints in `PokemonControllerTest`.
- [x] 5.4 Verify error handling (404, 502) remains consistent across new endpoints.
