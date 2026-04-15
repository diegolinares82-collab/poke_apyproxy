## 1. DTO Updates

- [x] 1.1 Create `PokemonTypeListResponse.java` with `count` and `results`.

## 2. Service Logic

- [x] 2.1 Update `getPokemonTypes` in `PokemonService.java` to return `PokemonTypeListResponse`.

## 3. Controller & Testing

- [x] 3.1 Update `PokemonController.java` to return `PokemonTypeListResponse` for the `/type` endpoint.
- [x] 3.2 Update `PokemonControllerTest.java` to verify the new simplified JSON structure.
- [x] 3.3 Verify all tests pass with `mvn test`.
