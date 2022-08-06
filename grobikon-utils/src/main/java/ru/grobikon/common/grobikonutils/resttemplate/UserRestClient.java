package ru.grobikon.common.grobikonutils.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.grobikon.common.grobikoncommonentity.entity.User;

//Старый способ не рекомендуется к использованию
@Component
public class UserRestClient {
    private static final String BASE_URL = "http://localhost:8765/grobikon-users/user";

    /**
     * Проверка - существует ли пользователь
     */
    public boolean userExists(Long userId) {
        //RestTemplate = deprecated
        var restTemplate = new RestTemplate();
        var request = new HttpEntity(userId);

        ResponseEntity<User> response = null;
        // если нужно получить объект - просто вызываете response.getBody() и произойдет автоматическая конвертация из JSON в POJO
        // в текущем вызове нам не нужен объект, т.к. мы просто проверяем, есть ли такой пользователь

        try{
            //вызов сервиса
            response = restTemplate.exchange(BASE_URL+"/id", HttpMethod.POST, request, User.class);
            return response.getStatusCode() == HttpStatus.OK;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false; // если статус не был 200
    }
}
