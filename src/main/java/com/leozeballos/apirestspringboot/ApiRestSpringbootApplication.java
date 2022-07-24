package com.leozeballos.apirestspringboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiRestSpringbootApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @RequestMapping("/")
    public String index() {
        return "<h1>Welcome to API REST Spring Boot</h1>" +
                "<p>This is a simple API REST Spring Boot application.</p>" +
                "<p>This API REST Spring Boot application is developed by Leo Zeballos.</p>" +
                "<p>To see the API REST Swagger UI, please click <a href='/swagger-ui.html'>here</a>.</p>";
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiRestSpringbootApplication.class, args);
    }

}
