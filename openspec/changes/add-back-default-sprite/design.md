## Context

We need to add the `back_default` sprite to the Pokémon detail response.

## Goals / Non-Goals

**Goals:**
- Update `Sprites` in both `PokeApiPokemonResponse` (external) and `PokemonResponse` (internal) to include `String backDefault`.
- Map the value in `PokemonService`.
- Add Swagger description for the new field.

**Non-Goals:**
- Adding other sprites (shiny, female, etc.) unless requested.

## Decisions

### 1. External DTO Update
Update `PokeApiPokemonResponse.Sprites` to include `private String backDefault;` and annotate with `@JsonProperty("back_default")`.

### 2. Internal DTO Update
Update `PokemonResponse.Sprites` to include `private String backDefault;` and annotate with `@JsonProperty("back_default")` and `@Schema`.

### 3. Service Mapping
Update `PokemonService.getPokemonByName`:
```java
.sprites(PokemonResponse.Sprites.builder()
                .frontDefault(externalResponse.getSprites().getFrontDefault())
                .backDefault(externalResponse.getSprites().getBackDefault()) // Add this
                .other(...)
                .build())
```

## Risks / Trade-offs

- **[Risk]** None. Some Pokémon might not have a back sprite in PokeAPI (returns null), which our DTO should handle gracefully.
