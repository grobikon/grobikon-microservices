package ru.grobikon.grobikonconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class GrobikonConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrobikonConfigApplication.class, args);
    }

}
