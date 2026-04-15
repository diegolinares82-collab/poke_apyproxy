# PokeProxy - Pokémon API Proxy

Un servicio intermedio (Proxy) robusto construido con Spring Boot que consume datos de la API oficial de Pokémon [PokeAPI](https://pokeapi.co/) y los entrega de forma simplificada, optimizada y profesionalmente documentada.

## 🚀 Tecnologías Utilizadas

*   **Lenguaje:** Java 21
*   **Framework:** Spring Boot 3.4.1
*   **Cliente HTTP:** Spring RestClient
*   **Documentación:** SpringDoc OpenAPI (Swagger UI)
*   **Caché:** Spring Cache (ConcurrentMapCache)
*   **Productividad:** Project Lombok
*   **Build Tool:** Maven

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una estructura de capas para el desacoplamiento de responsabilidades:

1.  **Controller (`PokemonController`):** Gestiona los puntos de entrada (Endpoints) y está decorado con anotaciones de OpenAPI.
2.  **Service (`PokemonService`):** Contiene la lógica de negocio, transformación de DTOs y gestión de URLs dinámicas.
3.  **Client (`PokeApiClient`):** Comunicación directa con PokeAPI con manejo de excepciones resiliente.
4.  **Config:** Configuración global de CORS, RestClient y Cache.

## 🛣️ Endpoints Disponibles

La API está completamente documentada. Puedes explorar los detalles técnicos, esquemas de JSON y probar los endpoints en la interfaz de Swagger.

### Resumen de Endpoints:

| Método | Endpoint | Descripción | Ejemplo |
| :--- | :--- | :--- | :--- |
| `GET` | `/pokemon` | Catálogo paginado de Pokémon. | `/pokemon?limit=20&offset=0` |
| `GET` | `/pokemon/{idOrName}` | Datos detallados (stats, tipos, habilidades, sprites). | `/pokemon/pikachu` |
| `GET` | `/pokemon-species/{idOrName}` | Descripción (flavor text) y género en varios idiomas. | `/pokemon-species/1` |
| `GET` | `/type` | Lista simplificada de todos los tipos elementales. | `/type` |
| `GET` | `/type/{idOrName}` | Lista de Pokémon filtrados por un tipo específico. | `/type/electric` |

## ⚙️ Configuración y Variables de Envío

El proyecto es altamente configurable para diferentes entornos mediante el archivo `src/main/resources/application.properties` o variables de entorno del sistema.

| Propiedad | Variable de Entorno | Descripción | Valor por Defecto |
| :--- | :--- | :--- | :--- |
| `server.port` | `PORT` | Puerto de escucha del servidor. | `8080` |
| `cors.allowed-origins`| `CORS_ALLOWED_ORIGINS` | Orígenes permitidos para CORS (separados por coma). | `*` |
| `proxy.base-url` | `PROXY_BASE_URL` | URL base utilizada para generar enlaces en el JSON. | `http://localhost:8080` |

### Ejemplo para Render:
Si despliegas en Render, debes configurar estas variables en su panel:
- `PROXY_BASE_URL`: `https://tu-servicio.onrender.com`
- `CORS_ALLOWED_ORIGINS`: `https://tu-frontend.com` (opcional)

## 🛠️ Cómo Ejecutar Localmente

1.  Clonar el repositorio.
2.  Asegurarse de tener Java 21 y Maven instalados.
3.  Ejecutar el comando:
    ```bash
    mvn spring-boot:run
    ```
4.  Acceder a la documentación en `http://localhost:8080/swagger-ui.html`.

## ⚠️ Mantenibilidad
- El código incluye Javadoc completo en controladores y servicios.
- Se utiliza un `GlobalExceptionHandler` para manejar errores 404 y 502 de forma elegante.
