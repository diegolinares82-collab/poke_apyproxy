package com.example.pokeproxy.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokeApiTypeResponse {

    private int id;
    private String name;
    private List<PokemonEntry> pokemon;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PokemonEntry {
        private PokemonResource pokemon;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PokemonResource {
        private String name;
        private String url;
    }
}
