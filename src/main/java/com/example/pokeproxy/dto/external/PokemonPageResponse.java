package com.example.pokeproxy.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonPageResponse {
    @JsonProperty("count")
    private int count;
    private String next;
    private String previous;
    private List<PokemonResults> results;
}