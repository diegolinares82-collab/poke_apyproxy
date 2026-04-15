## Context

We need to add an `effort` field to the `stats` array in the Pokémon detail response.

## Goals / Non-Goals

**Goals:**
- Update `StatEntry` in both `PokeApiPokemonResponse` (external) and `PokemonResponse` (internal) to include `Integer effort`.
- Map the value in `PokemonService`.
- Add Swagger description for the new field.

**Non-Goals:**
- Changing other fields or structure.

## Decisions

### 1. External DTO Update
Update `PokeApiPokemonResponse.StatEntry` to include `private Integer effort;`.

### 2. Internal DTO Update
Update `PokemonResponse.StatEntry` to include `private Integer effort;` and annotate with `@Schema(description = "Puntos de esfuerzo (EV) otorgados", example = "0")`.

### 3. Service Mapping
Update `PokemonService.getPokemonByName`:
```java
.stats(externalResponse.getStats().stream()
                .map(s -> PokemonResponse.StatEntry.builder()
                                .baseStat(s.getBaseStat())
                                .effort(s.getEffort()) // Add this
                                .stat(PokemonResponse.StatDetail.builder()
                                                .name(s.getStat().getName())
                                                .url(s.getStat().getUrl())
                                                .build())
                                .build())
                .collect(Collectors.toList()))
```

## Risks / Trade-offs

- **[Risk]** None identified. The `effort` field is as standard in PokeAPI as `base_stat`.
