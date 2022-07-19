package com.uu.mircroservice.core.jwt;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JwtTokenData {
    private Integer id, shopId;
    private String username, role;

}
