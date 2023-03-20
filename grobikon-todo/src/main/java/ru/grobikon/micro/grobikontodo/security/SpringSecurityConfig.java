package ru.grobikon.micro.grobikontodo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import ru.grobikon.common.grobikonutils.converter.KCRoleConverter;

/**
 * в старых версиях spring security нужно было наследовать от спец. класса WebSecurityConfigurerAdapter
 * Подробнее https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 *
 * @Configuration данный класс будет считан как конфиг spring контейнера
 * @EnableWebSecurity Включает механизм защиты адресов, которые настраиваются в SecurityFilter
 * @EnableGlobalMethodSecurity включение механизма для защиты методов по ролям
 */
@Configuration // данный класс будет считан как конфиг для spring контейнера
@EnableWebSecurity // включает механизм защиты адресов, которые настраиваются в SecurityFilterChain
@EnableGlobalMethodSecurity(prePostEnabled = true) // включение механизма для защиты методов по ролям
public class SpringSecurityConfig {

    // создается спец. бин, который отвечает за настройки запросов по http (метод вызывается автоматически) Spring контейнером
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // конвертер для настройки spring security
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        // подключаем конвертер ролей
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KCRoleConverter());


        // все сетевые настройки
        http.authorizeRequests()
                .antMatchers("/category/*", "/priority/*", "/task/*").hasRole("user")
                .anyRequest().authenticated() // остальной API будет доступен только аутентифицированным пользователям

                .and() // добавляем новые настройки, не связанные с предыдущими

                .oauth2ResourceServer()// добавляем конвертер ролей из JWT в Authority (Role)
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);

        return http.build();
    }

}
