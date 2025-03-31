package com.murallagraph.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.murallagraph.repository")
@ComponentScan(basePackages = {"com.murallagraph.controller", "com.murallagraph.service", "com.murallagraph.backend", "com.murallagraph.repository"})
public class MurallaGraphApplication {
    public static void main(String[] args) {
        SpringApplication.run(MurallaGraphApplication.class, args);
    }
}
