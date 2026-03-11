# Curls – Guía 5.2 Autenticación y Autorización

Arrancar: `mvn spring-boot:run` (puerto 8080).

Usuarios: **user** / user (USER), **admin** / admin (USER, ADMIN).

---

## GET /api/public/welcome (permitAll – sin auth)

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/public/welcome'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/public/welcome"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/public/welcome"
```

---

## GET /api/admin/dashboard (solo ADMIN)

**Linux / Mac**
```bash
curl -s -u admin:admin 'http://localhost:8080/api/admin/dashboard'
```

**Windows CMD**
```cmd
curl -u admin:admin "http://localhost:8080/api/admin/dashboard"
```

**PowerShell**
```powershell
$cred = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("admin:admin"))
Invoke-RestMethod -Uri "http://localhost:8080/api/admin/dashboard" -Headers @{ Authorization = "Basic $cred" }
```

Como **user** (403 Forbidden):

**Linux / Mac**
```bash
curl -s -u user:user -w "\nHTTP %{http_code}\n" 'http://localhost:8080/api/admin/dashboard'
```

---

## GET /api/user/profile (cualquier autenticado)

**Linux / Mac**
```bash
curl -s -u user:user 'http://localhost:8080/api/user/profile'
```

**Windows CMD**
```cmd
curl -u user:user "http://localhost:8080/api/user/profile"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/user/profile" -Headers @{ Authorization = "Basic $([Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes('user:user')))" }
```

---

## Resumen

| Ruta | permitAll | user | admin |
|------|-----------|------|-------|
| /api/public/welcome | ✅ | ✅ | ✅ |
| /api/user/profile | ❌ | ✅ | ✅ |
| /api/admin/dashboard | ❌ | 403 | ✅ |

`/static/**` está en **web.ignoring()** (no pasa por Spring Security).
