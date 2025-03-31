package com.example.Project.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Vehicle Repair Hub API")
                        .version("1.0.0")
                        .description("A Vehicle Repair Hub, providing seamless vehicle repair service booking and management."));
    }
}
