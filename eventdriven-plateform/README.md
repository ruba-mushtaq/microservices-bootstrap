# EventDriven Platform

A **microservices-based e-commerce platform** designed for portfolio demonstration.  
This project showcases **reactive Spring Boot services**, **event-driven architecture with Kafka**, and **service-to-service communication**.

---

## Services

| Service               | Port  | Description                                        |
|-----------------------|-------|----------------------------------------------------|
| Product Service       | 8081  | CRUD operations for products                      |
| Cart Service          | 8082  | Manage user cart, emits `CartUpdatedEvent`       |
| Order Service         | 8083  | Process orders, emits `OrderEvent`               |
| Inventory Service     | 8084  | Adjust stock based on events, emits `InventoryEvent` |
| Notification Service  | 8085  | Consumes events and logs notifications           |

---

## Features

- **Product Management:** Add, update, delete, list products  
- **Cart Management:** Add/remove items in cart, reactive stock updates  
- **Order Processing:** Place orders and track status  
- **Inventory Management:** Stock adjustments based on cart and order events  
- **Notifications:** Logs events for portfolio demonstration  
- **Event-driven Architecture:** Uses **Kafka** to communicate between services  
- **Reactive Programming:** Implemented with **Spring WebFlux** and **R2DBC**  
- **JWT Authentication:** Optional for securing user requests  

---

## Tech Stack

- **Java 17**  
- **Spring Boot 3.4.x**  
- **Spring WebFlux (Reactive)**  
- **Spring Data R2DBC + H2 Database**  
- **Kafka (Event-driven communication)**  
- **Lombok**  
- **Docker (optional for full stack)**  

---

## API Endpoints

### Product Service
```
GET    /products
GET    /products/{id}
POST   /products
PUT    /products/{id}
DELETE /products/{id}
```

### Cart Service
```
GET    /cart/{userId}
POST   /cart
PATCH  /cart/{cartId}
DELETE /cart/{cartId}
```

### Order Service
```
GET    /orders
GET    /orders/user/{userId}
POST   /orders
PATCH  /orders/{orderId}  # update order status
```

### Inventory Service
```
GET    /inventory
```

### Notification Service
- Logs events from Inventory and Order services.

---

## Event Flow (Kafka Topics)

| Topic Name        | Producer          | Consumer               |
|------------------|-----------------|----------------------|
| cart-updated      | Cart Service     | Inventory Service     |
| order-events      | Order Service    | Inventory, Notification Services |
| inventory-events  | Inventory Service| Notification Service  |

---

## Architecture Overview

```
[Product Service] --> [Cart Service] --> [Order Service] --> [Inventory Service] --> [Notification Service]
        |                    |                  |                 |                          |
        v                    v                  v                 v                          v
    REST APIs           Kafka Events       Kafka Events      Kafka Events              Logs/Notifications
```

- **Reactive Services** using Spring WebFlux  
- **Event-driven Communication** via Kafka Topics  
- **H2 Database (R2DBC)** per service for simplicity  
- **Notification Service** logs event messages (can be extended to email or push notifications)  

---

## Running Locally (STS/Eclipse)

1. **Start Kafka in KRaft mode (no ZooKeeper required)**

```bash
docker-compose -f docker-compose-kafka.yml up -d
```

2. **Run each microservice from IDE (STS/Eclipse)**

```bash
# Example for Product Service
./mvnw spring-boot:run
```

3. **Test API Flow with Postman or curl**

- Add product → Add to cart → Place order → Check inventory → Notifications logged  

---

## Docker (Optional)

Each service has a `Dockerfile`. You can optionally run all services and Kafka together via Docker Compose for full-stack testing.

---

## Project Structure

```
ecommerce-platform-mini/
├── product-service/
├── cart-service/
├── order-service/
├── inventory-service/
├── notification-service/
└── docker-compose.yml
```

---

## Portfolio Showcase

This project demonstrates:

- **Microservice Architecture**  
- **Reactive Programming with Spring WebFlux**  
- **Event-driven Communication with Kafka**  
- **End-to-end Service Integration**  
- **Realistic E-commerce Workflow Simulation**  
- **JWT Authentication (optional)**  

This is ideal for demonstrating **senior-level design skills** in your GitHub portfolio.

---

## Author

**Your Name** – Senior Java Developer | Microservices Enthusiast

