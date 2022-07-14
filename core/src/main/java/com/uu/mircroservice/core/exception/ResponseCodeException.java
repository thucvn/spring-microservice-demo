package com.uu.mircroservice.core.exception;

import org.springframework.http.HttpStatus;
import com.uu.mircroservice.core.config.Error;
public class ResponseCodeException extends RuntimeException {
    Error error;
    HttpStatus status = HttpStatus.BAD_REQUEST;

    public ResponseCodeException(HttpStatus status, Error error) {
        this(error);
        this.status = status;
    }

    public ResponseCodeException(Error error) {
        super();
        this.error = error;
    }
}
