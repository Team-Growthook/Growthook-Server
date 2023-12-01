package com.example.growthookserver.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Grothook API 명세서",
                description = "Grothook API 명세서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/api/v1/**"};

        return GroupedOpenApi.builder()
                .group("Grothook API v1")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi healthCheckOpenApi(){
        String[] healthCheckPaths = {"/health"};

        return GroupedOpenApi.builder()
                .group("Health Check API")
                .pathsToMatch(healthCheckPaths)
                .build();
    }
}