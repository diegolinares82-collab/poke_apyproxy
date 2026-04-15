package com.example.pokeproxy.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokeApiSpeciesResponse {
    
    private int id;
    private String name;
    
    @JsonProperty("flavor_text_entries")
    private List<FlavorTextEntry> flavorTextEntries;
    
    private List<GenusEntry> genera;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FlavorTextEntry {
        @JsonProperty("flavor_text")
        private String flavorText;
        private Language language;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GenusEntry {
        private String genus;
        private Language language;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Language {
        private String name;
    }
}
