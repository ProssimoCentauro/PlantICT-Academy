package it.academy.firstStep.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedUsernameException extends RuntimeException {
    public DuplicatedUsernameException(String message) {
        super(message);
    }
}
