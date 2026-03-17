package com.spring.professional.exam.tutorial.module06.guide61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication combina:
 * - @Configuration: esta clase es fuente de beans
 * - @EnableAutoConfiguration: activa la auto-configuración de Spring Boot
 * - @ComponentScan: escanea beans en este paquete y sub-paquetes
 */
@SpringBootApplication
public class BootIntroApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootIntroApplication.class, args);
    }
}
