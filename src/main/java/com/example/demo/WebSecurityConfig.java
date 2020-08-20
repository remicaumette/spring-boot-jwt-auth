package com.example.demo;

import com.example.demo.component.JwtAuthenticationManager;
import com.example.demo.component.JwtServerAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@EnableWebFluxSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http, JwtAuthenticationManager authenticationManager, JwtServerAuthenticationConverter converter) {
        AuthenticationWebFilter filter = new AuthenticationWebFilter(authenticationManager);
        filter.setServerAuthenticationConverter(converter);

        return http.authorizeExchange()
                .pathMatchers("/", "/users", "/auth/login").permitAll()
                .anyExchange().authenticated()
                .and()
                .addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION)
                .formLogin().disable()
                .logout().disable()
                .httpBasic().disable()
                .csrf().disable()
                .build();
    }
}
