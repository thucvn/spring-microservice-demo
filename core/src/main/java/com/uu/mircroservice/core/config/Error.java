package com.uu.mircroservice.core.config;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
public enum Error {

    INVALID_INPUT("The input data is not valid, please check again!"),
    TOKEN_EXPIRED("Token login is expired, please login again!"),
    TOKEN_NOT_VALID("Token is not valid, please try again!");
    private String message;
    Error(String message) {
        this.message = message;
    }


    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(json());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @JsonValue
    public Map json() {
        Map<String, String> val = new HashMap<>();
        val.put("code", this.name());
        val.put("message", this.getMessage());
        return val;
    }
}
