package com.spring.professional.exam.tutorial.module06.guide63.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Endpoint personalizado de Actuator.
 *
 * Anotaciones:
 * - @Endpoint(id = "releases"): se expone en /actuator/releases
 * - @ReadOperation: responde a GET
 * - @WriteOperation: responde a POST
 * - @DeleteOperation: responde a DELETE
 *
 * También existe @WebEndpoint (solo web) y @JmxEndpoint (solo JMX).
 */
@Component
@Endpoint(id = "releases")
public class ReleasesEndpoint {

    private final ConcurrentLinkedQueue<Map<String, String>> releases = new ConcurrentLinkedQueue<>();

    public ReleasesEndpoint() {
        Map<String, String> initial = new LinkedHashMap<>();
        initial.put("version", "1.0.0");
        initial.put("fecha", "2026-01-15");
        initial.put("descripcion", "Release inicial");
        releases.add(initial);
    }

    @ReadOperation
    public Map<String, Object> getReleases() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total", releases.size());
        result.put("releases", releases);
        return result;
    }

    @WriteOperation
    public Map<String, String> addRelease(String version, String descripcion) {
        Map<String, String> release = new LinkedHashMap<>();
        release.put("version", version);
        release.put("fecha", LocalDateTime.now().toLocalDate().toString());
        release.put("descripcion", descripcion);
        releases.add(release);

        Map<String, String> response = new LinkedHashMap<>();
        response.put("status", "Release agregado");
        response.put("version", version);
        return response;
    }

    @DeleteOperation
    public Map<String, String> clearReleases() {
        int count = releases.size();
        releases.clear();
        Map<String, String> response = new LinkedHashMap<>();
        response.put("status", count + " releases eliminados");
        return response;
    }
}
