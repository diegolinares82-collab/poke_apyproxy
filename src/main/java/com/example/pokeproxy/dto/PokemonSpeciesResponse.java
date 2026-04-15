package com.example.pokeproxy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Información de la especie de un Pokémon")
public class PokemonSpeciesResponse {
    
    @Schema(description = "Lista de entradas de texto descriptivo en varios idiomas")
    @JsonProperty("flavor_text_entries")
    private List<FlavorTextEntry> flavorTextEntries;
    
    @Schema(description = "Lista de géneros o categorías del Pokémon")
    private List<GenusEntry> genera;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Entrada de texto descriptivo")
    public static class FlavorTextEntry {
        @Schema(description = "Texto descriptivo del Pokémon", example = "A strange seed was planted on its back at birth.")
        @JsonProperty("flavor_text")
        private String flavorText;
        private Language language;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Entrada de género/categoría")
    public static class GenusEntry {
        @Schema(description = "Nombre de la categoría", example = "Seed Pokémon")
        private String genus;
        private Language language;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Información del idioma")
    public static class Language {
        @Schema(description = "Nombre del idioma", example = "en")
        private String name;
    }
}
