package com.example.pokeproxy.controller;

import com.example.pokeproxy.dto.PokemonResponse;
import com.example.pokeproxy.dto.PokemonCatalogResponse;
import com.example.pokeproxy.dto.PokemonSpeciesResponse;
import com.example.pokeproxy.dto.PokemonTypeListResponse;
import com.example.pokeproxy.service.PokemonService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para manejar las solicitudes relacionadas con los Pokémon.
 */
@RestController
@Tag(name = "Pokemon", description = "Endpoints para consultar información de Pokémon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    /**
     * Obtiene los datos simplificados de un Pokémon buscándolo por su nombre o id.
     *
     * @param idOrName Nombre o ID del Pokémon a consultar (ej. "pikachu" o "25")
     * @return Objeto PokemonResponse con los datos simplificados
     */
    @Operation(summary = "Obtener un Pokémon", description = "Devuelve la información simplificada de un Pokémon de PokeAPI")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Pokémon encontrado"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Pokémon no encontrado"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "502", description = "Error en la PokeAPI externa")
    })
    @GetMapping("/pokemon/{idOrName}")
    public PokemonResponse getPokemon(
            @Parameter(description = "Nombre o ID del Pokémon", example = "bulbasaur") @PathVariable String idOrName) {
        return pokemonService.getPokemonByName(idOrName);
    }

    /**
     * Obtiene una lista paginada de Pokémon.
     *
     * @param limit Cantidad máxima de resultados a retornar (por defecto 20)
     * @param offset Punto de inicio de la paginación (por defecto 0)
     * @return Respuesta estructurada con metadatos de paginación y lista de resultados
     */
    @Operation(summary = "Filtrar un pokemon", description = "Devuelve una lista paginada de Pokémon de PokeAPI")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "502", description = "Error en la PokeAPI externa")
    })
    @GetMapping("/pokemon")
    public PokemonCatalogResponse getPokemons(
            @Parameter(description = "Límite de resultados") @RequestParam(defaultValue="20", required=false) int limit,
            @Parameter(description = "Desplazamiento inicial") @RequestParam(defaultValue="0", required=false) int offset) {
        return pokemonService.getPokemonsLimitOffset(limit, offset);
    }

    /**
     * Obtiene información sobre la especie de un Pokémon (descripciones y géneros).
     *
     * @param idOrName Nombre o ID de la especie
     * @return Objeto con arreglos de descripciones (flavor text) y géneros en varios idiomas
     */
    @Operation(summary = "Obtener especie de un Pokémon", description = "Devuelve el genus y el flavor text de un Pokémon en múltiples idiomas")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Especie encontrada"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Especie no encontrada"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "502", description = "Error en la PokeAPI externa")
    })
    @GetMapping("/pokemon-species/{idOrName}")
    public PokemonSpeciesResponse getPokemonSpecies(
            @Parameter(description = "Nombre o ID de la especie", example = "bulbasaur") @PathVariable String idOrName) {
        return pokemonService.getPokemonSpecies(idOrName);
    }

    /**
     * Lista todos los tipos de Pokémon disponibles.
     *
     * @return Lista simplificada de tipos con nombre y URL
     */
    @Operation(summary = "Obtener lista de tipos", description = "Devuelve todos los tipos de Pokémon")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de tipos obtenida"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "502", description = "Error en la PokeAPI externa")
    })
    @GetMapping("/type")
    public PokemonTypeListResponse getPokemonTypes() {
        return pokemonService.getPokemonTypes();
    }

    /**
     * Obtiene los Pokémon asociados a un tipo específico.
     *
     * @param idOrName Nombre o ID del tipo (ej. "fire")
     * @return Lista de Pokémon que pertenecen a ese tipo
     */
    @Operation(summary = "Obtener detalle de tipo", description = "Devuelve los Pokémon filtrados por ese tipo")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Detalle del tipo obtenido"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Tipo no encontrado"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "502", description = "Error en la PokeAPI externa")
    })
    @GetMapping("/type/{idOrName}")
    public PokemonCatalogResponse getPokemonTypeDetails(
            @Parameter(description = "Nombre o ID del tipo", example = "fire") @PathVariable String idOrName) {
        return pokemonService.getPokemonTypeDetails(idOrName);
    }
}
