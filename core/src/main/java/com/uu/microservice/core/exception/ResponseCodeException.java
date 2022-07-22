package com.uu.microservice.core.exception;

import com.uu.microservice.core.config.Error;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ResponseCodeException extends RuntimeException {
    Error error;
    HttpStatus status = HttpStatus.BAD_REQUEST;

    public ResponseCodeException(HttpStatus status, Error error) {
        this(error);
        this.status = status;
    }

    public ResponseCodeException(int status, Error error) {
        this(error);
        this.status = HttpStatus.valueOf(status);
    }

    public ResponseCodeException(Error error) {
        super();
        this.error = error;
    }
}
