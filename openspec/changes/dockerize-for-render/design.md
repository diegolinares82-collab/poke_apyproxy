## Context

We need a production-ready Dockerfile for a Spring Boot application.

## Goals / Non-Goals

**Goals:**
- Use a multi-stage build to keep the final image size small.
- Use Java 21 (Eclipse Temurin is a good base).
- Ensure the application respects the `PORT` environment variable.
- Optimized for quick builds (caching dependencies).

**Non-Goals:**
- Setting up a full CI/CD pipeline (only the Dockerfile).

## Decisions

### 1. Multi-stage Build
We will use `maven:3.9-eclipse-temurin-21` for the build stage and `eclipse-temurin:21-jre-jammy` for the runtime stage. This separates build-time dependencies (Maven, source code) from the final artifact (.jar file).

### 2. Dependency Caching
We will copy the `pom.xml` and download dependencies first to take advantage of Docker layer caching.

### 3. Entrypoint
The entrypoint will be `java -jar app.jar`. Since we used `${PORT:8080}` in `application.properties`, Spring Boot will automatically use the `PORT` variable provided by Render.

## Risks / Trade-offs

- **[Trade-off]** Using a JRE instead of a full JDK in the final image improves security and size but prevents using tools like `jstack` or `jmap` inside the container if needed for deep debugging.
