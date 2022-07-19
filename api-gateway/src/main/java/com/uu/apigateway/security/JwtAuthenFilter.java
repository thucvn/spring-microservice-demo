package com.uu.apigateway.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uu.microservice.core.config.Constants;
import com.uu.microservice.core.config.Error;
import com.uu.microservice.core.exception.ResponseCodeException;
import com.uu.microservice.core.jwt.JwtConfig;
import com.uu.microservice.core.jwt.JwtTokenData;
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
            builder.headers(httpHeaders -> {
                httpHeaders.remove(Constants.HEADER_AUTHOR);
                try {
                    httpHeaders.set(Constants.HEADER_AUTHOR, data.json());
                } catch (JsonProcessingException e) {
                    throw new ResponseCodeException(Error.TOKEN_NOT_VALID);
                }
            });
        }

        return chain.filter(exchange.mutate().request(builder.build()).build());
    }
}
