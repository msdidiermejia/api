package com.bun.register.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SrvAPIRegister")
                        .description("Construccion de servicio para registro y consultas de clientes bu.")
                        .version("2.0.0"));
    }
}