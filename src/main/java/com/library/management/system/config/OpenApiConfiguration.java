package com.library.management.system.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfiguration {
    @Bean
    public Info apiInfo() {
        return new Info()
                .title("Library Management System API")
                .description("Library Management System API")
                .version("1.0");
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

}