package com.example.growthookserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GrowthookServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowthookServerApplication.class, args);
    }

}
