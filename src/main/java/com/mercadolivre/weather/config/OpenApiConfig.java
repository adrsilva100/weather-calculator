package com.mercadolivre.weather.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .path("/wheater/v1", new PathItem())
                .info(new Info().title("API wheater calculator").description("API that calculate wheater that some planets").version("openapi: 3.0.0"));
    }
}
