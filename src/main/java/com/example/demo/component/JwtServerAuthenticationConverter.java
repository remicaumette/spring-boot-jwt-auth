package com.example.demo.component;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {
    private final String HEADER = "Authorization";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange)
                .filter(t -> t.getRequest().getHeaders().containsKey(HEADER))
                .map(t -> {
                    String token = t.getRequest().getHeaders().get(HEADER).get(0);
                    System.out.println(token);
                    return new UsernamePasswordAuthenticationToken(null, token);
                });
    }
}
