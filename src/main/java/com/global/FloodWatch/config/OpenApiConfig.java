package com.global.FloodWatch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FloodWatch API")
                        .version("v0.0.1")
                        .description("API para o sistema FloodWatch de prevenção e resposta a enchentes.")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://floodwatch/api/license"))
                );
    }
}
