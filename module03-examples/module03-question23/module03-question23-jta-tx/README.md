# module03-question23-jta-tx — MySQL 8

Demo JTA con dos datasources MySQL 8. Aquí tienes cómo levantar la base de datos con **Podman** o **Docker**.

## Requisitos

- **Podman** o **Docker**
- Puerto **3306** libre en el host

---

## Podman

### Levantar el contenedor

```bash
podman run -d \
  --name mysql8-jta \
  -e MYSQL_ROOT_PASSWORD=root \
  -p 3306:3306 \
  docker.io/library/mysql:8.0
```

### Esperar a que MySQL esté listo

```bash
podman exec mysql8-jta mysqladmin -u root -proot ping -h localhost
```

### Crear bases de datos, usuario y tablas

Desde el directorio del módulo (`module03-question23-jta-tx`):

```bash
podman exec -i mysql8-jta mysql -u root -proot < src/main/resources/manual-schema.sql
```

### Usuario para conexión desde el host (recomendado)

Si la aplicación corre en tu máquina y se conecta a `localhost:3306`, MySQL ve la conexión como remota. Crea el usuario `%`:

```bash
podman exec -it mysql8-jta mysql -u root -proot -e "
CREATE USER IF NOT EXISTS 'spring-tutorial'@'%' IDENTIFIED BY 'spring-tutorial';
GRANT ALL PRIVILEGES ON \`spring-tutorial-employees\`.* TO 'spring-tutorial'@'%';
GRANT ALL PRIVILEGES ON \`spring-tutorial-products\`.* TO 'spring-tutorial'@'%';
FLUSH PRIVILEGES;
"
```

### Parar y eliminar

```bash
podman stop mysql8-jta
podman rm mysql8-jta
```

---

## Docker

### Levantar el contenedor

```bash
docker run -d \
  --name mysql8-jta \
  -e MYSQL_ROOT_PASSWORD=root \
  -p 3306:3306 \
  mysql:8
```

### Esperar a que MySQL esté listo

```bash
docker exec mysql8-jta mysqladmin -u root -proot ping -h localhost
```

### Crear bases de datos, usuario y tablas

Desde el directorio del módulo (`module03-question23-jta-tx`):

```bash
docker exec -i mysql8-jta mysql -u root -proot < src/main/resources/manual-schema.sql
```

### Usuario para conexión desde el host (recomendado)

Si la aplicación corre en tu máquina y se conecta a `localhost:3306`, crea el usuario `%`:

```bash
docker exec -it mysql8-jta mysql -u root -proot -e "
CREATE USER IF NOT EXISTS 'spring-tutorial'@'%' IDENTIFIED BY 'spring-tutorial';
GRANT ALL PRIVILEGES ON \`spring-tutorial-employees\`.* TO 'spring-tutorial'@'%';
GRANT ALL PRIVILEGES ON \`spring-tutorial-products\`.* TO 'spring-tutorial'@'%';
FLUSH PRIVILEGES;
"
```

### Parar y eliminar

```bash
docker stop mysql8-jta
docker rm mysql8-jta
```

---

## Resumen de credenciales

| Qué            | Valor                    |
|----------------|--------------------------|
| Host           | `localhost`              |
| Puerto         | `3306`                   |
| Usuario app    | `spring-tutorial`        |
| Contraseña app | `spring-tutorial`        |
| Root (solo BD) | `root` / `root`          |
| Bases de datos | `spring-tutorial-employees`, `spring-tutorial-products` |

---

## Orden recomendado

1. Levantar el contenedor (podman o docker).
2. Esperar con `mysqladmin ping`.
3. Ejecutar `manual-schema.sql`.
4. Crear usuario `spring-tutorial`@`%` para acceso desde el host.
5. Ejecutar la aplicación.
