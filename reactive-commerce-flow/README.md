#  Microservices Architecture -- User, Payment & Order Services (Spring WebFlux + JWT)

This repository contains a reactive microservices system built with
**Spring Boot WebFlux**, **Reactive H2 database**, and **JWT Authentication**.\
It includes three independent services:

-   **User Service** -- User registration, login, JWT generation\
-   **Payment Service** -- Wallet balance deduction and payment
    processing\
-   **Order Service** -- Order creation, token validation, and payment
    integration

##  Architecture Overview

This system follows a clean microservices pattern:

-   Each service runs independently\
-   All services connect to reactive H2 database\
-   Order Service communicates with Payment Service\
-   JWT is validated in every request using a WebFlux `WebFilter`

##  Security (JWT)

-   Login route returns a JWT token\
-   Every service validates JWT in a filter\
-   Unauthorized requests → **401 UNAUTHORIZED**

Example Authorization header:

    Authorization: Bearer <your_jwt_token>

##  Project Structure

    reactive-commerce-flow/
     ├── user-service/
     │    ├── controllers/
     │    ├── services/
     │    ├── repository/
     │    └── JwtUtil.java
     │
     ├── payment-service/
     │    ├── controllers/
     │    ├── services/
     │    ├── repository/
     │    └── PaymentController.java
     │
     └── order-service/
          ├── controllers/
          ├── services/
          ├── repository/
          ├── JwtAuthenticationFilter.java
          └── OrderController.java

##  Tech Stack

-   Java 17\
-   Spring Boot 3 (Reactive WebFlux)\
-   Reactive H2 database\
-   JWT for authentication\
-   Project Reactor (Mono / Flux)

##  User Service Endpoints

  Method   Endpoint                  Description
  -------- ------------------------- -----------------------
  POST      `/auth/register`          Register User
  POST      `/auth/register`          Login user

##  Order Service Endpoints

  Method   Endpoint                  Description
  -------- ------------------------- -----------------------
  GET      `/orders`                 Get all orders
  GET      `/orders/user/{userId}`   Get orders for a user
  POST     `/orders`                 Create a new order
  PATCH    `/orders/{id}`            Update order status
  
> All endpoints require a valid JWT.

##  Payment Service Endpoints

  Method   Endpoint                         Description
  -------- -------------------------        -----------------------
  POST      `/payments`          	        Update pending order to confirm/paid
  GET      `/payments/order/{orderId}`      Get order by order id

> All endpoints require a valid JWT.

##  Inter-Service Communication

Order Service → Payment Service

**Payment Request Example**

``` json
POST /payments/pay
{
  "userId": "123",
  "amount": 250
}
```

**Payment Service returns:**\
- `SUCCESS` → order marked as **CONFIRMED**\
- `FAILED` → order marked as **FAILED**

##  How to Run the Services

### 1. Start Each Service

``` bash
mvn spring-boot:run
```

##  Sample Test Flow

### 1️⃣ Get JWT Token (login)

``` json
POST /users/login
{
  "email": "test@gmail.com",
  "password": "1234"
}
```

### Create an Order

``` bash
curl -X POST http://localhost:8081/orders   -H "Authorization: Bearer <token>"   -H "Content-Type: application/json"   -d '{"userId":"123","amount":200}'
```

