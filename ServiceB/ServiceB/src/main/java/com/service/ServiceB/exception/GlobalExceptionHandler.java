package com.service.ServiceB.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("errors", errors);
        return ResponseEntity.badRequest().body(response);
    }

/*    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate() {
        Map<String, String> errors = new HashMap<>();
        errors.put("email", "Email already exists");
        errors.put("phone_number", "Phone already exists");
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("errors", errors);
        return ResponseEntity.badRequest().body(response);
    } */
}