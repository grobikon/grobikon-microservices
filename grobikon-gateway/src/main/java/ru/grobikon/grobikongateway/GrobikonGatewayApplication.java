package ru.grobikon.grobikongateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GrobikonGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrobikonGatewayApplication.class, args);
    }

}
