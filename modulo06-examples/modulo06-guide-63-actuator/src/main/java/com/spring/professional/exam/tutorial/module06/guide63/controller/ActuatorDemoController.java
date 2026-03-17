package com.spring.professional.exam.tutorial.module06.guide63.controller;

import com.spring.professional.exam.tutorial.module06.guide63.actuator.CustomHealthIndicator;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Controller para demostrar métricas personalizadas y control del health indicator.
 */
@RestController
@RequestMapping("/api")
public class ActuatorDemoController {

    private final Counter requestCounter;
    private final CustomHealthIndicator healthIndicator;

    public ActuatorDemoController(MeterRegistry meterRegistry, CustomHealthIndicator healthIndicator) {
        this.requestCounter = Counter.builder("api.requests.total")
                .description("Total de peticiones a la API demo")
                .tag("controller", "ActuatorDemo")
                .register(meterRegistry);
        this.healthIndicator = healthIndicator;
    }

    /**
     * Endpoint que incrementa un contador de métricas personalizado.
     * Visible en /actuator/metrics/api.requests.total
     */
    @GetMapping("/demo")
    public Map<String, Object> demo() {
        requestCounter.increment();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("mensaje", "Petición procesada. Contador incrementado.");
        result.put("total_requests", requestCounter.count());
        result.put("metrica_en", "/actuator/metrics/api.requests.total");
        return result;
    }

    /**
     * Permite simular una caída del servicio externo (health indicator).
     * POST /api/health/toggle?up=false → pone el health indicator en DOWN
     * POST /api/health/toggle?up=true  → pone el health indicator en UP
     */
    @PostMapping("/health/toggle")
    public Map<String, Object> toggleHealth(@RequestParam boolean up) {
        healthIndicator.setServiceUp(up);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("externalService_status", up ? "UP" : "DOWN");
        result.put("verificar_en", "/actuator/health");
        return result;
    }

    @GetMapping("/actuator-guia")
    public Map<String, Object> guia() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("endpoints_principales", new String[]{
                "/actuator          → Lista de endpoints habilitados",
                "/actuator/health   → Estado de la aplicación (UP/DOWN)",
                "/actuator/info     → Información de la aplicación",
                "/actuator/metrics  → Lista de métricas disponibles",
                "/actuator/beans    → Todos los beans del contexto",
                "/actuator/env      → Propiedades y variables de entorno",
                "/actuator/mappings → Todos los @RequestMapping",
                "/actuator/releases → Endpoint personalizado (custom)"
        });
        result.put("seguridad", "En producción, proteger con Spring Security. Exponer solo lo necesario.");
        result.put("config_clave", new String[]{
                "management.endpoints.web.exposure.include=*  → Exponer todos",
                "management.endpoint.health.show-details=always → Mostrar detalles de health",
                "management.server.port=9090 → Puerto separado para Actuator"
        });
        return result;
    }
}
