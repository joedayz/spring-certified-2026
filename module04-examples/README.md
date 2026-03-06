# Módulo 04 – Web, REST y Testing

Ejemplos para las guías **3.1**, **3.2**, **4.1** y **4.2** del Spring Certification 2026.

## Estructura

| Módulo | Guía | Contenido |
|--------|------|-----------|
| **module04-guide-31-web** | 3.1 Web Applications | `@RestController`, `@GetMapping`, `@RequestParam`, `@PathVariable`, Message Converters (JSON), `ResponseEntity` |
| **module04-guide-32-rest** | 3.2 Aplicaciones REST | CRUD completo (GET, POST, PUT, DELETE), `@ResponseStatus`, `ServletUriComponentsBuilder` (Location), **RestTemplate** con `RestTemplateBuilder` |
| **module04-guide-41-testing** | 4.1 Testing Spring | `@SpringBootTest(webEnvironment = RANDOM_PORT)`, **TestRestTemplate**, `@ActiveProfiles("test")`, `@Sql` (schema/data), BD H2 en memoria |
| **module04-guide-42-testing-advanced** | 4.2 Testing Avanzado | **MockMVC**, `@WebMvcTest`, `@MockBean`, **@DataJpaTest**, `TestEntityManager`, `@AutoConfigureMockMvc` |

## Cómo ejecutar

```bash
# Compilar todo el módulo 04
mvn clean compile -f module04-examples

# Ejecutar todos los tests
mvn test -f module04-examples

# Ejecutar una aplicación (ej. guía 3.1)
mvn spring-boot:run -f module04-examples/module04-guide-31-web

# Guía 3.1 – Probar endpoints
# GET http://localhost:8080/api/saludo?nombre=Juan
# GET http://localhost:8080/api/items
# GET http://localhost:8080/api/items/1

# Guía 3.2 – API REST de tareas (el CommandLineRunner usa RestTemplate al arrancar)
mvn spring-boot:run -f module04-examples/module04-guide-32-rest
# GET/POST/PUT/DELETE http://localhost:8080/api/tasks
```

**Ejemplos curl (Windows CMD, PowerShell, Linux/Mac)** en cada submódulo:
- [module04-guide-31-web/CURLS.md](module04-guide-31-web/CURLS.md)
- [module04-guide-32-rest/CURLS.md](module04-guide-32-rest/CURLS.md)
- [module04-guide-41-testing/CURLS.md](module04-guide-41-testing/CURLS.md)
- [module04-guide-42-testing-advanced/CURLS.md](module04-guide-42-testing-advanced/CURLS.md)

## Resumen por guía

### 3.1 – Web Applications with Spring Boot
- DispatcherServlet, HandlerMapping, Message Converters.
- `GreetingController`: `/api/saludo`, `/api/items`, `/api/items/{id}`.
- Despliegue JAR con `spring-boot-maven-plugin`.

### 3.2 – Aplicaciones REST
- Códigos HTTP: 200, 201 Created (+ Location), 204 No Content, 404.
- `TaskController` + `TaskService` en memoria.
- `RestTemplateClientRunner`: ejemplo de cliente con `RestTemplateBuilder`, `postForEntity`, `exchange`, `delete`.

### 4.1 – Testing Spring Applications
- `@SpringBootTest(webEnvironment = RANDOM_PORT)` + `TestRestTemplate`.
- `@ActiveProfiles("test")` + `application-test.properties` (H2).
- `@Sql(scripts = "/schema.sql")` y `@Sql(scripts = "/data.sql")` para poblar la BD antes de cada test.

### 4.2 – Testing Avanzado (MockMVC y slices)
- **MockMVC**: `@WebMvcTest(BookController.class)`, `@MockBean` para `BookRepository`, `mockMvc.perform(get(...)).andExpect(...)`.
- **Slice JPA**: `@DataJpaTest`, `TestEntityManager` (`persistAndFlush`), tests del repositorio sin levantar el servidor.
- **Contexto completo**: `@SpringBootTest` + `@AutoConfigureMockMvc` para probar con MockMvc y BD real.
