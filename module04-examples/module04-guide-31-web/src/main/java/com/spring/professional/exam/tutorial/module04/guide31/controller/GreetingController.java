package com.spring.professional.exam.tutorial.module04.guide31.controller;

import com.spring.professional.exam.tutorial.module04.guide31.dto.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Guía 3.1 - Web Applications with Spring Boot.
 * - @RestController = @Controller + @ResponseBody (Message Converters escriben JSON).
 * - @GetMapping para GET.
 * - @RequestParam para query parameters (?nombre=valor).
 * - @PathVariable para variables en la ruta (/items/123).
 * - ResponseEntity para controlar status y headers.
 */
@RestController
@RequestMapping("/api")
public class GreetingController {

    private static final List<Item> ITEMS = List.of(
            new Item(1L, "Laptop", "Portátil de desarrollo"),
            new Item(2L, "Monitor", "Pantalla 27 pulgadas"),
            new Item(3L, "Teclado", "Teclado mecánico")
    );

    /**
     * GET /api/saludo?nombre=Juan
     * Parámetro opcional con default.
     */
    @GetMapping("/saludo")
    public String saludo(@RequestParam(defaultValue = "Mundo") String nombre) {
        return "Hola, " + nombre + "!";
    }

    /**
     * GET /api/items → lista de ítems (JSON vía Message Converter).
     */
    @GetMapping("/items")
    public List<Item> listarItems() {
        return ITEMS;
    }

    /**
     * GET /api/items/1 → ítem por ID.
     * @PathVariable extrae el valor de la URI.
     */
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> obtenerItem(@PathVariable Long id) {
        Optional<Item> found = ITEMS.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
        return found
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
