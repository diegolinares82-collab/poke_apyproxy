## Context

The current Pokemon Proxy API only supports retrieving a single Pokemon by its ID or name. However, consuming clients need to display lists of Pokemon, species information (like flavor text and genus), and filter by type. To support modern UI displays, we need a complete set of endpoints that proxy requests to the underlying PokeAPI properly. 

## Goals / Non-Goals

**Goals:**
- Provide a paginated catalog endpoint (`/pokemon`).
- Provide species data (`/pokemon-species/{id or name}`).
- Provide type listing (`/type`) and filtering (`/type/{id or name}`).
- Handle errors gracefully mimicking current exception management.

**Non-Goals:**
- Caching implementations.
- Adding database storage internally.
- Handling full evolution chains or non-essential resource types at this time.

## Decisions

- **Use of spring-cloud-starter-openfeign / RestTemplate:** We will continue using the previously chosen `PokeApiClient` pattern and augment it with new methods, as it represents a robust way to wrap the external PokeAPI.
- **Data Models:** Define new minimal DTOs to encapsulate the responses to avoid exposing full, bloated PokeAPI JSON shapes (e.g., `PokemonCatalogResponse`, `PokemonSpecies`, `TypeResource`).
- **Endpoint naming:** Standardized RESTful naming, matching the source PokeAPI structure but under our domain.

## Risks / Trade-offs

- **Risk: External Rate Limiting** -> Mitigation: Since caching isn't built yet, rely on standard PokeAPI rate limit behaviors and handle 429 exceptions gracefully in `PokemonService`.
- **Risk: Complex data structures** -> Mitigation: Ensure our DTOs only capture required fields (like flavor text) instead of deserializing the whole tree of `pokemon-species`.
