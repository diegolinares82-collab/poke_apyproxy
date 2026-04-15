## Context

The user wants the `GET /pokemon-species/{idOrName}` endpoint to return multiple entries for flavor text and genera, matching the structure provided.

## Goals / Non-Goals

**Goals:**
- Update `PokemonSpeciesResponse` to support arrays of `flavor_text_entries` and `genera`.
- Ensure JSON keys match the hyphenated PokeAPI format (`flavor_text_entries`, `flavor_text`).
- Update the mapping in `PokemonService`.

**Non-Goals:**
- Changing other DTOs.
- Adding language filtering at the proxy level (the client will receive all entries).

## Decisions

### 1. DTO Structure
We will mirrors the structure of the external API in the internal DTO to keep mapping simple. We will use `@JsonProperty` to ensure the JSON keys match the requirements.

### 2. Nested Classes
We will use static inner classes in `PokemonSpeciesResponse` for `FlavorTextEntry`, `GenusEntry`, and `Language`.

## Risks / Trade-offs

- **[Trade-off]** More data being transferred to the client. By returning all entries instead of a single one, the response size increases. However, this provides more flexibility to the client.
- **[Risk]** Breaking change for existing consumers of the species endpoint. → **[Mitigation]** This is a direct requirement from the user.
