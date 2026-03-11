# Curls – Guía 5.3 Method Security

Arrancar: `mvn spring-boot:run` (puerto 8080).

Usuarios: **user** / user (USER), **admin** / admin (ADMIN), **manager** / manager (MANAGER).

Todos los endpoints requieren autenticación (Basic).

---

## @Secured("ROLE_ADMIN") – GET /api/documents/admin-only

**Linux / Mac**
```bash
curl -s -u admin:admin 'http://localhost:8080/api/documents/admin-only'
```

**Windows CMD**
```cmd
curl -u admin:admin "http://localhost:8080/api/documents/admin-only"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/documents/admin-only" -Headers @{ Authorization = "Basic $([Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes('admin:admin')))" }
```

Como **user** → 403.

---

## @RolesAllowed – GET /api/documents/user-or-manager

**Linux / Mac**
```bash
curl -s -u user:user 'http://localhost:8080/api/documents/user-or-manager'
curl -s -u manager:manager 'http://localhost:8080/api/documents/user-or-manager'
```

**Windows CMD**
```cmd
curl -u user:user "http://localhost:8080/api/documents/user-or-manager"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/documents/user-or-manager" -Headers @{ Authorization = "Basic $([Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes('user:user')))" }
```

---

## @PreAuthorize – GET /api/documents/pre-admin, pre-admin-or-manager, require-id/{id}

**Linux / Mac**
```bash
curl -s -u admin:admin 'http://localhost:8080/api/documents/pre-admin'
curl -s -u manager:manager 'http://localhost:8080/api/documents/pre-admin-or-manager'
curl -s -u user:user 'http://localhost:8080/api/documents/require-id/doc1'
```

**Windows CMD**
```cmd
curl -u admin:admin "http://localhost:8080/api/documents/pre-admin"
curl -u manager:manager "http://localhost:8080/api/documents/pre-admin-or-manager"
curl -u user:user "http://localhost:8080/api/documents/require-id/doc1"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/documents/pre-admin" -Headers @{ Authorization = "Basic $([Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes('admin:admin')))" }
```

---

## @PostAuthorize (returnObject.owner == authentication.name) – GET /api/documents/{id}

Solo el **dueño** del documento puede ver el cuerpo. Doc 1 → owner "user", doc 2 → "admin", doc 3 → "manager".

**Linux / Mac**
```bash
# user ve su doc (id=1)
curl -s -u user:user 'http://localhost:8080/api/documents/1'
# user no puede ver doc 2 (admin) → 403
curl -s -u user:user -w "\n%{http_code}\n" 'http://localhost:8080/api/documents/2'
```

**Windows CMD**
```cmd
curl -u user:user "http://localhost:8080/api/documents/1"
curl -u user:user -w "\n%%{http_code}\n" "http://localhost:8080/api/documents/2"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/documents/1" -Headers @{ Authorization = "Basic $([Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes('user:user')))" }
```

---

## GET /api/documents/list (hasAnyRole ADMIN, MANAGER)

**Linux / Mac**
```bash
curl -s -u admin:admin 'http://localhost:8080/api/documents/list'
curl -s -u manager:manager 'http://localhost:8080/api/documents/list'
```

**Windows CMD**
```cmd
curl -u admin:admin "http://localhost:8080/api/documents/list"
curl -u manager:manager "http://localhost:8080/api/documents/list"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/documents/list" -Headers @{ Authorization = "Basic $([Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes('admin:admin')))" }
```
