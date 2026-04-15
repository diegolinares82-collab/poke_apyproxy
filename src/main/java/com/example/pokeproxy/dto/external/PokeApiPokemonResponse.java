package com.example.pokeproxy.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PokeApiPokemonResponse {
    private Integer id;
    private String name;
    private Integer height;
    private Integer weight;
    private List<TypeEntry> types;
    private List<AbilityEntry> abilities;
    private List<StatEntry> stats;
    private Sprites sprites;

    @Data
    public static class TypeEntry {
        private Integer slot;
        private TypeDetail type;
    }

    @Data
    public static class TypeDetail {
        private String name;
        private String url;
    }

    @Data
    public static class AbilityEntry {
        private AbilityDetail ability;
        @JsonProperty("is_hidden")
        private boolean isHidden;
    }

    @Data
    public static class AbilityDetail {
        private String name;
    }

    @Data
    public static class StatEntry {
        @JsonProperty("base_stat")
        private Integer baseStat;
        private Integer effort;
        private StatDetail stat;
    }

    @Data
    public static class StatDetail {
        private String name;
        private String url;
    }

    @Data
    public static class Sprites {
        @JsonProperty("front_default")
        private String frontDefault;
        private OtherSprites other;
    }

    @Data
    public static class OtherSprites {
        @JsonProperty("official-artwork")
        private Artwork officialArtwork;
        private Home home;
    }

    @Data
    public static class Artwork {
        @JsonProperty("front_default")
        private String frontDefault;
    }

    @Data
    public static class Home {
        @JsonProperty("front_default")
        private String frontDefault;
    }
}
