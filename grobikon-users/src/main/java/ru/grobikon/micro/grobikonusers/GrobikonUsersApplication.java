package ru.grobikon.micro.grobikonusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"ru.grobikon.common", "ru.grobikon.micro.grobikonusers"})
@EnableJpaRepositories(basePackages = {"ru.grobikon.micro.grobikonusers"})
@RefreshScope   //динамически читает значения из файла конфигурации
public class GrobikonUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrobikonUsersApplication.class, args);
    }

}
