package com.ApiGateWay.GateWay.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routers {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("Auth",r->r.path("/api/getSongsList")
                        .and().method("GET").and()
                        .uri("http://localhost:8081")
                )
                .route("Auth",r->r.path("/api/uploadSongs")
                        .and().method("POST")
                        .and().uri("http://localhost:8081")
                )
                .route("Auth",r->r.path("/api/downloadSong")
                        .and().method("GET")
                        .and().uri("http://localhost:8081")
                )
                .build();
    }
}
