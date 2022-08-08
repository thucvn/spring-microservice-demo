package com.uu.microservice.core.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JwtTokenData {
    private Integer id, shopId;
    private String username, type;

    public String json() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public String jsonRfc6570() throws JsonProcessingException {
        String expanded = json();
        expanded = expanded.replaceAll( "\\{", "%7B");
        expanded = expanded.replaceAll("}", "%7D");
        return expanded;
    }

    public static JwtTokenData fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (json.startsWith("%7B") && json.endsWith("%7D")) {
            json = json.replaceAll("%7B", "{");
            json = json.replaceAll("%7D", "}");
        }
        return objectMapper.readValue(json, JwtTokenData.class);
    }

}
