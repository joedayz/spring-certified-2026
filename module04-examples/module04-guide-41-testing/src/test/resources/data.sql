-- Datos iniciales para tests con @Sql (idempotente: borrar antes de insertar)
DELETE FROM product;
INSERT INTO product (id, name, price) VALUES (1, 'Producto A', 10.50);
INSERT INTO product (id, name, price) VALUES (2, 'Producto B', 25.00);
INSERT INTO product (id, name, price) VALUES (3, 'Producto C', 99.99);
