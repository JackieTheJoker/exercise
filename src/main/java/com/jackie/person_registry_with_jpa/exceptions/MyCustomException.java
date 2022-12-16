package com.jackie.person_registry_with_jpa.exceptions;

import org.springframework.http.HttpStatus;

public class MyCustomException extends RuntimeException {
    private int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    private String message;

    public MyCustomException() {}

    public MyCustomException(String message) {
        super(message);
    }

    public MyCustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}