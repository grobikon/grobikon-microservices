package ru.grobikon.common.grobikonutils.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.grobikon.common.grobikoncommonentity.entity.User;

@Component
public class UserWebClient {

    private static final String BASE_URL = "http://localhost:8765/grobikon-users/user";

    /**
     * Проверка - существует ли пользователь
     */
    public boolean userExists(Long userId) {

        try{
            var user = WebClient.create(BASE_URL)
                    .post()
                    .uri("/id")
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
}
