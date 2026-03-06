# Curls – Guía 4.2 Testing avanzado (Books API)

Arrancar la app: `mvn spring-boot:run` (puerto 8080).

---

## GET /api/books (listar)

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/books'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/books"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/books"
```

---

## POST /api/books (crear – 201 + Location)

**Linux / Mac**
```bash
curl -s -X POST -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","author":"Robert Martin","isbn":"978-0132350884"}' \
  'http://localhost:8080/api/books' -D -
```

**Windows CMD**
```cmd
curl -X POST -H "Content-Type: application/json" -d "{\"title\":\"Clean Code\",\"author\":\"Robert Martin\",\"isbn\":\"978-0132350884\"}" "http://localhost:8080/api/books" -i
```

**PowerShell**
```powershell
$body = '{"title":"Clean Code","author":"Robert Martin","isbn":"978-0132350884"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/books" -Method Post -Body $body -ContentType "application/json"
# Con headers (Location):
(Invoke-WebRequest -Uri "http://localhost:8080/api/books" -Method Post -Body $body -ContentType "application/json" -UseBasicParsing).Headers.Location
```

---

## GET /api/books/1

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/books/1'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/books/1"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/books/1"
```

---

## Resumen por entorno

| Acción   | Linux/Mac (curl) | Windows CMD (curl) | PowerShell |
|----------|-------------------|---------------------|------------|
| GET list | `curl -s 'http://localhost:8080/api/books'` | `curl "http://localhost:8080/api/books"` | `Invoke-RestMethod -Uri "http://localhost:8080/api/books"` |
| POST     | `curl -X POST -H "Content-Type: application/json" -d '{"title":"X","author":"Y","isbn":"Z"}' 'http://localhost:8080/api/books'` | `curl -X POST -H "Content-Type: application/json" -d "{\"title\":\"X\",\"author\":\"Y\",\"isbn\":\"Z\"}" "http://localhost:8080/api/books"` | `Invoke-RestMethod -Uri "..." -Method Post -Body $body -ContentType "application/json"` |
| GET by id| `curl -s 'http://localhost:8080/api/books/1'` | `curl "http://localhost:8080/api/books/1"` | `Invoke-RestMethod -Uri "http://localhost:8080/api/books/1"` |

En **CMD** los literales JSON con comillas dobles deben escaparse: `\"`.
