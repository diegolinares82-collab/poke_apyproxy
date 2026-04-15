## Why

Render allows deploying applications using Docker, which provides a consistent and isolated environment. Creating a Dockerfile ensures that the application runs with the correct Java version and dependencies, regardless of the target platform's native environment. This simplifies the deployment process and improves reliability.

## What Changes

- **NEW**: `Dockerfile` in the project root.
- **NEW**: `.dockerignore` to optimize the build image size.

## Capabilities

### New Capabilities
- `containerized-deployment`: The application can now be built into a Docker image and deployed to any container orchestration service, including Render.

## Impact

- `Dockerfile`: New build instructions.
- `.dockerignore`: New file to exclude unnecessary files from the Docker context.
