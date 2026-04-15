package com.example.pokeproxy.service;

import com.example.pokeproxy.client.PokeApiClient;
import com.example.pokeproxy.dto.PokemonResponse;
import com.example.pokeproxy.dto.PokemonCatalogResponse;
import com.example.pokeproxy.dto.PokemonSpeciesResponse;
import com.example.pokeproxy.dto.PokemonTypeListResponse;
import com.example.pokeproxy.dto.external.PokeApiPokemonResponse;
import com.example.pokeproxy.dto.external.PokemonListResponse;
import com.example.pokeproxy.dto.external.PokemonPageResponse;
import com.example.pokeproxy.dto.external.PokeApiSpeciesResponse;
import com.example.pokeproxy.dto.external.PokeApiTypeResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio encargado de orquestar la llamada a la API externa
 * y transformar la respuesta al formato requerido por nuestro proxy.
 */
@Slf4j
@Service
public class PokemonService {

        private final PokeApiClient pokeApiClient;

        @Value("${proxy.base-url}")
        private String proxyBaseUrl;

        public PokemonService(PokeApiClient pokeApiClient) {
                this.pokeApiClient = pokeApiClient;
        }

        /**
         * Obtiene y mapea los datos de un Pokémon, utilizando caché.
         *
         * @param name Nombre del Pokémon
         * @return PokemonResponse con la información ya simplificada
         */
        @Cacheable(value = "pokemon", key = "#name")
        public PokemonResponse getPokemonByName(String name) {
                try {
                        log.info("Fetching Pokemon data for: {}", name);
                        PokeApiPokemonResponse externalResponse = pokeApiClient.getPokemon(name);

                        return PokemonResponse.builder()
                                        .id(externalResponse.getId())
                                        .name(externalResponse.getName())
                                        .height(externalResponse.getHeight())
                                        .weight(externalResponse.getWeight())
                                        .types(externalResponse.getTypes().stream()
                                                        .map(t -> PokemonResponse.TypeEntry.builder()
                                                                        .slot(t.getSlot())
                                                                        .type(PokemonResponse.TypeDetail.builder()
                                                                                        .name(t.getType().getName())
                                                                                        .url(t.getType().getUrl())
                                                                                        .build())
                                                                        .build())
                                                        .collect(Collectors.toList()))
                                        .abilities(externalResponse.getAbilities().stream()
                                                        .map(a -> PokemonResponse.AbilityEntry.builder()
                                                                        .ability(PokemonResponse.AbilityDetail.builder()
                                                                                        .name(a.getAbility().getName())
                                                                                        .build())
                                                                        .isHidden(a.isHidden())
                                                                        .build())
                                                        .collect(Collectors.toList()))
                                        .stats(externalResponse.getStats().stream()
                                                        .map(s -> PokemonResponse.StatEntry.builder()
                                                                        .baseStat(s.getBaseStat())
                                                                        .effort(s.getEffort())
                                                                        .stat(PokemonResponse.StatDetail.builder()
                                                                                        .name(s.getStat().getName())
                                                                                        .url(s.getStat().getUrl())
                                                                                        .build())
                                                                        .build())
                                                        .collect(Collectors.toList()))
                                        .sprites(PokemonResponse.Sprites.builder()
                                                        .frontDefault(externalResponse.getSprites().getFrontDefault())
                                                        .backDefault(externalResponse.getSprites().getBackDefault())
                                                        .other(PokemonResponse.OtherSprites.builder()
                                                                        .officialArtwork(PokemonResponse.Artwork
                                                                                        .builder()
                                                                                        .frontDefault(externalResponse
                                                                                                        .getSprites()
                                                                                                        .getOther()
                                                                                                        .getOfficialArtwork()
                                                                                                        .getFrontDefault())
                                                                                        .build())
                                                                        .home(PokemonResponse.Home.builder()
                                                                                        .frontDefault(externalResponse
                                                                                                        .getSprites()
                                                                                                        .getOther()
                                                                                                        .getHome()
                                                                                                        .getFrontDefault())
                                                                                        .build())
                                                                        .build())
                                                        .build())
                                        .build();
                } catch (Exception e) {
                        log.error("Error fetching Pokemon '{}': {}", name, e.getMessage());
                        throw e;
                }
        }

        /**
         * Genera un PokemonResponse vacío como endpoint de prueba.
         *
         * @return instanciación vacía de PokemonResponse
         */
        public PokemonResponse getPokemonAll() {
                try {
                        log.info("Generando un PokemonResponse vacío");
                        return PokemonResponse.builder()
                                        .id(null) // Opcional, por defecto es null
                                        .name("Empty") // Opcional, para identificarlo
                                        .types(List.of()) // Lista vacía en lugar de null
                                        .abilities(List.of()) // Lista vacía en lugar de null
                                        .build();
                } catch (Exception e) {
                        log.error("Error fetching Pokemon '{}': {}", e.getMessage());
                        throw e;
                }
        }

