## 1. DTO Updates

- [x] 1.1 Update `PokeApiPokemonResponse.java` with all nested fields (`height`, `weight`, `sprites`, `stats`, etc.).
- [x] 1.2 Update `PokemonResponse.java` to match the requested JSON structure using nested DTO classes.

## 2. Service Logic

- [x] 2.1 Update `getPokemonByName` in `PokemonService.java` to map the expanded data from the external API to the new internal structure.

## 3. Testing & Verification

- [x] 3.1 Update `PokemonControllerTest.java` to mock the new detailed response and assert all nested fields.
- [x] 3.2 Verify all tests pass with `mvn test`.
