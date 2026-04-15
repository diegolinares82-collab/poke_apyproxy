# PokeAPI Proxy — Spring Boot Backend

## 1. Project Overview


Este proyecto es una API proxy ligera construida con Java y Spring Boot que se sitúa delante de la PokéAPI pública (https://pokeapi.co/). En lugar de que las aplicaciones cliente consuman directamente la API externa, todas las solicitudes pasan por este proxy, el cual obtiene los datos de PokéAPI, los transforma a un modelo de dominio simplificado y devuelve un contrato limpio y estable a los consumidores.

Al controlar el contrato entre nuestros clientes y el proveedor externo, ganamos la capacidad de agregar caché, limitación de tasa (rate-limiting), monitoreo y transformación de respuestas sin impactar a los consumidores.

Es importante mencionar que la API de pokemons es publica y no requiere autenticación y que se requiere utilizar
maven como instalador de paquetes.


---

## 2. Objectives

| # | Objetivo |
|---|----------|
| 1 | Exponer un endpoint REST (GET /pokemon/{name}) que retorne datos simplificados de Pokémon. |
| 2 | Desacoplar a los consumidores internos del contrato externo de PokéAPI. |
| 3 | Retornar solo los campos relevantes para el dominio: id, nombre, tipos y habilidades. |
| 4 | Manejar errores del servicio externo de forma controlada y consistente. |
| 5 | Proveer una arquitectura limpia por capas, fácil de extender y probar. |

## 3. Technology Stack

- Language: Java 17
- Framework: Spring Boot
- Build Tool: Maven
- HTTP Client: RestClient or WebClient
- Testing: JUnit 5, Mockito, MockMvc




---

## 4. Architecture Description

La aplicación sigue una arquitectura en capas con separación clara de responsabilidades:

### Responsabilidades por capa

- Controller — Define los endpoints REST, valida parámetros y retorna códigos HTTP adecuados. No contiene lógica de negocio.
- Service — Orquesta la llamada al cliente, transforma la respuesta y aplica reglas de negocio.
- Client — Encapsula la comunicación HTTP con PokéAPI usando RestTemplate o WebClient.
- DTO — Clases que representan la estructura de entrada/salida de datos.

┌─────────────────────────────────────────────┐
│                  Controller                 │  ← Receives HTTP requests, delegates to Service
├─────────────────────────────────────────────┤
│                   Service                   │  ← Business logic & response mapping
├─────────────────────────────────────────────┤
│                   Client                    │  ← HTTP calls to external PokéAPI
├─────────────────────────────────────────────┤
│                    DTOs                     │  ← Data transfer objects (request/response)
└─────────────────────────────────────────────┘


### Layer Responsibilities

- Controller — Define los endpoints REST, valida los parámetros de ruta y retorna los códigos de estado HTTP adecuados. No contiene lógica de negocio.
- Service — Orquesta la llamada a la capa cliente, mapea la respuesta cruda del proveedor externo hacia un DTO simplificado y maneja las reglas de negocio.
- Client — Encapsula toda la comunicación HTTP con la PokéAPI externa usando RestClient (o WebClient) de Spring. Aísla los detalles de integración con terceros.
- DTO — Clases de datos simples que definen la estructura de la respuesta de la API que se envía a los consumidores y la estructura deserializada de la respuesta proveniente de la PokéAPI.

---

## 5. Folder Structure


src/
└── main/
    └── java/
        └── com/
            └── example/
                └── pokeproxy/
                    ├── PokeProxyApplication.java          # Spring boot punto de entrada
                    ├── controller/
                    │   └── PokemonController.java         # REST controller
                    ├── service/
                    │   └── PokemonService.java            # Lógica de negocio
                    ├── client/
                    │   └── PokeApiClient.java              # Integración externa API
                    ├── dto/
                    │   ├── PokemonResponse.java            # Simplificada respuesta DTO
                    │   └── external/
                    │       └── PokeApiPokemonResponse.java # Raw upstream DTO
                    ├── exception/
                    │   ├── PokemonNotFoundException.java   # Excepciones de dominio
                    │   └── GlobalExceptionHandler.java     # @ControllerAdvice
                    └── config/
                        └── RestClientConfig.java           # RestClient bean config


---

## 6. API Design and Endpoints

### GET /pokemon/{name}

Retrieve simplified Pokémon data by name.

| Attribute       | Value |
|-----------------|-------|
| *Method*      | GET |
| *Path*        | /pokemon/{name} |
| *Path Param*  | name — The Pokémon name (case-insensitive, e.g. pikachu) |
| *Success*     | 200 OK |
| *Not Found*   | 404 Not Found |
| *Upstream Err* | 502 Bad Gateway |

#### Response Fields


| Campo | Tipo | Descripción |
|------|------|-------------|
| id | entero | Número en la Pokédex |
| name | string | Nombre |
| types | string[] | Tipos |
| abilities | string[] | Habilidades |

---

## 7. Data Flow

Client App                Proxy API                          PokéAPI
    │                         │                                 │
    │  GET /pokemon/pikachu   │                                 │
    │────────────────────────►│                                 │
    │                         │  GET /api/v2/pokemon/pikachu    │
    │                         │────────────────────────────────►│
    │                         │                                 │
    │                         │  200 OK  (full JSON payload)    │
    │                         │◄────────────────────────────────│
    │                         │                                 │
    │                         │  Map to simplified DTO          │
    │                         │                                 │
    │  200 OK (simplified)    │                                 │
    │◄────────────────────────│                                 │

Cliente → Proxy → PokéAPI → Proxy → Cliente


1. El cliente envía GET /pokemon/{name}
2. El Controller recibe la petición
3. El Service llama al Client
4. El Client consume PokéAPI
5. El Service transforma la respuesta
6. El Controller retorna el resultado


---

## 8. Error Handling Strategy

Se usa un @ControllerAdvice global:
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Pokémon 'fakemon' was not found"
}
```
Casos: 
| Escenario | Respuesta |
|----------|----------|
| No existe | 404 |
| API caída | 502 |
| Error interno | 502 |
| Parámetro inválido | 400 |
## Principios
*No exponer errores del proveedor externo
*Respuestas consistentes
*Manejo de timeouts


## 9. External Dependencies

### PokéAPI
- URL base: https://pokeapi.co/api/v2
- Sin autenticación
- Límite: 100 requests/min

---

## 10. Example Request and Response

### Request


GET /pokemon/pikachu HTTP/1.1
Host: localhost:8080
Accept: application/json


### Response — 200 OK
```json
{
  "id": 25,
  "name": "pikachu",
  "types": [
    "electric"
  ],
  "abilities": [
    "static",
    "lightning-rod"
  ]
}
```


### Response — 404 Not Found


GET /pokemon/fakemon

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Pokémon 'fakemon' was not found"
}
```

