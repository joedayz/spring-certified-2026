package com.spring.professional.exam.tutorial.module06.guide61.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * CommandLineRunner se ejecuta al arrancar la aplicación Spring Boot.
 * Es útil para inicialización, carga de datos, validación, etc.
 *
 * - Se puede tener múltiples CommandLineRunner, ordenados con @Order
 * - También existe ApplicationRunner (recibe ApplicationArguments en vez de String[])
 */
@Component
@Order(1)
public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║  Guía 6.1 – Spring Boot Intro                  ║");
        System.out.println("║  CommandLineRunner ejecutado al arranque        ║");
        System.out.println("║  Endpoints:                                     ║");
        System.out.println("║    GET /api/info            → Info general      ║");
        System.out.println("║    GET /api/beans           → Lista de beans    ║");
        System.out.println("║    GET /api/auto-config-beans → Beans auto      ║");
        System.out.println("║    GET /api/conditional/status → @Conditional   ║");
        System.out.println("║    GET /api/conditional/greet?name=Joe          ║");
        System.out.println("║    GET /api/conditional/farewell?name=Joe       ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        if (args.length > 0) {
            System.out.println("Argumentos recibidos: " + String.join(", ", args));
        }
    }
}
