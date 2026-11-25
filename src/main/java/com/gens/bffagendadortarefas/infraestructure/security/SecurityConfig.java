package com.gens.bffagendadortarefas.infraestructure.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = SecurityConfig.SECURITY_SCHEME,
        type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@OpenAPIDefinition( info = @Info(title = "Agendador de Tarefas API",
        version = "1.0", description = "API para cadastro e gerenciamento de tarefas"))
public class SecurityConfig {

    public static final String SECURITY_SCHEME = "bearerAuth";
}
