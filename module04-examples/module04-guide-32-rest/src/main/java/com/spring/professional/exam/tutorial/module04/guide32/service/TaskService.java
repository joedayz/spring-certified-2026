package com.spring.professional.exam.tutorial.module04.guide32.service;

import com.spring.professional.exam.tutorial.module04.guide32.dto.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final Map<Long, Task> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Task> findAll() {
        return new ArrayList<>(store.values());
    }

    public Task findById(Long id) {
        return store.get(id);
    }

    public Task create(Task task) {
        long id = idGenerator.getAndIncrement();
        Task created = new Task(id, task.getTitle(), task.getDescription(), false);
        store.put(id, created);
        return created;
    }

    public Task update(Long id, Task task) {
        if (!store.containsKey(id)) return null;
        Task updated = new Task(id, task.getTitle(), task.getDescription(), task.isCompleted());
        store.put(id, updated);
        return updated;
    }

    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
