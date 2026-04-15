package com.example.pokeproxy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información detallada de un Pokémon")
public class PokemonResponse {
    @Schema(description = "ID único del Pokémon", example = "25")
    private Integer id;
    
    @Schema(description = "Nombre del Pokémon", example = "pikachu")
    private String name;
    
    @Schema(description = "Altura en decímetros", example = "4")
    private Integer height;
    
    @Schema(description = "Peso en hectogramos", example = "60")
    private Integer weight;
    
    @Schema(description = "Lista de tipos del Pokémon")
    private List<TypeEntry> types;
    
    @Schema(description = "Lista de habilidades del Pokémon")
    private List<AbilityEntry> abilities;
    
    @Schema(description = "Lista de estadísticas base")
    private List<StatEntry> stats;
    
    @Schema(description = "Sprites y artes oficiales")
    private Sprites sprites;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Entrada de tipo con su posición")
    public static class TypeEntry {
        @Schema(description = "Posición del tipo en la lista del Pokémon", example = "1")
        private Integer slot;
        private TypeDetail type;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Detalles del nombre y URL del tipo")
    public static class TypeDetail {
        @Schema(description = "Nombre del tipo", example = "electric")
        private String name;
        @Schema(description = "URL para obtener más detalles del tipo", example = "https://pokeapi.co/api/v2/type/13/")
        private String url;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Entrada de habilidad")
    public static class AbilityEntry {
        private AbilityDetail ability;
        @Schema(description = "Si la habilidad es oculta para este Pokémon")
        @JsonProperty("is_hidden")
        private boolean isHidden;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AbilityDetail {
        @Schema(description = "Nombre de la habilidad", example = "static")
        private String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Entrada de estadística")
    public static class StatEntry {
        @Schema(description = "Valor base de la estadística", example = "35")
        @JsonProperty("base_stat")
        private Integer baseStat;

        @Schema(description = "Puntos de esfuerzo (EV) otorgados", example = "0")
        private Integer effort;

        private StatDetail stat;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatDetail {
        @Schema(description = "Nombre de la estadística", example = "hp")
        private String name;

        @Schema(description = "URL para obtener más detalles de la estadística", example = "https://pokeapi.co/api/v2/stat/1/")
        private String url;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Contenedor de imágenes del Pokémon")
    public static class Sprites {
        @Schema(description = "Sprite frontal por defecto", example = "url_to_sprite")
        @JsonProperty("front_default")
        private String frontDefault;
        private OtherSprites other;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Otras representaciones visuales")
    public static class OtherSprites {
        @JsonProperty("official-artwork")
        private Artwork officialArtwork;
        private Home home;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Artwork {
        @Schema(description = "Arte oficial frontal", example = "url_to_artwork")
        @JsonProperty("front_default")
        private String frontDefault;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Home {
        @Schema(description = "Sprite estilo 'Home' frontal", example = "url_to_home_sprite")
        @JsonProperty("front_default")
        private String frontDefault;
    }
}
