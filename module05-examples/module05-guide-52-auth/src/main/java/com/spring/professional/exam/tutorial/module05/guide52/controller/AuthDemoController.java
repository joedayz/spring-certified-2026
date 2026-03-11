package com.spring.professional.exam.tutorial.module05.guide52.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Guía 5.2 - Demostración de autorización por URL.
 *
 * /api/public/*  → permitAll() (pasa por Filter Chain, sin autenticación).
 * /api/admin/*   → hasRole("ADMIN").
 * /api/user/*    → authenticated() (cualquier usuario autenticado).
 */
@RestController
@RequestMapping("/api")
public class AuthDemoController {

    @GetMapping("/public/welcome")
    public Map<String, String> publicWelcome() {
        return Collections.singletonMap("message", "Público: no requiere autenticación (permitAll)");
    }

    @GetMapping("/admin/dashboard")
    public Map<String, String> adminDashboard() {
        return Collections.singletonMap("message", "Solo ADMIN puede ver este recurso");
    }

    @GetMapping("/user/profile")
    public Map<String, String> userProfile() {
        return Collections.singletonMap("message", "Cualquier usuario autenticado puede ver esto");
    }
}
