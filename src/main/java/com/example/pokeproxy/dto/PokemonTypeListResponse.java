package com.example.pokeproxy.dto;

import com.example.pokeproxy.dto.external.PokemonListResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta simplificada con la lista de tipos de Pokémon")
public class PokemonTypeListResponse {
    @Schema(description = "Cantidad total de tipos disponibles", example = "20")
    private int count;
    
    @Schema(description = "Lista de tipos con nombre y URL")
    private List<PokemonListResponse> results;
}
