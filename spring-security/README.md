# Spring Security - Learning Guide

A hands-on project to learn **Spring Security** incrementally — each branch demonstrates a different authentication mechanism, progressing from the simplest to the most production-ready approach.

## Tech Stack

| Tool              | Version |
|-------------------|---------|
| Java              | 21      |
| Spring Boot       | 4.0.3   |
| Gradle            | Groovy DSL |
| Spring Security   | (managed by Spring Boot BOM) |

## Project Structure

```
src/main/java/com/demo/springsecurity/
├── SpringSecurityApplication.java      # Entry point
├── controller/
│   └── DemoController.java             # Sample REST endpoint (GET /hello)
└── ...                                 # Additional classes per branch
```

## Branch Guide

Each branch builds on the concepts from the previous one. Follow them in order for the best learning experience.

| #  | Branch Name           | Authentication Type                | What You Will Learn |
|----|-----------------------|------------------------------------|---------------------|
| 1  | `01-default-form`     | Default (Form-based login)         | What Spring Security gives you out of the box — auto-generated login page, default user, CSRF protection |
| 2  | `02-basic-auth`       | HTTP Basic Authentication          | Stateless auth via `Authorization: Basic` header, how to disable form login, browser popup dialog via `WWW-Authenticate` header, testing with cURL/Postman & browser |
| 3  | `03-in-memory-auth`   | In-Memory User Store               | Configuring multiple users & roles in memory using `InMemoryUserDetailsManager`, `PasswordEncoder` |
| 4  | `04-role-based-auth`  | Role-Based Access Control          | `@PreAuthorize`, `@Secured`, role hierarchies, method-level security, restricting endpoints by role |
| 5  | `05-database-auth`    | Database Authentication (MySQL/PostgreSQL) | Production-like setup with an external DB, `UserDetailsService` implementation, JPA entities for users & roles |
| 6  | `06-jwt-auth`         | JWT (JSON Web Token)               | Stateless token-based auth, issuing & validating JWTs, refresh tokens, securing REST APIs |

## Getting Started

### Prerequisites

- **Java 21** installed ([sdkman](https://sdkman.io/) recommended)
- **Git** installed

### Clone & Run

```bash
# Clone the repository
git clone <your-repo-url>
cd spring-security

# Checkout the branch you want to study
git checkout 01-default-form

# Run the application
./gradlew bootRun
```

The app starts on **http://localhost:8080**.

### Test Endpoint

```
GET http://localhost:8080/hello
```

Returns: `Hello World` (once authenticated)

## Branch Details

### 01-default-form — Default Form-Based Login

Spring Security's out-of-the-box behavior. Just adding the `spring-boot-starter-security` dependency gives you:
- An auto-generated login page at `/login`
- A default user (`user`) with a password printed in the console at startup
- CSRF protection enabled
- All endpoints secured by default

### 02-basic-auth — HTTP Basic Authentication

Switches from form-based to HTTP Basic auth. Credentials are sent as a Base64-encoded `Authorization` header with every request. When accessed from a browser, the server responds with `401` + `WWW-Authenticate: Basic` header, triggering the browser's native popup dialog for credentials. Ideal for testing with cURL, Postman, and the browser.

### 03-in-memory-auth — In-Memory Users & Roles

Defines multiple users with different roles directly in a `SecurityConfig` class. No database needed — great for prototyping and understanding role-based access control.

### 04-role-based-auth — Role-Based Access Control

Builds on the in-memory users from the previous branch to explore authorization. Covers:
- Restricting endpoints by role using `hasRole()` and `hasAuthority()`
- Method-level security with `@PreAuthorize` and `@Secured`
- Role hierarchies (e.g., ADMIN inherits USER permissions)
- Custom access-denied handling

### 05-database-auth — Database Authentication (MySQL/PostgreSQL)

A production-like setup where users and roles live in a real relational database. Implements a custom `UserDetailsService` backed by JPA entities.

### 06-jwt-auth — JWT Token-Based Authentication

Fully stateless authentication using JSON Web Tokens. Covers:
- Login endpoint that issues a JWT
- Filter that validates the token on every request
- Token expiry and refresh mechanism
- Securing REST APIs without server-side sessions

## Useful References

- [Spring Security Official Docs](https://docs.spring.io/spring-security/reference/)
- [Spring Boot Security Reference](https://docs.spring.io/spring-boot/4.0.3/reference/web/spring-security.html)
- [Securing a Web Application (Spring Guide)](https://spring.io/guides/gs/securing-web/)
- [Baeldung - Spring Security Series](https://www.baeldung.com/security-spring)
