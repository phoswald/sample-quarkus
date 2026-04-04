# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

### Build and Run
```bash
# Build
mvn clean package

# Run in dev mode (hot reload)
mvn quarkus:dev

# Run tests (unit/integration with H2 in-memory DB)
mvn test

# Run a single test class
mvn test -Dtest=ApplicationTest

# Build native image
mvn clean package -Pnative

# Build Docker image
mvn clean package -Pdocker

# Build and deploy Docker image
mvn clean deploy -Pdocker
```

## Architecture

### Application Structure

All JAX-RS resources are mounted under `/app` (defined by `RestApplication` with `@ApplicationPath("/app")`). The app has two kinds of endpoints:

- **REST API** (`/app/rest/*`): JSON/XML endpoints for programmatic access
- **Page controllers** (`/app/pages/*`): HTML endpoints using Qute templates, require authentication

### Package Layout

- `com.github.phoswald.sample` — `RestApplication` (JAX-RS root), `AppMessages`
- `com.github.phoswald.sample.sample` — Sample REST endpoints (`SampleResource`) and Qute page (`SampleController`)
- `com.github.phoswald.sample.task` — Task CRUD: `TaskResource` (REST), `TaskController` (HTML pages), `TaskEntity` (JPA), `TaskRepository`, `TaskViewModel`
- `com.github.phoswald.sample.security` — `UserEntity`, `UserResource`, `PasswordUtility`
- `com.github.phoswald.sample.health` — SmallRye Health liveness/readiness checks

### Technology Stack

- **Quarkus 3.x (latest)** with Java 25
- **JAX-RS** via `quarkus-rest` (not the legacy `quarkus-resteasy`)
- **JSON** via `quarkus-rest-jsonb`, **XML** via `quarkus-rest-jaxb`
- **Templating**: Qute (`quarkus-rest-qute`), templates in `src/main/resources/templates/*.qute.html`
- **Database**: Hibernate ORM with PostgreSQL in production, H2 in-memory for tests
- **Security**: Form-based auth (`quarkus-security-jpa`); `/app/pages/*` requires authentication
- **Observability**: SmallRye Health at `/q/health`, Micrometer/Prometheus metrics at `/q/metrics`

### Configuration

- Production config: `src/main/resources/application.properties` (PostgreSQL, form auth enabled)
- Test config: `src/test/resources/application.properties` (H2 in-memory, auth disabled)
- Tests use `@QuarkusTest` with `@TestSecurity` to bypass form auth
- Integration tests (`*IT.java`) run against native binary with `-Pnative`

### Static Resources

- `src/main/resources/META-INF/resources/` — served at root (`/`): `index.html`, `login.html`, `error.html`
