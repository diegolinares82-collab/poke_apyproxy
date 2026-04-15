## Context

We need to add a `url` field to the `stat` object in the `stats` array of the Pokémon detail response.

## Goals / Non-Goals

**Goals:**
- Update `PokemonResponse.StatDetail` to include `String url`.
- Ensure the `PokemonService` correctly maps the `url` from the external PokeAPI response.
- Update Swagger documentation for the new field.

**Non-Goals:**
- Changing other fields in the Pokémon response.

## Decisions

### 1. DTO Update
Add `private String url;` to `PokemonResponse.StatDetail` and annotate it with `@Schema(description = "URL para obtener más detalles de la estadística", example = "https://pokeapi.co/api/v2/stat/1/")`.

### 2. Service Logic
In `getPokemonByName`, update the `stats` stream mapping:
```java
.stats(externalResponse.getStats().stream()
                .map(s -> PokemonResponse.StatEntry.builder()
                                .baseStat(s.getBaseStat())
                                .stat(PokemonResponse.StatDetail.builder()
                                                .name(s.getStat().getName())
                                                .url(s.getStat().getUrl()) // Add this
                                                .build())
                                .build())
                .collect(Collectors.toList()))
```

## Risks / Trade-offs

- **[Trade-off]** Adding more fields increases the JSON response size slightly, but this is minimal and requested by the user for better context.
