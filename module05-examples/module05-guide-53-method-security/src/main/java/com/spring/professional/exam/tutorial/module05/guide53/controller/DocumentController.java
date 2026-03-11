package com.spring.professional.exam.tutorial.module05.guide53.controller;

import com.spring.professional.exam.tutorial.module05.guide53.domain.Document;
import com.spring.professional.exam.tutorial.module05.guide53.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Guía 5.3 - El controller solo delega al servicio.
 * La seguridad está en la capa de servicio (@PreAuthorize, @Secured, etc.).
 */
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/admin-only")
    public String adminOnly() {
        return documentService.adminOnly();
    }

    @GetMapping("/user-or-manager")
    public String userOrManager() {
        return documentService.userOrManager();
    }

    @GetMapping("/pre-admin")
    public String preAuthorizeAdmin() {
        return documentService.preAuthorizeAdmin();
    }

    @GetMapping("/pre-admin-or-manager")
    public String preAuthorizeAdminOrManager() {
        return documentService.preAuthorizeAdminOrManager();
    }

    @GetMapping("/require-id/{id}")
    public String requireId(@PathVariable String id) {
        return documentService.requireId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable String id) {
        Document doc = documentService.getDocument(id);
        return doc != null ? ResponseEntity.ok(doc) : ResponseEntity.notFound().build();
    }

    @GetMapping("/list")
    public String listForAdminOrManager() {
        return documentService.listForAdminOrManager();
    }
}
