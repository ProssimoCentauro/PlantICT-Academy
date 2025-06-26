package com.academy.ggTournaments.exception;

import org.springframework.http.HttpStatus;

public class GGException extends RuntimeException {
    private final HttpStatus status;

    public GGException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
