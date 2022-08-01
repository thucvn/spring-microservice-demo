package com.uu.apigateway.config;

import com.uu.microservice.core.exception.ResponseCodeException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request,
                                                  ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);
        Throwable throwable = getError(request);
        if (throwable instanceof ResponseCodeException) {
            map.put("status", ((ResponseCodeException) throwable).getStatus().value());
            map.put("code", ((ResponseCodeException) throwable).getError().name());
            map.put("message", ((ResponseCodeException) throwable).getError().getMessage());
        }
        return map;
    }

}
