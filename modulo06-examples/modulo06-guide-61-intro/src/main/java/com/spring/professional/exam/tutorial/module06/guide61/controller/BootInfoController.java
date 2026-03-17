package com.spring.professional.exam.tutorial.module06.guide61.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BootInfoController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ServerProperties serverProperties;

    /**
     * Muestra información básica de Spring Boot:
     * - Qué es @SpringBootApplication
     * - Starters: dependencias pre-empaquetadas
     * - Auto-configuración: configura beans según el classpath
     */
    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("springBootApplication", "@Configuration + @EnableAutoConfiguration + @ComponentScan");
        info.put("starters", "Dependencias pre-empaquetadas: spring-boot-starter-web, -data-jpa, -security, etc.");
        info.put("auto-configuration", "Spring Boot configura beans automáticamente según las dependencias del classpath.");
        info.put("servidor_puerto", serverProperties.getPort() != null ? serverProperties.getPort() : "8080 (default)");
        return info;
    }

    /**
     * Lista beans registrados automáticamente por la auto-configuración.
     * Demuestra cuántos beans crea Spring Boot sin configuración manual.
     */
    @GetMapping("/beans")
    public Map<String, Object> beans() {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total_beans", beanNames.length);
        result.put("beans", Arrays.asList(beanNames));
        return result;
    }

    /**
     * Muestra solo los beans relacionados con auto-configuración.
     */
    @GetMapping("/auto-config-beans")
    public Map<String, Object> autoConfigBeans() {
        String[] allBeans = context.getBeanDefinitionNames();

        List<String> autoConfigRelated = Arrays.stream(allBeans)
                .filter(name -> name.toLowerCase().contains("auto")
                        || name.toLowerCase().contains("dispatcher")
                        || name.toLowerCase().contains("jackson")
                        || name.toLowerCase().contains("errorpage")
                        || name.toLowerCase().contains("tomcat"))
                .sorted()
                .collect(Collectors.toList());

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("descripcion", "Beans creados automáticamente por Spring Boot (muestra parcial)");
        result.put("total", autoConfigRelated.size());
        result.put("beans_auto", autoConfigRelated);
        return result;
    }
}
