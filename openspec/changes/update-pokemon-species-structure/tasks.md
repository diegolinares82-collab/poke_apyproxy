## 1. DTO Updates

- [x] 1.1 Update `PokemonSpeciesResponse.java` to include `flavor_text_entries` and `genera` as lists, using nested classes and `@JsonProperty`.

## 2. Service Logic

- [x] 2.1 Update `getPokemonSpecies` in `PokemonService.java` to map the arrays from `PokeApiSpeciesResponse` to the new `PokemonSpeciesResponse` structure.

## 3. Testing & Verification

- [x] 3.1 Update `PokemonControllerTest.java` to mock the new species response and verify the array structure.
- [x] 3.2 Verify all tests pass with `mvn test`.
