package com.mantenimiento.equipos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 2026 - Sistema de gestion mantenimiento de edificios de equipos")
                        .version("1.0")
                        .description("API para gestionar el mantenimiento de equipos en edificios"));
    }

}
