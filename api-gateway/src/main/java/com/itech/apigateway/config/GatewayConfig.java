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

                .build();
    }

    @Bean
    public RouteLocator wordsRouteLocator(RouteLocatorBuilder builder)
    {
        String loadBalancerUri = "lb://sensitive-word-service";
        return builder.routes()
                .route("add", r -> r
                        .method("POST")
                        .and()
                        .path("/api/v2/sensitiveWords/word/**")
                        .uri(loadBalancerUri))

                .route("retrieve-all", r -> r
                        .method("GET")
                        .and()
                        .path("/api/v2/sensitiveWords/words/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri(loadBalancerUri))

                .route("retrieve-one", r -> r
                        .method("GET")
                        .and()
                        .path("/api/v2/sensitiveWords/word/search/**")
                        .filters(f -> f.addRequestParameter("word", "{word}"))
                        .uri(loadBalancerUri))

                .route("update", r -> r
                        .method("PUT")
                        .and()
                        .path("/api/v2/sensitiveWords/word/{id}/**")
                        .filters(f -> f.rewritePath("/api/v2/sensitiveWords/word/(?<id>.*)", " /${id}"))
                        .uri(loadBalancerUri))

                .route("delete", r -> r
                        .method("DELETE")
                        .and()
                        .path("/api/v2/sensitiveWords/word/{id}/**")
                        .filters(f -> f.rewritePath("/api/v2/sensitiveWords/word/(?<id>.*)", " /${id}"))
                        .uri(loadBalancerUri))

                .build();
    }
}