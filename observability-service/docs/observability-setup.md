# Observability setup (explainers & runbook)


## Overview
This repo wires a Spring Boot service to Prometheus, Grafana and Tempo.


Flow:
1. Spring Boot exposes metrics at `/actuator/prometheus` (Micrometer). These metrics include counters, timers and histograms.
2. Prometheus scrapes the metrics and stores them.
3. Grafana reads Prometheus and displays dashboards.
4. Traces are reported from the app to Tempo (via OTLP/Jaeger compatible receiver) and linked in Grafana Explore/Tempo UI.


## Key Actuator / Micrometer config hints
- Add dependency `io.micrometer:micrometer-registry-prometheus`.
- Expose `/actuator/prometheus` and `/actuator/health` in application config file (application.yml or application.properties).


Example `application.yml` entries:


```yaml
management:
 endpoints:
  web:
  exposure:
   include: health,info,prometheus,loggers
 metrics:
  distribution:
   percentiles-histogram:
    http_server_requests: true