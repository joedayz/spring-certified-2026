package com.spring.professional.exam.tutorial.module06.guide63.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * HealthIndicator personalizado.
 *
 * Spring Boot Actuator incluye health indicators automáticos para:
 * - DataSource (db), Disk (diskSpace), Redis, Mongo, RabbitMQ, etc.
 *
 * Este ejemplo simula la verificación de un servicio externo.
 * El health endpoint agrega todos los indicators:
 * - Si todos son UP → estado global UP
 * - Si alguno es DOWN → estado global DOWN
 */
@Component("externalService")
public class CustomHealthIndicator implements HealthIndicator {

    private boolean serviceUp = true;

    @Override
    public Health health() {
        if (serviceUp) {
            return Health.up()
                    .withDetail("servicio", "API Externa")
                    .withDetail("url", "https://api.example.com")
                    .withDetail("responseTime", "42ms")
                    .build();
        } else {
            return Health.down()
                    .withDetail("servicio", "API Externa")
                    .withDetail("error", "Connection timeout")
                    .build();
        }
    }

    public void setServiceUp(boolean serviceUp) {
        this.serviceUp = serviceUp;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }
}
