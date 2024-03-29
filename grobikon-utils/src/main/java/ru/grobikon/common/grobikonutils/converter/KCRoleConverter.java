package ru.grobikon.common.grobikonutils.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// класс конвертер из данных JWT в роли spring security
public class KCRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        // получаем доступ к разделу JSON
        var realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        // если раздел JSON не будет найден - значит нет ролей
        if (realmAccess == null || realmAccess.isEmpty()) {
            return new ArrayList<>(); // пустая коллекция - нет ролей
        }

        // для того, чтобы spring контейнер понимал роли из jwt -
        // нужно их преобразовать в коллекцию GrantedAuthority
        // функц. код - для примера (кто уже возможно использовал его)
        // проходим по всем значениям из JSON
        // создаем объект SimpleGrantedAuthority - это дефолтная реализация GrantedAuthority
        // префикс ROLE обязатален
        Collection<GrantedAuthority> returnValue = ((List<String>) realmAccess.get("roles"))
                .stream().map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return returnValue;
    }
}
