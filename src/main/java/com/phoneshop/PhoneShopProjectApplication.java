package com.phoneshop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "PhoneShop Project API",
                version = "1.0",
                description = "API with Spring Boot"
        )
)
public class PhoneShopProjectApplication {

    public static void main(String[] args) {SpringApplication.run(PhoneShopProjectApplication.class, args);}

}

