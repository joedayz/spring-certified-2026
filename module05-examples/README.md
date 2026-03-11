# Módulo 05 – Spring Security

Ejemplos para las guías **5.1 Conceptos**, **5.2 Autenticación y Autorización** y **5.3 Method Security** (Spring Certification 2026).

## Estructura

| Módulo | Guía | Contenido |
|--------|------|-----------|
| **module05-guide-51-concepts** | 5.1 Conceptos | Filter Chain, SecurityContext, Principal, Authorities, DelegatingPasswordEncoder (BCrypt), permitAll vs autenticado, CSRF/CORS |
| **module05-guide-52-auth** | 5.2 Auth | Autorización por URL (orden de reglas), permitAll(), web.ignoring(), in-memory users, CORS |
| **module05-guide-53-method-security** | 5.3 Method Security | @PreAuthorize, @PostAuthorize, @Secured, @RolesAllowed, SpEL, seguridad en capa de servicio |

## Cómo ejecutar

```bash
# Compilar todo
mvn clean compile -f module05-examples

# Guía 5.1 – Conceptos
mvn spring-boot:run -f module05-examples/module05-guide-51-concepts

# Guía 5.2 – Auth (orden de reglas, permitAll, ignoring)
mvn spring-boot:run -f module05-examples/module05-guide-52-auth

# Guía 5.3 – Method Security
mvn spring-boot:run -f module05-examples/module05-guide-53-method-security
```

Usuarios en memoria (todos los módulos):

| Usuario | Contraseña | Roles |
|---------|------------|--------|
| alumno / user | Perusalen123 / user | USER |
| admin | admin | USER, ADMIN |
| manager | manager | USER, MANAGER (solo 5.3) |

Autenticación: **HTTP Basic**. Ejemplo curl:

```bash
curl -u user:user http://localhost:8080/api/me
```

En cada submódulo hay un **CURLS.md** con ejemplos para Linux/Mac, Windows CMD y PowerShell.

- [module05-guide-51-concepts/CURLS.md](module05-guide-51-concepts/CURLS.md)
- [module05-guide-52-auth/CURLS.md](module05-guide-52-auth/CURLS.md)
- [module05-guide-53-method-security/CURLS.md](module05-guide-53-method-security/CURLS.md)

## Resumen por guía

### 5.1 – Conceptos
- **Autenticación** (quién eres) → **Autorización** (qué puedes hacer). Principal, Authorities.
- **Filter Chain**: SecurityContextPersistenceFilter, BasicAuthenticationFilter, ExceptionTranslationFilter, etc.
- **Contraseñas**: BCrypt / DelegatingPasswordEncoder, nunca texto plano.
- **CSRF**: proteger POST/PUT/DELETE cuando se usan cookies; en API stateless se puede deshabilitar.
- **CORS**: orígenes, métodos y headers permitidos.

### 5.2 – Autenticación y autorización
- **Orden de reglas**: first match wins; poner reglas más específicas primero.
- **permitAll()**: acceso sin login pero la petición **sí** pasa por la cadena de filtros.
- **web.ignoring()**: la petición **no** pasa por Spring Security (estáticos).
- In-memory users, CORS con `CorsConfigurationSource`.

### 5.3 – Method Security
- **@EnableGlobalMethodSecurity**(prePostEnabled, securedEnabled, jsr250Enabled).
- **@PreAuthorize**: SpEL antes de ejecutar (hasRole, hasAuthority, #id).
- **@PostAuthorize**: SpEL después; puede usar `returnObject` y `authentication.name`.
- **@Secured** / **@RolesAllowed**: roles (convención ROLE_).
- Asegurar la **capa de servicio**, no solo controllers.
