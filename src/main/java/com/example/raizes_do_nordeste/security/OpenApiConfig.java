package com.example.raizes_do_nordeste.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Define o cadeado de segurança para todas as rotas
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                // Configura como o botão "Authorize" vai funcionar (Esperando um JWT)
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                // Textos de apresentação da sua página
                .info(new Info()
                        .title("API - Raízes do Nordeste")
                        .description("Documentação interativa da API de Gestão de Franquias.")
                        .version("1.0.0"));
    }
}
