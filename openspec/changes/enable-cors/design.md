## Context

Spring Boot provides several ways to enable CORS. The most flexible and standard approach for global configuration is implementing `WebMvcConfigurer`.

## Goals / Non-Goals

**Goals:**
- Enable CORS globally for all endpoints.
- Allow configurable origins via `application.properties`.
- Allow standard HTTP methods (GET, POST, etc.) and common headers.

**Non-Goals:**
- Implementing per-controller CORS using `@CrossOrigin` (preferring global config).
- Adding Spring Security just for CORS.

## Decisions

### 1. Global WebMvcConfigurer
We will create `WebConfig` implement `WebMvcConfigurer`. This allows us to define `addCorsMappings` in a single place.

### 2. Configurable Origins
We will add `cors.allowed-origins` to `application.properties` with a default value of `*`. This follows best practices for environment-specific configuration.

## Risks / Trade-offs

- **[Risk]** Using `*` as the default allowed origin is permissive. However, for a public proxy API or local development, it provides the best out-of-the-box experience. Production deployments should override this property.
