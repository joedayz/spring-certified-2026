package com.spring.professional.exam.tutorial.module06.guide61.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Demuestra las anotaciones @Conditional de Spring Boot.
 *
 * Familia de @Conditional:
 * - @ConditionalOnClass: el bean se crea solo si la clase está en el classpath
 * - @ConditionalOnMissingBean: solo si NO existe otro bean del mismo tipo
 * - @ConditionalOnProperty: solo si una propiedad tiene un valor específico
 * - @ConditionalOnBean: solo si ya existe un bean del tipo indicado
 * - @ConditionalOnMissingClass: solo si la clase NO está en el classpath
 * - @ConditionalOnWebApplication: solo en contextos web
 */
@Configuration
public class ConditionalConfig {

    @Bean
    @ConditionalOnProperty(name = "app.feature.greeting", havingValue = "true", matchIfMissing = true)
    public GreetingService greetingService() {
        return new GreetingService();
    }

    @Bean
    @ConditionalOnProperty(name = "app.feature.farewell", havingValue = "true")
    public FarewellService farewellService() {
        return new FarewellService();
    }

    public static class GreetingService {
        public String greet(String name) {
            return "¡Hola, " + name + "! Bienvenido a Spring Boot.";
        }
    }

    public static class FarewellService {
        public String farewell(String name) {
            return "¡Adiós, " + name + "! Hasta pronto.";
        }
    }
}
