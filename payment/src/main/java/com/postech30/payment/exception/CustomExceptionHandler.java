package com.postech30.payment.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
    public class CustomExceptionHandler {
    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<Object> handleCadastroClienteException(CardNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", e.getMessage());
        body.put("path", request.getRequestURI());

        return new ResponseEntity<>(body, status);
    }



}
