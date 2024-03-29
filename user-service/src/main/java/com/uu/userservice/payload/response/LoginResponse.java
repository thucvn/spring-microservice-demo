package com.uu.userservice.payload.response;

import com.uu.userservice.repository.model.UserEntity;
import lombok.Data;

@Data
public class LoginResponse {
    private int id;
    private String name;

    private String username;

    private String type;

    private Integer shopId;
    private String token;

    public LoginResponse(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.username = entity.getUsername();
        this.type = entity.getType().name();
        this.shopId = entity.getShopId();
    }

    public LoginResponse accessToken(String token) {
        this.token = token;
        return this;
    }
}
