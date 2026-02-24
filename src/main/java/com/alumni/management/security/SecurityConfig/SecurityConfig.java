package com.alumni.management.security.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.alumni.management.security.JwtFilter.JwtFilter;

// Disables CSRF
// Allows login & registration without token
//Requires authentication for everything else
// Makes app stateless (no server sessions)
@Configuration


//When you add this annotation to a @Configuration class, it enables Spring Security's
//AOP (Aspect-Oriented Programming) security interceptors. This means you can dictate
//exactly who is allowed to execute a specific method based on their roles, authorities
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    // Public endpoints
                    .requestMatchers("/api/users/login").permitAll()
                    .requestMatchers("/api/users").permitAll()
                    .requestMatchers("/api/events").permitAll()

                    // Admin endpoints
                    .requestMatchers("/api/events/admin/**").hasRole("ADMIN")

                    // Authenticated endpoints
                    .requestMatchers("/api/events/my").authenticated()

                    // Everything else
                    .anyRequest().authenticated()
            )
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter,
                    org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}