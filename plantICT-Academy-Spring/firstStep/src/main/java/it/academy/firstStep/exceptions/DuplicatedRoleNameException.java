package it.academy.firstStep.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatedRoleNameException extends RuntimeException {
    public DuplicatedRoleNameException(String message) {
        super(message);
    }
}
