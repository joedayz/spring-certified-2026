# Guía 6.3 – Spring Boot Actuator – Comandos cURL

## Iniciar la aplicación

```bash
mvn spring-boot:run -f modulo06-examples/modulo06-guide-63-actuator
```

---

## 1. Listar endpoints de Actuator disponibles

### Linux / Mac
```bash
curl -s http://localhost:8080/actuator | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/actuator
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/actuator
```

---

## 2. Health (incluye health indicator personalizado)

### Linux / Mac
```bash
curl -s http://localhost:8080/actuator/health | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/actuator/health
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/actuator/health
```

---

## 3. Info (info estática + InfoContributor dinámico)

### Linux / Mac
```bash
curl -s http://localhost:8080/actuator/info | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/actuator/info
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/actuator/info
```

---

## 4. Métricas disponibles

### Linux / Mac
```bash
curl -s http://localhost:8080/actuator/metrics | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/actuator/metrics
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/actuator/metrics
```

---

## 5. Métrica específica (JVM memory)

### Linux / Mac
```bash
curl -s http://localhost:8080/actuator/metrics/jvm.memory.used | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/actuator/metrics/jvm.memory.used
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/actuator/metrics/jvm.memory.used
```

---

## 6. Generar peticiones y ver métrica personalizada

### Linux / Mac
```bash
# Hacer varias peticiones
curl -s http://localhost:8080/api/demo | python3 -m json.tool
curl -s http://localhost:8080/api/demo | python3 -m json.tool
curl -s http://localhost:8080/api/demo | python3 -m json.tool

# Ver métrica personalizada
curl -s http://localhost:8080/actuator/metrics/api.requests.total | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/api/demo
curl http://localhost:8080/api/demo
curl http://localhost:8080/actuator/metrics/api.requests.total
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/api/demo
Invoke-RestMethod -Uri http://localhost:8080/api/demo
Invoke-RestMethod -Uri http://localhost:8080/actuator/metrics/api.requests.total
```

---

## 7. Simular caída del servicio externo (Health indicator DOWN)

### Linux / Mac
```bash
# Poner servicio DOWN
curl -s -X POST "http://localhost:8080/api/health/toggle?up=false" | python3 -m json.tool

# Verificar health (debería mostrar DOWN)
curl -s http://localhost:8080/actuator/health | python3 -m json.tool

# Restaurar servicio UP
curl -s -X POST "http://localhost:8080/api/health/toggle?up=true" | python3 -m json.tool

# Verificar health (debería mostrar UP)
curl -s http://localhost:8080/actuator/health | python3 -m json.tool
```

### Windows CMD
```cmd
curl -X POST "http://localhost:8080/api/health/toggle?up=false"
curl http://localhost:8080/actuator/health
curl -X POST "http://localhost:8080/api/health/toggle?up=true"
curl http://localhost:8080/actuator/health
```

### PowerShell
```powershell
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/health/toggle?up=false"
Invoke-RestMethod -Uri http://localhost:8080/actuator/health
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/health/toggle?up=true"
Invoke-RestMethod -Uri http://localhost:8080/actuator/health
```

---

## 8. Endpoint personalizado: /actuator/releases

### Linux / Mac
```bash
# Leer releases (GET)
curl -s http://localhost:8080/actuator/releases | python3 -m json.tool

# Agregar release (POST)
curl -s -X POST http://localhost:8080/actuator/releases \
  -H "Content-Type: application/json" \
  -d '{"version":"2.0.0","descripcion":"Nueva funcionalidad"}' | python3 -m json.tool

# Leer de nuevo
curl -s http://localhost:8080/actuator/releases | python3 -m json.tool

# Eliminar todas (DELETE)
curl -s -X DELETE http://localhost:8080/actuator/releases | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/actuator/releases
curl -X POST http://localhost:8080/actuator/releases -H "Content-Type: application/json" -d "{\"version\":\"2.0.0\",\"descripcion\":\"Nueva funcionalidad\"}"
curl http://localhost:8080/actuator/releases
curl -X DELETE http://localhost:8080/actuator/releases
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/actuator/releases
Invoke-RestMethod -Method Post -Uri http://localhost:8080/actuator/releases -ContentType "application/json" -Body '{"version":"2.0.0","descripcion":"Nueva funcionalidad"}'
Invoke-RestMethod -Uri http://localhost:8080/actuator/releases
Invoke-RestMethod -Method Delete -Uri http://localhost:8080/actuator/releases
```

---

## 9. Otros endpoints útiles de Actuator

### Linux / Mac
```bash
# Beans registrados
curl -s http://localhost:8080/actuator/beans | python3 -m json.tool

# Variables de entorno
curl -s http://localhost:8080/actuator/env | python3 -m json.tool

# Mappings (todos los @RequestMapping)
curl -s http://localhost:8080/actuator/mappings | python3 -m json.tool

# Condiciones de auto-configuración
curl -s http://localhost:8080/actuator/conditions | python3 -m json.tool
```

---

## 10. Guía rápida de Actuator

### Linux / Mac
```bash
curl -s http://localhost:8080/api/actuator-guia | python3 -m json.tool
```

### Windows CMD
```cmd
curl http://localhost:8080/api/actuator-guia
```

### PowerShell
```powershell
Invoke-RestMethod -Uri http://localhost:8080/api/actuator-guia
```
