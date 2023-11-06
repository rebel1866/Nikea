package com.ikea.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("productId", r->r.path("/product/**").uri("lb://PRODUCT-SERVICE"))
                .route("orderId", r->r.path("/order/**").uri("lb://ORDER-SERVICE"))
                .build();
    }
}
