package com.spring.professional.exam.tutorial.module04.guide32.controller;

import com.spring.professional.exam.tutorial.module04.guide32.dto.Task;
import com.spring.professional.exam.tutorial.module04.guide32.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Guía 3.2 - Aplicaciones REST.
 * GET, POST, PUT, DELETE con códigos de estado y Location header.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        if (task == null) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
        return ResponseEntity.ok(task);
    }

    /**
     * POST - 201 Created + header Location con URI del recurso creado.
     */
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task created = taskService.create(task);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    /**
     * PUT - 204 No Content cuando la actualización es exitosa.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Task task) {
        Task updated = taskService.update(id, task);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(NO_CONTENT).build();
    }

    /**
     * DELETE - 204 No Content cuando la eliminación es exitosa.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = taskService.delete(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
