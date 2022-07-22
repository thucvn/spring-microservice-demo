package com.uu.userservice.controller;

import com.uu.microservice.core.config.Constants;
import com.uu.microservice.core.config.Error;
import com.uu.microservice.core.exception.ResponseCodeException;
import com.uu.userservice.repository.model.UserEntity;
import com.uu.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public List<UserEntity> list(@RequestHeader(Constants.HEADER_AUTHOR) String header) {
        System.out.println(header);
        return userService.listAll();
    }

    @GetMapping("error")
    public void test() {
        throw new ResponseCodeException(Error.TOKEN_EXPIRED);
    }
}
