package com.uu.microservice.core.config;

import com.uu.microservice.core.exception.ResponseCodeException;

public interface Constants {
    public static final ResponseCodeException invalidInput = new ResponseCodeException(Error.INVALID_INPUT);
    public static final String HEADER_AUTHOR = "AuthorizationInfo";
    public interface Endpoint {
        static final String PRODUCT_MICROSERVICE = "product-service";
    }
}
