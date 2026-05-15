package com.example.raizes_do_nordeste.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<Map<String, Object>> handleEstoqueInsuficiente(EstoqueInsuficienteException ex, HttpServletRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "ESTOQUE_INSUFICIENTE");
        body.put("message", ex.getMessage());
        body.put("details", new ArrayList<>());
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("path", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
    
}
