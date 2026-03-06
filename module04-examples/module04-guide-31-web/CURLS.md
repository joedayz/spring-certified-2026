# Curls – Guía 3.1 Web Applications

Arrancar la app: `mvn spring-boot:run` (puerto 8080).

---

## GET /api/saludo?nombre=Juan

**Linux / Mac**
```bash
curl 'http://localhost:8080/api/saludo?nombre=Juan'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/saludo?nombre=Juan"
```

**PowerShell**
```powershell
curl.exe "http://localhost:8080/api/saludo?nombre=Juan"
# o
Invoke-RestMethod -Uri "http://localhost:8080/api/saludo?nombre=Juan"
```

---

## GET /api/saludo (sin parámetro → default "Mundo")

**Linux / Mac**
```bash
curl 'http://localhost:8080/api/saludo'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/saludo"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/saludo"
```

---

## GET /api/items (lista JSON)

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/items'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/items"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/items"
```

---

## GET /api/items/1 (ítem por ID)

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/items/1'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/items/1"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/items/1"
```

---

## GET /api/items/99 (404 Not Found)

**Linux / Mac**
```bash
curl -s -w "\nHTTP %{http_code}\n" 'http://localhost:8080/api/items/99'
```

**Windows CMD**
```cmd
curl -w "\nHTTP %%{http_code}\n" "http://localhost:8080/api/items/99"
```

**PowerShell**
```powershell
try { Invoke-RestMethod -Uri "http://localhost:8080/api/items/99" } catch { $_.Exception.Response.StatusCode.value__ }
```
