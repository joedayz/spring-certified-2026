# Curls – Guía 5.1 Conceptos (Spring Security)

Arrancar: `mvn spring-boot:run` (puerto 8080).

Usuarios: **alumno** / Perusalen123 (USER), **admin** / admin (USER, ADMIN).

---

## Cómo generar el header `Authorization: Basic`

El valor es **Base64 de `usuario:contraseña`** (sin espacio, codificación ASCII/UTF-8).

Si te da **401**, comprueba:
1. Que la app que corre es **module05-guide-51-concepts** (usuarios `alumno` y `admin`).
2. Que no haya espacios ni caracteres raros al copiar el Base64.
3. Mejor: usa `curl -u usuario:contraseña` y evita el header manual.

**Generar el Base64:**

**Linux / Mac**
```bash
echo -n "alumno:Perusalen123" | base64
# Resultado: YWx1bW5vOlBlcnVzYWxlbjEyMw==
```

**PowerShell**
```powershell
[Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("alumno:Perusalen123"))
# Resultado: YWx1bW5vOlBlcnVzYWxlbjEyMw==
```

**Windows CMD** (PowerShell en una línea)
```cmd
powershell -Command "[Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes('alumno:Perusalen123'))"
```

Luego:
```bash
curl -s -H "Authorization: Basic YWx1bW5vOlBlcnVzYWxlbjEyMw==" "http://localhost:8080/api/me"
```

---

## GET /api/public/info (sin autenticación – permitAll)

**Linux / Mac**
```bash
curl -s 'http://localhost:8080/api/public/info'
```

**Windows CMD**
```cmd
curl "http://localhost:8080/api/public/info"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/public/info"
```

---

## GET /api/me (autenticado – Basic Auth)

**Recomendado:** usar `-u usuario:contraseña` para evitar errores con el Base64.

**Linux / Mac**
```bash
curl -s -u alumno:Perusalen123 'http://localhost:8080/api/me'
```

Si quieres el header manual (usa el Base64 que generaste arriba):
```bash
curl -s -H "Authorization: Basic YWx1bW5vOlBlcnVzYWxlbjEyMw==" 'http://localhost:8080/api/me'
```

**Windows CMD**
```cmd
curl -u alumno:Perusalen123 "http://localhost:8080/api/me"
```

**PowerShell**
```powershell
$cred = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("alumno:Perusalen123"))
Invoke-RestMethod -Uri "http://localhost:8080/api/me" -Headers @{ Authorization = "Basic $cred" }
```

---

## GET /api/me como admin (ver authorities)

**Linux / Mac**
```bash
curl -s -u admin:admin 'http://localhost:8080/api/me'
```

**Windows CMD**
```cmd
curl -u admin:admin "http://localhost:8080/api/me"
```

**PowerShell**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/me" -Credential (Get-Credential -UserName admin -Message "Password: admin")
# o con header:
$cred = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("admin:admin"))
Invoke-RestMethod -Uri "http://localhost:8080/api/me" -Headers @{ Authorization = "Basic $cred" }
```

---

## Sin credenciales (401 Unauthorized)

**Linux / Mac**
```bash
curl -s -w "\nHTTP %{http_code}\n" 'http://localhost:8080/api/me'
```

**Windows CMD**
```cmd
curl -w "\nHTTP %%{http_code}\n" "http://localhost:8080/api/me"
```

**PowerShell**
```powershell
try { Invoke-RestMethod -Uri "http://localhost:8080/api/me" } catch { $_.Exception.Response.StatusCode.value__ }
```
