package com.example.pokeproxy.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class PokemonTypeResponse {
    private int id;
    private String name;
    private List<PokemonSimplified> pokemons;

    @Data
    @Builder
    public static class PokemonSimplified {
        private int id;
        private String name;
    }
}
