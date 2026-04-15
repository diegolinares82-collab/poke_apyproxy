## 1. DTO Updates

- [x] 1.1 Fix `PokemonPageResponse.java` (external mapping) and add correct fields (`count`, `next`, `previous`).
- [x] 1.2 Update `PokemonCatalogResponse.java` to include `count`, `next`, and `previous`.
- [x] 1.3 Update `PokemonListResponse.java` to have only `name` and `url`.

## 2. Service Logic

- [x] 2.1 Implement pagination link generation in `PokemonService` for `next` and `previous`.
- [x] 2.2 Update `getPokemonsLimitOffset` in `PokemonService` to populate all new fields.
- [x] 2.3 Update `getPokemonTypes` in `PokemonService` to follow the same structure.
- [x] 2.4 Update `getPokemonTypeDetails` in `PokemonService` to follow the same structure.

## 3. Controller & Testing

- [x] 3.1 Update `PokemonController` return types and documentation.
- [x] 3.2 Update `PokemonControllerTest` to verify the new paginated JSON structure.
- [x] 3.3 Verify 404/502 handling remains consistent.
