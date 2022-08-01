package com.uu.userservice.service;

import com.uu.microservice.core.jwt.JwtConfig;
import com.uu.microservice.core.jwt.JwtTokenData;
import com.uu.microservice.core.utils.ReflectionUtils;
import com.uu.userservice.feignInteface.ProductInterface;
import com.uu.userservice.payload.request.LoginRequest;
import com.uu.userservice.payload.response.LoginResponse;
import com.uu.userservice.repository.UserRepository;
import com.uu.userservice.repository.model.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ProductInterface productInterface;

    public List<UserEntity> listAll() {
//        System.out.println(productInterface.getAll());
//        System.out.println(productInterface.testErrorSystem());
        return userRepository.findAll();
    }

    public LoginResponse login(LoginRequest loginRequest) {

        var userOp = userRepository.findByUsernameAndActiveIsTrue(loginRequest.getUsername().trim().toLowerCase());
        if (userOp.isPresent()) {
            // user exists and generate
            UserEntity user = userOp.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                JwtTokenData tokenData = ReflectionUtils.copyValue(user, JwtTokenData.class);
                String token = JwtConfig.generateToken(tokenData);
                return new LoginResponse(user).accessToken(token);
            }
        }
        return null;
    }
}
