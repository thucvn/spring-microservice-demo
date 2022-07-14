package com.uu.mircroservice.core.jwt;

import lombok.Data;

@Data
public class JwtTokenData {
    private Integer id, shopId;
    private String username, role;

}
