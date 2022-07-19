package com.uu.microservice.coreproxy.config;

import com.uu.microservice.core.config.Error;
import com.uu.microservice.core.exception.ResponseCodeException;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CustomControllerAdvice {

    @ExceptionHandler(ResponseCodeException.class)
    public ResponseEntity<Error> handleResponseCodeException(ResponseCodeException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getError());
    }

}
