# Tasks: Pokemon Proxy API

## 1. Setup del proyecto

- [x] 1.1 Crear proyecto Spring Boot (Java 17, Maven) via start.spring.io
- [x] 1.2 Configurar dependencias necesarias (web, lombok, etc.)
- [x] 1.3 Definir estructura de paquetes base

## 2. Configuración HTTP Client

- [x] 2.1 Crear configuración de RestClient
- [x] 2.2 Definir base URL de PokéAPI en application.properties

## 3. Implementación del Client

- [x] 3.1 Crear clase PokeApiClient
- [x] 3.2 Implementar método para consumir `/api/v2/pokemon/{name}`
- [x] 3.3 Manejar errores de comunicación

## 4. Definición de DTOs

- [x] 4.1 Crear PokemonResponse (respuesta simplificada)
- [x] 4.2 Crear PokeApiPokemonResponse (respuesta externa)

## 5. Implementación del Service

- [x] 5.1 Crear PokemonService
- [x] 5.2 Llamar al client
- [x] 5.3 Mapear respuesta externa a DTO interno
- [x] 5.4 Manejar lógica de negocio

## 6. Implementación del Controller

- [x] 6.1 Crear PokemonController
- [x] 6.2 Exponer endpoint GET /pokemon/{name}
- [x] 6.3 Validar parámetros de entrada

## 7. Manejo de errores

- [x] 7.1 Crear excepción personalizada (PokemonNotFoundException)
- [x] 7.2 Implementar GlobalExceptionHandler (@ControllerAdvice)
- [x] 7.3 Mapear errores a respuestas HTTP (404, 502)

## 8. Pruebas básicas

- [x] 8.1 Probar endpoint con Postman o curl
- [x] 8.2 Validar respuestas correctas
- [x] 8.3 Validar manejo de errores

## 9. Mejoras opcionales

- [x] 9.1 Agregar logs
- [x] 9.2 Preparar estructura para caché futura

## 10. Testing Strategy

### Task: Create Service Unit Tests

- [x] 10.1 Usar mockito para dependencias de mock
- [x] 10.2 Test successful Pokémon retrieval
- [x] 10.3 Test not found scenario
- [x] 10.4 Test external API failure handling
- [x] 10.5 Cubrir aproximadamente el 90% de las líneas de código

---

### Task: Create Controller Tests

- [x] 11.1 Use MockMvc
- [x] 11.2 Validate GET /pokemon/{name}
- [x] 11.3 Assert HTTP 200, 404, 502 responses

## 12. Documentación y Swagger

- [x] 12.1 Agregar dependencia springdoc-openapi a pom.xml
- [x] 12.2 Añadir comentarios Javadoc a PokeApiClient, PokemonService, PokemonController
- [x] 12.3 Configurar metadatos básicos de Swagger (@OpenAPIDefinition)