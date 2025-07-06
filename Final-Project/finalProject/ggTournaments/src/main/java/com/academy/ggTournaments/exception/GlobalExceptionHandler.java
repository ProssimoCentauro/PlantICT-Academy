package com.academy.ggTournaments.exception;

import com.academy.ggTournaments.exception.GGException;
import com.academy.ggTournaments.exception.InvalidMatchException;
import com.academy.ggTournaments.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ResponseEntity<Object> buildResponse(HttpStatus status, Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, status);
    }

    // --- GG EXCEPTION ---
    @ExceptionHandler(GGException.class)
    public ResponseEntity<Object> handleGGException(GGException ex, WebRequest request) {
        logger.error("GG EXCEPTION: {}", ex.getMessage(), ex);
        return buildResponse(ex.getStatus(), ex, request);
    }

    // --- NOT FOUND ---
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException ex, WebRequest request) {
        logger.error("NOT FOUND: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.NOT_FOUND, ex, request);
    }

    // --- CONFLICT ---
    @ExceptionHandler({InvalidMatchException.class})
    public ResponseEntity<Object> handleConflictExceptions(RuntimeException ex, WebRequest request) {
        logger.error("CONFLICT: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.CONFLICT, ex, request);
    }

    // --- GENERIC ---
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        logger.error("UNEXPECTED ERROR: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }
}
