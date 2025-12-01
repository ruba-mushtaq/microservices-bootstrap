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


- `observability-service/` — the Spring Boot application
- `docker/` — docker-compose and monitoring configs (Prometheus, Grafana, Tempo)
- `docs/` — setup guides and metric explanations


---


## Quick start (developer)


1. Move your existing project into `observability-service/` (rename to `observability-service` if necessary).
2. Make sure the service has Actuator, Micrometer Prometheus registry, and tracing (Micrometer + Tempo) configured.
3. From repo root, run:


```bash
cd docker
docker compose up --build