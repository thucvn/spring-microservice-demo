package com.uu.mircroservice.core.config;

import com.uu.mircroservice.core.exception.ResponseCodeException;

public interface Constants {
    public static final ResponseCodeException invalidInput = new ResponseCodeException(Error.INVALID_INPUT);

    public interface Endpoint {
        static final String PRODUCT_MICROSERVICE = "product-service";
    }
}
