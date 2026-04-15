package com.example.pokeproxy.dto;

import com.example.pokeproxy.dto.external.PokemonListResponse;
import lombok.Builder;
import lombok.Data;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Data
@Builder
@Schema(description = "Respuesta paginada con lista de Pokémon")
public class PokemonCatalogResponse {
    @Schema(description = "Cantidad total de elementos", example = "1302")
    private int count;
    
    @Schema(description = "URL para la siguiente página de resultados", example = "http://localhost:8080/pokemon?limit=20&offset=20")
    private String next;
    
    @Schema(description = "URL para la página anterior de resultados", example = "null")
    private String previous;
    
    @Schema(description = "Lista de resultados simplificados")
    private List<PokemonListResponse> results;
}
