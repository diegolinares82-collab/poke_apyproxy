## Context

The user wants a detailed Pokémon response matching exactly a provided JSON structure. Currently, both the internal `PokemonResponse` and the external `PokeApiPokemonResponse` are too simplistic.

## Goals / Non-Goals

**Goals:**
- Update `PokemonResponse` to support the requested nested JSON structure (sprites, types, stats, abilities).
- Update `PokeApiPokemonResponse` to correctly map the PokeAPI JSON into Java objects.
- Implement mapping logic in `PokemonService`.

**Non-Goals:**
- Changing other endpoints like catalog or species.
- Adding caching or other performance optimizations (unless already existing).

## Decisions

### 1. Nested DTOs
We will use static inner classes within `PokemonResponse` to represent the nested structures (`Sprites`, `Types`, `Stats`, `Abilities`). This keeps the DTO file self-contained.

### 2. External Mapping
We will expand `PokeApiPokemonResponse` with the same nested structure (using `@JsonProperty` if needed to match PokeAPI names, though PokeAPI names usually match CamelCase-to-Hyphen if using Jackson defaults, but we'll be explicit).

### 3. Service Mapping
The `PokemonService` will map 1:1 from the external DTO to the internal DTO. No complex logic is needed other than moving data from one object to another.

## Risks / Trade-offs

- **[Risk]** The new DTO structure is much larger and might affect memory/serialization performance slightly. → **[Mitigation]** The data is still relatively small (~few KB per response) and requested by the user for frontend needs.
- **[Risk]** Breaking change for any existing clients. → **[Mitigation]** This is an explicit requirement from the stakeholders (the user).
