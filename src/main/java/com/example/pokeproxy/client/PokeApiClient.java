package com.example.pokeproxy.client;

import com.example.pokeproxy.dto.external.PokeApiPokemonResponse;
import com.example.pokeproxy.dto.external.PokemonPageResponse;
import com.example.pokeproxy.dto.external.PokeApiSpeciesResponse;
import com.example.pokeproxy.dto.external.PokeApiTypeResponse;
import com.example.pokeproxy.exception.PokeApiException;
import com.example.pokeproxy.exception.PokemonNotFoundException;
import com.example.pokeproxy.exception.PokemonNotFoundLimitException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * Cliente para consumir la API externa de Pokémon (PokeAPI).
 */
@Component
public class PokeApiClient {

    private final RestClient restClient;
    private final String baseUrl;

    public PokeApiClient(RestClient restClient, @Value("${pokeapi.base-url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    /**
     * Hace una llamada GET a PokeAPI para buscar un Pokémon.
     *
     * @param name El nombre del Pokémon
     * @return Los datos crudos del Pokémon externalizados en PokeApiPokemonResponse
     * @throws PokemonNotFoundException si el servidor devuelve un 404
     * @throws PokeApiException         si hay otro error general del servidor o
     *                                  cliente
     */
    public PokeApiPokemonResponse getPokemon(String name) {
        try {
            return restClient.get()
                    .uri(baseUrl + "/pokemon/{name}", name.toLowerCase())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                            throw new PokemonNotFoundException(name);
                        }
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        throw new PokeApiException("External PokeAPI server error: " + response.getStatusCode());
                    })
                    .body(PokeApiPokemonResponse.class);
        } catch (PokemonNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new PokeApiException("Error communicating with external PokeAPI", e);
        }
    }

    public PokemonPageResponse getPokemonLimitOffset(int limit, int offset) {
        try {
            return restClient.get()
                    .uri(baseUrl + "/pokemon?limit={limit}&offset={offset}", limit, offset)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                            throw new PokemonNotFoundLimitException(limit, offset);
                        }
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        throw new PokeApiException("External PokeAPI server error: " + response.getStatusCode());
                    })
                    .body(PokemonPageResponse.class);
        } catch (PokemonNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new PokeApiException("Error communicating with external PokeAPI", e);
        }
    }
    public PokeApiSpeciesResponse getPokemonSpecies(String nameOrId) {
        try {
            return restClient.get()
                    .uri(baseUrl + "/pokemon-species/{id}", nameOrId.toLowerCase())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                            throw new PokemonNotFoundException(nameOrId);
                        }
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        throw new PokeApiException("External PokeAPI server error: " + response.getStatusCode());
                    })
                    .body(PokeApiSpeciesResponse.class);
        } catch (PokemonNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new PokeApiException("Error communicating with external PokeAPI", e);
        }
    }

    public PokemonPageResponse getPokemonTypes() {
        try {
            return restClient.get()
                    .uri(baseUrl + "/type")
                    .retrieve()
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        throw new PokeApiException("External PokeAPI server error: " + response.getStatusCode());
                    })
                    .body(PokemonPageResponse.class);
        } catch (Exception e) {
            throw new PokeApiException("Error communicating with external PokeAPI", e);
        }
    }

    public PokeApiTypeResponse getPokemonTypeDetails(String nameOrId) {
        try {
            return restClient.get()
                    .uri(baseUrl + "/type/{id}", nameOrId.toLowerCase())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                            throw new PokemonNotFoundException(nameOrId);
                        }
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        throw new PokeApiException("External PokeAPI server error: " + response.getStatusCode());
                    })
                    .body(PokeApiTypeResponse.class);
        } catch (PokemonNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new PokeApiException("Error communicating with external PokeAPI", e);
        }
    }
}
