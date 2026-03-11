package com.spring.professional.exam.tutorial.module05.guide51.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Guía 5.1 - Conceptos: Principal, Authorities, SecurityContext.
 *
 * Flujo: la petición pasa por la Filter Chain → SecurityContextPersistenceFilter
 * recupera/crea el SecurityContext y lo asocia al hilo (SecurityContextHolder).
 * Tras autenticación, el Controller puede leer Authentication (Principal + Authorities).
 */
@RestController
@RequestMapping("/api")
public class ConceptsController {

    /**
     * Público: no requiere autenticación (permitAll).
     * Explica el flujo y las amenazas (contraseñas, CSRF, CORS).
     */
    @GetMapping("/public/info")
    public Map<String, String> publicInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("conceptos", "Autenticación (quién eres) → Autorización (qué puedes hacer)");
        info.put("principal", "El sujeto autenticado (usuario, dispositivo, sistema)");
        info.put("authorities", "Permisos o roles: ROLE_ADMIN, READ_PRIVILEGE, etc.");
        info.put("password_storage", "Nunca texto plano. BCrypt, Argon2, salt único. DelegatingPasswordEncoder.");
        info.put("csrf", "Proteger POST/PUT/PATCH/DELETE cuando se usan cookies.");
        info.put("cors", "Orígenes, métodos y headers permitidos para peticiones cross-origin.");
        return info;
    }

    /**
     * Protegido: requiere autenticación.
     * Muestra el Principal y las Authorities del usuario actual (SecurityContext).
     */
    @GetMapping("/me")
    public Map<String, Object> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> me = new HashMap<>();
        me.put("principal", auth != null ? auth.getName() : null);
        me.put("authenticated", auth != null && auth.isAuthenticated());
        if (auth != null && auth.getAuthorities() != null) {
            me.put("authorities", auth.getAuthorities().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList()));
        }
        return me;
    }
}
