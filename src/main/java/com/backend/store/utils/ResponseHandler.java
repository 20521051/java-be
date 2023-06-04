package com.backend.store.utils;

import org.springframework.http.HttpStatus;

import com.backend.store.utils.HttpException;

public class ResponseHandler {

    public static <T> Response<T> handlerResSuccess(String message, T data) {
        return new Response<>(message, data);
    }

    public static void handleResFailure(String error, HttpStatus statusCode) {
        throw new HttpException(statusCode, error);
    }

    public static class Response<T> {
        private final String message;
        private final T data;

        public Response(String message, T data) {
            this.message = message;
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public T getData() {
            return data;
        }
    }
}
