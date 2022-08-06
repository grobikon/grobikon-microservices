package ru.grobikon.micro.grobikontodo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.grobikon.common.grobikoncommonentity.entity.User;

@FeignClient(name = "grobikon-users")
public interface UserFeignClient {

    @PostMapping("/user/id")
    ResponseEntity<User> findUserById(@RequestBody Long id);
}
