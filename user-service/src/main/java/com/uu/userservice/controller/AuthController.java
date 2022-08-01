package com.uu.userservice.controller;

import com.uu.microservice.core.config.Error;
import com.uu.microservice.core.exception.ResponseCodeException;
import com.uu.userservice.payload.request.LoginRequest;
import com.uu.userservice.payload.response.LoginResponse;
import com.uu.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        var re = userService.login(loginRequest);
        if (re == null) throw new ResponseCodeException(Error.WRONG_CREDENTIAL);
        return re;
    }
}
