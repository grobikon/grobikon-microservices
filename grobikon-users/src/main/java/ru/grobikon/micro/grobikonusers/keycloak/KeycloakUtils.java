package ru.grobikon.micro.grobikonusers.keycloak;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.grobikon.micro.grobikonusers.dto.UserDTO;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
public class KeycloakUtils {

    // настройки из файла properties
    @Value("${keycloak.auth-server-url}")
    private String serverURL;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String clientID;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    private static Keycloak keycloak; // сылка на единственный экземпляр объекта KC
    private static RealmResource realmResource; // доступ к API realm
    private static UsersResource usersResource;   // доступ к API для работы с пользователями

    // создание объекта KC
    public Keycloak getInstance() {
        if (keycloak == null) { // создаем объект только 1 раз

            keycloak = KeycloakBuilder.builder()
                    .realm(realm)
                    .serverUrl(serverURL)
                    .clientId(clientID)
                    .clientSecret(clientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .build();

            realmResource = keycloak.realm(realm);

            usersResource = realmResource.users();

        }
        return keycloak;
    }

    // создание пользователя для KC
    public Response createKeycloakUser(UserDTO user) {

        // данные пароля - специальный объект-контейнер CredentialRepresentation
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        // данные пользователя (можете задавать или убирать любые поля - зависит от требуемого функционала)
        // специальный объект-контейнер UserRepresentation
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

        // вызов KC (всю внутреннюю кухню за нас делает библиотека - формирует REST запросы, заполняет параметры и пр.)
        Response response = usersResource.create(kcUser);

        return response;

    }

    // данные о пароле
    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false); // не нужно будет менять пароль после первого входа
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
