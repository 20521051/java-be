package com.backend.store.utils;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String error;

    public HttpException(HttpStatus statusCode, String error) {
        super(error);
        this.statusCode = statusCode;
        this.error = error;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }
}
