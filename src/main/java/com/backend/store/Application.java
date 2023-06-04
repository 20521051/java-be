package com.backend.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Swagger-ui: http://localhost:8080/swagger-ui/index.html");
        System.out.println("Swagger-api-docs: http://localhost:8080/v3/api-docs");
        System.out.println("MongoDB: http://localhost:27017/mydatabase");
        System.out.println("Backend: http://localhost:8080/");
    }
}
