package com.example.pokeproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "PokeProxy API", version = "1.0", description = "Proxy API for Pokemon data"))
public class PokeproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokeproxyApplication.class, args);
	}

}
