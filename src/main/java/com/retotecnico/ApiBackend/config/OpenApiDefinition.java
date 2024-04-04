package com.retotecnico.ApiBackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(name = "Jhonatan Bermudez"),
        description = "API encargada de la lógica de negocio para el registro de personas",
        title = "ApiBackend",
        version = "1.0"),
        servers = @Server(description = "Local", url = "http://localhost:8080")
)
public class OpenApiDefinition {
}