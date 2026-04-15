package com.example.pokeproxy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PokemonNotFoundLimitException extends RuntimeException {
    public PokemonNotFoundLimitException(int limit, int offset) {
        super("Pokémon  with '" + limit + "' and" + offset + " was not found");
    }
}
