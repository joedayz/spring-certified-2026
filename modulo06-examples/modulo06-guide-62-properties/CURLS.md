# Guía 6.2 – Properties, @ConfigurationProperties y Profiles – Comandos cURL

## Iniciar la aplicación

```bash
# Default profile
mvn spring-boot:run -f modulo06-examples/modulo06-guide-62-properties

# Perfil DEV
mvn spring-boot:run -f modulo06-examples/modulo06-guide-62-properties -Dspring-boot.run.profiles=dev

# Perfil PROD
mvn spring-boot:run -f modulo06-examples/modulo06-guide-62-properties -Dspring-boot.run.profiles=prod
```

---

## 1. Propiedades con @Value

### Linux / Mac
```bash
curl -s http://localhost:8080/api/value | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/api/value
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/api/value
```

---

## 2. @ConfigurationProperties (tipos complejos, objetos anidados)

### Linux / Mac
```bash
curl -s http://localhost:8080/api/config-properties | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/api/config-properties
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/api/config-properties
```

---

## 3. Profiles activos y orden de precedencia

### Linux / Mac
```bash
curl -s http://localhost:8080/api/profiles | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/api/profiles
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/api/profiles
```

---

## 4. Probar cambio de profile

Detener la app y reiniciar con perfil `dev`:

```bash
mvn spring-boot:run -f modulo06-examples/modulo06-guide-62-properties -Dspring-boot.run.profiles=dev
```

Luego repetir las llamadas anteriores para ver los valores diferentes.

---

## 5. Sobreescribir propiedad por línea de comando

```bash
mvn spring-boot:run -f modulo06-examples/modulo06-guide-62-properties \
  -Dspring-boot.run.arguments="--app.message=Sobreescrito_por_CLI"
```

Verificar:
```bash
curl -s http://localhost:8080/api/value | python3 -m json.tool
```
