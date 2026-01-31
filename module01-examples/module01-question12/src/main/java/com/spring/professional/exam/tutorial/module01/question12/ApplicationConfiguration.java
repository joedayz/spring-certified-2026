package com.spring.professional.exam.tutorial.module01.question12;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@ComponentScan
@PropertySources({
        @PropertySource(value = "file:${app-home}/app-db.properties", ignoreResourceNotFound = true),
        @PropertySource("classpath:/app-defaults.properties")
})
public class ApplicationConfiguration {
}
