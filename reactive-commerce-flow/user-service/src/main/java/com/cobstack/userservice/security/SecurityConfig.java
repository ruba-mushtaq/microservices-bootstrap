package com.cobstack.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	 @Bean
	     SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
	        http
	            .csrf(ServerHttpSecurity.CsrfSpec::disable)
	            .authorizeExchange(exchanges -> exchanges
	                .pathMatchers("/auth/**").permitAll()
	                .anyExchange().authenticated()
	            )
	            .httpBasic(withDefaults());
	        return http.build();
	    }
    @Bean
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