        /**
         * Obtiene una página de Pokémon utilizando paginación por limit y offset.
         *
         * @param limit Cantidad de resultados por página
         * @param offset Número de resultados a saltar
         * @return Objeto de respuesta con la lista de Pokémon y enlaces de navegación
         */
        public PokemonCatalogResponse getPokemonsLimitOffset(int limit, int offset) {
                try {

                        // 🔹 Llamada a PokéAPI (DTO externo)
                        PokemonPageResponse externalResponse = pokeApiClient.getPokemonLimitOffset(limit, offset);

                        // 🔹 Transformación a tu modelo
                        List<PokemonListResponse> pokemons = externalResponse.getResults().stream().map(pokemon -> {
                                String idStr = pokemon.getUrl().replaceAll(".*/pokemon/(\\d+)/?", "$1");
                                String proxyUrl = proxyBaseUrl + "/pokemon/" + idStr;

                                return PokemonListResponse.builder()
                                                .name(pokemon.getName())
                                                .url(proxyUrl)
                                                .build();
                        }).toList();

                        String baseUrl = proxyBaseUrl + "/pokemon";
                        String next = (offset + limit < externalResponse.getCount())
                                        ? baseUrl + "?limit=" + limit + "&offset=" + (offset + limit)
                                        : null;
                        String previous = (offset > 0)
                                        ? baseUrl + "?limit=" + limit + "&offset=" + Math.max(0, offset - limit)
                                        : null;

                        // 🔹 Construir respuesta final
                        return PokemonCatalogResponse.builder()
                                        .count(externalResponse.getCount())
                                        .next(next)
                                        .previous(previous)
                                        .results(pokemons)
                                        .build();

                } catch (Exception e) {
                        log.error("Error fetching pokemons: {}", e.getMessage());
                        throw new RuntimeException("Error fetching pokemons", e);
                }
        }

        /**
         * Obtiene información detallada de la especie de un Pokémon.
         *
         * @param nameOrId Nombre o ID del Pokémon
         * @return Respuesta con textos de ambientación y géneros
         */
        public PokemonSpeciesResponse getPokemonSpecies(String nameOrId) {
                log.info("Fetching Pokemon species data for: {}", nameOrId);
                PokeApiSpeciesResponse externalResponse = pokeApiClient.getPokemonSpecies(nameOrId);

                List<PokemonSpeciesResponse.FlavorTextEntry> flavorTextEntries = externalResponse.getFlavorTextEntries()
                                .stream()
                                .map(entry -> PokemonSpeciesResponse.FlavorTextEntry.builder()
                                                .flavorText(entry.getFlavorText().replace("\n", " ").replace("\f", " "))
                                                .language(PokemonSpeciesResponse.Language.builder()
                                                                .name(entry.getLanguage().getName()).build())
                                                .build())
                                .collect(Collectors.toList());

                List<PokemonSpeciesResponse.GenusEntry> genera = externalResponse.getGenera().stream()
                                .map(entry -> PokemonSpeciesResponse.GenusEntry.builder()
                                                .genus(entry.getGenus())
                                                .language(PokemonSpeciesResponse.Language.builder()
                                                                .name(entry.getLanguage().getName()).build())
                                                .build())
                                .collect(Collectors.toList());

                return PokemonSpeciesResponse.builder()
                                .flavorTextEntries(flavorTextEntries)
                                .genera(genera)
                                .build();
        }

        /**
         * Obtiene la lista simplificada de todos los tipos de Pokémon.
         *
         * @return Listado de tipos con nombre y URL de acceso
         */
        public PokemonTypeListResponse getPokemonTypes() {
                log.info("Fetching Pokemon types list");
                PokemonPageResponse externalResponse = pokeApiClient.getPokemonTypes();

                List<PokemonListResponse> types = externalResponse.getResults().stream().map(result -> {
                        String idStr = result.getUrl().replaceAll(".*/type/(\\d+)/?", "$1");
                        return PokemonListResponse.builder()
                                        .name(result.getName())
                                        .url(proxyBaseUrl + "/type/" + idStr)
                                        .build();
                }).collect(Collectors.toList());

                return PokemonTypeListResponse.builder()
                                .count(externalResponse.getCount())
                                .results(types)
                                .build();
        }

        /**
         * Obtiene los Pokémon asociados a un tipo específico.
         *
         * @param nameOrId Nombre o ID del tipo
         * @return Colección de Pokémon filtrados por tipo
         */
        public PokemonCatalogResponse getPokemonTypeDetails(String nameOrId) {
                log.info("Fetching Pokemon type details for: {}", nameOrId);
                PokeApiTypeResponse externalResponse = pokeApiClient.getPokemonTypeDetails(nameOrId);

                List<PokemonListResponse> pokemons = externalResponse.getPokemon().stream()
                                .map(entry -> {
                                        String idStr = entry.getPokemon().getUrl().replaceAll(".*/pokemon/(\\d+)/?",
                                                        "$1");
                                        return PokemonListResponse.builder()
                                                        .name(entry.getPokemon().getName())
                                                        .url(proxyBaseUrl + "/pokemon/" + idStr)
                                                        .build();
                                })
                                .collect(Collectors.toList());

                return PokemonCatalogResponse.builder()
                                .count(pokemons.size())
                                .next(null)
                                .previous(null)
                                .results(pokemons)
                                .build();
        }
}