---

## 11. Testing Strategy
El proyecto implementará una estrategia de pruebas en capas:

### Unit Tests
-   La capa de Service será probada usando JUnit 5 + Mockito
-   Las dependencias externas serán simuladas (cliente de PokéAPI)

### Controller Tests
- Se usará MockMvc para validar los endpoints REST
- Se validarán códigos de estado HTTP y el cuerpo de la respuesta

### Client Tests
- Client → pruebas utilizando respuestas simuladas (Mockito para pruebas unitarias). WireMock puede usarse opcionalmente para pruebas de nivel de integración.

### Test Coverage Scenarios
- 200 OK — successful Pokémon retrieval
- 404 Not Found — Pokémon does not exist
- 502 Bad Gateway — external API failure or timeout
- Invalid input — 400 Bad Request

## 12. Future Improvements

| Improvement | Description |
|-------------|-------------|
| *Caché* | Agregar una caché en memoria (por ejemplo, Caffeine o Spring Cache) para reducir las llamadas a PokéAPI y mejorar la latencia. Los datos de Pokémon son mayormente estáticos, lo que los convierte en un excelente candidato para caché. |
| *Rate Limiting* | Implementar limitación de tasa (por ejemplo, Bucket4j o Resilience4j RateLimiter) para proteger tanto el proxy como la API externa contra abuso. |
| *Circuit Breaker* |Usar Resilience4j Circuit Breaker para fallar rápido cuando PokéAPI esté degradada, evitando fallos en cascada. |
| *Endpoints adicionales* | Exponer endpoints para habilidades (/ability/{name}), tipos (/type/{name}) y búsqueda/listado con paginación. |
| *API Documentation* | Integrar Springdoc OpenAPI para generar automáticamente Swagger UI y especificaciones OpenAPI 3 0. |
| *Health Check* | Agregar un endpoint /actuator/health que verifique la conectividad con PokéAPI. |
| *Contenerización* | Proveer un Dockerfile y docker-compose.yml para entornos locales y CI/CD consistentes. |
| *Pruebas de integración* | Usar WireMock para simular respuestas de PokéAPI y escribir pruebas end-to-end de controladores. |
| *Métricas y observabilidad* | Exportar métricas con Micrometer (número de peticiones, latencia, tasa de errores) e integrarlas con Prometheus/Grafana. |