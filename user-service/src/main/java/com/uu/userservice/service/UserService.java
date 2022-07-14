package com.uu.userservice.service;

import com.uu.userservice.payload.request.LoginRequest;
import com.uu.userservice.payload.response.LoginResponse;
import com.uu.userservice.repository.UserRepository;
import com.uu.userservice.repository.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var userOp = userRepository.findByUsernameAndActiveIsTrue(loginRequest.getUsername().trim().toLowerCase());
        if (userOp.isPresent()) {
            // user exists and generate
            UserEntity user = userOp.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = "123";
                return new LoginResponse(user).accessToken(token);
            }
        }
        return null;
    }
}
