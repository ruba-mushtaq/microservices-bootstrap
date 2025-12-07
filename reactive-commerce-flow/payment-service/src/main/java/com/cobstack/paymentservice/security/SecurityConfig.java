package com.cobstack.paymentservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.cobstack.paymentservice.filter.JwtAuthenticationFilter;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	 SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtAuthenticationFilter filter) {
		http.csrf(csrf -> csrf.disable()).authorizeExchange(auth -> auth.anyExchange().authenticated())
				.addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION);

		return http.build();
	}

}
