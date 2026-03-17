package com.spring.professional.exam.tutorial.module06.guide61.controller;

import com.spring.professional.exam.tutorial.module06.guide61.config.ConditionalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/conditional")
public class ConditionalController {

    @Autowired(required = false)
    private ConditionalConfig.GreetingService greetingService;

    @Autowired(required = false)
    private ConditionalConfig.FarewellService farewellService;

    /**
     * Demuestra @ConditionalOnProperty:
     * - greetingService se crea por defecto (matchIfMissing = true)
     * - farewellService solo se crea si app.feature.farewell=true
     */
    @GetMapping("/status")
    public Map<String, Object> conditionalStatus() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("greetingService_activo", greetingService != null);
        result.put("farewellService_activo", farewellService != null);
        result.put("nota", "greetingService usa matchIfMissing=true; farewellService requiere app.feature.farewell=true");
        return result;
    }

    @GetMapping("/greet")
    public Map<String, String> greet(@RequestParam(defaultValue = "Alumno") String name) {
        Map<String, String> result = new LinkedHashMap<>();
        if (greetingService != null) {
            result.put("mensaje", greetingService.greet(name));
        } else {
            result.put("error", "GreetingService no está activo (app.feature.greeting=false)");
        }
        return result;
    }

    @GetMapping("/farewell")
    public Map<String, String> farewell(@RequestParam(defaultValue = "Alumno") String name) {
        Map<String, String> result = new LinkedHashMap<>();
        if (farewellService != null) {
            result.put("mensaje", farewellService.farewell(name));
        } else {
            result.put("error", "FarewellService no está activo. Activar con app.feature.farewell=true");
        }
        return result;
    }
}
