package com.example.pokeproxy.service;

import com.example.pokeproxy.client.PokeApiClient;
import com.example.pokeproxy.dto.PokemonResponse;
import com.example.pokeproxy.dto.external.PokeApiPokemonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private PokeApiClient pokeApiClient;

    private PokemonService pokemonService;

    @BeforeEach
    void setUp() {
        pokemonService = new PokemonService(pokeApiClient);
    }

    @Test
    void getPokemonByName_ShouldReturnMappedResponse() {
        // Arrange
        String name = "pikachu";
        PokeApiPokemonResponse externalResponse = new PokeApiPokemonResponse();
        externalResponse.setId(25);
        externalResponse.setName(name);
        externalResponse.setHeight(4);
        externalResponse.setWeight(60);

        PokeApiPokemonResponse.TypeEntry typeEntry = new PokeApiPokemonResponse.TypeEntry();
        typeEntry.setSlot(1);
        PokeApiPokemonResponse.TypeDetail typeDetail = new PokeApiPokemonResponse.TypeDetail();
        typeDetail.setName("electric");
        typeDetail.setUrl("url");
        typeEntry.setType(typeDetail);
        externalResponse.setTypes(Collections.singletonList(typeEntry));

        PokeApiPokemonResponse.AbilityEntry abilityEntry = new PokeApiPokemonResponse.AbilityEntry();
        PokeApiPokemonResponse.AbilityDetail abilityDetail = new PokeApiPokemonResponse.AbilityDetail();
        abilityDetail.setName("static");
        abilityEntry.setAbility(abilityDetail);
        abilityEntry.setHidden(false);
        externalResponse.setAbilities(Collections.singletonList(abilityEntry));

        PokeApiPokemonResponse.StatEntry statEntry = new PokeApiPokemonResponse.StatEntry();
        statEntry.setBaseStat(35);
        PokeApiPokemonResponse.StatDetail statDetail = new PokeApiPokemonResponse.StatDetail();
        statDetail.setName("hp");
        statEntry.setStat(statDetail);
        externalResponse.setStats(Collections.singletonList(statEntry));

        PokeApiPokemonResponse.Sprites sprites = new PokeApiPokemonResponse.Sprites();
        sprites.setFrontDefault("front");
        PokeApiPokemonResponse.OtherSprites other = new PokeApiPokemonResponse.OtherSprites();
        PokeApiPokemonResponse.Artwork artwork = new PokeApiPokemonResponse.Artwork();
        artwork.setFrontDefault("artwork");
        other.setOfficialArtwork(artwork);
        PokeApiPokemonResponse.Home home = new PokeApiPokemonResponse.Home();
        home.setFrontDefault("home");
        other.setHome(home);
        sprites.setOther(other);
        externalResponse.setSprites(sprites);

        when(pokeApiClient.getPokemon(name)).thenReturn(externalResponse);

        // Act
        PokemonResponse response = pokemonService.getPokemonByName(name);

        // Assert
        assertEquals(25, response.getId());
        assertEquals(name, response.getName());
        assertEquals(4, response.getHeight());
        assertEquals(60, response.getWeight());
        assertEquals("electric", response.getTypes().get(0).getType().getName());
        assertEquals("static", response.getAbilities().get(0).getAbility().getName());
        assertEquals(35, response.getStats().get(0).getBaseStat());
        assertEquals("artwork", response.getSprites().getOther().getOfficialArtwork().getFrontDefault());
    }
}
