## 1. Configuration

- [x] 1.1 Add `proxy.base-url=http://localhost:${server.port:8080}` to `application.properties`.

## 2. Refactoring

- [x] 2.1 Inject `proxy.base-url` into `PokemonService.java` using `@Value`.
- [x] 2.2 Replace hardcoded URLs in `getPokemonsLimitOffset` with the injected value.
- [x] 2.3 Replace hardcoded URLs in `getPokemonTypes` with the injected value.
- [x] 2.4 Replace hardcoded URLs in `getPokemonTypeDetails` with the injected value.

## 3. Verification

- [x] 3.1 Run `mvn compile` to ensure no errors were introduced.
- [x] 3.2 Run `mvn test` to ensure tests still pass (tests might need updates if they assert on exact URL strings).
