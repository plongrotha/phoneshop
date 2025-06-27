package com.phoneshop.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "Phone Shop API", version = "1.0", description = "Phone Management"),
        servers = @Server(url = "http://localhost:8080"))
@Configuration
public class SwaggerConfig {
}
