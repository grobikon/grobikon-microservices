package ru.grobikon.micro.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import ru.grobikon.common.grobikoncommonentity.entity.Activity;

@SpringBootApplication
@EnableEurekaClient
public class GrobikonTodoApplication {

    private Activity activity;

    public static void main(String[] args) {
        SpringApplication.run(GrobikonTodoApplication.class, args);
    }

}
