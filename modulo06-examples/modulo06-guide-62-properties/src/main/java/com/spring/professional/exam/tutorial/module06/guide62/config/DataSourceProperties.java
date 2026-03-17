package com.spring.professional.exam.tutorial.module06.guide62.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demuestra @ConfigurationProperties con tipos complejos:
 * listas, mapas y objetos anidados.
 *
 * Prefijo: app.datasource
 */
@Component
@ConfigurationProperties(prefix = "app.datasource")
public class DataSourceProperties {

    private String url = "jdbc:h2:mem:testdb";
    private String username = "sa";
    private String driverClassName = "org.h2.Driver";
    private PoolConfig pool = new PoolConfig();
    private List<String> initScripts = new ArrayList<>();
    private Map<String, String> additionalProperties = new HashMap<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public PoolConfig getPool() {
        return pool;
    }

    public void setPool(PoolConfig pool) {
        this.pool = pool;
    }

    public List<String> getInitScripts() {
        return initScripts;
    }

    public void setInitScripts(List<String> initScripts) {
        this.initScripts = initScripts;
    }

    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, String> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public static class PoolConfig {
        private int maxSize = 10;
        private int minIdle = 2;
        private long timeoutMs = 30000;

        public int getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public long getTimeoutMs() {
            return timeoutMs;
        }

        public void setTimeoutMs(long timeoutMs) {
            this.timeoutMs = timeoutMs;
        }
    }
}
