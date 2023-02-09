package com.dwrik.gateway.config;

import com.dwrik.gateway.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.function.Predicate;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("booking-service", r -> r.path("/booking/**")
                        .filters(f -> f.filter(authFilter).stripPrefix(1))
                        .uri("lb://booking"))
                .route("checkin-service", r -> r.path("/checkin/**")
                        .filters(f -> f.filter(authFilter).stripPrefix(1))
                        .uri("lb://checkin"))
                .route("flight-service", r -> r.path("/flight/**")
                        .filters(f -> f.filter(authFilter).stripPrefix(1))
                        .uri("lb://flight"))
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(authFilter).stripPrefix(1))
                        .uri("lb://auth"))
                .build();
    }
}
