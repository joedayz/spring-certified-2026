package com.spring.professional.exam.tutorial.module04.guide41;

import com.spring.professional.exam.tutorial.module04.guide41.dto.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Guía 4.1 - Testing Spring Applications.
 * - @SpringBootTest(webEnvironment = RANDOM_PORT) inicia el servidor embebido en un puerto aleatorio.
 * - TestRestTemplate hace peticiones HTTP reales a la aplicación.
 * - @ActiveProfiles("test") activa application-test.properties (BD en memoria).
 * - @Sql ejecuta scripts antes del test (poblar BD).
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void health_returnsOk() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/products/health", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("OK");
    }

    @Test
    public void getAll_returnsProductsFromSql() {
        ResponseEntity<Product[]> response = restTemplate.getForEntity("/api/products", Product[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(3);
        assertThat(response.getBody()[0].getName()).isEqualTo("Producto A");
        assertThat(response.getBody()[0].getPrice()).isEqualTo(10.50);
    }

    @Test
    public void getById_existing_returns200() {
        ResponseEntity<Product> response = restTemplate.getForEntity("/api/products/1", Product.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getName()).isEqualTo("Producto A");
    }

    @Test
    public void getById_notFound_returns404() {
        ResponseEntity<Product> response = restTemplate.getForEntity("/api/products/999", Product.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
