package com.uu.userservice.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username, password;
}
