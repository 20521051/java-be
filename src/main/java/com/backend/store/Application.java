package com.backend.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@Configuration
@EnableMongoRepositories
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("\n[Swagger-ui]: http://localhost:8080/swagger-ui/index.html");
        System.out.println("[Swagger-api-docs]: http://localhost:8080/v3/api-docs");
        System.out.println("[MongoDB]: http://localhost:27017/Future");
        System.out.println("[Server]: http://localhost:8080/");
        System.out.println("[Server]: http://localhost:5500/api/v1");
    }
}
