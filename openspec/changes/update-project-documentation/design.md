## Context

We need to rewrite `project.md` to be a comprehensive summary of the project.

## Goals / Non-Goals

**Goals:**
- Update the list of technologies (Java 17 -> 21, Spring Boot 3.4.1).
- List ALL endpoints with examples.
- Add a section for Documentation (Swagger).
- Add a section for Configuration (CORS, Proxy URL).
- Add a section for Deployment (Render).

**Non-Goals:**
- Modifying any application logic or DTOs.

## Decisions

### 1. Unified Documentation in `project.md`
Instead of splitting info across multiple files, we will make `project.md` the "Source of Truth" for the project's current state.

### 2. Detailed Endpoint Mapping
We will use a table to show:
- `/pokemon`: Catalog with paging.
- `/pokemon/{idOrName}`: Detailed view.
- `/pokemon-species/{idOrName}`: Multi-language species info.
- `/type`: Simplified type list.
- `/type/{idOrName}`: Filtering by type.

### 3. Configuration Best Practices
We will document the environment variables `CORS_ALLOWED_ORIGINS`, `PROXY_BASE_URL`, and `PORT`.

## Risks / Trade-offs

- **[Risk]** Documentation can become stale if not updated with code. However, at this stage of the project, it provides a much-needed final overview.
