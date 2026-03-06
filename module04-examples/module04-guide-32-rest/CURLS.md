# Curls – Guía 3.2 Aplicaciones REST (Tasks API)

Arrancar la app: `mvn spring-boot:run` (puerto 8080).

---

## GET /api/tasks (listar todas)

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/tasks'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/tasks"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/tasks"
```

---

## POST /api/tasks (crear – 201 + Location)

**Linux / Mac**
```bash
curl -s -X POST -H "Content-Type: application/json" \
  -d '{"title":"Mi tarea","description":"Descripción","completed":false}' \
  'http://localhost:8080/api/tasks' -w "\nLocation: %{redirect_url}\n" -D -
```

**Windows CMD**
```cmd
curl -X POST -H "Content-Type: application/json" -d "{\"title\":\"Mi tarea\",\"description\":\"Descripcion\",\"completed\":false}" "http://localhost:8080/api/tasks" -i
```

**PowerShell**
```powershell
$body = '{"title":"Mi tarea","description":"Descripcion","completed":false}'
Invoke-RestMethod -Uri "http://localhost:8080/api/tasks" -Method Post -Body $body -ContentType "application/json"
# Ver headers (Location):
Invoke-WebRequest -Uri "http://localhost:8080/api/tasks" -Method Post -Body $body -ContentType "application/json" -UseBasicParsing | Select-Object -ExpandProperty Headers
```

---

## GET /api/tasks/1 (obtener por ID)

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/tasks/1'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/tasks/1"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/tasks/1"
```

---

## PUT /api/tasks/1 (actualizar – 204 No Content)

**Linux / Mac**
```bash
curl -s -X PUT -H "Content-Type: application/json" \
  -d '{"title":"Tarea actualizada","description":"Nueva desc","completed":true}' \
  -w "\n%{http_code}\n" -o NUL 'http://localhost:8080/api/tasks/1'
```

**Windows CMD**
```cmd
curl -X PUT -H "Content-Type: application/json" -d "{\"title\":\"Tarea actualizada\",\"description\":\"Nueva desc\",\"completed\":true}" "http://localhost:8080/api/tasks/1" -w "\n%%{http_code}\n" -o NUL
```

**PowerShell**
```powershell
$body = '{"title":"Tarea actualizada","description":"Nueva desc","completed":true}'
Invoke-RestMethod -Uri "http://localhost:8080/api/tasks/1" -Method Put -Body $body -ContentType "application/json"
```

---

## DELETE /api/tasks/1 (204 No Content)

**Linux / Mac**
```bash
curl -s -X DELETE 'http://localhost:8080/api/tasks/1' -w "\n%{http_code}\n" -o NUL
```

**Windows CMD**
```cmd
curl -X DELETE "http://localhost:8080/api/tasks/1" -w "\n%%{http_code}\n" -o NUL
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/tasks/1" -Method Delete
```

---

## Flujo completo (Linux / Mac)

```bash
# Crear
RES=$(curl -s -X POST -H "Content-Type: application/json" \
  -d '{"title":"Curl task","description":"Test","completed":false}' \
  -D - -o /tmp/out.json 'http://localhost:8080/api/tasks')
LOC=$(echo "$RES" | grep -i location | cut -d' ' -f2 | tr -d '\r')
echo "Created: $LOC"
# Listar
curl -s 'http://localhost:8080/api/tasks'
# Actualizar (reemplaza 1 por el id si es distinto)
curl -s -X PUT -H "Content-Type: application/json" -d '{"title":"Updated","description":"","completed":true}' "$LOC"
# Eliminar
curl -s -X DELETE "$LOC"
```
