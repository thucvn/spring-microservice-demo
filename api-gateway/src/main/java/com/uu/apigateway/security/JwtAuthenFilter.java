package com.uu.apigateway.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uu.mircroservice.core.config.Error;
import com.uu.mircroservice.core.exception.ResponseCodeException;
import com.uu.mircroservice.core.jwt.JwtConfig;
import com.uu.mircroservice.core.jwt.JwtTokenData;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenFilter implements GlobalFilter {

    private String getJwtFromRequest(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String jwt = getJwtFromRequest(exchange.getRequest());
        ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
        if (StringUtils.hasText(jwt) && JwtConfig.validateToken(jwt)) {
            JwtTokenData data = JwtConfig.getTokenData(jwt);
            ObjectMapper objectMapper = new ObjectMapper();
            builder.headers(httpHeaders -> {
                httpHeaders.remove("AuthorInfo");
                try {
                    httpHeaders.set("AuthorInfo", objectMapper.writeValueAsString(data));
                } catch (JsonProcessingException e) {
                    throw new ResponseCodeException(Error.TOKEN_NOT_VALID);
                }
            });
        }

        return chain.filter(exchange.mutate().request(builder.build()).build());
    }
}
