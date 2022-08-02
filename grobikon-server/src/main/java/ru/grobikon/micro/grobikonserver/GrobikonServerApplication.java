package ru.grobikon.micro.grobikonserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GrobikonServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrobikonServerApplication.class, args);
    }

}
