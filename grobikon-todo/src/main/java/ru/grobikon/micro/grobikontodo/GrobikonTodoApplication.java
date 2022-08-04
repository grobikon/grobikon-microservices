package ru.grobikon.micro.grobikontodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"ru.grobikon.common"})
@EnableJpaRepositories(basePackages = {"ru.grobikon.common.grobikoncommonentity"})
public class GrobikonTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrobikonTodoApplication.class, args);
    }

}
