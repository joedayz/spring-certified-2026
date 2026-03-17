# Módulo 06 – Spring Boot

Ejemplos para las guías **6.1 Intro y Auto-configuración**, **6.2 Properties y Profiles** y **6.3 Actuator** (Spring Certification 2026).

## Estructura

| Módulo | Guía | Contenido |
|--------|------|-----------|
| **modulo06-guide-61-intro** | 6.1 Intro | @SpringBootApplication, Starters, Auto-configuración, @ConditionalOnProperty, CommandLineRunner |
| **modulo06-guide-62-properties** | 6.2 Properties | @Value, @ConfigurationProperties, Profiles (dev/prod), orden de precedencia, tipos complejos |
| **modulo06-guide-63-actuator** | 6.3 Actuator | Health, Info, Metrics, endpoint personalizado (@Endpoint), HealthIndicator, InfoContributor, Micrometer Counter |

## Cómo ejecutar

```bash
# Compilar todo
mvn clean compile -f modulo06-examples

# Guía 6.1 – Spring Boot Intro y Auto-configuración
mvn spring-boot:run -f modulo06-examples/modulo06-guide-61-intro

# Guía 6.2 – Properties y Profiles (default)
mvn spring-boot:run -f modulo06-examples/modulo06-guide-62-properties

# Guía 6.2 – Properties con perfil DEV
mvn spring-boot:run -f modulo06-examples/modulo06-guide-62-properties -Dspring-boot.run.profiles=dev

# Guía 6.2 – Properties con perfil PROD
mvn spring-boot:run -f modulo06-examples/modulo06-guide-62-properties -Dspring-boot.run.profiles=prod

# Guía 6.3 – Actuator
mvn spring-boot:run -f modulo06-examples/modulo06-guide-63-actuator
```

En cada submódulo hay un **CURLS.md** con ejemplos para Linux/Mac, Windows CMD y PowerShell.

- [modulo06-guide-61-intro/CURLS.md](modulo06-guide-61-intro/CURLS.md)
- [modulo06-guide-62-properties/CURLS.md](modulo06-guide-62-properties/CURLS.md)
- [modulo06-guide-63-actuator/CURLS.md](modulo06-guide-63-actuator/CURLS.md)

## Resumen por guía

### 6.1 – Spring Boot Intro y Auto-configuración
- **@SpringBootApplication** = @Configuration + @EnableAutoConfiguration + @ComponentScan.
- **Starters**: dependencias pre-empaquetadas (spring-boot-starter-web, -data-jpa, -security).
- **Auto-configuración**: Spring Boot crea beans automáticamente según las dependencias del classpath.
- **@Conditional**: familia de anotaciones para condicionar la creación de beans:
  - `@ConditionalOnClass`, `@ConditionalOnMissingBean`, `@ConditionalOnProperty`, etc.
- **CommandLineRunner** / **ApplicationRunner**: ejecutar lógica al arranque.

### 6.2 – Properties, @ConfigurationProperties y Profiles
- **@Value("${key:default}")**: inyecta propiedades individuales, soporta SpEL y valores por defecto.
- **@ConfigurationProperties(prefix)**: vincula grupo de propiedades a un POJO tipado.
  - Soporta tipos complejos: listas, mapas, objetos anidados.
  - Requiere `spring-boot-configuration-processor` para IDE autocomplete.
- **Profiles**: `application-{profile}.properties` se carga sobre el default.
  - Activar: `--spring.profiles.active=dev` o `-Dspring-boot.run.profiles=dev`.
- **Orden de precedencia** (mayor a menor):
  1. Argumentos de línea de comando
  2. Variables de entorno del SO
  3. `application-{profile}.properties`
  4. `application.properties`
  5. `@PropertySource`
  6. `SpringApplication.setDefaultProperties()`

### 6.3 – Spring Boot Actuator
- **spring-boot-starter-actuator**: agrega endpoints de monitoreo listos para usar.
- **Endpoints principales**: `/actuator/health`, `/actuator/info`, `/actuator/metrics`, `/actuator/beans`, `/actuator/env`, `/actuator/mappings`.
- **HealthIndicator**: verificación personalizada de salud (DB, servicios externos, disco).
- **InfoContributor**: información dinámica en `/actuator/info`.
- **Métricas con Micrometer**: Counter, Gauge, Timer para instrumentar la aplicación.
- **@Endpoint personalizado**: `@ReadOperation` (GET), `@WriteOperation` (POST), `@DeleteOperation` (DELETE).
- **Seguridad**: en producción, proteger endpoints con Spring Security. Exponer solo los necesarios con `management.endpoints.web.exposure.include`.
