package com.spring.professional.exam.tutorial.module06.guide62.controller;

import com.spring.professional.exam.tutorial.module06.guide62.config.AppInfoProperties;
import com.spring.professional.exam.tutorial.module06.guide62.config.DataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PropertiesController {

    /**
     * @Value inyecta valores individuales de propiedades.
     * Soporta SpEL y valores por defecto con ":"
     */
    @Value("${app.message:Mensaje por defecto}")
    private String appMessage;

    @Value("${app.max-retries:3}")
    private int maxRetries;

    @Autowired
    private AppInfoProperties appInfoProperties;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private Environment environment;

    /**
     * Muestra propiedades inyectadas con @Value
     */
    @GetMapping("/value")
    public Map<String, Object> valueDemo() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("app.message", appMessage);
        result.put("app.max-retries", maxRetries);
        result.put("nota", "@Value inyecta propiedades individuales. Usar ':' para valor por defecto.");
        return result;
    }

    /**
     * Muestra propiedades agrupadas con @ConfigurationProperties
     */
    @GetMapping("/config-properties")
    public Map<String, Object> configProperties() {
        Map<String, Object> result = new LinkedHashMap<>();

        Map<String, String> info = new LinkedHashMap<>();
        info.put("name", appInfoProperties.getName());
        info.put("description", appInfoProperties.getDescription());
        info.put("version", appInfoProperties.getVersion());
        info.put("environment", appInfoProperties.getEnvironment());
        result.put("appInfo (prefix=app.info)", info);

        Map<String, Object> ds = new LinkedHashMap<>();
        ds.put("url", dataSourceProperties.getUrl());
        ds.put("username", dataSourceProperties.getUsername());
        ds.put("driverClassName", dataSourceProperties.getDriverClassName());
        ds.put("pool.maxSize", dataSourceProperties.getPool().getMaxSize());
        ds.put("pool.minIdle", dataSourceProperties.getPool().getMinIdle());
        ds.put("pool.timeoutMs", dataSourceProperties.getPool().getTimeoutMs());
        ds.put("initScripts", dataSourceProperties.getInitScripts());
        ds.put("additionalProperties", dataSourceProperties.getAdditionalProperties());
        result.put("dataSource (prefix=app.datasource)", ds);

        return result;
    }

    /**
     * Muestra los profiles activos y el orden de precedencia.
     *
     * Orden de precedencia (mayor a menor):
     * 1. Argumentos de línea de comando (--server.port=9090)
     * 2. Variables de entorno del SO (SERVER_PORT=9090)
     * 3. application-{profile}.properties
     * 4. application.properties
     * 5. @PropertySource
     * 6. Valores por defecto en SpringApplication.setDefaultProperties
     */
    @GetMapping("/profiles")
    public Map<String, Object> profiles() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("active_profiles", Arrays.asList(environment.getActiveProfiles()));
        result.put("default_profiles", Arrays.asList(environment.getDefaultProfiles()));
        result.put("app.info.environment", environment.getProperty("app.info.environment"));
        result.put("precedencia", new String[]{
                "1. Argumentos de línea de comando (--key=value)",
                "2. Variables de entorno del SO (KEY_VALUE)",
                "3. application-{profile}.properties",
                "4. application.properties",
                "5. @PropertySource en @Configuration",
                "6. SpringApplication.setDefaultProperties()"
        });
        result.put("activar_profile", "mvn spring-boot:run -Dspring-boot.run.profiles=dev");
        return result;
    }
}
