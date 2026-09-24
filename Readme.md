# FlashSeat 🎟️

FlashSeat is a production-oriented event ticket booking backend built with
Spring Boot, PostgreSQL, Redis, Kafka, JWT authentication, and Docker.

## 🚀 Features

- User registration and authentication
- JWT-based authentication and authorization
- Event management
- Seat management
- Seat availability validation
- Redis-based temporary seat locking
- Booking management
- Kafka booking events
- Microservice communication using OpenFeign
- PostgreSQL database
- Swagger/OpenAPI documentation
- Spring Boot Actuator
- Global exception handling
- Request/response logging
- Correlation / Request ID
- Docker containerization
- Docker Compose

## 🛠️ Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

### Database
- PostgreSQL

### Caching / Locking
- Redis

### Messaging
- Apache Kafka

### API Documentation
- Swagger / OpenAPI

### DevOps
- Docker
- Docker Compose

## 🏗️ Architecture

```text
                    Client
                      |
                      v
              +---------------+
              |  Spring Boot   |
              |    FlashSeat   |
              +---------------+
                 /     |      \
                /      |       \
               v       v        v
        PostgreSQL   Redis     Kafka
          Database    Seat     Booking
                     Lock      Events








Authorization: Bearer <JWT_TOKEN>




Client
   |
   v
Booking API
   |
   v
Validate User/Event/Seat
   |
   v
Check Redis Seat Lock
   |
   v
Check Seat Availability
   |
   v
Book Seat
   |
   +----> PostgreSQL
   |
   +----> Kafka
              |
              v
       Booking Event Consumer






API Documentation

Swagger UI:  http://localhost:8080/swagger-ui/index.html


OpenAPI specification:

http://localhost:8080/v3/api-docs




Health Monitoring

Spring Boot Actuator:  http://localhost:8080/actuator/health



Docker

Build the application: 
 docker build -t flashseat:1.0 .

Run with Docker Compose:

docker compose up -d

Stop:

docker compose down

Check containers:

docker compose ps

View logs:

docker compose logs -f
📋 Example Endpoints
Authentication
POST /auth/login
Events
GET  /events
POST /events
Seats
GET  /events/{eventId}/seats
POST /events/{eventId}/seats
Bookings
POST /bookings
🧱 Production Features

FlashSeat includes several production-oriented backend concepts:

JWT authentication
Role-based authorization
Redis distributed seat locking
Kafka asynchronous event processing
Global API exception handling
HTTP status-based error responses
Request/response logging
Correlation IDs
Health monitoring
Docker containerization
Docker Compose