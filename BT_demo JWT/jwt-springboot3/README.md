This is a minimal Spring Boot 3 JWT example.

Endpoints:
- POST /api/auth/register { fullName, email, password }
- POST /api/auth/login { email, password } -> returns { token }

H2 console: http://localhost:8080/h2-console

Notes:
- Update `jwt.secret` in `application.properties` to a long random string for production.
- Java 17 required to build.
