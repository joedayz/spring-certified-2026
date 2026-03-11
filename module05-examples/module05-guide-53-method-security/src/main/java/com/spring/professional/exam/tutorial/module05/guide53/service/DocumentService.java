package com.spring.professional.exam.tutorial.module05.guide53.service;

import com.spring.professional.exam.tutorial.module05.guide53.domain.Document;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.Map;

/**
 * Guía 5.3 - Seguridad a nivel de métodos.
 *
 * Buenas prácticas: asegurar la capa de servicio (no solo controllers),
 * así schedulers, mensajería y otros puntos de entrada se benefician.
 *
 * @PreAuthorize: evaluación antes de ejecutar (SpEL: hasRole, hasAuthority, #id).
 * @PostAuthorize: evaluación después; puede usar returnObject.
 * @Secured / @RolesAllowed: comparan roles (convención ROLE_).
 */
@Service
public class DocumentService {

    private final Map<String, Document> store = new HashMap<>();

    public DocumentService() {
        store.put("1", new Document("1", "user", "Documento de user"));
        store.put("2", new Document("2", "admin", "Documento de admin"));
        store.put("3", new Document("3", "manager", "Documento de manager"));
    }

    /** Solo ADMIN. @Secured usa el nombre exacto del authority (ROLE_ADMIN). */
    @Secured("ROLE_ADMIN")
    public String adminOnly() {
        return "Solo ADMIN puede ver esto (@Secured)";
    }

    /** Solo USER o MANAGER. JSR-250, mismo comportamiento que @Secured para roles. */
    @RolesAllowed({"USER", "MANAGER"})
    public String userOrManager() {
        return "USER o MANAGER pueden ver esto (@RolesAllowed)";
    }

    /** SpEL: hasRole('ADMIN') — Spring añade el prefijo ROLE_ si no está. */
    @PreAuthorize("hasRole('ADMIN')")
    public String preAuthorizeAdmin() {
        return "Solo ADMIN (@PreAuthorize hasRole)";
    }

    /** SpEL: hasAuthority explícito (permiso granular, ej. 'documents:read'). */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
    public String preAuthorizeAdminOrManager() {
        return "ADMIN o MANAGER (@PreAuthorize hasAuthority)";
    }

    /** SpEL: acceso al argumento del método por nombre (#id). */
    @PreAuthorize("#id != null")
    public String requireId(String id) {
        return "ID recibido: " + id;
    }

    /**
     * @PostAuthorize: la decisión depende del objeto devuelto.
     * returnObject.owner == authentication.name → solo el dueño puede "ver" el resultado.
     * Si no existe (null), permitir para que el controller devuelva 404.
     */
    @PostAuthorize("returnObject == null || returnObject.owner == authentication.name")
    public Document getDocument(String id) {
        return store.get(id);
    }

    /** hasAnyRole para múltiples roles. */
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String listForAdminOrManager() {
        return "Listado visible para ADMIN o MANAGER";
    }
}
