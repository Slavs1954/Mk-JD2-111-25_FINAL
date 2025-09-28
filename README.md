# Finance Management Application

A modular finance management application built with Spring Boot and PostgreSQL. The project is split into multiple services, each handling a specific domain of the application.

All services are containerized using Docker and can be composed together using Docker Compose.

---

## Services / Modules

| Service              | Port | Description                                                          |
| -------------------- | ---- | -------------------------------------------------------------------- |
| `postgres`           | 5433 | Database service for storing app data.                               |
| `user-service`       | 8080 | Handles user registration, authentication, and user data management. |
| `mailer-service`     | 8081 | Sends email verification codes.                                      |
| `classifier-service` | 8082 | Provides classification and reference data.                          |
| `account-service`    | 8083 | Manages user accounts and financial operations.                      |
| `audit-service`      | 8084 | Tracks changes and events in the system.                             |
| `scheduler-service`  | 8085 | Handles scheduled operations.                                        |
| `report-service`     | 8086 | Generates and provides financial reports.                            |

---

## Prerequisites

* **Docker**
* **Docker Compose**
* **Maven**
* **Java 17+** *(if running services locally without Docker)*

---

## Environment Variables

All services use `.env` files to configure credentials and settings. The main `.env` folder contains:

* `common.env` – shared environment variables for all services.
* Service-specific `.env` files, e.g.:

  * `user-service.env`
  * `mailer-service.env`
  * `account-service.env`
  * `audit-service.env`
  * `report-service.env`

Make sure to fill out required values (e.g., database username, password, JWT secrets) before starting the services.

---

## Running the Application
First you have to build the application
From the project root directory, run:
```bash
mvn clean package
```
Then again from root directory, run:

```bash
docker-compose --env-file ./env/common.env up --build
```

This will:

1. Build the .jar files of microservices
2. Start the PostgreSQL database.
3. Build and start all microservices.
4. Expose the services on their respective ports (see table above).

To stop the application:

```bash
docker-compose down
```

To rebuild a single service after changes:

```bash
docker-compose build <service-name>
```

---

## Notes

* Each service uses the `docker` Spring profile by default.
* Services communicate with each other via Docker’s internal network using service names and HTTP. For example, `user-service` can call `mailer-service` at `http://mailer-service:8081`.
* The PostgreSQL database is persisted in a Docker volume `postgres_data`.

---

## Directory Structure

```
/user-service
/mailer-service
/classifier-service
/account-service
/audit-service
/scheduler-service
/report-service
/env/
src/main/resources/sql
docker-compose.yaml
```

* Each service has its own Dockerfile and environment variables.
* The `env` folder contains shared and service-specific environment configuration.

---
## Specification
This project was made in compliance with openAPI specifications of [ITAcademyPersonalFinanceTools](https://github.com/WestDragon/ITAcademyPersonalFinanceTools)
