package com.leozeballos.apirestspringboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiRestSpringbootApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @GetMapping("/")
    public String index() {
        return "<html>" +
                "<head>" +
                    "<title>Welcome to the Blog API</title>" +
                "</head>" +
                "<body>" +
                    "<h1>Welcome to the Blog API.</h1>" +
                    "<p>This application, developed by Leonel Zeballos, provides a simple and efficient API REST service using the Spring Boot framework.</p>" +
                    "<p>To access the API REST Swagger UI and explore its endpoints, please click <a href='/swagger-ui.html'>here</a>.</p>" +
                "</body>" +
            "</html>";
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiRestSpringbootApplication.class, args);
    }

}
