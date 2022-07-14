package com.uu.mircroservice.core.config;

public enum Error {

    INVALID_INPUT("The input data is not valid, please check again!"),
    TOKEN_EXPIRED("Token login is expired, please login again!"),
    TOKEN_NOT_VALID("Token is not valid, please try again!");
    private String message;
    Error(String message) {
        this.message = message;
    }

}
