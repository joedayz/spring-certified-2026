package com.spring.professional.exam.tutorial.module06.guide63.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * InfoContributor agrega información al endpoint /actuator/info.
 *
 * Fuentes de info por defecto:
 * - info.* en application.properties (InfoPropertiesInfoContributor)
 * - META-INF/build-info.properties (BuildInfoContributor)
 * - git.properties (GitInfoContributor)
 *
 * Con InfoContributor programático puedes agregar datos dinámicos.
 */
@Component
public class CustomInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> curso = new LinkedHashMap<>();
        curso.put("nombre", "Spring Professional Certification 2026");
        curso.put("modulo", "06 - Spring Boot");
        curso.put("guia", "6.3 - Actuator");
        curso.put("servidor_hora", LocalDateTime.now().toString());
        builder.withDetail("curso", curso);
    }
}
