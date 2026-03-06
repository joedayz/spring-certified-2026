# Curls – Guía 4.1 Testing (Productos API)

Arrancar la app: `mvn spring-boot:run` (puerto 8080).  
La BD se inicializa con `schema.sql`; la tabla `product` puede estar vacía si no usas `data.sql` en main.

---

## GET /api/products/health

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/products/health'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/products/health"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/products/health"
```

---

## GET /api/products (listar; vacío o con datos según BD)

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/products'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/products"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/products"
```

---

## GET /api/products/1

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/products/1'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/products/1"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/products/1"
```

---

## Ejemplo 404

**Linux / Mac**
```bash
curl -s -w "\nHTTP %{http_code}\n" 'http://localhost:8080/api/products/999'
```

**Windows CMD**
```cmd
curl -w "\nHTTP %%{http_code}\n" "http://localhost:8080/api/products/999"
```

**PowerShell**
```powershell
try { Invoke-RestMethod -Uri "http://localhost:8080/api/products/999" } catch { $_.Exception.Response.StatusCode.value__ }
```
