# Distributed Service Health Monitor

A dependency-aware service monitoring system built with Java and Spring Boot.

This project simulates a simplified Site Reliability Engineering (SRE) monitoring platform capable of:

* monitoring multiple services through HTTP health endpoints,
* detecting service failures,
* propagating dependency outages,
* performing scheduled health checks,
* logging operational events and failures.

The goal of this project was to better understand backend systems, service communication, observability concepts, and Java development.

---

# Features

## Service Health Endpoints

The application exposes multiple simulated services:

* `/payment/health`
* `/database/health`
* `/auth/health`

Each service returns structured JSON health information.

Example:

```json
{
  "service": "payment",
  "status": "UP",
  "timestamp": "2026-05-12T23:10:00"
}
```

---

## Monitoring Endpoint

The monitoring endpoint aggregates service states:

```text
/monitor
```

Example response:

```json
[
  {
    "service": "payment",
    "status": "DEGRADED"
  },
  {
    "service": "database",
    "status": "DOWN"
  },
  {
    "service": "auth",
    "status": "UP"
  }
]
```

---

## Dependency Propagation

Services can depend on other services.

Example:

```text
payment-service → depends on → database-service
```

If the database service fails:

* database → `DOWN`
* payment → `DEGRADED`

This simulates distributed system behavior.

---

## Scheduled Health Checks

The monitoring system automatically performs periodic health checks using Spring scheduling.

Monitoring runs continuously in the background without requiring manual API calls.

---

## Structured Logging

The application logs operational events using Spring Boot logging.

Example log output:

```text
DATABASE SERVICE DOWN
PAYMENT SERVICE DEGRADED DUE TO DATABASE FAILURE
```

---

# Technologies Used

* Java 21
* Spring Boot
* Maven
* REST APIs
* HTTP communication
* Scheduled tasks
* SLF4J / Logback logging

---

# Project Structure

```text
src/main/java/com/daniele/monitor
│
├── config
│   └── ServiceConfig.java
│
├── controller
│   ├── HealthController.java
│   └── MonitorController.java
│
├── model
│   └── ServiceStatus.java
│
├── service
│   └── MonitoringService.java
│
└── DistributedServiceHealthMonitorApplication.java
```

# Available Endpoints

| Endpoint           | Description                   |
| ------------------ | ----------------------------- |
| `/payment/health`  | Payment service health        |
| `/database/health` | Database service health       |
| `/auth/health`     | Authentication service health |
| `/monitor`         | Aggregated monitoring status  |

---

# What I Learned

Through this project I practiced:

* Spring Boot fundamentals
* REST API development
* JSON serialization/deserialization
* Service-to-service HTTP communication
* Scheduled background tasks
* Failure handling
* Dependency-aware monitoring logic
* Structured logging
* Production-style backend architecture

---

# Future Improvements

Possible future enhancements:

* Docker containerization
* Persistent monitoring history
* Dynamic service registration
* Retry/backoff strategies
* Web dashboard
* Real multi-process service deployment

---

# Purpose of the Project

The main goal of this project was educational.

I wanted to build a Java project that focuses on reliability, monitoring, and backend systems concepts instead of a traditional CRUD application.
