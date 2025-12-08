# Observability — Docker Setup


A focused demo repository showing observability for a Spring Boot microservice using:


- Spring Boot (Actuator)
- Micrometer
- Prometheus (metrics collection)
- Grafana (visualization)
- Tempo (distributed tracing)
- Docker & Docker Compose


This repo is intended as a portfolio piece demonstrating how to add, run, and visualize observability for a Spring Boot service.


---


## Contents


- `logging-and-observability/sample-service` — the Spring Boot application
- `logging-and-observability/` — docker-compose and monitoring configs (Prometheus, Grafana, Tempo)
- `docs/` — explained important links


---


## Quick start (developer)


1. Move your existing project into `logging-and-observability/` (rename to `logging-and-observability` if necessary).
2. Make sure the service has Actuator, Micrometer Prometheus registry, and tracing (Micrometer + Tempo) configured.
3. From repo root, run:


```bash
cd logging-and-observability
docker compose up --build