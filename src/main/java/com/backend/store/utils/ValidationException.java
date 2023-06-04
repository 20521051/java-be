package com.backend.store.utils;

public class ValidationException extends RuntimeException {
    private int statusCode;
    private String error;
    private Object fields;

    public ValidationException(int statusCode, String error, Object fields) {
        this.statusCode = statusCode;
        this.error = error;
        this.fields = fields;
    }

    // Getters
    public int getStatusCode() {
        return this.statusCode;
    }

    public String getError() {
        return this.error;
    }

    public Object getFields() {
        return this.fields;
    }
}
