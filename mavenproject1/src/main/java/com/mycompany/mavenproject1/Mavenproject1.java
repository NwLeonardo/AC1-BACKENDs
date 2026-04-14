package com.mycompany.mavenproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mycompany.mavenproject1", "com.aula.chamados"})
public class Mavenproject1 {
    public static void main(String[] args) {
        System.setProperty("spring.classformat.ignore", "true");
        
        SpringApplication app = new SpringApplication(Mavenproject1.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run(args);
    }
}