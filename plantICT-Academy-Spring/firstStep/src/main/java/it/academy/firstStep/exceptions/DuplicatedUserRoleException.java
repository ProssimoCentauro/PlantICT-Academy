package it.academy.firstStep.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatedUserRoleException extends RuntimeException {
    public DuplicatedUserRoleException(String message) {
        super(message);
    }
}
