package com.spring.professional.exam.tutorial.module05.guide53.domain;

/**
 * Recurso de ejemplo para @PostAuthorize (decisión según el objeto devuelto).
 */
public class Document {
    private final String id;
    private final String owner;  // username del dueño
    private final String title;

    public Document(String id, String owner, String title) {
        this.id = id;
        this.owner = owner;
        this.title = title;
    }

    public String getId() { return id; }
    public String getOwner() { return owner; }
    public String getTitle() { return title; }
}
