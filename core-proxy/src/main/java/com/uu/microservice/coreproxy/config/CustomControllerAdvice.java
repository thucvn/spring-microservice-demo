package com.uu.microservice.coreproxy.config;

import com.uu.microservice.core.config.Error;
import com.uu.microservice.core.exception.ResponseCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
@Log4j2
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RequiredArgsConstructor
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {
    private final ErrorAttributes errorAttributes;

    @ExceptionHandler(ResponseCodeException.class)
    public ResponseEntity<Map<String, Object>> handleResponseCodeException(ResponseCodeException ex, WebRequest request) {
        Map<String, Object> body = errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));
        body.put("status", ex.getStatus().value());
        body.put("message", ex.getError().getMessage());
        body.put("code", ex.getError().name());
        body.remove("error");
        return ResponseEntity.status(ex.getStatus()).body(body);
    }
//    @ExceptionHandler(ResponseCodeException.class)
//    public ResponseEntity<Error> handler(ResponseCodeException ex) {
//        return ResponseEntity.status(ex.getStatus()).body(ex.getError());
//    }

}
