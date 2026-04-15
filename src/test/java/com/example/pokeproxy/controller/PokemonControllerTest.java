package com.example.pokeproxy.controller;

import com.example.pokeproxy.dto.PokemonResponse;
import com.example.pokeproxy.dto.PokemonCatalogResponse;
import com.example.pokeproxy.dto.external.PokemonListResponse;
import com.example.pokeproxy.dto.PokemonSpeciesResponse;
import com.example.pokeproxy.dto.PokemonTypeListResponse;
import com.example.pokeproxy.exception.PokemonNotFoundException;
import com.example.pokeproxy.exception.PokeApiException;
import com.example.pokeproxy.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PokemonController.class)
class PokemonControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private PokemonService pokemonService;

        @Test
        void getPokemon_ShouldReturnResult() throws Exception {
                // Arrange
                String name = "pikachu";
                PokemonResponse.TypeEntry typeEntry = PokemonResponse.TypeEntry.builder()
                                .slot(1)
                                .type(PokemonResponse.TypeDetail.builder().name("electric").url("url").build())
                                .build();
                PokemonResponse.AbilityEntry abilityEntry = PokemonResponse.AbilityEntry.builder()
                                .ability(PokemonResponse.AbilityDetail.builder().name("static").build())
                                .isHidden(false)
                                .build();
                PokemonResponse.StatEntry statEntry = PokemonResponse.StatEntry.builder()
                                .baseStat(35)
                                .stat(PokemonResponse.StatDetail.builder().name("hp").build())
                                .build();
                PokemonResponse.Sprites sprites = PokemonResponse.Sprites.builder()
                                .frontDefault("front")
                                .other(PokemonResponse.OtherSprites.builder()
                                                .officialArtwork(PokemonResponse.Artwork.builder()
                                                                .frontDefault("artwork").build())
                                                .home(PokemonResponse.Home.builder().frontDefault("home").build())
                                                .build())
                                .build();

                PokemonResponse response = PokemonResponse.builder()
                                .id(25)
                                .name(name)
                                .height(4)
                                .weight(60)
                                .types(Collections.singletonList(typeEntry))
                                .abilities(Collections.singletonList(abilityEntry))
                                .stats(Collections.singletonList(statEntry))
                                .sprites(sprites)
                                .build();

                when(pokemonService.getPokemonByName(name)).thenReturn(response);

                // Act & Assert
                mockMvc.perform(get("/pokemon/{name}", name))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(25))
                                .andExpect(jsonPath("$.name").value(name))
                                .andExpect(jsonPath("$.height").value(4))
                                .andExpect(jsonPath("$.weight").value(60))
                                .andExpect(jsonPath("$.types[0].type.name").value("electric"))
                                .andExpect(jsonPath("$.abilities[0].ability.name").value("static"))
                                .andExpect(jsonPath("$.stats[0].base_stat").value(35))
                                .andExpect(jsonPath("$.sprites.other['official-artwork'].front_default")
                                                .value("artwork"));
        }

        @Test
        void getPokemonsCatalog_ShouldReturnResult() throws Exception {
                // Arrange
                PokemonListResponse item = PokemonListResponse.builder()
                                .name("bulbasaur")
                                .url("http://localhost:8080/pokemon/1")
                                .build();
                PokemonCatalogResponse response = PokemonCatalogResponse.builder()
                                .count(1)
                                .next("http://localhost:8080/pokemon?limit=20&offset=20")
                                .previous(null)
                                .results(Collections.singletonList(item))
                                .build();

                when(pokemonService.getPokemonsLimitOffset(20, 0)).thenReturn(response);

                // Act & Assert
                mockMvc.perform(get("/pokemon").param("limit", "20").param("offset", "0"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.count").value(1))
                                .andExpect(jsonPath("$.next").value("http://localhost:8080/pokemon?limit=20&offset=20"))
                                .andExpect(jsonPath("$.results[0].name").value("bulbasaur"))
                                .andExpect(jsonPath("$.results[0].url").value("http://localhost:8080/pokemon/1"));
        }

        @Test
        void getPokemonSpecies_ShouldReturnResult() throws Exception {
                // Arrange
                String idOrName = "1";
                PokemonSpeciesResponse.FlavorTextEntry entry = PokemonSpeciesResponse.FlavorTextEntry.builder()
                                .flavorText("Description")
                                .language(PokemonSpeciesResponse.Language.builder().name("es").build())
                                .build();
                PokemonSpeciesResponse.GenusEntry genus = PokemonSpeciesResponse.GenusEntry.builder()
                                .genus("Genus")
                                .language(PokemonSpeciesResponse.Language.builder().name("es").build())
                                .build();

                PokemonSpeciesResponse response = PokemonSpeciesResponse.builder()
                                .flavorTextEntries(Collections.singletonList(entry))
                                .genera(Collections.singletonList(genus))
                                .build();

                when(pokemonService.getPokemonSpecies(idOrName)).thenReturn(response);

                // Act & Assert
                mockMvc.perform(get("/pokemon-species/{idOrName}", idOrName))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.flavor_text_entries[0].flavor_text").value("Description"))
                                .andExpect(jsonPath("$.flavor_text_entries[0].language.name").value("es"))
                                .andExpect(jsonPath("$.genera[0].genus").value("Genus"));
        }

        @Test
        void getPokemonTypes_ShouldReturnResult() throws Exception {
                // Arrange
                PokemonListResponse item = PokemonListResponse.builder()
                                .name("normal")
                                .url("http://localhost:8080/type/1")
                                .build();
                PokemonTypeListResponse response = PokemonTypeListResponse.builder()
                                .count(10)
                                .results(Collections.singletonList(item))
                                .build();

                when(pokemonService.getPokemonTypes()).thenReturn(response);

                // Act & Assert
                mockMvc.perform(get("/type"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.count").value(10))
                                .andExpect(jsonPath("$.results[0].name").value("normal"))
                                .andExpect(jsonPath("$.results[0].url").value("http://localhost:8080/type/1"))
                                .andExpect(jsonPath("$.next").doesNotExist())
                                .andExpect(jsonPath("$.previous").doesNotExist());
        }

        @Test
        void getPokemonTypeDetails_ShouldReturnResult() throws Exception {
                // Arrange
                String idOrName = "fire";
                PokemonListResponse pkmn = PokemonListResponse.builder()
                                .name("charmander")
                                .url("http://localhost:8080/pokemon/4")
                                .build();
                PokemonCatalogResponse response = PokemonCatalogResponse.builder()
                                .count(1)
                                .next(null)
                                .previous(null)
                                .results(Collections.singletonList(pkmn))
                                .build();

                when(pokemonService.getPokemonTypeDetails(idOrName)).thenReturn(response);

                // Act & Assert
                mockMvc.perform(get("/type/{idOrName}", idOrName))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.count").value(1))
                                .andExpect(jsonPath("$.results[0].name").value("charmander"))
                                .andExpect(jsonPath("$.results[0].url").value("http://localhost:8080/pokemon/4"));
        }

        @Test
        void verifyErrorHandling_404() throws Exception {
                String idOrName = "unknown";
                when(pokemonService.getPokemonByName(idOrName)).thenThrow(new PokemonNotFoundException(idOrName));

                mockMvc.perform(get("/pokemon/{idOrName}", idOrName))
                                .andExpect(status().isNotFound());
        }

        @Test
        void verifyErrorHandling_502() throws Exception {
                String idOrName = "error";
                when(pokemonService.getPokemonByName(idOrName)).thenThrow(new PokeApiException("External error"));

                mockMvc.perform(get("/pokemon/{idOrName}", idOrName))
                                .andExpect(status().isBadGateway());
        }
}
