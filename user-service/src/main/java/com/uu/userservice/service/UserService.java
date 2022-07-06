package com.uu.userservice.service;

import com.uu.userservice.repository.UserRepository;
import com.uu.userservice.repository.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }
}
