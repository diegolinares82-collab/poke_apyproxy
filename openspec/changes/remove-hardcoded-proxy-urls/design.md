## Context

The current implementation in `PokemonService` manually concatenates `http://localhost:8080` to result IDs. This must be replaced with a dynamic approach.

## Goals / Non-Goals

**Goals:**
- Eliminate all occurrences of `http://localhost:8080` in the source code.
- Ensure URLs in the JSON response are dynamically generated based on the current request or configuration.

**Non-Goals:**
- Changing the structure of the JSON response fields (only the content of the `url` fields will change).

## Decisions

### 1. Unified Base URL Configuration
We will add a property `proxy.base-url` to `application.properties`.
```properties
proxy.base-url=http://localhost:${server.port:8080}
```

### 2. URL Construction Logic
In `PokemonService`, we will inject this property and use it to build URLs. 
Alternatively, for even more robustness, we could use `ServletUriComponentsBuilder.fromCurrentContextPath()`, but since the service might be called outside a request context in some scenarios (unlikely here but possible), an injected property is safer.

We will use the injected property:
```java
@Value("${proxy.base-url}")
private String proxyBaseUrl;
```

## Risks / Trade-offs

- **[Trade-off]** Using a property means the user must configure it correctly for different environments. However, it's easier to manage than hardcoded strings and allows for different schemes (http vs https) and ports without code changes.
