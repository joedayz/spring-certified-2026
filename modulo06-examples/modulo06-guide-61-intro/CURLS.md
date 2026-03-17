# Guía 6.1 – Spring Boot Intro y Auto-configuración – Comandos cURL

## Iniciar la aplicación

```bash
mvn spring-boot:run -f modulo06-examples/modulo06-guide-61-intro
```

---

## 1. Info general de Spring Boot

### Linux / Mac
```bash
curl -s http://localhost:8080/api/info | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/api/info
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/api/info
```

---

## 2. Listar todos los beans registrados

### Linux / Mac
```bash
curl -s http://localhost:8080/api/beans | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/api/beans
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/api/beans
```

---

## 3. Beans de auto-configuración

### Linux / Mac
```bash
curl -s http://localhost:8080/api/auto-config-beans | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/api/auto-config-beans
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/api/auto-config-beans
```

---

## 4. Estado de beans condicionales (@ConditionalOnProperty)

### Linux / Mac
```bash
curl -s http://localhost:8080/api/conditional/status | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/api/conditional/status
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/api/conditional/status
```

---

## 5. Greeting (activo por defecto)

### Linux / Mac
```bash
curl -s "http://localhost:8080/api/conditional/greet?name=Joe" | python3 -m json.tool
```

### Windows CMD
```cmd
curl "http://localhost:8080/api/conditional/greet?name=Joe"
```

### PowerShell
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/conditional/greet?name=Joe"
```

---

## 6. Farewell (inactivo por defecto – activar con `app.feature.farewell=true`)

### Linux / Mac
```bash
curl -s "http://localhost:8080/api/conditional/farewell?name=Joe" | python3 -m json.tool
```

### Windows CMD
```cmd
curl "http://localhost:8080/api/conditional/farewell?name=Joe"
```

### PowerShell
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/conditional/farewell?name=Joe"
```
