package com.spring.professional.exam.tutorial.module04.guide32.client;

import com.spring.professional.exam.tutorial.module04.guide32.dto.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Guía 3.2 - Uso de RestTemplate para invocar servicios REST.
 * RestTemplateBuilder respeta application.properties y ajustes de Jackson.
 */
@Component
@Profile("!test")
public class RestTemplateClientRunner implements CommandLineRunner {

    private final RestTemplate restTemplate;

    public RestTemplateClientRunner(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public void run(String... args) {
        String baseUrl = "http://localhost:8080/api/tasks";

        // POST - crear recurso
        Task newTask = new Task(null, "Tarea desde RestTemplate", "Descripción", false);
        ResponseEntity<Task> createResponse = restTemplate.postForEntity(baseUrl, newTask, Task.class);
        System.out.println("POST 201: " + createResponse.getBody());
        System.out.println("Location: " + createResponse.getHeaders().getLocation());

        // GET - obtener todos (ParameterizedTypeReference para List<Task>)
        ResponseEntity<List<Task>> listResponse = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Task>>() {}
        );
        System.out.println("GET 200: " + listResponse.getBody());

        // PUT - actualizar (exchange para control total)
        Long id = createResponse.getBody().getId();
        Task toUpdate = new Task(id, "Tarea actualizada", "Nueva descripción", true);
        ResponseEntity<Void> putResponse = restTemplate.exchange(
                baseUrl + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(toUpdate),
                Void.class
        );
        System.out.println("PUT 204: " + putResponse.getStatusCode());

        // DELETE
        restTemplate.delete(baseUrl + "/" + id);
        System.out.println("DELETE 204 ejecutado");
    }
}
