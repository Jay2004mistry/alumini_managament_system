package com.alumni.management.security.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.alumni.management.security.JwtFilter.JwtFilter;

// Disables CSRF
// Allows login & registration without token
//Requires authentication for everything else
// Makes app stateless (no server sessions)
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/login").permitAll()
						.requestMatchers("/api/users").permitAll() // allow registration
						.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtFilter,
				org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
