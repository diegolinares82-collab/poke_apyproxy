## 1. DTO Refactoring

- [x] 1.1 Add `url` field to `PokemonResponse.StatDetail` in `PokemonResponse.java`.
- [x] 1.2 Add `@Schema` annotation to the new `url` field for Swagger.

## 2. Service Update

- [x] 2.1 Update `PokemonService.getPokemonByName` to map the `url` for each stat.

## 3. Verification

- [x] 3.1 Run `mvn compile` to ensure no errors were introduced.
- [x] 3.2 Run `mvn test` to ensure tests still pass (might need update if they assert on stats JSON structure).
