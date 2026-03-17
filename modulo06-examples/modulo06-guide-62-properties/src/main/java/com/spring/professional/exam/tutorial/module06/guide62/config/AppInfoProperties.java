package com.spring.professional.exam.tutorial.module06.guide62.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ConfigurationProperties vincula un prefijo de propiedades a un POJO.
 *
 * Ventajas sobre @Value:
 * - Tipado fuerte, refactorizable
 * - Validación con @Validated + JSR-303
 * - Agrupación lógica de propiedades relacionadas
 * - IDE autocomplete (con spring-boot-configuration-processor)
 *
 * Prefijo: app.info → busca app.info.name, app.info.description, etc.
 */
@Component
@ConfigurationProperties(prefix = "app.info")
public class AppInfoProperties {

    private String name = "default-app";
    private String description = "Aplicación de ejemplo";
    private String version = "0.0.1";
    private String environment = "unknown";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
