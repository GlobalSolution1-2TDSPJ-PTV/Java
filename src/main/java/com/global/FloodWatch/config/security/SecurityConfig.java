package com.global.FloodWatch.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/auth/login",
                                "/auth/refresh",
                                "/api/usuarios",
                                "/api/sos"
                        ).permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/abrigos/**",
                                "/api/alertas/**",
                                "/api/sos"
                        ).permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/abrigos/**"
                        ).hasRole("ONG")
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/abrigos/**"
                        ).hasRole("ONG")
                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/api/abrigos/**",
                                "/api/sos/**"
                        ).hasRole("ONG")
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/abrigos/**"
                        ).hasRole("ONG")
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/alertas/**",
                                "/api/drones/**",
                                "/api/leituras-sensor/**"
                        ).hasRole("DEFESA_CIVIL")
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/alertas/**",
                                "/api/drones/**",
                                "/api/leituras-sensor/**",
                                "/api/sos/**"
                        ).hasRole("DEFESA_CIVIL")
                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/api/alertas/**",
                                "/api/drones/**",
                                "/api/leituras-sensor/**"
                        ).hasRole("DEFESA_CIVIL")
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/alertas/**",
                                "/api/drones/**",
                                "/api/leituras-sensor/**"
                        ).hasRole("DEFESA_CIVIL")
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/drones/**",
                                "/api/leituras-sensor/**"
                        ).hasRole("DEFESA_CIVIL")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

