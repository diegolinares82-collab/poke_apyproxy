## Context

The user wants a specific JSON structure for the `GET /type` endpoint: `{ count, results }`.

## Goals / Non-Goals

**Goals:**
- Create a new DTO `PokemonTypeListResponse` to avoid affecting the main catalog pagination.
- Update `PokemonService` and `PokemonController` to use this new DTO.
- Ensure the JSON format matches the requirements.

**Non-Goals:**
- Changing the structure of the main catalog (`GET /pokemon`).
- Changing the structure of type details (`GET /type/{idOrName}`).

## Decisions

### 1. New DTO
Instead of modifying `PokemonCatalogResponse` (which is shared), we will introduce `PokemonTypeListResponse`. This follows the principle of least surprise and avoids breaking the main catalog endpoint.

### 2. Reuse PokemonListResponse
We will continue to use `PokemonListResponse` for the items within the `results` array, as it already contains `name` and `url`.

## Risks / Trade-offs

- **[Trade-off]** Adding a new DTO class increases the code footprint slightly. However, it's better than making a shared DTO less flexible or causing side effects.
