package com.cuckoofi.gatewayservice.config;


import com.cuckoofi.gatewayservice.filter.AuthenticationPrefilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, AuthenticationPrefilter authFilter) {
        return builder.routes()
                .route("auth-service-route", r -> r
                        .path("/auth/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationPrefilter.Config())))
                        .uri("lb://auth-service/"))
                .route("priceline-service-route", r -> r
                        .path("/priceline/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationPrefilter.Config())))
                        .uri("lb://priceline-service/"))
                .build();
    }
}
