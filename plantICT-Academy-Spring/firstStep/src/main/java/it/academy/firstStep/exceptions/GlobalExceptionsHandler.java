package it.academy.firstStep.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionsHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionsHandler.class);

    private ResponseEntity<Object> buildResponse(HttpStatus status, Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, status);
    }

    // --- NOT FOUND ---
    @ExceptionHandler({RoleNotFoundException.class, UserNotFoundException.class, UserRoleNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException ex, WebRequest request) {
        logger.error("NOT FOUND: {}", ex.getMessage());
        return buildResponse(HttpStatus.NOT_FOUND, ex, request);
    }

    // --- CONFLICT ---
    @ExceptionHandler({DuplicatedRoleNameException.class, DuplicatedUsernameException.class, DuplicatedUserRoleException.class})
    public ResponseEntity<Object> handleConflictExceptions(RuntimeException ex, WebRequest request) {
        logger.error("CONFLICT: {}", ex.getMessage());
        return buildResponse(HttpStatus.CONFLICT, ex, request);
    }

    // --- GENERIC ---
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        logger.error("UNEXPECTED ERROR: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }
}
