package com.example.demo.component;

import com.example.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtService jwtService;

    @Autowired
    public JwtAuthenticationManager(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(t -> jwtService.validateJwt(t.getCredentials().toString()))
                .map(jwt -> new UsernamePasswordAuthenticationToken(
                    jwt.getBody().getSubject(),
                    authentication.getCredentials().toString(),
                    new ArrayList<>()
                ));
    }
}
