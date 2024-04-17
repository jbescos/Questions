package com.github.jbescos.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.jbescos.springboot")
public class SpringBootApp extends SpringBootServletInitializer {

    // In case it is packaged as a jar
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }
}
