package com.itech.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route("user-service", r -> r
                        .path("/api/v2/blooped/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://user-service"))

                .route("sensitive-word-service", r -> r
                        .path("/api/words/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://sensitive-word-service"))

                .route("service1", r -> r
                        .path("/sayHello/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("service1://sayHello/"))
                
                .build();
    }

}
