package ru.grobikon.common.grobikonutils.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.grobikon.common.grobikoncommonentity.entity.User;

@Component
public class UserWebClient {

    private static final String BASE_URL = "http://localhost:8765/grobikon-users/user/";
    private static final String BASE_URL_DATA = "http://localhost:8765/grobikon-todo/data/";

    /**
     * Проверка - существует ли пользователь
     */
    public boolean userExists(Long userId) {

        try{
            var user = WebClient.create(BASE_URL)
                    .post()
                    .uri("id")
                    .bodyValue(userId)  //тело запроса
                    .retrieve() //вызывает сам микросервис
                    .bodyToFlux(User.class)//полученный объект будет упакован в объект Flux(для асинхронного кода чтобы можно было подписываться на изменения и т.д.)
                    .blockFirst(); //Блокируем поток до получения 1й записи

            //вызов сервиса
            return user != null;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false; // если статус не был 200
    }


    /**
     * Проверка - существует ли пользователь
     */
    public Flux<User> userExistsAsync(Long userId) {
        return WebClient.create(BASE_URL)
                .post()
                .uri("/id")
                .bodyValue(userId)  //тело запроса
                .retrieve() //вызывает сам микросервис
                .bodyToFlux(User.class);//полученный объект будет упакован в объект Flux(для асинхронного кода чтобы можно было подписываться на изменения и т.д.)
    }

    // иниц. начальных данных
    public Flux<Boolean> initUserDataAsync(Long userId) {
        return WebClient.create(BASE_URL_DATA)
                .post()
                .uri("init")
                .bodyValue(userId)  //тело запроса
                .retrieve() //вызывает сам микросервис
                .bodyToFlux(Boolean.class);//полученный объект будет упакован в объект Flux(для асинхронного кода чтобы можно было подписываться на изменения и т.д.)
    }
}
