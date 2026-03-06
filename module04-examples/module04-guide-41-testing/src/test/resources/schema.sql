-- Schema para tests (ejecutado por spring.sql.init o @Sql)
CREATE TABLE IF NOT EXISTS product (
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL
);
