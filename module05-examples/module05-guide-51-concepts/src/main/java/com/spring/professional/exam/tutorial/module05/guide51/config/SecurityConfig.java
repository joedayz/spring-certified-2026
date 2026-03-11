package com.spring.professional.exam.tutorial.module05.guide51.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Guía 5.1 - Conceptos de seguridad.
 *
 * Flujo: Request → Filter Chain (SecurityContextPersistenceFilter, BasicAuthFilter, etc.)
 *        → Authentication → Authorization → Controller → Response.
 *
 * Password storage: BCrypt / DelegatingPasswordEncoder (nunca texto plano).
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/me").authenticated()
                .anyRequest().authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable(); // API REST stateless; en apps con cookies habilitar CSRF
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("alumno").password(passwordEncoder().encode("Perusalen123")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("USER", "ADMIN");
    }

    /**
     * DelegatingPasswordEncoder permite múltiples algoritmos y migración futura.
     * Por defecto usa bcrypt; prefijo en BD: {id}encodedPassword (ej. {bcrypt}...).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }
}
